package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Libro{
	private IntegerProperty precio, cantidad, idLibro;
	private StringProperty nombre, autor;

	public Libro(){
		precio = new SimpleIntegerProperty();
		cantidad = new SimpleIntegerProperty();
		idLibro = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty("");
		autor = new SimpleStringProperty("");
	}
	public Libro(int idLibro, int precio, int cantidad, String nombre, String autor){
		this.precio = new SimpleIntegerProperty(precio);
		this.cantidad = new SimpleIntegerProperty(cantidad);
		this.idLibro = new SimpleIntegerProperty(idLibro);
		this.nombre = new SimpleStringProperty(nombre);
		this.autor = new SimpleStringProperty(autor);
	}
	public int getIdLibro(){
		return idLibro.get();
	}
	public void setIdLibro(int idLibro){
		this.idLibro.set(idLibro);
	}
	public IntegerProperty idLibroProperty(){
		return idLibro;
	}

	public int getPrecio(){
		return precio.get();
	}
	public void setPrecio(int precio){
		this.precio.set(precio);
	}
	public IntegerProperty precioProperty(){
		return precio;
	}

	public int getCantidad(){
		return cantidad.get();
	}
	public void setCantidad(int cantidad){
		this.cantidad.set(cantidad);
	}
	public IntegerProperty cantidadProperty(){
		return cantidad;
	}
	
	public String getNombre(){
		return nombre.get();
	}
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	public StringProperty nombreProperty(){
		return nombre;
	}

	public String getAutor(){
		return autor.get();
	}
	public void setAutor(String autor){
		this.autor.set(autor);
	}
	public StringProperty autorProperty(){
		return autor;
	}
}
