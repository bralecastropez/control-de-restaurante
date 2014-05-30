package org.brandon.beans;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;

public class Pedido{
	private IntegerProperty idPedido, idFactura;
	private StringProperty estado;

	public Pedido(){
		idPedido = new SimpleIntegerProperty();
		idFactura = new SimpleIntegerProperty();
		estado = new SimpleStringProperty("");
	}
	public Pedido(int idPedido, int idFactura, String estado){
		this.idPedido = new SimpleIntegerProperty(idPedido);
		this.idFactura = new SimpleIntegerProperty(idFactura);
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
	
	//Obtener idFactura
	public int getIdFactura(){
		return idFactura.get();
	}
	//Mandar idFactura
	public void setIdFactura(int idFactura){
		this.idFactura.set(idFactura);
	}
	//Devolver idFactura
	public IntegerProperty idFacturaProperty(){
		return idFactura;
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
