package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.beans.Ingrediente;
import org.brandon.db.Conexion;

public class ManejadorIngrediente{
	private Conexion cnx;
	private ObservableList<Ingrediente> listaDeIngredientes;

	public ManejadorIngrediente(Conexion cnx){
		this.cnx=cnx;
		this.listaDeIngredientes=FXCollections.observableArrayList();
	}
	public void actualizarListaDeIngredientes(){
		listaDeIngredientes.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idIngrediente, precio, cantidad, nombre FROM Ingrediente");
		try{
			while(resultado.next()){
				Ingrediente ingrediente = new Ingrediente(resultado.getInt("idIngrediente"), resultado.getInt("precio"), resultado.getInt("cantidad"), resultado.getString("nombre"));
				listaDeIngredientes.add(ingrediente);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Ingrediente> getListaDeIngredientes(){
		actualizarListaDeIngredientes();
		return listaDeIngredientes;
	}
	public void eliminarIngrediente(Ingrediente ingrediente){
		cnx.ejecutarSentencia("DELETE FROM Ingrediente WHERE idIngrediente="+ingrediente.getIdIngrediente());
		actualizarListaDeIngredientes();
	}
	public void agregarIngrediente(Ingrediente ingrediente){
		cnx.ejecutarSentencia("INSERT INTO Ingrediente(nombre, precio, cantidad) VALUES ('"+ingrediente.getNombre()+"',"+ingrediente.getPrecio()+","+ingrediente.getCantidad()+")");
		actualizarListaDeIngredientes();

	}
	public void modificarIngrediente(Ingrediente ingrediente){
		cnx.ejecutarSentencia("UPDATE Ingrediente SET nombre='"+ingrediente.getNombre()+"', precio="+ingrediente.getPrecio()+", cantidad="+ingrediente.getCantidad()+" WHERE idIngrediente="+ingrediente.getIdIngrediente());
		actualizarListaDeIngredientes();
	}
}