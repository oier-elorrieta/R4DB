package ModeloBBDD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
 
public class metodoakErabiltzaile {	

	public static void sartuDatuak(String izena, String abizena, String pasahitza, String nan, String nif) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.insertDatuak + "('" + izena + "', '" + abizena + "','" + pasahitza + "','" + nan
				+ "', '" + nif + "')");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du sartu erabiltzailea", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
