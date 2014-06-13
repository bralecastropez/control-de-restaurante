package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ingrediente{
	private IntegerProperty precio, cantidad, idIngrediente;
	private StringProperty nombre;

	public Ingrediente(){
		precio = new SimpleIntegerProperty();
		cantidad = new SimpleIntegerProperty();
		idIngrediente = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty("");
	}
	public Ingrediente(int idIngrediente, int precio, int cantidad, String nombre){
		this.precio = new SimpleIntegerProperty(precio);
		this.cantidad = new SimpleIntegerProperty(cantidad);
		this.idIngrediente = new SimpleIntegerProperty(idIngrediente);
		this.nombre = new SimpleStringProperty(nombre);
	}
	public int getIdIngrediente(){
		return idIngrediente.get();
	}
	public void setIdIngrediente(int idIngrediente){
		this.idIngrediente.set(idIngrediente);
	}
	public IntegerProperty idIngredienteProperty(){
		return idIngrediente;
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
}