package Modelo;

public class Kontsultak {

	static final String selectProduktuKantitatea = "SELECT count(nombre) from producto where Tipo = 'Comida' or Tipo = 'Bebida'";

	static final String selectPlaterKantitatea = "SELECT count(nombre) from producto where Tipo = 'Plato'";

	static final String selectProduktuak = "SELECT * from producto where Tipo = 'Comida' or Tipo = 'Bebida'";

	static final String selectPlaterrak = "SELECT * from producto where Tipo = 'Plato'";

	static final String selectProductuakEtaPlaterrak = "select * from producto";

	// ______________________________________________________________________________________________________________________________________________________________________________________________________
	//______________________________________________________________________________________________________________________________________________________________________________________________________

	static final String selectDNI = "SELECT dni from usuario";

	static final String selectNIFBakarrik = "SELECT nif from local";

	static final String selectNifLocal = "SELECT nif from local where NIF = ";

	static final String selectErabiltzailea = "SELECT DNI,contrasenya FROM usuario where dni = " ;

	static final String selectNIF = "SELECT NIF FROM usuario where dni = ";

	static final String selectTipoLokala = "SELECT Tipo from local where NIF = ";

	static final String selectLokalarenIzena = "SELECT nombre FROM local where NIF = ";

	static final String selectLokalarenNif = "SELECT Tipo from local where NIF = ";

	static final String selectLokalaMota = selectLokalarenNif + "(SELECT NIF from usuario where DNI = ";

	static final String selectMaxNumTrans= "SELECT max(NumTrans) from operaciones";

	static final String selectIzenaFabrikantea = "SELECT Nombre from fabricante where Nombre = ";

	static final String selectFabrikantea = selectIzenaFabrikantea + "(SELECT N_Fabricante from producto where Nombre = ";

	static final String selectStockNomProducto = "SELECT stock from vende where NomProducto =";

	static final String selectPlaterraMota = "SELECT nombre from plato where TipoDePlato = ";

	static final String selectPlaterMotak = "SELECT distinct TipoDePlato from plato";

	static final String selectPlaterKodea = "SELECT Cod_Plato FROM plato where Nombre = "; 

	static final String selectNifFaktura = "SELECT nif from niffactura where nif = ";

	static final String selectTiene = "select NomProducto,NumTrans from tiene where NomProducto = ";

	static final String selectIncluye = "select * from incluye where Num_Trans =";

	static final String selectCountCodPlato = "select count(Cod_Plato) from incluye where Num_Trans =";

	static final String selectCodPlato = "select Cod_Plato from incluye where Num_Trans = ";

	static final String selectOfrece = "select * from ofrece where NIFLocal = ";

	static final String selectStockNifLocal = "SELECT stock from vende where NIFLocal = (";

	static final String selectNifOperaciones = "select NIF from operaciones where NumTrans =";

	static final String selectMaxNumTransKomanda = "select max(NumTrans) from comanda";

	static final String selectOperazioMota = "select TipoOperacion from operaciones where NumTrans = ("+selectMaxNumTrans+")";
	
	static final String selectProduktuMota = "select Tipo from producto where Nombre =";

	// *****************************************************************************************************************************************************************************************************

	static final String insertDatuak = "INSERT INTO usuario VALUES";

	static final String insertOperaciones = "INSERT INTO operaciones VALUES";

	static final String insertNifFaktura = "INSERT INTO niffactura VALUES";

	static final String insertFactura = "INSERT INTO factura VALUES";

	static final String insertEskaera = "INSERT INTO pedidos VALUES";

	static final String insertTiene = "INSERT INTO tiene VALUES";

	static final String insertHornikuntza = "INSERT INTO aprovisionamiento VALUES";

	static final String insertKomanda = "INSERT INTO comanda VALUES";

	static final String insertIncluye = "INSERT INTO incluye VALUES";

	static final String insertOfrece = "INSERT INTO ofrece VALUES";

	static final String insertNumTransOperaciones = "insert into operaciones (NumTrans) values (";

	// *****************************************************************************************************************************************************************************************************

	static final String updateOperaciones = "update operaciones set Fecha =";

	static final String updateTiene = "update tiene set N_Unidades =";

	static final String updateIncluye = "update incluye set Num_Platos ="; 

	// *****************************************************************************************************************************************************************************************************

	static final String delete = "delete from operaciones where NumTrans = (select max(NumTrans) from comanda)";

	// *****************************************************************************************************************************************************************************************************

	static final String selectTop5 = "select * from Top5";

	static final String selectEgunero = "select * from operatividadDiaria";

	static final String selectAstero = "select * from operatividadSemanal";
}
