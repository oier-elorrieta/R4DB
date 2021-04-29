package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class metodoakHornikuntza {
	
	public static String jasoHornikuntzarakoFabrikantea(String produktua) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String izenaFabrikantea = null;
		String query1 = (Kontsultak.selectFabrikantea + "'" + produktua + "')");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				izenaFabrikantea = re.getString("Nombre");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi produktu fabrikantea", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return izenaFabrikantea;
	}

	public static void sartuHornikuntza(int transferentziaZenbakia, String produktua, int produktu_kantitatea, double totala, char operazioMota) throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String izenaFabrikantea = jasoHornikuntzarakoFabrikantea(produktua);
		String query1 = (Kontsultak.insertHornikuntza + "(" + transferentziaZenbakia + ",'" + izenaFabrikantea + "')");
		String query2 = (Kontsultak.insertTiene+"('" + produktua + "'," + transferentziaZenbakia + "," + produktu_kantitatea + "," + totala + ", '"+ operazioMota+"')");
		try { 
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1);
			Statement s1;
			s1 = konekzioa.createStatement();
			s1.executeUpdate(query2);
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du hornikuntza egin", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static void stockGehitu(String produktua, int produktu_kantitatea, String NIF) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.updateStock + "Stock + "+produktu_kantitatea+" where NIFLocal = '"+NIF+"' and NomProducto = '"+produktua+"'");
		try { 
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1); 
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du stocka gehitu", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}