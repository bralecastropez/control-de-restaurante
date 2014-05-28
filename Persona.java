package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Persona{
	private IntegerProperty idPersona, idRol;
	private StringProperty nombre, pass;
	public Persona(){
		idPersona = new SimpleIntegerProperty();
		idRol = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty();
		pass = new SimpleStringProperty();
	}
	public Persona(int idPersona, int idRol, String nombre, String pass){
		this.idPersona = new SimpleIntegerProperty(idPersona);
		this.idRol = new SimpleIntegerProperty(idRol);
		this.nombre = new SimpleStringProperty(nombre);
		this.pass = new SimpleStringProperty(pass);
	}
	public int getIdPersona(){
		return idPersona.get();
	}
	public void setIdPersona(int idPersona){
		this.idPersona.set(idPersona);
	}
	public IntegerProperty idPersonaProperty(){
		return idPersona;
	}

	public int getIdRol(){
		return idRol.get();
	}
	public void setIdRol(int idRol){
		this.idRol.set(idRol);
	}
	public IntegerProperty idRolProperty(){
		return idRol;
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
	
	public String getPass(){
		return pass.get();
	}
	public void setPass(String pass){
		this.pass.set(pass);
	}
	public StringProperty passProperty(){
		return pass;
	}
}
