package Models;
import java.util.ArrayList;

import Interfaces.Situação;

public class Pessoa {
	
	String nome;
	String CPF;
	int idade;
	int diaVacinado;
	String endereco;
	String cartaoSUS;
	String email;
	String telefone;
	String profissao;
	ArrayList<String> comorbidades;
	Situação situacao;
	
	public Pessoa(String nome, String cpf, int idade,int diaVacinado, String endereco, String cartao, String email, String telefone, String profissao, ArrayList<String> comorbidades) {
		this.nome = nome;
		this.CPF = cpf;
		this.idade = idade;
		this.diaVacinado = diaVacinado;
		this.endereco = endereco;
		this.cartaoSUS = cartao;
		this.email = email;
		this.telefone = telefone;
		this.profissao = profissao;
		this.comorbidades = comorbidades;
	}
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCPF() {
		return CPF;
	}

	public void setCPF(String cPF) {
		CPF = cPF;
	}
	
	public int getDiaVacinado() {
		return this.diaVacinado;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getCartaoSUS() {
		return cartaoSUS;
	}

	public void setCartaoSUS(String cartaoSUS) {
		this.cartaoSUS = cartaoSUS;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public ArrayList<String> getComorbidades() {
		return comorbidades;
	}

	public void adicionaComorbidade(String comorbidade) {
		this.comorbidades.add(comorbidade);
	}
	
	public void removeComorbidade(String comorbidade) {
		this.comorbidades.remove(comorbidade);
	}

	public String getSituacao() {
		return this.situacao.getSituacao();
	}

	public void setSituacao(Situação situacao) {
		this.situacao = situacao;
	}
	
	
}