package br.com.empresa.lab02.modelo;

public class Frete {
	private Integer codigo;
	private Cidade cidade;
	private Cliente cliente;
	private String descricao;
	private double peso;
	private double valor;
	
	public Integer getCodigo() {
		return codigo;
	}
	
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	
	public Cidade getCidade() {
		return cidade;
	}
	
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public double getPeso() {
		return peso;
	}
	
	public void setPeso(double peso) {
		this.peso = peso;
	}
	
	public double getValor() {
		return valor;
	}
	
	public void setValor(double valor) {
		this.valor = valor;
	}

	@Override
	public String toString() {
		return "Frete [codigo=" + codigo + ", cidade=" + cidade + ", cliente=" + cliente + ", descricao=" + descricao
				+ ", peso=" + peso + ", valor=" + valor + "]";
	}
	
	
}
