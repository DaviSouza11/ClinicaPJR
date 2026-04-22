package br.com.davi.clinica.dao;

import br.com.davi.clinica.factory.ConnectionFactory;
import br.com.davi.clinica.model.Medico;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MedicoDAO {

    public void cadastrar(Medico medico){
        String sql = "Insert INTO medicos (nome, cpf, especialidade, crm) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, medico.getName());
            stmt.setString(2, medico.getCpf());
            stmt.setString(3, medico.getEspecialidade());
            stmt.setString(4, medico.getCRM());

            stmt.execute();
            System.out.println("Médico " + medico.getName() + " cadastrado com sucesso!");

        } catch (SQLException e) {
            throw new RuntimeException("Erro ao salvar Medico " + e.getMessage());
        }

    }
}
