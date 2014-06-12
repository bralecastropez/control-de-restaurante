package org.brandon.manejadores;

import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import org.brandon.db.Conexion;
import org.brandon.beans.Pedido;

/**
*	@author Brandon Castro
*/

public class ManejadorPedido{
	private Conexion cnx;
	private ObservableList<Pedido> listaDePedidos;

	/**
	 * @param cnx La clase conexion para obtener la lista de la base de datos
	 */
	public ManejadorPedido(Conexion cnx){
		this.cnx=cnx;
		this.listaDePedidos=FXCollections.observableArrayList();
	}
	public void actualizarListaDePedidos(){
		listaDePedidos.clear();
		ResultSet resultado = cnx.ejecutarConsulta("SELECT  idPedido, estado FROM Pedido");
		try{
			while(resultado.next()){
				Pedido pedido = new Pedido(resultado.getInt("idPedido"), resultado.getString("estado"));
				listaDePedidos.add(pedido);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	/**
	 * @return La lista de pedidos
	 */
	public ObservableList<Pedido> getListaDePedidos(){
		actualizarListaDePedidos();
		return listaDePedidos;
	}
	/**
	 * @param pedido Requiere de un pedido para poder ser eliminado
	 */
	public void eliminarPedido(Pedido pedido){
		cnx.ejecutarSentencia("DELETE FROM Pedido WHERE idPedido="+pedido.getIdPedido());
		actualizarListaDePedidos();
	}
	/**
	 * @param pedido Requiere de un pedio para poder agregarlo
	 */
	public void agregarPedido(Pedido pedido){
		cnx.ejecutarSentencia("INSERT INTO Pedido(estado) VALUES ('"+pedido.getEstado()+"')");
		actualizarListaDePedidos();

	}
	/**
	 * @param pedido Requiere de un pedido para poder ser eliminado
	 */
	public void modificarPedido(Pedido pedido){
		cnx.ejecutarSentencia("UPDATE Pedido SET estado='"+pedido.getEstado()+"' WHERE idPedido="+pedido.getIdPedido());
		actualizarListaDePedidos();
	}
}

