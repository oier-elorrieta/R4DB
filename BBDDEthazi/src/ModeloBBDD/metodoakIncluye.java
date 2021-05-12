package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class metodoakIncluye {

	public static void incluye(int platerKodea, int kantitatea, double prezioa, String nif)
			throws ClassNotFoundException, SQLException {
		int numTrans = ModeloBBDD.metodoJasoTransakzioZbk.jasoTransakzioZbk() - 1;
		if (begiratuIncluye(platerKodea, numTrans) == false) {
			sartuIncluye(platerKodea, kantitatea, prezioa, nif, numTrans);
		} else {
			updateIncluye(platerKodea, kantitatea, prezioa, numTrans);
		}
	}

	public static boolean begiratuIncluye(int platerKodea, int numTrans) throws ClassNotFoundException, SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		boolean sartutaDago = false;
		String query1 = (Kontsultak.selectIncluye + "'" + numTrans + "' and Cod_Plato = '" + platerKodea + "'");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				sartutaDago = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du begiratu tabla incluye", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return sartutaDago;
	}

	public static void updateIncluye(int platerKodea, int kantitatea, double prezioa, int numTrans)
			throws ClassNotFoundException, SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.updateIncluye + "Num_Platos + " + kantitatea + ", PrecioActual = PrecioActual + "
				+ prezioa + " where Cod_Plato = " + platerKodea + " and Num_Trans = "
				+ (ModeloBBDD.metodoJasoTransakzioZbk.jasoTransakzioZbk() - 1) + "");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du gehitu tabla incluyeri", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static void sartuIncluye(int platerKodea, int kantitatea, double prezioa, String nif, int numTrans) throws SQLException {
		Connection konekzioa = ConnectionPool.getInstance().getConnection();
		String query1 = (Kontsultak.insertIncluye + "('" + numTrans + "', '" + platerKodea + "', '" + kantitatea
				+ "', '" + prezioa + "')");
		try {
			Statement s1;
			s1 = konekzioa.createStatement();
			s1.executeUpdate(query1);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du sartu tabla incluyeri", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
}
