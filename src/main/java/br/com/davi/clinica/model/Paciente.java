package br.com.davi.clinica.model;

public class Paciente extends Pessoa {
    private String dataNascimento;

    public Paciente(int id,  String name, String cpf, String dataNascimento){
        super(0,name,cpf);
        this.dataNascimento = dataNascimento;
    }

    public Paciente(String name, String cpf, String dataNascimento){
        super(0,name,cpf);
        this.dataNascimento = dataNascimento;
    }

    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @Override
    public String toString() {
        return name + " cpf: " + cpf + " Data de Nascimento: " +  dataNascimento;
    }
}
