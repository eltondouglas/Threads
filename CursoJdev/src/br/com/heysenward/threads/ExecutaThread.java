package br.com.heysenward.threads;

import javax.swing.JFrame;

public class ExecutaThread {
	public static void main(String[] args) {
		ThreadTela threadTela = new ThreadTela();
		threadTela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
