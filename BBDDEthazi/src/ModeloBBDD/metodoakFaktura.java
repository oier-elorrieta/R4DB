package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class metodoakFaktura {

	public static boolean begiratuFakturanNIF(String NIF) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.selectNifFaktura+"'" + NIF + "'");
		boolean nifDago = false;
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			while (re.next()) {
				NIF = re.getString("NIF");
				nifDago = true;
				break;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi fakturaren NIF", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return nifDago;
	}

	public static void insertNifFaktura(String NIF, String izena_hartzaile, String abizena_hartzaile) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query2 = (Kontsultak.insertNifFaktura + "('" + NIF + "', '" + izena_hartzaile + "', '" + abizena_hartzaile + "')");
		try {
			Statement st;
			st = konekzioa.createStatement();
			st.executeUpdate(query2);
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du sartu NIF faktura", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void sartuFaktura(int transferentziaZenbakia, String NIF, String izena_hartzaile, String abizena_hartzaile) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		boolean nifDago = begiratuFakturanNIF(NIF);
		if (nifDago == false) {
			insertNifFaktura(NIF, izena_hartzaile, abizena_hartzaile);
		}
		String query1 = (Kontsultak.insertFactura + "('" + transferentziaZenbakia + "','" + NIF + "');");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1); 
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du sartu faktura", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}