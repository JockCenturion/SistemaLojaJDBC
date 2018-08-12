package br.com.empresa.lab02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import br.com.empresa.lab02.modelo.Cliente;


public class ClienteDAO {
	
	private Connection connection;
	
	
	public ClienteDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void adicionaCliente(Cliente cliente) {
		
		try {
			
			PreparedStatement ps = this.connection.prepareStatement("INSERT INTO tbl_cliente (codigo_cliente, nome_cliente, endereco, telefone) "
																	+ "VALUES (NULL, ?, ?, ?)");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEndereco());
			ps.setString(3, cliente.getTelefone());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Cliente> listaClientes() {
		
		try {
			
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT codigo_cliente, nome_cliente, endereco, telefone FROM tbl_cliente");
			rs = ps.executeQuery();
			
			List<Cliente> lista = new ArrayList<Cliente>();
			
			while (rs.next()) {
				
				Cliente cliente = new Cliente();
				cliente.setCodigo(rs.getInt("codigo_cliente"));
				cliente.setNome(rs.getString("nome_cliente"));
				cliente.setEndereco(rs.getString("endereco"));
				cliente.setTelefone(rs.getString("telefone"));
				
				lista.add(cliente);
			}
			
			return lista;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void alteraCliente(Cliente cliente) {
		
		try {
			
			PreparedStatement ps = this.connection.prepareStatement("UPDATE tbl_cliente SET nome_cliente = ?, endereco = ?, telefone = ? WHERE codigo_cliente = ?");
			ps.setString(1, cliente.getNome());
			ps.setString(2, cliente.getEndereco());
			ps.setString(3, cliente.getTelefone());
			ps.setInt(4, cliente.getCodigo());
			ps.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void deletaCliente(Integer id) {
		
		try {
			
			PreparedStatement ps = this.connection.prepareStatement("DELETE FROM tbl_cliente WHERE codigo_cliente = ?");
			ps.setInt(1, id);
			ps.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public Cliente buscaPeloTitularPor(Integer id) {
		
		try {
			
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT * FROM tbl_cliente WHERE codigo_cliente = ?");
			ps.setInt(1, id);
			
			rs = ps.executeQuery();
			
			if ( !(rs.next()) ) {
				throw new RuntimeException("Objeto Cliente Não Encontrado Em BD");
			}
			
			Cliente cliente = new Cliente();
			
			cliente.setCodigo(rs.getInt("codigo_cliente"));
			cliente.setNome(rs.getString("nome_cliente"));
			cliente.setEndereco(rs.getString("endereco"));
			cliente.setTelefone(rs.getString("telefone"));
		
			ps.close();
			rs.close();
			
			return cliente;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

}
