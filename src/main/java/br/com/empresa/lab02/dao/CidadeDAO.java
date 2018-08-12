package br.com.empresa.lab02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.empresa.lab02.modelo.Cidade;

public class CidadeDAO {
	
	private Connection connection;
	
	public CidadeDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void adicionaCidade(Cidade cidade) {
		
		try {
			PreparedStatement ps = this.connection.prepareStatement("INSERT INTO tbl_cidade(codigo_cidade, nome_cidade, UF, taxa) " 
																	+ "	VALUES (NULL, ?, ?, ?)");
			ps.setString(1, cidade.getNome());
			ps.setString(2, cidade.getUF());
			ps.setDouble(3, cidade.getTaxa());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public List<Cidade> listaCidades() {
		
		try {
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT codigo_cidade, nome_cidade, UF, taxa FROM tbl_cidade");
			rs = ps.executeQuery();
			
			List<Cidade> lista = new ArrayList<Cidade>();
			
			while (rs.next()) {
				Cidade cidade = new Cidade();
				cidade.setCodigo(rs.getInt("codigo_cidade"));
				cidade.setNome(rs.getString("nome_cidade"));
				cidade.setUF(rs.getString("UF"));
				cidade.setTaxa(rs.getDouble("taxa"));
				
				lista.add(cidade);
			}
			
			ps.close();
			rs.close();
			
			return lista;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void alteraCidade(Cidade cidade) {
		
		try {
			PreparedStatement ps = this.connection.prepareStatement("UPDATE tbl_cidade SET nome_cidade = ?, UF = ?, taxa = ? WHERE codigo_cidade = ?");
			ps.setString(1, cidade.getNome());
			ps.setString(2, cidade.getUF());
			ps.setDouble(3, cidade.getTaxa());
			ps.setInt(4, cidade.getCodigo());
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public void deletaCidade(Integer id) {
		
		try {
			
			PreparedStatement ps = this.connection.prepareStatement("DELETE FROM tbl_cidade WHERE codigo_cidade = ?");
			ps.setInt(1, id);
			ps.execute();
			ps.close();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Cidade procupaPelo(Integer id) {
		
		try {
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT nome_cidade, UF, taxa FROM tbl_cidade WHERE codigo_cidade = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if ( !rs.next() ) {
				throw new RuntimeException("");
			}
			
			Cidade cidade = new Cidade();
			
			cidade.setCodigo(id);
			cidade.setNome(rs.getString("nome_cidade"));
			cidade.setUF(rs.getString("UF"));
			cidade.setTaxa(rs.getDouble("taxa"));
			
			ps.close();
			rs.close();
			
			return cidade;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	public Cidade procuraPelaCidadePor(Integer id) {
		
		try {
			
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT * FROM tbl_cidade WHERE codigo_cidade = ?");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			
			if ( !rs.next() ) {
				throw new RuntimeException("Objeto Cidade Não Encontrado em BD");
			}
			
			Cidade cidade = new Cidade();
			
			cidade.setCodigo(rs.getInt("codigo_cidade"));
			cidade.setNome(rs.getString("nome_cidade"));
			cidade.setTaxa(rs.getDouble("taxa"));
			cidade.setUF(rs.getString("UF"));
			
			ps.close();
			rs.close();
			
			return cidade;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
}
