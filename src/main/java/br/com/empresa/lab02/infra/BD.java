package br.com.empresa.lab02.infra;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BD {
	
	private Connection connection;
	
	public BD(Connection connection) {
		this.connection = connection;
	}
	
	public void geraTabelaCliente() {
		try {
			
			String schema =   "CREATE TABLE tbl_cliente (" 
						    + "codigo_cliente INT NOT NULL AUTO_INCREMENT," 
						    + "nome_cliente VARCHAR(30),"
						    + "endereco VARCHAR(30),"
						    + "telefone VARCHAR(30),"
						    + "PRIMARY KEY (codigo_cliente)"
							+ ") ENGINE = InnoDB";
			
			Statement statement = this.connection.createStatement();
			statement.execute(schema);
			
		} catch (SQLException e) {
			System.out.println("A tabela CLIENTE já existe");
			e.printStackTrace();
		}
	}
	
	
	
}
