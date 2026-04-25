package br.com.davi.clinica.dto;

public class CadastroResultado {
    private int idUsuario;
    private int codigoGerado;

    public CadastroResultado(int idUsuario, int codigoGerado){
        this.idUsuario = idUsuario;
        this.codigoGerado = codigoGerado;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getCodigoGerado() {
        return codigoGerado;
    }

    public void setCodigoGerado(int codigoGerado) {
        this.codigoGerado = codigoGerado;
    }
}
