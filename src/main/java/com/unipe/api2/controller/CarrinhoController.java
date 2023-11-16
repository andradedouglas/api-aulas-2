package com.unipe.api2.controller;

import com.unipe.api2.service.CarrinhoService;
import com.unipe.api2.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/carrinho")
public class CarrinhoController {
    @Autowired
    private CarrinhoService carrinhoService;

    @PostMapping("/adicionar-produto/{idProduto}")
    public ResponseEntity<Object> adicionarProduto(@PathVariable int idProduto, @RequestParam int idUsuario) {
        return RequestResposta.retornar(carrinhoService.adicionarProduto(idProduto, idUsuario));
    }

    @GetMapping("/ver/{idCarrinho}")
    public ResponseEntity<Object> verCarrinho(@PathVariable int idCarrinho) {
        return RequestResposta.retornar(carrinhoService.verCarrinho(idCarrinho));
    }

    @DeleteMapping("/deletar/{idCarrinho}")
    public ResponseEntity<Object> deletarCarrinho(@PathVariable int idCarrinho) {
        return RequestResposta.retornar(carrinhoService.deletarCarrinho(idCarrinho));
    }

    @DeleteMapping("/{idCarrinho}/deletar-produto/{idProduto}")
    public ResponseEntity<Object> deletarProduto(@PathVariable int idCarrinho, @PathVariable int idProduto) {
        return RequestResposta.retornar(carrinhoService.deletarProduto(idCarrinho, idProduto));
    }
}
