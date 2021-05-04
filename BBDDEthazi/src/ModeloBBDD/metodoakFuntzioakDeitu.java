package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class metodoakFuntzioakDeitu {

	public static double dirutotala() {
		Connection konekzioa = BBDDKonexioa.getConexion();
		double diruTotala = 0;
		String query1 = ("select importeTotal()");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				diruTotala = re.getDouble("importeTotal()");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi plater motak", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return diruTotala;
	}
}