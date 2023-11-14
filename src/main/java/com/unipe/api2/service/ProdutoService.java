package com.unipe.api2.service;

import com.unipe.api2.dto.form.ProdutoForm;
import com.unipe.api2.model.Produto;
import com.unipe.api2.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    public Produto buscarPorId(int id){
        return produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Produto salvar(ProdutoForm produtoForm){
        Produto produto = new Produto(produtoForm);
        return produtoRepository.save(produto);
    }

    public Produto atualizaDados(int id, Map<String, Object> dadosAtualizados) {

        Produto produtoAtual = produtoRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        //
        return produtoRepository.save(produtoAtual);
    }

    public void deletar(int id){
        produtoRepository.deleteById(id);
    }
}
