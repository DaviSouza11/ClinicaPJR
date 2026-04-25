package br.com.davi.clinica.service;

import br.com.davi.clinica.dao.UsuarioDAO;
import br.com.davi.clinica.model.Usuario;
import br.com.davi.clinica.security.SenhaBCrypt;

public class UsuarioService {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    public int cadastrar(Usuario usuario){

        String hash = SenhaBCrypt.gerarHash(usuario.getSenha());

        usuario.setSenha(hash);

        return usuarioDAO.cadastrar(usuario);
    }
}
