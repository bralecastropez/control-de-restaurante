package org.brandon.manejadores;

import org.brandon.beans.Usuario;
import org.brandon.db.Conexion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
*	@author Brandon Castro
*/

public class ManejadorUsuario{
	private ObservableList<Usuario> listaDeUsuarios;
	private int IdRol;
	@SuppressWarnings("unused")
	private Usuario usuarioConectado;
	private Conexion cnx;
	
	/**
	 * @param conexion La clase conexion para poder obtener la lista de Usuario
	 */
	public ManejadorUsuario(Conexion conexion){
		listaDeUsuarios = FXCollections.observableArrayList();
		cnx = conexion;
		this.actualizarLista();
	}
	public void actualizarLista(){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT Usuario.idUsuario,Usuario.nombre, Usuario.pass,Rol.nombre AS Rol, Usuario.idRol FROM Usuario INNER JOIN Rol ON Usuario.idRol=Rol.idRol");
		try{
			if(resultado!=null){
				while(resultado.next()){
					Usuario usuario = new Usuario(resultado.getInt("idUsuario"), resultado.getInt("idRol"), resultado.getString("nombre"), resultado.getString("pass"));
					listaDeUsuarios.add(usuario);
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	 * @return La lista de usuarios
	 */
	public ObservableList<Usuario> getListaDeUsuarios(){
		return this.listaDeUsuarios;
	}
	public void desconectar(){
		this.usuarioConectado=null;
	}
	/**
	 * 
	 * @param nombre Nombre del usuario para poder obtener su rol
	 * @param pass	Contraseña del usuario para poder obtener su rol
	 * @return	El rol de usuario
	 */
	public int getRol(String nombre, String pass){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idRol FROM Usuario WHERE nombre='"+nombre+"' AND pass='"+pass+"'");
		try{
			if(resultado!=null){
				if(resultado.next()){
					IdRol = resultado.getInt("idRol");
					return IdRol;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return IdRol;
	}
	/**
	 * @param nombre Nombre del usuario
	 * @param pass	Contraseña del Usuario
	 * @return	El usuario se ha conectado
	 */
	public boolean conectar(String nombre, String pass){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idUsuario, nombre, pass, idRol FROM Usuario WHERE nombre='"+nombre+"' AND pass='"+pass+"'");
		try{
			if(resultado!=null){
				if(resultado.next()){
					usuarioConectado = new Usuario(resultado.getInt("idUsuario"), resultado.getInt("idRol"), resultado.getString("nombre"), resultado.getString("pass"));
					return true;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return false;
	}

}
