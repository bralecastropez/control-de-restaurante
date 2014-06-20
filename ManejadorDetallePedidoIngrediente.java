package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.beans.DetallePedidoIngrediente;
import org.brandon.db.Conexion;

public class ManejadorDetallePedidoIngrediente {
	private Conexion cnx;
	private ObservableList<DetallePedidoIngrediente> listaDeDetallePedidoIngrediente;

	public ManejadorDetallePedidoIngrediente(Conexion cnx){
		this.cnx=cnx;
		this.listaDeDetallePedidoIngrediente=FXCollections.observableArrayList();
	}
	public void actualizarListaDeDetallePedidoIngrediente(){
		listaDeDetallePedidoIngrediente.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idIngrediente, idPedido, cantidad FROM DetallePedidoIngrediente");
		try{
			while(resultado.next()){
				DetallePedidoIngrediente detallePedidoIngrediente = new DetallePedidoIngrediente(resultado.getInt("idIngrediente"), resultado.getInt("idPedido"), resultado.getInt("cantidad"));
				listaDeDetallePedidoIngrediente.add(detallePedidoIngrediente);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<DetallePedidoIngrediente> getListaDeDetallePedidoIngrediente(){
		actualizarListaDeDetallePedidoIngrediente();
		return listaDeDetallePedidoIngrediente;
	}
}
