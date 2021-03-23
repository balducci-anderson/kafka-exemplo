package com.devbalducci.view;

import java.util.Scanner;
import java.util.UUID;

import com.devbalducci.kafka.Producer;
import com.devbalducci.modelo.Ingresso;

public class NovoIngresso {

	public static void main(String[] args) {
	
		String opcao = "opcao";
		int quantidade = 0;
		
		Ingresso ingresso = new Ingresso();
		
		while(opcao.contains("opcao")) {
			System.out.println("------------------");
			System.out.println("Compra de Ingresso");
			System.out.println("1.Filme");
			System.out.println("2.Show");
			System.out.println("3.Teatro");
			System.out.print("Opcao: ");
			opcao = new Scanner(System.in).next();
			
			if(opcao.contains("1"))
				ingresso.setEvento("Filme");
			else if(opcao.contains("2"))
				ingresso.setEvento("Show");
			else if(opcao.contains("3"))
				ingresso.setEvento("Teatro");
			else
				opcao = "opcao";
		}
		
		opcao = "opcao";
		
		while(opcao.contains("opcao")) {
			System.out.println("");
			System.out.print("Quantidade: ");
			opcao = new Scanner(System.in).next();
			try {
				quantidade = Integer.parseInt(opcao);
			} catch(Exception ex) {
				opcao = "opcao";
			}
		}
		
		ingresso.setQuantidade(quantidade);
		ingresso.setId(UUID.randomUUID());
		
		Producer<Ingresso> producer = new Producer<>("NOVO_INGRESSO");
		
		try {
			producer.run(ingresso);
		} catch (Exception e) {
			System.out.println("Erro ao enviar novo ingresso");
		}
	}
}
