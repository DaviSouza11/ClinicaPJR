package br.com.davi.clinica.model;

public class Agenda {

    private int id;
    private int idMedico;
    private int idPaciente;
    private String dataConsulta;
    private String horaConsulta;
    private String status;

    public Agenda(int id, int idMedico, int idPaciente, String dataConsulta, String horaConsulta,String status ) {
        this.id = id;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.status = status;
    }

    public Agenda(int idMedico, int idPaciente, String dataConsulta, String horaConsulta,String status ) {
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.dataConsulta = dataConsulta;
        this.horaConsulta = horaConsulta;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(String dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public String getHorarioConsulta() {
        return horaConsulta;
    }

    public void setHorarioConsulta(String horarioConsulta) {
        this.horaConsulta = horarioConsulta;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getHoraConsulta() {
        return horaConsulta;
    }

    public void setHoraConsulta(String horaConsulta) {
        this.horaConsulta = horaConsulta;
    }

    @Override
    public String toString() {
        return "Horário: " + dataConsulta + " às " + horaConsulta +
                " | Status: " + status +
                " | (ID do Médico: " + idMedico + " | Paciente ID: " + idPaciente;
    }
}
