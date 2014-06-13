package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Bebida{
	private IntegerProperty precio, idBebida;
	private StringProperty nombre;

	public Bebida(){
		precio = new SimpleIntegerProperty();
		idBebida = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty("");
	}
	public Bebida(int idBebida, int precio, String nombre){
		this.precio = new SimpleIntegerProperty(precio);
		this.idBebida = new SimpleIntegerProperty(idBebida);
		this.nombre = new SimpleStringProperty(nombre);
	}
	public int getIdBebida(){
		return idBebida.get();
	}
	public void setIdBebida(int idBebida){
		this.idBebida.set(idBebida);
	}
	public IntegerProperty idBebidaProperty(){
		return idBebida;
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
	
	public String getNombre(){
		return nombre.get();
	}
	public void setNombre(String nombre){
		this.nombre.set(nombre);
	}
	public StringProperty nombreProperty(){
		return nombre;
	}
}