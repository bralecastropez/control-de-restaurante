package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
*	@author Brandon Castro
*/

public class DetallePedidoPlatillo {
	private IntegerProperty idPedido, idPlatillo, cantidad;
	
	public DetallePedidoPlatillo(){
		idPedido = new SimpleIntegerProperty();
		idPlatillo = new SimpleIntegerProperty();
		cantidad = new SimpleIntegerProperty();
	}
	public DetallePedidoPlatillo(int idPedido, int idPlatillo, int cantidad){
		this.idPedido = new SimpleIntegerProperty(idPedido);
		this.idPlatillo = new SimpleIntegerProperty(idPlatillo);
		this.cantidad = new SimpleIntegerProperty(cantidad);
	}
	
	public int getIdPedido(){
		return idPedido.get();
	}
	//Mandar idPedido
	public void setIdPedido(int idPedido){
		this.idPedido.set(idPedido);
	}
	//Devolver idPedio
	public IntegerProperty idPedidoProperty(){
		return idPedido;
	}
	public int getIdPlatillo(){
		return idPlatillo.get();
	}
	public void setIdPlatillo(int idIngrediente){
		this.idPlatillo.set(idIngrediente);
	}
	public IntegerProperty idPlatilloProperty(){
		return idPlatillo;
	}
	public int getIdCantidad(){
		return cantidad.get();
	}
	public void setIdCantidad(int cantidad){
		this.cantidad.set(cantidad);
	}
	public IntegerProperty idCantidadProperty(){
		return cantidad;
	}
}
