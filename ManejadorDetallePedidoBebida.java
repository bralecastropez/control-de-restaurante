package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.beans.DetallePedidoBebida;
import org.brandon.db.Conexion;

public class ManejadorDetallePedidoBebida {
	private Conexion cnx;
	private ObservableList<DetallePedidoBebida> listaDeDetallePedidoBebida;

	public ManejadorDetallePedidoBebida(Conexion cnx){
		this.cnx=cnx;
		this.listaDeDetallePedidoBebida=FXCollections.observableArrayList();
	}
	public void actualizarListaDeDetallePedidoBebida(){
		listaDeDetallePedidoBebida.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idBebida, idPedido, cantidad FROM DetallePedidoBebida");
		try{
			while(resultado.next()){
				DetallePedidoBebida detallePedidoBebida = new DetallePedidoBebida(resultado.getInt("idBebida"), resultado.getInt("idPedido"), resultado.getInt("cantidad"));
				listaDeDetallePedidoBebida.add(detallePedidoBebida);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<DetallePedidoBebida> getListaDeDetallePedidoBebida(){
		actualizarListaDeDetallePedidoBebida();
		return listaDeDetallePedidoBebida;
	}
}
