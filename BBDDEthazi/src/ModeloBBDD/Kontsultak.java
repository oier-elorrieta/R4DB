package ModeloBBDD;

public class Kontsultak {

	public static final String selectProduktuKantitatea = "SELECT count(nombre) from producto where Tipo = 'Comida' or Tipo = 'Bebida'";

	public static final String selectPlaterKantitatea = "SELECT count(nombre) from producto where Tipo = 'Plato'";

	public static final String selectProduktuak = "SELECT * from producto where Tipo = 'Comida' or Tipo = 'Bebida'";

	public static final String selectPlaterrak = "SELECT * from producto where Tipo = 'Plato'";

	public static final String selectProductuakEtaPlaterrak = "select * from producto";

	// ______________________________________________________________________________________________________________________________________________________________________________________________________
	//______________________________________________________________________________________________________________________________________________________________________________________________________

	public static final String selectDNI = "SELECT dni from usuario";

	public static final String selectNIFBakarrik = "SELECT nif from local";

	public static final String selectNifLocal = "SELECT nif from local where NIF = ";

	public static final String selectErabiltzailea = "SELECT DNI,contrasenya FROM usuario where dni = " ;

	public static final String selectNIF = "SELECT NIF FROM usuario where dni = ";

	public static final String selectTipoLokala = "SELECT Tipo from local where NIF = ";

	public static final String selectLokalarenIzena = "SELECT nombre FROM local where NIF = ";

	public static final String selectLokalarenNif = "SELECT Tipo from local where NIF = ";

	public static final String selectLokalaMota = selectLokalarenNif + "(SELECT NIF from usuario where DNI = ";

	public static final String selectMaxNumTrans= "SELECT max(NumTrans) from operaciones";

	public static final String selectIzenaFabrikantea = "SELECT Nombre from fabricante where Nombre = ";

	public static final String selectFabrikantea = selectIzenaFabrikantea + "(SELECT N_Fabricante from producto where Nombre = ";

	public static final String selectStockNomProducto = "SELECT stock from vende where NomProducto =";

	public static final String selectPlaterraMota = "SELECT nombre from plato where TipoDePlato = ";

	public static final String selectPlaterMotak = "SELECT distinct TipoDePlato from plato";

	public static final String selectPlaterKodea = "SELECT Cod_Plato FROM plato where Nombre = "; 

	public static final String selectNifFaktura = "SELECT nif from niffactura where nif = ";

	public static final String selectTiene = "select NomProducto,NumTrans from tiene where NomProducto = ";

	public static final String selectIncluye = "select * from incluye where Num_Trans =";

	public static final String selectCountCodPlato = "select count(Cod_Plato) from incluye where Num_Trans =";

	public static final String selectCodPlato = "select Cod_Plato from incluye where Num_Trans = ";

	public static final String selectOfrece = "select * from ofrece where NIFLocal = ";

	public static final String selectStockNifLocal = "SELECT stock from vende where NIFLocal = (";

	public static final String selectNifOperaciones = "select NIF from operaciones where NumTrans =";

	public static final String selectMaxNumTransKomanda = "select max(NumTrans) from comanda";

	public static final String selectOperazioMota = "select TipoOperacion from operaciones where NumTrans = ("+selectMaxNumTrans+")";
	
	public static final String selectProduktuMota = "select Tipo from producto where Nombre =";

	// *****************************************************************************************************************************************************************************************************

	public static final String insertDatuak = "INSERT INTO usuario VALUES";

	public static final String insertOperaciones = "INSERT INTO operaciones VALUES";

	public static final String insertNifFaktura = "INSERT INTO niffactura VALUES";

	public static final String insertFactura = "INSERT INTO factura VALUES";

	public static final String insertEskaera = "INSERT INTO pedidos VALUES";

	public static final String insertTiene = "INSERT INTO tiene VALUES";

	public static final String insertHornikuntza = "INSERT INTO aprovisionamiento VALUES";

	public static final String insertKomanda = "INSERT INTO comanda VALUES";

	public static final String insertIncluye = "INSERT INTO incluye VALUES";

	public static final String insertOfrece = "INSERT INTO ofrece VALUES";

	public static final String insertNumTransOperaciones = "insert into operaciones (NumTrans , TipoOperacion) VALUES";

	// *****************************************************************************************************************************************************************************************************

	public static final String updateOperaciones = "update operaciones set Total_Operaciones =";

	public static final String updateTiene = "update tiene set N_Unidades =";

	public static final String updateIncluye = "update incluye set Num_Platos ="; 
	
	public static final String updateStock = "update vende set Stock =";

	// *****************************************************************************************************************************************************************************************************

	public static final String delete = "delete from operaciones where NumTrans = (select max(NumTrans) from comanda)";

}
