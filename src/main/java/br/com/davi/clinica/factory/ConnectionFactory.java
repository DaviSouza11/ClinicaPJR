package br.com.davi.clinica.factory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/PRJClinica";
    private static final String USER = "root";
    private static final String PASS = "";

    public Connection getConnection(){
        try{
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (SQLException e){
            throw new RuntimeException("Erro ao conectar ao banco de dados!" + e.getMessage());
        }
    }
}
