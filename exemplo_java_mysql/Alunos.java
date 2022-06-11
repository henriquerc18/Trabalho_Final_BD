//
//  Codigo extraido do curso JavaStarter 
//
//  http://www.t2ti.com/curso/video/java/basico/java_starter.php
//
//  Retirado do modulo 10 - paginas 14 e 15 -  prof. Daniel Muller - junho/2012
//
//  

package Pacote;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;
import javax.swing.*;

public class Alunos implements ActionListener {
	// criamos e instaciamos um JFrame chamado 'janela'
	JFrame janela = new JFrame();
	// criamos um JPanel chamado painel
	JPanel painel = new JPanel();
	// criamos um JLabel chamado rotulo e com o texto "Seu nome: "
	JLabel rotulo = new JLabel("Matricula do Aluno: ");
	JLabel rotulo2 = new JLabel("Nota 1: ");
	JLabel rotulo3 = new JLabel("Nota 2: ");
	JLabel rotulo4 = new JLabel("Nota 3: ");
	// criamos um JTextField chamado texto com o tamanho 5
	JTextField texto = new JTextField(20);
	JTextField texto2 = new JTextField(20);
	JTextField texto3 = new JTextField(20);
	JTextField texto4 = new JTextField(20);
	// criamos e instanciamos um JButton chamado 'botao' e com o texto "OK"
	JButton botao = new JButton("OK");

	Alunos() {
		// definimos o titulo da janela
		janela.setTitle("Acesso ao Banco de Dados");
		// definimos a largura e a altura da janela
		janela.setSize(300, 700);
		// define a posicao da janela na tela
		janela.setLocation(50, 20);
		// define que ao fechar a janela, encerre a aplicacao

		janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		painel.setLayout(new GridLayout(16, 1, 5, 5));
		// definimos o layout do painel
		painel.setLayout(new FlowLayout(FlowLayout.CENTER, 30, 20));
		// adicionamos o rotulo ao painel
		painel.add(rotulo);
		painel.add(texto);
		painel.add(rotulo2);
		painel.add(texto2);
		painel.add(rotulo3);
		painel.add(texto3);
		painel.add(rotulo4);
		painel.add(texto4);
		// adicionamos o texto ao painel

		// adicionamos o botao ao painel
		painel.add(botao);
		// adicionamos o painel a janela
		janela.add(painel);
		// mostramos a janela
		janela.setVisible(true);
		// registramos o botao ao Listener
		botao.addActionListener(this);
	}

	// construimos o metodo exigido pela interface
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == botao) {

			JOptionPane.showMessageDialog(null, texto.getText());
			// variaveis de resultado de acoes
			Statement ps;
			ResultSet result;

			// abre conexao
			Conexao2 conn = new Conexao2();
			try {
				ps = conn.getConnection().createStatement();
				// insercao de dados

				// 1 - Ler para cada aluno o seu codigo e as notas obtidas;
				String sqlInsert = "INSERT INTO Aluno(Matricula,Nota1,Nota2,Nota3)VALUES('"
						+ texto.getText()
						+ "','"
						+ texto2.getText()
						+ "','"
						+ texto3.getText() + "','" + texto4.getText() + "')";
				System.out.print(sqlInsert);
				ps.executeUpdate(sqlInsert);

				// consulta de dados

				// 2 - Calcular para cada aluno o escore padrao relativo a cada
				// prova;
				EscorePadrao EP = new EscorePadrao();
				EP.EscorePad(Float.parseFloat(texto2.getText()),
						Float.parseFloat(texto3.getText()),
						Float.parseFloat(texto4.getText()));
				System.out.println("\nEscore: " + EP.getEscore() + "\n");

				String query = "select * from Aluno";
				result = ps.executeQuery(query);
				String CodAluno;
				while (result.next()) {
					CodAluno = result.getString("Matricula");
					System.out.print(CodAluno + "\n");
				}

				// fecha conexao
				result.close();
				conn.closeConnection();
				janela.dispose();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
	}

}