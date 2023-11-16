package com.unipe.api2.controller;

import com.unipe.api2.dto.form.ProdutoForm;
import com.unipe.api2.model.Produto;
import com.unipe.api2.service.ProdutoService;
import com.unipe.api2.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Object> buscarPorId(@PathVariable int id){
        return RequestResposta.retornar(produtoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody ProdutoForm produtoForm){
        return RequestResposta.retornar(produtoService.salvar(produtoForm));}

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @RequestBody ProdutoForm dadosAtualizados){
        return RequestResposta.retornar(produtoService.atualizaDados(id, dadosAtualizados));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable int id){
        return RequestResposta.retornar(produtoService.deletar(id));
    }


}
