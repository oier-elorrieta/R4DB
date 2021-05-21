package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 
import java.util.Calendar;

import javax.swing.JOptionPane;

public class metodoaProbabilitatea {

	// *****************************************************************************************************************************************************************************************************
	
	public static void ProbGuztiasartu(String prodA, String prodB, float emaitza, String fecha) throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = "INSERT INTO HistoricoEstablecimientoSemanal VALUES ('"+emaitza+"','"+fecha+"','"+prodA+"','"+prodB+"')";
		try {
			Statement s1;
			s1 = konekzioa.createStatement();
			s1.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// *****************************************************************************************************************************************************************************************************
	
	public static void ProbLocalasartu(String prodA, String prodB, float emaitza, String nif, String fecha) throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = "INSERT INTO HistoricoEstablecimientoSemanalTd VALUES ('"+emaitza+"','"+fecha+"','"+prodA+"','"+prodB+"','"+nif+"')";
		try {
			Statement s1;
			s1 = konekzioa.createStatement();
			s1.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	// *****************************************************************************************************************************************************************************************************

	public static void Fechasartu() throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = "INSERT INTO fecha VALUES ('"+stringfecha()+"')";
		try {
			Statement s1;
			s1 = konekzioa.createStatement();
			s1.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// *****************************************************************************************************************************************************************************************************
	
	public static String stringfecha() {
		Calendar cal= Calendar.getInstance();
		int anio= cal.get(Calendar.YEAR);
		int mes=cal.get(Calendar.MONTH);
		int dia=cal.get(Calendar.DAY_OF_MONTH);
		int hora=cal.get(Calendar.HOUR);
		int min=cal.get(Calendar.MINUTE);
		int seg=cal.get(Calendar.SECOND);
		String fecha=anio+"-"+mes+"-"+dia+" "+hora+":"+min+":"+seg;
		return fecha;
	}

	// *****************************************************************************************************************************************************************************************************
	
	public static boolean konprobatufecha() {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = ("select Fecha from fecha where Fecha= '"+stringfecha()+"'");
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
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return badago;
	}
}