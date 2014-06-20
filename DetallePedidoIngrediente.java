package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

/**
*	@author Brandon Castro
*/

public class DetallePedidoIngrediente {
	private IntegerProperty idPedido, idIngrediente, cantidad;
	
	public DetallePedidoIngrediente(){
		idPedido = new SimpleIntegerProperty();
		idIngrediente = new SimpleIntegerProperty();
		cantidad = new SimpleIntegerProperty();
	}
	public DetallePedidoIngrediente(int idPedido, int idIngrediente, int cantidad){
		this.idPedido = new SimpleIntegerProperty(idPedido);
		this.idIngrediente = new SimpleIntegerProperty(idIngrediente);
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
	public int getIdIngrediente(){
		return idIngrediente.get();
	}
	public void setIdIngrediente(int idIngrediente){
		this.idIngrediente.set(idIngrediente);
	}
	public IntegerProperty idIngredienteProperty(){
		return idIngrediente;
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
