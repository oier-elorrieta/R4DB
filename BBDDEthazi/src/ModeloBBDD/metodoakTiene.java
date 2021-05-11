package ModeloBBDD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

import Modelo.Hornikuntza;
import Modelo.Karritoa;  

public class metodoakTiene {

	public static void sartuTiene(ArrayList<Karritoa> karroa, int numTrans, String erabiltzaile) throws ClassNotFoundException, SQLException {
		for (int i = 0; i < karroa.size(); i++) {
			String elikagaia = karroa.get(i).getElikagaia();
			int kopurua = karroa.get(i).getKopuru();
			double prezioa = karroa.get(i).getBalioa(); 
			String operazioMota = jasoOperazioMota();
			if (begiratuTiene(elikagaia, numTrans) == false) {
				insertTiene(elikagaia, kopurua, prezioa, operazioMota);
				updateOperaciones();
				gehituVende(elikagaia, erabiltzaile);
			} else {
				updateTiene(elikagaia, kopurua, prezioa, operazioMota);
				updateOperaciones();
				gehituVende(elikagaia, erabiltzaile);
			}
			
		}
	}

	public static void insertTiene(String elikagaia, int kopurua, double prezioa, String operazioMota) throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();  
		String query1 = (Kontsultak.insertTiene + "('" + elikagaia + "', " + (ModeloBBDD.metodoJasoTransakzioZbk.jasoTransakzioZbk() - 1) + ", " + kopurua
				+ ", " + prezioa + ", '"+ operazioMota +"')");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du gehitu tiene taulari", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}

	public static boolean begiratuTiene(String Elikagaia, int numTrans) {
		boolean egia = false;
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.selectTiene+"'" + Elikagaia
				+ "' and NumTrans = '" + (numTrans - 1) + "'");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			while (re.next()) {
				egia = true;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi tiene taula", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return egia;
	}

	public static void updateTiene(String elikagaia, int kopurua, double prezioa, String operazioMota) throws ClassNotFoundException, SQLException { 
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.updateTiene+"N_Unidades + " + kopurua + ", Precio = Precio + " + prezioa
				+ " where NomProducto = '" + elikagaia + "' and NumTrans = "+(ModeloBBDD.metodoJasoTransakzioZbk.jasoTransakzioZbk()-1)+"");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du gehitu produktu berdina", "ERROR", JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void gehituVende(String elikagaia, String erabiltzailea) throws ClassNotFoundException, SQLException {
		if(!begiratuProduktuMota(elikagaia).equals("Plato")) {
			if (metodoakKonprobaketak.begiratuStock(elikagaia, metodoakKonprobaketak.konprobatuNIF(erabiltzailea)) < 5) {
				Hornikuntza h1 = new Hornikuntza(ModeloBBDD.metodoJasoTransakzioZbk.jasoTransakzioZbk(), produktuDirua(elikagaia), elikagaia, metodoakKonprobaketak.konprobatuNIF(erabiltzailea), 50);
				ModeloBBDD.metodoakOperaciones.sartuOperaciones(h1.getTransferentziaZenbakia(), h1.getTotala(), h1.getNIF(), h1.getOperazioMota());
				ModeloBBDD.metodoakHornikuntza.sartuHornikuntza(h1.getTransferentziaZenbakia(), h1.getProduktua(), h1.getProduktu_kantitatea(), h1.getTotala(), h1.getOperazioMota());
				ModeloBBDD.metodoakHornikuntza.stockGehitu(h1.getProduktua(), h1.getProduktu_kantitatea(), h1.getNIF());
			}
		}
	}
	
	public static double produktuDirua(String elikagaia) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		double totala = 0;
		String query1 = (Kontsultak.selectProduktuSaltzekoPrezioa +"'" + elikagaia + "'");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if(re.next()) {
				totala = re.getDouble("Precio_Compra")*50;
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi operazio mota", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return totala;
	}

	public static String jasoOperazioMota () {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String operazioMota = null;
		String query1 = (Kontsultak.selectOperazioMota);
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if(re.next()) {
				operazioMota = re.getString("TipoOperacion");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du ikusi operazio mota", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return operazioMota;
	}

	public static String begiratuProduktuMota (String produktua) {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String produktuMota = null;
		String query1 = (Kontsultak.selectProduktuMota + "'"+produktua+"'");
		try {
			ResultSet re;
			PreparedStatement p;
			p = konekzioa.prepareStatement(query1);
			re = p.executeQuery();
			if(re.next()) {
				produktuMota = re.getString("Tipo");
			}
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du produktu mota ikusi", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
		return produktuMota;
	}
	
	public static void updateOperaciones() throws ClassNotFoundException, SQLException {
		Connection konekzioa = BBDDKonexioa.getConexion();
		String query1 = (Kontsultak.updateOperaciones + dirutotala() + " where NumTrans = (select max(NumTrans) from tiene)");
		try {
			Statement s;
			s = konekzioa.createStatement();
			s.executeUpdate(query1);
		} catch (SQLException e) { 
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Datu baseak ezin du kalkulatu operazio totala", "ERROR", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	public static double dirutotala() {
		Connection konekzioa = BBDDKonexioa.getConexion();
		double diruTotala = 0;
		String query1 = (Kontsultak.function);
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