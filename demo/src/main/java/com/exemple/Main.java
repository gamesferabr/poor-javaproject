package com.exemple;
import com.exemple.util.DatabaseConnection;
public class Main {
    public static void main(String[] args) 
    {
        try {
            DatabaseConnection.getConnection();
            System.out.println("Conexão com o banco de dados realizada com sucesso!");
        } catch (Exception e) {
            System.err.println("Erro ao conectar com o banco de dados: " + e.getMessage());
        }    
    }
}