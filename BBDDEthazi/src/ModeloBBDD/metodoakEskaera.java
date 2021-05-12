package ModeloBBDD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class metodoakEskaera {
	
	public static void sartuEskaera(int transferentziaZenbakia, String helbidea) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.insertEskaera + "('" + transferentziaZenbakia + "', '" + helbidea + "')");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1); 
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du sartu eskaera", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

}
