package br.com.davi.clinica.controller;

import br.com.davi.clinica.model.Usuario;
import br.com.davi.clinica.service.UsuarioService;
import br.com.davi.clinica.dto.CadastroResultado;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class usuarioController {

    private final UsuarioService usuarioService;

    public usuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public CadastroResultado resultado(@RequestBody Usuario usuario){
        return usuarioService.cadastrarComVerificacaoEmail(usuario);
    }
}
