package Gerenciador;

import java.util.ArrayList;
import java.util.HashMap;

import Interfaces.Situação;
import Models.Pessoa;
import Models.Situação2;
import Models.Situação3;
import Models.Situação4;
import Models.Situação5;

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
			if(pessoa.getSituacao().equals("Não habitilidada para vacina.")) {
				if (avaliaPrimeiraDose(pessoa)) {
					avancaSituacao(pessoa);
				}
			}
			if(pessoa.getSituacao().equals("Tomou a 1° dose.")) {
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
		Situação situacaoAtual;
		if (pessoa.getSituacao().equals("Não habitilidada para vacina.")) {
			situacaoAtual = new Situação2();
		} else if (pessoa.getSituacao().equals("Habilitada para tomar a 1° dose")) {
			situacaoAtual = new Situação3();
		} else if (pessoa.getSituacao().equals("Tomou a 1° dose.")) {
			situacaoAtual = new Situação4();
		} else {
			situacaoAtual = new Situação5();
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
		if(pessoa.getSituacao().equals("Tomou a 1° dose.") && (this.diasPassados - pessoa.getDiaVacinado() >= 20)) {
			return true;
		}
		return false;
	}

}
