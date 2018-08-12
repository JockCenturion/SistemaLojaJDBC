package br.com.empresa.lab02.modelo;

public class Cidade {
	private Integer Codigo;
	private String nome;
	private String UF;
	private double taxa;
	
	public Integer getCodigo() {
		return Codigo;
	}
	
	public void setCodigo(Integer codigo) {
		Codigo = codigo;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getUF() {
		return UF;
	}
	
	public void setUF(String uF) {
		UF = uF;
	}
	
	public double getTaxa() {
		return taxa;
	}
	
	public void setTaxa(double taxa) {
		this.taxa = taxa;
	}

	@Override
	public String toString() {
		return "Cidade [Codigo=" + Codigo + ", nome=" + nome + ", UF=" + UF + ", taxa=" + taxa + "]";
	}
	
	
}
