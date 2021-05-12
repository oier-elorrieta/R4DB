package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class metodoakKonprobaketak {

	public static boolean begiratuNAN(String NAN) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectDNI+ " where DNI = '"+NAN+"'");
		boolean badago = false;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if(re.next()) {
				badago = true;
			}
		} catch (SQLException e) {
			e.printStackTrace(); 
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi NAN", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return badago;
	}

	// *****************************************************************************************************************************************************************************************************

	public static boolean begiratuNIF(String nif) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectNifLocal+"'"+nif+"'");
		boolean ondoDago = false;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if(re.next()) {
				ondoDago = true;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi NIF", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return ondoDago;
	}

	// *****************************************************************************************************************************************************************************************************

	public static int begiratuStock(String produktua, String nif) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectStockNomProducto + " '" + produktua + "' and nifLocal = '" + nif + "' ");
		int produktuKantitatea = 0;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				produktuKantitatea = re.getInt("Stock");
			}
		} catch (SQLException e) { 
			e.printStackTrace(); 
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi stocka", "ERROR", JOptionPane.ERROR_MESSAGE);
		} 
		return produktuKantitatea;
	}

	// *****************************************************************************************************************************************************************************************************

	public static String konprobatuErabiltzailea(String erabiltzailea, String pasahitza) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectErabiltzailea + "'" + erabiltzailea + "'");
		String erroreaLogeatzean = null;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				if (re.getString("DNI").equalsIgnoreCase(erabiltzailea)
						&& re.getString("Contrasenya").equalsIgnoreCase(pasahitza)) {
					erroreaLogeatzean = "EZ";
				} else if (re.getString("DNI").equalsIgnoreCase(erabiltzailea)
						&& !re.getString("Contrasenya").equalsIgnoreCase(pasahitza)) {
					JOptionPane.showMessageDialog(null, "Pasahitza ez da egokia", "ERROR", JOptionPane.ERROR_MESSAGE);
					erroreaLogeatzean = "Bai, pasahitza txarto";
				}
			} else {
				JOptionPane.showMessageDialog(null, "Ez zaude logeatuta", "ERROR", JOptionPane.ERROR_MESSAGE);
				erroreaLogeatzean = "Bai, erabiltzailea txarto";
			} 
		} catch (SQLException e) {  
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du komprobatu erabiltzailea", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return erroreaLogeatzean;
	}

	// *****************************************************************************************************************************************************************************************************

	public static String konprobatuNIF(String erabiltzailea) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectNIF + "'" + erabiltzailea + "'");
		String NIF = null;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				NIF = re.getString("NIF");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du komprobatu NIF", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return NIF;
	}

	// *****************************************************************************************************************************************************************************************************

	public static String konprobatuLokala(String erabiltzailea) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectLokalaMota + "'" + erabiltzailea + "')");
		String Tipo = null;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				Tipo = re.getString("Tipo");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi lokala", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return Tipo;
	}

	// *****************************************************************************************************************************************************************************************************

	public static String konprobatuLokalarenIzena(String NIF) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectLokalarenIzena + "'" + NIF + "'");
		String LokalarenIzena = null;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				LokalarenIzena = re.getString("nombre");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi lokalaren izena", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return LokalarenIzena;
	}
	
	// *****************************************************************************************************************************************************************************************************

	public static boolean konprobatuErabiltzaileAtributuenLuzeera(String nan, String izena, String abizena, String pasahitza, String nif) {
		boolean luzeeraEgokia = true;
		if (nan.length() == 0 || izena.length() == 0 || abizena.length() == 0 || pasahitza.length() == 0 || nif.length() == 0) {
			JOptionPane.showMessageDialog(null, "Leku guztiak beharrezkoak dira", "ERROR", JOptionPane.ERROR_MESSAGE);
			luzeeraEgokia = false;
		}else if (nan.length() != 9) {
			JOptionPane.showMessageDialog(null, "NAN luzeera ez da egokia", "ERROR", JOptionPane.ERROR_MESSAGE);
			luzeeraEgokia = false;
		}else if (izena.length() > 20) {
			JOptionPane.showMessageDialog(null, "Izenaren luzeera ez da egokia", "ERROR", JOptionPane.ERROR_MESSAGE);
			luzeeraEgokia = false;
		}else if (abizena.length() > 40) {
			JOptionPane.showMessageDialog(null, "Abizenaren luzeera ez da egokia", "ERROR", JOptionPane.ERROR_MESSAGE);
			luzeeraEgokia = false;
		}else if (pasahitza.length() > 20) {
			JOptionPane.showMessageDialog(null, "Pasahitzaren luzeera ez da egokia", "ERROR", JOptionPane.ERROR_MESSAGE);
			luzeeraEgokia = false;
		}else if (nif.length() != 9) {
			JOptionPane.showMessageDialog(null, "NIF-aren luzeera ez da egokia", "ERROR", JOptionPane.ERROR_MESSAGE);
			luzeeraEgokia = false;
		}
		return luzeeraEgokia;
	}
}