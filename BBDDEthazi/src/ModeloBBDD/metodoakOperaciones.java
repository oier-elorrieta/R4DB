package ModeloBBDD;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class metodoakOperaciones {
	
	public static void sartuOperaciones(int transferentziaZenbakia, double totala, String NIF, char operazioMota) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.insertOperaciones + "('" + transferentziaZenbakia + "' ,'" + totala + "','" + NIF + "', '"+ operazioMota +"')");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du sartu ticketa", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	};
}
