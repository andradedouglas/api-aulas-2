package com.unipe.api2.service;

import com.unipe.api2.dto.form.Login;
import com.unipe.api2.dto.form.UsuarioAtualizaDadosForm;
import com.unipe.api2.dto.form.UsuarioForm;
import com.unipe.api2.model.Usuario;
import com.unipe.api2.repository.UsuarioRepository;
import com.unipe.api2.utils.RequestResposta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;


    public List<Usuario> listar(){
        return usuarioRepository.findAll();
    }

    public RequestResposta buscarPorId(int id){
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);
        return usuarioOptional.isEmpty()
                ? trataUsuarioInexistente()
                : new RequestResposta(usuarioOptional.get(), HttpStatus.OK);
    }

    public RequestResposta buscarPorCPF(String cpf){
        Optional<Usuario> usuarioOptional = usuarioRepository.findUsuarioByCpf(cpf);
        return usuarioOptional.isEmpty()
                ? trataUsuarioInexistente()
                : new RequestResposta(usuarioOptional.get(), HttpStatus.OK);
    }
    public RequestResposta salvar(UsuarioForm usuarioForm){
        Optional<Usuario> usuarioAtual = usuarioRepository.findUsuarioByCpf(usuarioForm.getCpf());
        return usuarioAtual.isEmpty()
                ? new RequestResposta(usuarioRepository.save(new Usuario(usuarioForm)), HttpStatus.CREATED)
                : new RequestResposta("Usuário já cadastrado", HttpStatus.CONFLICT);
    }


    public RequestResposta atualizaDados(int id, UsuarioAtualizaDadosForm dadosAtualizados)  {

        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
        if (usuarioAtual.isEmpty()) {
            return trataUsuarioInexistente();
        }
        Usuario usuario = usuarioAtual.get();

        if (dadosAtualizados.getCpf() != null)
            usuario.setCpf(dadosAtualizados.getCpf());
        if (dadosAtualizados.getEmail() != null)
            usuario.setEmail(dadosAtualizados.getEmail());
        if (dadosAtualizados.getNomeCompleto() != null)
            usuario.setNomeCompleto(dadosAtualizados.getNomeCompleto());
        if (dadosAtualizados.getIdade() != 0)
            usuario.setIdade(dadosAtualizados.getIdade());
        if (dadosAtualizados.getUsername() != null)
            usuario.setUsername(dadosAtualizados.getUsername());

        return new RequestResposta(usuarioRepository.save(usuario), HttpStatus.CREATED);
    }

    @Transactional
    public RequestResposta atualizaSenha(int id, String novaSenha){
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
        if (usuarioAtual.isEmpty()) {
            return trataUsuarioInexistente();
        }
        Usuario usuario = usuarioAtual.get();
        usuario.setSenha(novaSenha);
        usuarioRepository.save(usuario);
        return new RequestResposta("Senha atualizada com sucesso", HttpStatus.OK);
    }



    public RequestResposta login(Login login){
        Optional<Usuario> usuarioAtual = usuarioRepository.findUsuarioByUsernameAndSenha(login.getUsername(), login.getSenha());
        return usuarioAtual.isEmpty()
                ? trataUsuarioInexistente()
                : new RequestResposta("Login realizado com sucesso", HttpStatus.ACCEPTED);
    }


    public RequestResposta deletar(int id){
        Optional<Usuario> usuarioAtual = usuarioRepository.findById(id);
        if (usuarioAtual.isEmpty()) {
            return trataUsuarioInexistente();
        }
        usuarioRepository.deleteById(id);
        return new RequestResposta("Deletado com sucesso", HttpStatus.OK);
    }

    private RequestResposta trataUsuarioInexistente() {
        return new RequestResposta("Usuário não encontrado", HttpStatus.NOT_FOUND);
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
