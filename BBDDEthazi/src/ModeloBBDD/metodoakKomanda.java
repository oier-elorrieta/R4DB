package ModeloBBDD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement; 
import javax.swing.JOptionPane; 

public class metodoakKomanda {
	
	public static void hasieratuOperaciones(int numTrans) throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.insertNumTransOperaciones + "(" + numTrans + ", 'C')");
		String query2 = (Kontsultak.insertKomanda + "(" + numTrans + ")");
		try {
			Statement s1;
			s1 = konekzioa.createStatement();
			s1.executeUpdate(query1);
			Statement s2;
			s2 = konekzioa.createStatement();
			s2.executeUpdate(query2);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du hasieratu operazioa", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void sartuKomanda(String NIF, double totala) throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.updateOperaciones + totala + " , NIF = '" + NIF
				+ "' where Numtrans = (" + Kontsultak.selectMaxNumTransKomanda + ")");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1); 
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du sartu komanda", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void ezabatuKomanda(int numTrans) throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.delete + numTrans);
		try {
			Statement s1;
			s1 = konekzioa.createStatement();
			s1.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ezabatu komanda", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}