package br.com.empresa.lab02;

import java.sql.SQLException;

import com.mysql.jdbc.Connection;


import br.com.empresa.lab02.infra.ConnectionFactory;

import br.com.empresa.lab02.dao.CidadeDAO;
import br.com.empresa.lab02.dao.ClienteDAO;
import br.com.empresa.lab02.dao.FreteDAO;
import br.com.empresa.lab02.modelo.Cliente;
import br.com.empresa.lab02.modelo.Frete;
import br.com.empresa.lab02.modelo.Cidade;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException
    {
    	/*Pega a conexão do MySQL*/
        Connection con = (Connection) ConnectionFactory.getConnectionMySQL();
        con.setAutoCommit(false);
        
        
        /*Consultas SQL USANDO JDBC e MAVEN*/
        
        ClienteDAO clienteDAO =  new ClienteDAO(con);
        CidadeDAO cidadeDAO = new CidadeDAO(con);
        FreteDAO freteDAO = new FreteDAO(con);
        
        /*Adicionando Cliente*/
        Cliente cliente = new Cliente();
        cliente.setNome("ZEZINHO");
        cliente.setEndereco("Rua 15");
        cliente.setTelefone("32375419");
        
        //clienteDAO.adicionaCliente(cliente); 
        
        /*Adicionando Cidade*/
        Cidade cidade = new Cidade();
        cidade.setNome("Terezina");
        cidade.setUF("PI");
        cidade.setTaxa(31.6);
        
        //cidadeDAO.adicionaCidade(cidade);
        
        /*Adicionando Frete*/
        Frete frete = new Frete();
        frete.setCidade(cidadeDAO.procupaPelo(10));
        frete.setCliente(clienteDAO.buscaPeloTitularPor(10));
        frete.setDescricao("Frete do ZEZINHO");
        frete.setPeso(15698);
        frete.setValor(55265);
        
        //freteDAO.adicionaFrete(frete);
        
        /*Recuperando valor do frete*/
        //System.out.println(freteDAO.recuperaValorDoFretePor(2));
        
        /*Recupera a lista de fretes de um cliente*/
        for (Frete frete1 : freteDAO.listaFreteClienteDe(1)) {
        //	System.out.println(frete1);
        }
        
        /*Recupera Frete(s) de maior valor*/
        for (Frete frete1 : freteDAO.recuperaFreteDeMaiorValor()) {
        	System.out.println(frete1);
        }
        
        /*Recupera a cidade que a maior destinatária de fretes*/
        //System.out.println(freteDAO.recuperaCidadeComMaiorQuantidadeDeFrete());
        
        
        
        
        /*Fechando a conexão e o commit*/
        con.commit();
        con.close();
    }
}
