package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class metodoakFuntzioakDeitu {

	public static double dirutotala() throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();		
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
	
	public static float funtzioprob(String prodA, String prodB) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		float emaitza = 0;
		String query1 = ("select proabilitatea('"+prodA+"','"+prodB+"')");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				emaitza = re.getFloat("proabilitatea('"+prodA+"','"+prodB+"')");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi plater motak", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return emaitza;
	}
}