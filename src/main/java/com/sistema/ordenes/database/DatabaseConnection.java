package com.sistema.ordenes.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    
    private static final String URL="jdbc:sqlite:sistema.db";

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(URL);
        }catch(SQLException e){
            System.err.println("Error al conectar a SQLite"+ e.getMessage());
            return null;
        }
    }
    public static void createTables(){
        String sql= "CREATE TABLE IF NOT EXISTS products ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "name TEXT NOT NULL,"
                    + "stock INTEGER NOT NULL,"
                    + "price REAL NOT NULL"
                    + ");";
        try(Connection conn = getConnection();
            Statement stmt= conn.createStatement()){
                stmt.execute(sql);
                System.out.println("Base de datos lista y tabla 'products' verificada.");
            }catch (SQLException e) {
                System.err.print("Errror al crear la tabla "+ e.getMessage());
            }
    }
}
