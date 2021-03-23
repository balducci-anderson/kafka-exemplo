package com.devbalducci.modelo;

import java.util.UUID;

public class Ingresso {

	private UUID id;
	private String evento;
	private int quantidade;

	public void setId(UUID id) {
		this.id = id;
	}

	public void setEvento(String evento) {
		this.evento = evento;
	}

	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}

	public UUID getId() {
		return id;
	}

	public String getEvento() {
		return evento;
	}

	public int getQuantidade() {
		return quantidade;
	}
}
