package br.com.davi.clinica.security;

import org.mindrot.jbcrypt.BCrypt;

public class SenhaBCrypt {

    private static final int CUSTO = 12;

    public static String gerarHash(String senha){
        return BCrypt.hashpw(senha, BCrypt.gensalt(CUSTO));
    }

    public boolean verificarSenha(String senhaDigitada, String hahsSalvo){
        return BCrypt.checkpw(senhaDigitada, hahsSalvo);
    }
}
