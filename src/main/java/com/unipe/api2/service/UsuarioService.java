package com.unipe.api2.service;

import com.unipe.api2.dto.form.Login;
import com.unipe.api2.dto.form.UsuarioForm;
import com.unipe.api2.model.Usuario;
import com.unipe.api2.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Map;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public Usuario buscarPorId(int id){
        return usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public Usuario buscarPorCPF(String cpf){
        return usuarioRepository.findUsuarioByCpf(cpf).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }
    public Usuario salvar(UsuarioForm usuarioForm){
        Usuario usuario = new Usuario(usuarioForm);
        return usuarioRepository.save(usuario);
    }



    public Usuario atualizaDados(int id, Map<String, Object> dadosAtualizados){

        Usuario usuarioAtual = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        //
        return usuarioRepository.save(usuarioAtual);
    }

    @Transactional
    public Usuario atualizaSenha(int id, String novaSenha){
        Usuario usuarioAtual = usuarioRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        usuarioAtual.setSenha(novaSenha);
        //usuarioRepository.save(usuarioAtual);
        return usuarioAtual;
    }

    public String login(Login login){
        Usuario usuarioAtual = usuarioRepository.findUsuarioByUsernameAndSenha(login.getUsername(), login.getSenha()).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        return "Login realizado com sucesso";
    }


    public void deletar(int id){
        usuarioRepository.deleteById(id);
    }

//    @Transactional
//    public void comprarUmIngresso(Integer usuarioId, Integer ingressoId){
//        Optional<Usuario> optionalUsuario = usuarioRepository.findById(usuarioId);
//        Optional<Ingresso> optionalIngresso = ingressoRepository.findById(ingressoId);
//
//        if (optionalUsuario.isPresent() && optionalIngresso.isPresent()) {
//           Usuario usuario = optionalUsuario.get();
//           Ingresso ingresso = optionalIngresso.get();
//           ingresso.setUsuario(usuario);
//           List<Ingresso> ingressos = usuario.getIngressos();
//           ingressos.add(ingresso);
//           usuario.setIngressos(ingressos);
//        }
//    }
}
