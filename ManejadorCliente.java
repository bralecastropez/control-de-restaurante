package org.brandon.manejadores;

import org.brandon.beans.Cliente;
import org.brandon.db.Conexion;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.SQLException;
import java.sql.ResultSet;

/**
*	@author Brandon Castro
*/

public class ManejadorCliente{
	private ObservableList<Cliente> listaDeClientes;
	@SuppressWarnings("unused")
	private Cliente clienteConectado;
	private Conexion cnx;
	public ManejadorCliente(Conexion conexion){
		listaDeClientes = FXCollections.observableArrayList();
		cnx = conexion;
		this.actualizarLista();
	}
	public void actualizarLista(){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idCliente, nombre FROM Cliente");
		try{
			if(resultado!=null){
				while(resultado.next()){
					Cliente cliente = new Cliente(resultado.getInt("idCliente"),resultado.getString("nombre"));
					listaDeClientes.add(cliente);
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Cliente> getListaDeClientes(){
		return this.listaDeClientes;
	}

}