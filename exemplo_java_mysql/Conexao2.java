//
//  Codigo extraido do link
// https://sites.google.com/site/carolinofr/codigos-em-java-3/exemplo-de-conexao-ao-mysql-utilizando-o-eclipse
//
//  Foram adicionados comandos para insercao no banco de dados por prof. Daniel Muller - junho/2012
//
//  

package Pacote;

import java.sql.*;

public class Conexao2 {

	public java.sql.Connection conn = null;

	private String url = null;
	private String jdbcDriver = null;
	private String dataBaseName = null;
	private String dataBasePrefix = null;
	private String dabaBasePort = null;
	private String hostName = null;
	private String userName = null;
	private String password = null;

	public Conexao2() {
		jdbcDriver = "com.mysql.jdbc.Driver"; 
		hostName = "localhost";
		userName = "root";
		password = "alunos";
		dataBaseName = "aula"; 
		dataBasePrefix = "jdbc:mysql://";
		dabaBasePort = "3306";
		url = dataBasePrefix + hostName + ":" + dabaBasePort + "/"
				+ dataBaseName + "?useUnicode=true&characterEncoding=utf8";
	}

	public java.sql.Connection getConnection() {
		try {
			if (conn == null) {
				Class.forName(jdbcDriver);
				conn = DriverManager.getConnection(url, userName, password);
			} else if (conn.isClosed()) {
				conn = null;
				return getConnection();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}


	public void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}}