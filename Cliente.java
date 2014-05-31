package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Cliente{
	private IntegerProperty idCliente;
	private StringProperty nombre;
	
	public Cliente(){
		idCliente = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty();
	}
	
	public Cliente(int idCliente, String nombre){
		this.idCliente = new SimpleIntegerProperty(idCliente);
		this.nombre = new SimpleStringProperty(nombre);
	}
	public int getIdCliente(){
		return idCliente.get();
	}
	public void setIdCliente(int idCliente){
		this.idCliente.set(idCliente);
	}
	public IntegerProperty idClienteProperty(){
		return idCliente;
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