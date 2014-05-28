package org.brandon.manejadores;

import org.brandon.beans.Persona;
import org.brandon.db.Conexion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.ResultSet;

public class ManejadorPersona{
	private ObservableList<Persona> listaDePersona;
	private Persona personaConectada;
	private Conexion cnx;
	public ManejadorPersona(Conexion conexion){
		listaDePersona = FXCollections.observableArrayList();
		cnx = conexion;
		this.actualizarLista();
	}
	public void actualizarLista(){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT Persona.idPersona,Persona.nombre, Persona.pass,Rol.nombre AS Rol, Persona.idRol FROM Persona INNER JOIN Rol ON Persona.idRol=Rol.idRol");
		try{
			if(resultado!=null){
				while(resultado.next()){
					Persona persona = new Persona(resultado.getInt("idPersona"), resultado.getInt("idRol"), resultado.getString("nombre"), resultado.getString("pass"));
					listaDePersona.add(persona);
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Persona> getListaDePersonas(){
		return this.listaDePersona;
	}
	public void desconectar(){
		this.personaConectada=null;
	}
	public boolean conectar(String nombre, String pass){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idPersona, nombre, pass, idRol FROM Persona WHERE nombre='"+nombre+"' AND pass='"+pass+"'");
		try{
			if(resultado!=null){
				if(resultado.next()){
					personaConectada = new Persona(resultado.getInt("idPersona"), resultado.getInt("idRol"), resultado.getString("nombre"), resultado.getString("pass"));
					return true;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return false;
	}

}
