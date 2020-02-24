package br.com.heysenward.threads;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ThreadTela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private GridBagLayout grbLayout = new GridBagLayout();
	private JPanel painel = new JPanel(grbLayout);

	private JLabel label1 = new JLabel("Thread 1");
	private JLabel label2 = new JLabel("Thread 2");

	private JTextField mostraTempo = new JTextField();
	private JTextField mostraTempo2 = new JTextField();

	private JButton buttonStart = new JButton("Start");
	private JButton buttonStop = new JButton("Stop");

	private Thread thread1;
	private Thread thread2;

	@SuppressWarnings("deprecation")
	public ThreadTela() {
		setTitle("Multiplas threads");
		setIconImage(new ImageIcon("/home/elton/Downloads/Thread.png").getImage());
		setSize(new Dimension(230, 230));
		setLocationRelativeTo(null);
		setResizable(false);

		// Organiza os itens na tela
		GridBagConstraints gridConstrains = new GridBagConstraints();

		// O espaçamento horizontal é setado como 2 para as labels e textFields
		gridConstrains.gridwidth = 2;
		gridConstrains.insets = new Insets(5, 10, 5, 5);
		gridConstrains.anchor = GridBagConstraints.WEST;

		// Thread 1
		gridConstrains.gridx = 0;
		gridConstrains.gridy = 0;
		label1.setPreferredSize(new Dimension(200, 25));
		painel.add(label1, gridConstrains);

		// Mostra tempo1
		gridConstrains.gridy++;
		mostraTempo.setPreferredSize(new Dimension(200, 25));
		mostraTempo.setEditable(false);
		painel.add(mostraTempo, gridConstrains);

		// Thread 2
		gridConstrains.gridy++;
		label2.setPreferredSize(new Dimension(200, 25));
		painel.add(label2, gridConstrains);

		// Mostra tempo2
		gridConstrains.gridy++;
		mostraTempo2.setPreferredSize(new Dimension(200, 25));
		mostraTempo2.setEditable(false);
		painel.add(mostraTempo2, gridConstrains);

		// O espaçamento horizontal é setado como 1 para os botões
		gridConstrains.gridwidth = 1;

		// Button start
		gridConstrains.gridy++;
		buttonStart.setPreferredSize(new Dimension(92, 25));
		painel.add(buttonStart, gridConstrains);

		// Captura evento do buttonStart
		buttonStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread1 = new Thread(runnable1);
				thread2 = new Thread(runnable2);
				thread1.start();
				thread2.start();
				buttonStop.setEnabled(true);
				buttonStart.setEnabled(false);

			}
		});

		// Button stop
		gridConstrains.gridx++;
		buttonStop.setPreferredSize(new Dimension(92, 25));
		buttonStop.setEnabled(false);
		painel.add(buttonStop, gridConstrains);

		// Captura evento do buttonStop
		buttonStop.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				thread1.stop();
				thread2.stop();
				buttonStart.setEnabled(true);
				buttonStop.setEnabled(false);
			}
		});

		// Adiciona o painel a janela
		add(painel, BorderLayout.WEST);

		setVisible(true);// Torna a tela visivel

	}

	// Thread responsaável por atualizar o primeiro textField
	private Runnable runnable1 = new Runnable() {

		@Override
		public void run() {

			mostraTempo.setText(DateTimeFormatter.ofPattern("hh:mm.ss").format(LocalDateTime.now()));
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	};

	// Thread responsável por atualizar o segundo textField
	private Runnable runnable2 = new Runnable() {

		@Override
		public void run() {
			LocalDateTime time = LocalDateTime.now();
			while (true) {
				mostraTempo2.setText(DateTimeFormatter.ofPattern("hh:mm.ss").format(time.plusSeconds(1)));
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	};

}
