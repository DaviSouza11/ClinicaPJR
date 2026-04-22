package br.com.davi.clinica.dao;

import br.com.davi.clinica.factory.ConnectionFactory;
import br.com.davi.clinica.model.Paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PacienteDAO {

    public void cadastrar(Paciente paciente){

        String sql = "Insert INTO pacientes (nome, cpf, dataNascimento) VALUES (?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, paciente.getName());
            stmt.setString(2, paciente.getCpf());
            stmt.setString(3, paciente.getDataNascimento());

            stmt.execute();
            System.out.println("Paciente " + paciente.getName() + " cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao cadastrar Paciente: " + e.getMessage());
        }

    }
}
