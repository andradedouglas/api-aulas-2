package com.unipe.api2.service;

import com.unipe.api2.model.Carrinho;
import com.unipe.api2.model.Produto;
import com.unipe.api2.model.Usuario;
import com.unipe.api2.repository.CarrinhoRepository;
import com.unipe.api2.repository.ProdutoRepository;
import com.unipe.api2.repository.UsuarioRepository;
import com.unipe.api2.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CarrinhoService {
    @Autowired
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public RequestResposta adicionarProduto(int idProduto, int idUsuario) {
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if (produtoOptional.isEmpty())
            return new RequestResposta("Produto não encontrado", HttpStatus.NOT_FOUND);
        if (usuarioOptional.isEmpty())
            return new RequestResposta("Usuário não encontrado", HttpStatus.NOT_FOUND);
        Produto produto = produtoOptional.get();
        Usuario usuario = usuarioOptional.get();
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findCarrinhoByDono(usuario.getId());
        if (carrinhoOptional.isEmpty()) { //primeiro produto do carrinho
            Carrinho carrinho = new Carrinho(usuario, produto);
            return new RequestResposta(carrinhoRepository.save(carrinho), HttpStatus.CREATED);
        }
        //o carrinho já possui ao menos 1 produto associado
        Carrinho carrinhoEncontrado = carrinhoOptional.get();
        carrinhoEncontrado.addProduto(produto);
        carrinhoRepository.save(carrinhoEncontrado);

        String mensagem = "Adicionado o produto " + produto.getNome() + " ao carrinho de " + usuario.getNomeCompleto() + "\n" + carrinhoEncontrado;

        return new RequestResposta(mensagem, HttpStatus.OK);
    }
    public RequestResposta verCarrinho(int idCarrinho) {
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(idCarrinho);
        return carrinhoOptional.isEmpty()
                ? new RequestResposta("Carrinho não encontrado", HttpStatus.NOT_FOUND)
                : new RequestResposta(carrinhoOptional.get(), HttpStatus.OK);
    }

    public RequestResposta deletarCarrinho(int idCarrinho) {
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(idCarrinho);
        if (carrinhoOptional.isEmpty())
            return new RequestResposta("Carrinho não encontrado", HttpStatus.NOT_FOUND);
        carrinhoRepository.delete(carrinhoOptional.get());
        return new RequestResposta("Carrinho deletado com sucesso", HttpStatus.OK);
    }

    public RequestResposta deletarProduto(int idCarrinho, int idProduto) {
        Optional<Carrinho> carrinhoOptional = carrinhoRepository.findById(idCarrinho);
        if (carrinhoOptional.isEmpty())
            return new RequestResposta("Carrinho não encontrado", HttpStatus.NOT_FOUND);
        Carrinho carrinho = carrinhoOptional.get();
        Optional<Produto> produtoOptional = produtoRepository.findById(idProduto);
        if (produtoOptional.isEmpty())
            return new RequestResposta("Produto não encontrado", HttpStatus.NOT_FOUND);
        Produto produto = produtoOptional.get();
        if (carrinho.removeProduto(produto)) {
            carrinhoRepository.save(carrinho);
            return new RequestResposta("Produto removido com sucesso", HttpStatus.OK);
        } else return new RequestResposta("Produto não encontrado no carrinho", HttpStatus.NOT_FOUND);
    }

}
