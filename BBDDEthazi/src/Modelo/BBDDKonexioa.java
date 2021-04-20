package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class BBDDKonexioa {
	
	private static final String CONTROLADOR = "com.mysql.jdbc.Driver";
	private static final String USUARIO = "root";
	private static final String URL = "jdbc:mysql://localhost:33060/ethazi3";
	private static final String CLAVE = "elorrieta";

	public static Connection getConexion() {

		Connection konekzioa = null;

		try {
			Class.forName(CONTROLADOR);
			konekzioa = DriverManager.getConnection(URL, USUARIO, CLAVE);		
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ez zaude datu basera konektatuta", "ERROR", JOptionPane.ERROR_MESSAGE);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Ez zaude datu basera konektatuta", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return konekzioa;
	}
	
	public static void main(String[] args) {
		getConexion();
	}
}
