package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.db.Conexion;
import org.brandon.beans.Libro;

public class ManejadorLibro{
	private Conexion cnx;
	private ObservableList<Libro> listaDeLibros;

	public ManejadorLibro(Conexion cnx){
		this.cnx=cnx;
		this.listaDeLibros=FXCollections.observableArrayList();
	}
	public void actualizarListaDeLibros(){
		listaDeLibros.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idLibro, precio, cantidad, nombre, autor FROM Libro");
		try{
			while(resultado.next()){
				Libro libro = new Libro(resultado.getInt("idLibro"), resultado.getInt("precio"), resultado.getInt("cantidad"), resultado.getString("nombre"), resultado.getString("autor"));
				listaDeLibros.add(libro);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Libro> getListaDeLibros(){
		actualizarListaDeLibros();
		return listaDeLibros;
	}
	public void eliminarLibro(Libro libro){
		cnx.ejecutarSentencia("DELETE FROM Libro WHERE idLibro="+libro.getIdLibro());
		actualizarListaDeLibros();
	}
	public void agregarLibro(Libro libro){
		cnx.ejecutarSentencia("INSERT INTO Libro(nombre, autor, precio, cantidad) VALUES ('"+libro.getNombre()+"','"+libro.getAutor()+"',"+libro.getPrecio()+","+libro.getCantidad()+")");
		actualizarListaDeLibros();

	}
	public void modificarLibro(Libro libro){
		cnx.ejecutarSentencia("UPDATE Libro SET nombre='"+libro.getNombre()+"', autor='"+libro.getAutor()+"', precio="+libro.getPrecio()+", cantidad="+libro.getCantidad()+" WHERE idLibro="+libro.getIdLibro());
		actualizarListaDeLibros();
	}
}

