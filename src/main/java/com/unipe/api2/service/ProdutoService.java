package com.unipe.api2.service;

import com.unipe.api2.dto.form.ProdutoForm;
import com.unipe.api2.model.Produto;
import com.unipe.api2.repository.ProdutoRepository;
import com.unipe.api2.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;


    public List<Produto> listar(){
        return produtoRepository.findAll();
    }

    public RequestResposta buscarPorId(int id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        return produtoOptional.isEmpty()
                ? trataProdutoInexistente()
                : new RequestResposta(produtoOptional.get(), HttpStatus.OK);    }

    public RequestResposta salvar(ProdutoForm produtoForm){
        Optional<Produto> produtoOptional = produtoRepository.findProdutoByNome(produtoForm.getNome());
        return produtoOptional.isEmpty()
                ? new RequestResposta(produtoRepository.save(new Produto(produtoForm)), HttpStatus.CREATED)
                : new RequestResposta("Produto já cadastrado", HttpStatus.CONFLICT);
    }

    public RequestResposta atualizaDados(int id, ProdutoForm dadosAtualizados) {

        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            return trataProdutoInexistente();
        }
        Produto produto = produtoOptional.get();
        if (dadosAtualizados.getNome() != null)
            produto.setNome(dadosAtualizados.getNome());
        if (dadosAtualizados.getValor() != 0.0)
            produto.setValor(dadosAtualizados.getValor());

        return new RequestResposta(produtoRepository.save(produto), HttpStatus.CREATED);
    }

    public RequestResposta deletar(int id){
        Optional<Produto> produtoOptional = produtoRepository.findById(id);
        if (produtoOptional.isEmpty()) {
            return trataProdutoInexistente();
        }
        produtoRepository.deleteById(id);
        return new RequestResposta("Deletado com sucesso", HttpStatus.OK);

    }

    private RequestResposta trataProdutoInexistente() {
        return new RequestResposta("Produto não encontrado", HttpStatus.NOT_FOUND);
    }
}
