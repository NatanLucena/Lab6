package Gerenciador;

import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.Situa��o;
import Models.Pessoa;
import Models.Situa��o2;
import Models.Situa��o3;
import Models.Situa��o4;
import Models.Situa��o5;

public class Gerenciador {

	HashMap<String, Pessoa> pessoas;
	ArrayList<String> comorbidades;
	ArrayList<String> profissoes;
	int idade;
	int diasPassados;

	public void cadastraPessoa(Pessoa pessoa) {
		if (this.avaliaPrimeiraDose(pessoa)) {
			this.avancaSituacao(pessoa);
		}
		this.pessoas.put(pessoa.getCPF(), pessoa);
	}
	
	public void iniciarVacinacaoDoDia() {
		this.diasPassados++;
		this.atualizaTodos();
	}

	public void atualizaPessoa(Pessoa pessoa) {
		this.pessoas.replace(pessoa.getCPF(), pessoa);
	}
	
	private void atualizaTodos() {
		for(String cpf : this.pessoas.keySet()) {
			Pessoa pessoa = this.pessoas.get(cpf);
			if(pessoa.getSituacao().equals("N�o habitilidada para vacina.")) {
				if (avaliaPrimeiraDose(pessoa)) {
					avancaSituacao(pessoa);
				}
			}
			if(pessoa.getSituacao().equals("Tomou a 1� dose.")) {
				if(avaliaSegundaDose(pessoa)) {
					avancaSituacao(pessoa);
				}
			}
			this.pessoas.replace(cpf, pessoa);
		}
	}

	public String getSituacao(String cpf) {
		return this.pessoas.get(cpf).getSituacao();
	}

	public void adicionaComorbidade(String comorbidade) {
		this.comorbidades.add(comorbidade);
		this.atualizaTodos();
	}

	public void adicionaProfissao(String profissao) {
		this.profissoes.add(profissao);
		this.atualizaTodos();
	}

	public void atualizaIdade(int novaIdade) {
		this.idade = novaIdade;
		this.atualizaTodos();
	}

	private void avancaSituacao(Pessoa pessoa) {
		Situa��o situacaoAtual;
		if (pessoa.getSituacao().equals("N�o habitilidada para vacina.")) {
			situacaoAtual = new Situa��o2();
		} else if (pessoa.getSituacao().equals("Habilitada para tomar a 1� dose")) {
			situacaoAtual = new Situa��o3();
		} else if (pessoa.getSituacao().equals("Tomou a 1� dose.")) {
			situacaoAtual = new Situa��o4();
		} else {
			situacaoAtual = new Situa��o5();
		}
		pessoa.setSituacao(situacaoAtual);
	}

	private boolean avaliaPrimeiraDose(Pessoa pessoa) {
		boolean retorno = false;
		for (String comorbidade : pessoa.getComorbidades()) {
			if (this.comorbidades.contains(comorbidade)) {
				retorno = true;
			}
		}
		if (pessoa.getIdade() >= idade) {
			retorno = true;
		}
		if (this.profissoes.contains(pessoa.getProfissao())) {
			retorno = true;
		}
		return retorno;
	}
	
	private boolean avaliaSegundaDose(Pessoa pessoa) {
		if(pessoa.getSituacao().equals("Tomou a 1� dose.") && (this.diasPassados - pessoa.getDiaVacinado() >= 20)) {
			return true;
		}
		return false;
	}

}
