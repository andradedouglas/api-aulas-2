package com.unipe.api2.controller;

import com.unipe.api2.dto.form.Login;
import com.unipe.api2.dto.form.UsuarioForm;
import com.unipe.api2.model.Usuario;
import com.unipe.api2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    public ResponseEntity<Usuario> buscarPorId(@PathVariable int id){
        return new ResponseEntity<>(usuarioService.buscarPorId(id), HttpStatus.OK);
    }
    @GetMapping("/busca-por-cpf/{cpf}")
    public ResponseEntity<Usuario> buscarPorCPF(@PathVariable String cpf){
        return new ResponseEntity<>(usuarioService.buscarPorCPF(cpf), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> criar(@RequestBody UsuarioForm usuarioForm){
        usuarioService.salvar(usuarioForm);
        return new ResponseEntity<>(("Usuário criado com sucesso"), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(@PathVariable int id, @RequestBody Map<String,Object> dadosAtualizados){
        return new ResponseEntity<>(usuarioService.atualizaDados(id, dadosAtualizados), HttpStatus.OK);
    }

    @PutMapping("/{id}/nova-senha")
    public ResponseEntity<Usuario> atualizarSenha(@PathVariable int id, @RequestBody String novaSenha) {
        return new ResponseEntity<>(usuarioService.atualizaSenha(id, novaSenha), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable int id){
        usuarioService.deletar(id);
        return new ResponseEntity<>("Deletado com sucesso", HttpStatus.NO_CONTENT);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody Login login ){
        return new ResponseEntity<>(usuarioService.login(login),HttpStatus.ACCEPTED);
    }

//    @PostMapping("/{id}/comprar-ingresso/{ingressoId}")
//    public ResponseEntity<String> compraIngresso(@PathVariable Integer id, @PathVariable Integer ingressoId){
//        usuarioService.comprarUmIngresso(id, ingressoId);
//        return new ResponseEntity<>("Ingresso comprado com sucesso", HttpStatus.OK);
//    }
}
