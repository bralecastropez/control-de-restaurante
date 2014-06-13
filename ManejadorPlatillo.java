package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.beans.Platillo;
import org.brandon.db.Conexion;

public class ManejadorPlatillo{
	private Conexion cnx;
	private ObservableList<Platillo> listaDePlatillos;

	public ManejadorPlatillo(Conexion cnx){
		this.cnx=cnx;
		this.listaDePlatillos=FXCollections.observableArrayList();
	}
	public void actualizarListaDePlatillos(){
		listaDePlatillos.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idPlatillo, precio, nombre FROM Platillo");
		try{
			while(resultado.next()){
				Platillo platillo = new Platillo(resultado.getInt("idPlatillo"), resultado.getInt("precio"), resultado.getString("nombre"));
				listaDePlatillos.add(platillo);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Platillo> getListaDePlatillos(){
		actualizarListaDePlatillos();
		return listaDePlatillos;
	}
	public void eliminarPlatillo(Platillo platillo){
		cnx.ejecutarSentencia("DELETE FROM Platillo WHERE idPlatillo="+platillo.getIdPlatillo());
		actualizarListaDePlatillos();
	}
	public void agregarPlatillo(Platillo platillo){
		cnx.ejecutarSentencia("INSERT INTO Platillo(nombre, precio) VALUES ('"+platillo.getNombre()+"',"+platillo.getPrecio()+")");
		actualizarListaDePlatillos();

	}
	public void modificarPlatillo(Platillo platillo){
		cnx.ejecutarSentencia("UPDATE Platillo SET nombre='"+platillo.getNombre()+"', precio="+platillo.getPrecio()+" WHERE idPlatillo="+platillo.getIdPlatillo());
		actualizarListaDePlatillos();
	}
}