package com.unipe.api2.controller;

import com.unipe.api2.dto.form.Login;
import com.unipe.api2.dto.form.UsuarioAtualizaDadosForm;
import com.unipe.api2.dto.form.UsuarioForm;
import com.unipe.api2.model.Usuario;
import com.unipe.api2.service.UsuarioService;
import com.unipe.api2.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @GetMapping("/listar")
    public ResponseEntity<List<Usuario>> listar(){
        return new ResponseEntity<>(usuarioService.listar(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> buscarPorId(@PathVariable int id){
        return RequestResposta.retornar(usuarioService.buscarPorId(id));
    }

    @GetMapping("/busca-por-cpf/{cpf}")
    public ResponseEntity<Object> buscarPorCPF(@PathVariable String cpf){
        return RequestResposta.retornar(usuarioService.buscarPorCPF(cpf));
    }

    @PostMapping
    public ResponseEntity<Object> criar(@RequestBody UsuarioForm usuarioForm){
        return RequestResposta.retornar(usuarioService.salvar(usuarioForm));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> atualizar(@PathVariable int id, @RequestBody UsuarioAtualizaDadosForm dadosAtualizados){
        return RequestResposta.retornar(usuarioService.atualizaDados(id, dadosAtualizados));
    }

    @PutMapping("/{id}/nova-senha")
    public ResponseEntity<Object> atualizarSenha(@PathVariable int id, @RequestBody String novaSenha) {
        return RequestResposta.retornar(usuarioService.atualizaSenha(id, novaSenha));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletar(@PathVariable int id){
        return RequestResposta.retornar(usuarioService.deletar(id));
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody Login login ){
        return RequestResposta.retornar(usuarioService.login(login));
    }



//    @PostMapping("/{id}/comprar-ingresso/{ingressoId}")
//    public ResponseEntity<String> compraIngresso(@PathVariable Integer id, @PathVariable Integer ingressoId){
//        usuarioService.comprarUmIngresso(id, ingressoId);
//        return new ResponseEntity<>("Ingresso comprado com sucesso", HttpStatus.OK);
//    }
}
