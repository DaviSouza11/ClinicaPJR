package br.com.davi.clinica.dao;

import br.com.davi.clinica.factory.ConnectionFactory;
import br.com.davi.clinica.model.Agenda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AgendaDAO {

    public void agendarConsulta(Agenda agenda){

        String sql = "INSERT INTO agendas (medico_id, paciente_id, dataConsulta, horaConsulta, status) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setInt(1, agenda.getIdMedico());
            stmt.setInt(2, agenda.getIdPaciente());
            stmt.setString(3, agenda.getDataConsulta());
            stmt.setString(4, agenda.getHoraConsulta());
            stmt.setString(5, agenda.getStatus());

            stmt.execute();
            System.out.println("Consulta agendada com sucesso para o dia " + agenda.getDataConsulta() + "!");
        } catch (SQLException e) {
            throw new RuntimeException("Erro ao agendar consulta: " + e.getMessage());
        }
    }
}
