package com.application.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.application.model.Produto;
import com.application.model.repository.ProdutoRepository;

@Controller
@RequestMapping("produto")
public class ProdutoController {

    @Autowired
    private ProdutoRepository repProduto;
    
    //#####################
    //CRUD - READ
    //#####################

    @GetMapping("/listar")
    public String listar(Model model){
        List<Produto> produtos = repProduto.findAll();
        model.addAttribute("produtos", produtos);
        return "modulos/produto/listar";
    }

    //#####################
    //CRUD - CREATE
    //#####################
    

    @GetMapping("/adicionar")
    public String adicionar(Produto produto){
        return "modulos/produto/adicionar";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Produto produto, BindingResult result,  Model model) {        

        if (result.hasErrors()) {
            return "modulos/produto/adicionar";        
        }
        repProduto.save(produto);
        return "redirect:/produto/listar";
    }

    //#####################
    //CRUD - UPDATE
    //#####################

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") int id, Model model){

        Produto produto = repProduto.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Id Não Encontrado:" + id));    
           
        model.addAttribute("produto", produto);
        return "modulos/produto/editar";
    }

    @PostMapping("/atualizar/{id}")
    public String atualizar(@Valid Produto produto, BindingResult result,  Model model , 
                            @PathVariable("id") int id){

        if (result.hasErrors()) {
            produto.setId(id);
            return "modulos/produto/editar";
        }

        repProduto.save(produto);
        return "redirect:/produto/listar";
    }

    //#####################
    //CRUD - DELETE
    //#####################

    @GetMapping("/excluir/{id}")
    public String excluir(Model model, @PathVariable("id") int id){
        Produto produto = repProduto.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Id Não Encontrado:" + id));    
           
        model.addAttribute("produto", produto);

        return "modulos/produto/excluir";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") int id, Produto produto, BindingResult result, Model model){

        if (result.hasFieldErrors()) {
            return "redirect:/produto/excluir/"+id;        
        }

        repProduto.delete(produto);
        return "redirect:/produto/listar";
    }

    
}
