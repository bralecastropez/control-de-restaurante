package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Usuario{
	private IntegerProperty idUsuario, idRol;
	private StringProperty nombre, pass;
	public Usuario(){
		idUsuario = new SimpleIntegerProperty();
		idRol = new SimpleIntegerProperty();
		nombre = new SimpleStringProperty();
		pass = new SimpleStringProperty();
	}
	public Usuario(int idUsuario, int idRol, String nombre, String pass){
		this.idUsuario = new SimpleIntegerProperty(idUsuario);
		this.idRol = new SimpleIntegerProperty(idRol);
		this.nombre = new SimpleStringProperty(nombre);
		this.pass = new SimpleStringProperty(pass);
	}
	public int getIdUsuario(){
		return idUsuario.get();
	}
	public void setIdUsuario(int idUsuario){
		this.idUsuario.set(idUsuario);
	}
	public IntegerProperty idUsuarioProperty(){
		return idUsuario;
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