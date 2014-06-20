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
	private String Estado;
	private int idPedido;
	private Conexion cnx;
	private ObservableList<Pedido> listaDePedidos;
	private ObservableList<String> listaPedidos;

	/**
	 * @param cnx La clase conexion para obtener la lista de la base de datos
	 */
	public ManejadorPedido(Conexion cnx){
		this.cnx=cnx;
		this.listaDePedidos=FXCollections.observableArrayList();
		this.listaPedidos=FXCollections.observableArrayList();
	}
	public void getPedidos(){
		listaPedidos.clear();
		ResultSet resultado = cnx.ejecutarConsulta("EXEC ConsultarPedido");
		try{
			while(resultado.next()){
				String [] pedidos = new String[]{String.valueOf(resultado.getInt("idPedido")), resultado.getString("estado"), resultado.getString("nombre"), resultado.getString("nombre"), resultado.getString("nombre")};
				listaPedidos.addAll(pedidos);
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
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
	/**
	*	@param pedido Requiere un pedido para obtener su estado
	*	@return Estado del pedido para poder ser Identificado
	*/
	public String obtenerEstado(Pedido pedido){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT estado FROM Pedido WHERE idPedido="+pedido.getIdPedido());
		try{
			if(resultado!=null){
				if(resultado.next()){
					Estado = resultado.getString("estado");
					return Estado;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return Estado;
	}
	public void agregarDetallePedidoPlatillo(int idPlatillo, int idPedido, int cantidad){
		cnx.ejecutarSentencia("INSERT INTO DetallePedidoPlatillo(idPlatillo,idPedido,cantidad) VALUES ("+idPlatillo+","+idPedido+","+cantidad+")");
	}
	public void agregarDetallePedidoBebida(int idBebida, int idPedido, int cantidad){
		cnx.ejecutarSentencia("INSERT INTO DetallePedidoBebida(idBebida,idPedido,cantidad) VALUES ("+idBebida+","+idPedido+","+cantidad+")");
	}
	public void agregarDetallePedidoIngrediente(int idIngrediente, int idPedido, int cantidad){
		cnx.ejecutarSentencia("INSERT INTO DetallePedidoIngrediente(idIngrediente,idPedido,cantidad) VALUES ("+idIngrediente+","+idPedido+","+cantidad+")");
	}
	/**
	 * @return idPedido para obtener el ultimo pedido
	 */
	public int obtenerUltimoIdPedido(){
		ResultSet resultado = cnx.ejecutarConsulta("SELECT idPedido FROM Pedido WHERE idPedido = (SELECT MAX(idPedido) from Pedido)");
		try{
			if(resultado!=null){
				if(resultado.next()){
					idPedido = resultado.getInt("idPedido")+1;
					return idPedido;
				}
			}
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return idPedido;
	}
}

