package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class metodoakPlaterZerrendak {

	// *****************************************************************************************************************************************************************************************************
	
	public static String[] platerMotak() {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.selectPlaterMotak);
		String platerMotak[] = new String[3];
		int i = 0;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			while (re.next()) {
				platerMotak[i] = re.getString("TipoDePlato");
				i++;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi plater motak", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return platerMotak;
	}

	// *****************************************************************************************************************************************************************************************************
	
	public static String[] platerMota(String platerMota, String tipoa) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.selectPlaterraMota + " '" + platerMota + "' and TipoPosicion = '" + tipoa + "'");
		String platerMotak[] = new String[zenbatPlaterMotaBakoitzeko(platerMota)];
		int i = 0;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			while (re.next()) {
				platerMotak[i] = re.getString("Nombre");
				i++;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi plater mota", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return platerMotak;
	}

	// *****************************************************************************************************************************************************************************************************
	
	public static String[] platerMotaArabera(String platerMota) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.selectPlaterraMota + "'" + platerMota + "'");
		String platerMotak[] = new String[zenbatPlaterMotaBakoitzeko(platerMota)];
		int i = 0;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			while (re.next()) {
				platerMotak[i] = re.getString("Nombre");
				i++;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du egin plater moten diferentzia", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return platerMotak;
	}

	// *****************************************************************************************************************************************************************************************************
	
	public static int zenbatPlaterMotaBakoitzeko(String platerMota) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = ("SELECT count('" + platerMota + "') from plato where TipoDePlato = '" + platerMota + "';");
		int i = 0;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				i = re.getInt("count('" + platerMota + "')");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi plater mota kantitatea", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return i;
	}

	// *****************************************************************************************************************************************************************************************************
	
	public static int jasoPlaterKodea(String platerra) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		int platerKodea = 0;
		String query1 = (Kontsultak.selectPlaterKodea + "'" + platerra + "'");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				platerKodea = re.getInt(1);
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi plater kodea", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return platerKodea;
	}
}
