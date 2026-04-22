package br.com.davi.clinica.dao;

import br.com.davi.clinica.factory.ConnectionFactory;
import br.com.davi.clinica.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;

public class UsuarioDAO {

    public int cadastrar(Usuario usuario) {
        String sql = "INSERT INTO usuarios (email, senha, perfil, ativo) VALUES (?, ?, ?, ?)";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            stmt.setString(1, usuario.getEmail());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getPerfil());
            stmt.setBoolean(4, false);

            stmt.execute();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()){
                return rs.getInt(1);
            } return 0;
        } catch (SQLException e){
            throw new RuntimeException("Erro ao criar usuário: " + e.getMessage());
        }
    }

    public void ativarConta(int idUsuario) {

        String sql = "Update usuarios SET ativo = ? Where id = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setBoolean(1, true);
            stmt.setInt(2, idUsuario);
            stmt.execute();
            System.out.println("Conta ativa com sucesso!");

        } catch (SQLException e){
            throw new RuntimeException("Erro ao ativar conta: " + e.getMessage());
        }

    }

    public Usuario validarLogin(String email, String senha) {

        String sql = "SELECT * FROM usuarios WHERE email = ? and senha = ?";

        try (Connection conn = new ConnectionFactory().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)){

            stmt.setString(1, email);
            stmt.setString(2, senha);
            ResultSet rs = stmt.executeQuery();

            if(rs.next()){
                Usuario u = new Usuario(rs.getString("email"), rs.getString("senha"), rs.getString("perfil"));
                u.setId(rs.getInt("id"));
                u.setAtivo(rs.getBoolean("ativo"));
                return u;
            }
            return null;
        } catch (SQLException e){
            throw new RuntimeException("Erro no login: " + e.getMessage());
        }
    }

}
