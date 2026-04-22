package br.com.davi.clinica.model;

public class Usuario {
    private int id;
    private String email;
    private String senha;
    private String perfil;
    private boolean ativo;

    private Pessoa dadosPerfil;

    public Usuario(String email, String senha, String perfil) {
        this.email = email;
        this.senha = senha;
        this.perfil = perfil;
        this.ativo = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getPerfil() {
        return perfil;
    }

    public void setPerfil(String perfil) {
        this.perfil = perfil;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public Pessoa getDadosPerfil() {
        return dadosPerfil;
    }

    public void setDadosPerfil(Pessoa dadosPerfil) {
        this.dadosPerfil = dadosPerfil;
    }

    public String getNomeParaTela() {
        if (dadosPerfil != null) {
            return dadosPerfil.getName();
        }
        return "Usuário sem perfil preenchido";
    }
}
