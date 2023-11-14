package com.unipe.api2.controller;

import com.unipe.api2.dto.form.ProdutoForm;
import com.unipe.api2.model.Produto;
import com.unipe.api2.model.Usuario;
import com.unipe.api2.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping("/listar")
    public ResponseEntity<List<Produto>> listar(){
        return new ResponseEntity<>(produtoService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> buscarPorId(@PathVariable int id){
        return new ResponseEntity<>(produtoService.buscarPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody ProdutoForm produtoForm){
        produtoService.salvar(produtoForm);
        return new ResponseEntity<>(("Produto criado com sucesso"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Produto> atualizar(@PathVariable int id, @RequestBody Map<String,Object> dadosAtualizados){
        return new ResponseEntity<>(produtoService.atualizaDados(id, dadosAtualizados), HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id){
        produtoService.deletar(id);
        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.NO_CONTENT);
    }


}
