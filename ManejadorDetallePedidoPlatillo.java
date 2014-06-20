package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.beans.DetallePedidoPlatillo;
import org.brandon.db.Conexion;

public class ManejadorDetallePedidoPlatillo {
	private Conexion cnx;
	private ObservableList<DetallePedidoPlatillo> listaDeDetallePedidoPlatillo;

	public ManejadorDetallePedidoPlatillo(Conexion cnx){
		this.cnx=cnx;
		this.listaDeDetallePedidoPlatillo=FXCollections.observableArrayList();
	}
	public void actualizarListaDeDetallePedidoPlatillo(){
		listaDeDetallePedidoPlatillo.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idBebida, idPedido, cantidad FROM DetallePedidoBebida");
		try{
			while(resultado.next()){
				DetallePedidoPlatillo detallePedidoPlatillo = new DetallePedidoPlatillo(resultado.getInt("idPlatillo"), resultado.getInt("idPedido"), resultado.getInt("cantidad"));
				listaDeDetallePedidoPlatillo.add(detallePedidoPlatillo);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<DetallePedidoPlatillo> getListaDeDetallePedidoBebida(){
		actualizarListaDeDetallePedidoPlatillo();
		return listaDeDetallePedidoPlatillo;
	}
}

