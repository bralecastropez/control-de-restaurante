package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.db.Conexion;
import org.brandon.beans.Pedido;

public class ManejadorPedido{
	private Conexion cnx;
	private ObservableList<Pedido> listaDePedidos;

	public ManejadorPedido(Conexion cnx){
		this.cnx=cnx;
		this.listaDePedidos=FXCollections.observableArrayList();
	}
	public void actualizarListaDePedidos(){
		listaDePedidos.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT  idPedido, idFactura, estado FROM Pedido");
		try{
			while(resultado.next()){
				Pedido pedido = new Pedido(resultado.getInt("idPedido"), resultado.getInt("idFactura"), resultado.getString("estado"));
				listaDePedidos.add(pedido);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public ObservableList<Pedido> getListaDePedidos(){
		actualizarListaDePedidos();
		return listaDePedidos;
	}
	public void eliminarPedido(Pedido pedido){
		cnx.ejecutarSentencia("DELETE FROM Pedido WHERE idPedido="+pedido.getIdPedido());
		actualizarListaDePedidos();
	}
	public void agregarPedido(Pedido pedido){
		cnx.ejecutarSentencia("INSERT INTO Pedido(idFactura, estado) VALUES ('"+pedido.getEstado()+"',"+pedido.getIdFactura()+")");
		actualizarListaDePedidos();

	}
	public void modificarPedido(Pedido pedido){
		cnx.ejecutarSentencia("UPDATE Pedido SET estado='"+pedido.getEstado()+"', idFactura="+pedido.getIdFactura()+" WHERE idPedido="+pedido.getIdPedido());
		actualizarListaDePedidos();
	}
}

