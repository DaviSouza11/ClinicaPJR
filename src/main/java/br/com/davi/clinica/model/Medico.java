package br.com.davi.clinica.model;

public class Medico extends Pessoa{
    private String especialidade;
    private String CRM;

    public Medico(int id, String name, String cpf, String especialidade, String CRM){
        super(0, name, cpf);
        this.especialidade = especialidade;
        this.CRM = CRM;
    }

    public Medico(String name, String cpf, String especialidade, String crm) {
        super(0, name, cpf); // Aqui sim passamos 0
        this.especialidade = especialidade;
        this.CRM = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getCRM() {
        return CRM;
    }

    public void setCRM(String CRM) {
        this.CRM = CRM;
    }

    @Override
    public String toString(){
        return name + " Especialidade: " + especialidade + " CRM: " + CRM;
    }
}
