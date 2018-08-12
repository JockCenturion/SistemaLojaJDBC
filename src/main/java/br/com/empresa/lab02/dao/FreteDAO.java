package br.com.empresa.lab02.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.com.empresa.lab02.modelo.Frete;
import br.com.empresa.lab02.modelo.Cidade;
import br.com.empresa.lab02.modelo.Cliente;



public class FreteDAO {
	
	private Connection connection;
	
	public FreteDAO(Connection connection) {
		this.connection = connection;
	}
	
	public void adicionaFrete(Frete frete) {
		
		try {
			
			PreparedStatement ps = this.connection.prepareStatement("INSERT INTO tbl_frete VALUES (NULL, ?, ?, ?, ?, ?)");
			ps.setInt(1, frete.getCidade().getCodigo());
			ps.setInt(2, frete.getCliente().getCodigo());
			ps.setString(3, frete.getDescricao());
			ps.setDouble(4, frete.getPeso());
			ps.setDouble(5, frete.getValor());
			ps.execute();
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public double recuperaValorDoFretePor(Integer idFrete) {
		
		try {
			
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT * FROM valorFrete WHERE codigo_frete = ?");
			ps.setInt(1, idFrete);
			rs = ps.executeQuery();
			
			if (!rs.next()) {
				throw new RuntimeException("Objeto Frete Não Encontrado No BD");
			}
			
			double valorFrete = rs.getDouble("ValorFrete");
			
			ps.close();
			rs.close();
			
			return valorFrete;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	

	public List<Frete> listaFreteClienteDe(Integer idCliente) {
		
		try {
			
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT * FROM tbl_frete WHERE codigo_cliente = ?");
			ps.setInt(1, idCliente);
			rs = ps.executeQuery();
			
			List<Frete> lista = new ArrayList<Frete>();
			
			while (rs.next()) {
				
				Frete frete = new Frete();
				
				frete.setCodigo(rs.getInt("codigo_frete"));
				frete.setCidade(buscaCidadePor(rs.getInt("codigo_cidade")));
				frete.setCliente(buscaClientePor(rs.getInt("codigo_cliente")));
				frete.setDescricao(rs.getString("descricao"));
				frete.setPeso(rs.getDouble("peso"));
				frete.setValor(rs.getDouble("valor"));
				
				lista.add(frete);
			}
			
			ps.close();
			rs.close();
			
			return lista;

		} catch (SQLException e) {
			throw new RuntimeException(e);
		}

	}
	
	
	/**
	 * <p> Recupera a cidade com maior frete</p><br>
	 * <p> Está consulta depende de uma view <br>
	 * @throws Qualquer erro ao executar uma consulta no banco de dados.
	 * @return a cidade com maior frete ou as cidades com o maior frete
	 */
	
	public List<Cidade> recuperaCidadeComMaiorQuantidadeDeFrete() { 
		
		try {
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT C.codigo_cidade, C.nome_cidade, C.UF, C.taxa FROM tbl_cidade C JOIN quantidadeFrete Q WHERE C.codigo_cidade = Q.codigo_cidade AND Quantidade = (SELECT MAX(Quantidade) FROM quantidadeFrete)");
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
	
	
	public List<Frete> recuperaFreteDeMaiorValor() {
		
		try {
			
			PreparedStatement ps;
			ResultSet rs;
			
			ps = this.connection.prepareStatement("SELECT F.codigo_frete, F.codigo_cidade, F.codigo_cliente, F.descricao, F.peso, F.valor "
					+ "FROM tbl_frete F JOIN tbl_cidade C "
					+ "WHERE F.codigo_cidade = C.codigo_cidade AND ROUND((peso * 10 + taxa), 2) = (SELECT MAX(ValorFrete) FROM valorFrete)");
			
			rs = ps.executeQuery();
			
			List<Frete> lista = new ArrayList<Frete>();
			
			while (rs.next()) {
				Frete frete = new Frete();
				
				frete.setCodigo(rs.getInt("F.codigo_frete"));
				frete.setCidade(buscaCidadePor(rs.getInt("F.codigo_cidade")));
				frete.setCliente(buscaClientePor(rs.getInt("F.codigo_cliente")));
				frete.setDescricao(rs.getString("F.descricao"));
				frete.setPeso(rs.getDouble("peso"));
				frete.setValor(rs.getDouble("valor"));
				
				lista.add(frete);
			}
			
			
			ps.close();
			rs.close();
			
			return lista;
			
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	private Cidade buscaCidadePor(Integer idCidade) {
		return (new CidadeDAO(connection)).procuraPelaCidadePor(idCidade);
	}
	
	private Cliente buscaClientePor(Integer idCliente) {
		return (new ClienteDAO(connection)).buscaPeloTitularPor(idCliente);
	}

}
