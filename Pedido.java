package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

/**
*	@author Brandon Castro
*/

public class Pedido{
	private IntegerProperty idPedido;
	private StringProperty estado;

	public Pedido(){
		idPedido = new SimpleIntegerProperty();
		estado = new SimpleStringProperty("");
	}
	public Pedido(int idPedido, String estado){
		this.idPedido = new SimpleIntegerProperty(idPedido);
		this.estado = new SimpleStringProperty(estado);
	}
	//Metodos del Pedido
	//obtener idPedido
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
	//Obtener Estado
	public String getEstado(){
		return estado.get();
	}
	//Mandar Estado
	public void setEstado(String estado){
		this.estado.set(estado);
	}
	//Devolver Estado
	public StringProperty estadoProperty(){
		return estado;
	}
}
