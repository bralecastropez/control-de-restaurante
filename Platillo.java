package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Platillo{
	private IntegerProperty precio, idPlatillo;
	private StringProperty nombre;

	public Platillo(){
		precio = new SimpleIntegerProperty();
		idPlatillo = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty("");
	}
	public Platillo(int idPlatillo, int precio, String nombre){
		this.precio = new SimpleIntegerProperty(precio);
		this.idPlatillo = new SimpleIntegerProperty(idPlatillo);
		this.nombre = new SimpleStringProperty(nombre);
	}
	public int getIdPlatillo(){
		return idPlatillo.get();
	}
	public void setIdPlatillo(int idPlatillo){
		this.idPlatillo.set(idPlatillo);
	}
	public IntegerProperty idPlatilloProperty(){
		return idPlatillo;
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