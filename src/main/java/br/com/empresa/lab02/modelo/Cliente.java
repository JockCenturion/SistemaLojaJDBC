package br.com.empresa.lab02.modelo;

public class Cliente {
	
	private Integer codigo;
	private String nome;
	private String endereco;
	private String telefone;
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	@Override
	public String toString() {
		return "Cliente [codigo=" + codigo + ", nome=" + nome + ", endereco=" + endereco + ", telefone=" + telefone
				+ "]";
	}
	
}
