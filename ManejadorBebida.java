package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.db.Conexion;
import org.brandon.beans.Bebida;

public class ManejadorBebida{
	private Conexion cnx;
	private ObservableList<Bebida> listaDeBebidas;

	public ManejadorBebida(Conexion cnx){
		this.cnx=cnx;
		this.listaDeBebidas=FXCollections.observableArrayList();
	}
	public void actualizarListaDeBebidas(){
		listaDeBebidas.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idBebida, precio, nombre FROM Bebida");
		try{
			while(resultado.next()){
				Bebida bebida = new Bebida(resultado.getInt("idBebida"), resultado.getInt("precio"), resultado.getString("nombre"));
				listaDeBebidas.add(bebida);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Bebida> getListaDeBebidas(){
		actualizarListaDeBebidas();
		return listaDeBebidas;
	}
	public void eliminarBebida(Bebida bebida){
		cnx.ejecutarSentencia("DELETE FROM Ingrediente WHERE idBebida="+bebida.getIdBebida());
		actualizarListaDeBebidas();
	}
	public void agregarBebida(Bebida bebida){
		cnx.ejecutarSentencia("INSERT INTO Ingrediente(nombre, precio) VALUES ('"+bebida.getNombre()+"',"+bebida.getPrecio()+")");
		actualizarListaDeBebidas();

	}
	public void modificarBebida(Bebida bebida){
		cnx.ejecutarSentencia("UPDATE Ingrediente SET nombre='"+bebida.getNombre()+"', precio="+bebida.getPrecio()+" WHERE idBebida="+bebida.getIdBebida());
		actualizarListaDeBebidas();
	}
}
