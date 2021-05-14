package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class metodoJasoTransakzioZbk {
	
	public static int jasoTransakzioZbk() throws SQLException, ClassNotFoundException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.selectMaxNumTrans);
		int TransakzioZbk = 0;
		try { 
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if (re.next()) {
				if (re.getInt("max(NumTrans)") == 0) {
					TransakzioZbk = 1;
				} else {
					TransakzioZbk = re.getInt("max(NumTrans)");
					TransakzioZbk++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return TransakzioZbk;
	}
}