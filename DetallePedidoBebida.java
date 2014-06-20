package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class DetallePedidoBebida {
	private IntegerProperty idPedido, idBebida, cantidad;
	
	public DetallePedidoBebida(){
		idPedido = new SimpleIntegerProperty();
		idBebida = new SimpleIntegerProperty();
		cantidad = new SimpleIntegerProperty();
	}
	public DetallePedidoBebida(int idPedido, int idBebida, int cantidad){
		this.idPedido = new SimpleIntegerProperty(idPedido);
		this.idBebida = new SimpleIntegerProperty(idBebida);
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
	public int getIdBebida(){
		return idBebida.get();
	}
	public void setIdBebida(int idBebida){
		this.idBebida.set(idBebida);
	}
	public IntegerProperty idBebidaProperty(){
		return idBebida;
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