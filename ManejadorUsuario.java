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
@SuppressWarnings({"unused"})
public class ManejadorUsuario{
	private ObservableList<Usuario> listaDeUsuarios;
	private int IdRol;
	private Usuario usuarioConectado;
	private Conexion cnx;
	
	/**
	 * @param conexion La clase conexion para poder obtener la lista de Usuario
	 */
	public ManejadorUsuario(Conexion conexion){
		listaDeUsuarios = FXCollections.observableArrayList();
		cnx = conexion;
		this.actualizarListaDeUsuarios();
	}
	public void actualizarListaDeUsuarios(){
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
		actualizarListaDeUsuarios();
		return this.listaDeUsuarios;
	}
	public void desconectar(){
		actualizarListaDeUsuarios();
		this.usuarioConectado=null;
	}
	/**
	 * @param nombre Nombre del usuario para poder obtener su rol
	 * @param pass	Contraseña del usuario para poder obtener su rol
	 * @return	El rol de usuario
	 */
	public int getRol(String nombre, String pass){
		actualizarListaDeUsuarios();
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
		actualizarListaDeUsuarios();
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
	public void eliminarUsuario(Usuario usuario){
		cnx.ejecutarSentencia("DELETE FROM Usuario WHERE idUsuario="+usuario.getIdUsuario());
		actualizarListaDeUsuarios();
	}
	public void agregarUsuario(Usuario usuario){
		cnx.ejecutarSentencia("INSERT INTO Usuario(nombre, pass, idRol) VALUES ('"+usuario.getNombre()+"','"+usuario.getPass()+"',"+usuario.getIdRol()+")");
		actualizarListaDeUsuarios();

	}
	public void modificarUsuario(Usuario usuario){
		cnx.ejecutarSentencia("UPDATE Usuario SET nombre='"+usuario.getNombre()+"', pass='"+usuario.getPass()+"', idRol="+usuario.getIdRol()+" WHERE idUsuario="+usuario.getIdUsuario());
		actualizarListaDeUsuarios();
	}

}
