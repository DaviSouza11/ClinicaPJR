package br.com.davi.clinica.service;

import br.com.davi.clinica.dao.UsuarioDAO;
import br.com.davi.clinica.model.Usuario;
import br.com.davi.clinica.security.SenhaBCrypt;
import br.com.davi.clinica.dto.CadastroResultado;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class UsuarioService {

    private final UsuarioDAO usuarioDAO;
    private final EmailService emailService;

    public UsuarioService(UsuarioDAO usuarioDAO, EmailService emailService) {
        this.usuarioDAO = usuarioDAO;
        this.emailService = emailService;
    }

    public CadastroResultado cadastrarComVerificacaoEmail(Usuario usuario){
        String hash = SenhaBCrypt.gerarHash(usuario.getSenha());
        usuario.setSenha(hash);

        int idGerado = usuarioDAO.cadastrar(usuario);
        int codigoSeguranca = 100000 + new Random().nextInt(999999);

        try {
            emailService.enviarCodigo(usuario.getEmail(), codigoSeguranca);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar cógido",e);
        }
        return new CadastroResultado(idGerado, codigoSeguranca);
    }
    public void ativarConta(int idUsuario) {
        usuarioDAO.ativarConta(idUsuario);
    }
}
