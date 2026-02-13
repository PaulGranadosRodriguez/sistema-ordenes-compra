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
        
        String sqlBrands = "CREATE TABLE IF NOT EXISTS brands("
                           +"id INTEGER PRIMARY KEY AUTOINCREMENT, "
                           +"name TEXT NOT NULL UNIQUE "
                           +");";        
        
        String sqlProducts=   "CREATE TABLE IF NOT EXISTS products ("
                            + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                            + "name TEXT NOT NULL, "
                            + "stock INTEGER NOT NULL, "
                            + "price REAL NOT NULL, "
                            + "brand_id INTEGER, "
                            + "FOREIGN KEY (brand_id) REFERENCES brands(id)"
                            + ");";
        try(Connection conn = getConnection();
            Statement stmt= conn.createStatement()){
                stmt.execute("PRAGMA foreign_keys=ON;");
                stmt.execute(sqlBrands);
                stmt.execute(sqlProducts);
                System.out.println("Base de datos lista y tabla 'products' verificada.");
            }catch (SQLException e) {
                System.err.print("Errror al crear la tabla "+ e.getMessage());
            }
    }
}
