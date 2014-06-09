package org.brandon.sistema;

import javafx.collections.ObservableList;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;

import java.util.ArrayList;

import org.brandon.db.Conexion;
import org.brandon.manejadores.ManejadorPedido;
import org.brandon.beans.Pedido;

/**
*	@author Brandon Castro
*/

public class ModuloEmpleado implements EventHandler<Event>{
	private Tab tPrincipalEmpleado;
	private TabPane tpPrincipalEmpleado;
	private ToolBar tbPrincipal;
	private BorderPane bpPedidos;
	private Conexion conexion;
	private TableView<Pedido> tvPedidos;
	private ManejadorPedido mPedido;
	//Agregar,Eliminar y Editar Pedido
	//FALSE=AGREGAR, TRUE=MODIFICAR
	@SuppressWarnings("unused")
	private boolean estadoMantenimiento;
	private Button btnAgregar, btnEditar, btnEliminar;
	private Tab tAgregar, tModificar, tPedidos;
	
	/**
	*	@return Tabla Principal del empleado
	*/
	public Tab getTabPrincipalEmpleado(){
		if(tPrincipalEmpleado==null){
			tPrincipalEmpleado = new Tab("Modulo Empleado");
			tPrincipalEmpleado.setContent(this.getTabPanePrincipal());
			tPrincipalEmpleado.setClosable(false);
		}
		return tPrincipalEmpleado;
	}
	public void setMPedido(ManejadorPedido mPedido){
		this.mPedido=mPedido;
	}
	/**
	*	@return TabPane contendor
	*/
	public TabPane getTabPanePrincipal(){
		if(tpPrincipalEmpleado==null){
			conexion = new Conexion();
			this.setMPedido(new ManejadorPedido(conexion));
			tpPrincipalEmpleado = new TabPane();
			tpPrincipalEmpleado.getTabs().add(this.getTabPedidos());
			tpPrincipalEmpleado.getStylesheets().add("Login.css");
		}
		return tpPrincipalEmpleado;
	}
	/**
	*	@return Tabla de Pedidos
	*/
	public Tab getTabPedidos(){
		if(tPedidos==null){
			tPedidos = new Tab("Pedidos");
			tPedidos.setContent(this.getBorderPanePedidos());
			tPedidos.setClosable(false);
		}
		return tPedidos;
	}
	/**
	*	@return Tabla de Pedidos Agregar
	*/
	public Tab getTabPedidosAgregar(){
		if(tAgregar==null){
			tAgregar = new Tab("Pedidos");
		}
		return tAgregar;
	}
	/**
	*	@return Tabla de Pedidos Modificar
	*/
	public Tab getTabPedidosModificar(){
		if(tModificar==null){
			tModificar = new Tab("Pedidos");
		}
		return tModificar;
	}
	/**
	*	@return BorderPane Pedidos
	*/
	public BorderPane getBorderPanePedidos(){
		if(bpPedidos==null){
			bpPedidos = new BorderPane();
			bpPedidos.setTop(this.getToolBarPrincipal());
			bpPedidos.setCenter(this.getContentPedidos());
		}
		return bpPedidos;
	}
	/**
	* @return Tabla que muestra todos los pedidos.
	*/
	@SuppressWarnings("unchecked")
	public TableView<Pedido> getContentPedidos(){
		if(tvPedidos==null){
			tvPedidos = new TableView<Pedido>();

			tvPedidos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TableColumn<Pedido, String> columnaEstado = new TableColumn<Pedido, String>("Estado");
			columnaEstado.setCellValueFactory(new PropertyValueFactory<Pedido, String>("estado"));

			TableColumn<Pedido, Integer> columnaIdPedido = new TableColumn<Pedido, Integer>("Numero de Pedido");
			columnaIdPedido.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("idPedido"));

			TableColumn<Pedido, Integer> columnaIdFactura = new TableColumn<Pedido, Integer>("Factura");
			columnaIdFactura.setCellValueFactory(new PropertyValueFactory<Pedido, Integer>("idFactura"));

			tvPedidos.getColumns().setAll(columnaEstado, columnaIdPedido, columnaIdFactura);
			tvPedidos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvPedidos.setItems(mPedido.getListaDePedidos());
		}
		return tvPedidos;
	}
	/**
	*	@return ToolBar para los Pedidos
	*/
	public ToolBar getToolBarPrincipal(){
		if(tbPrincipal==null){
			tbPrincipal = new ToolBar();
			
			btnAgregar = new Button("Agregar");
			btnAgregar.addEventHandler(ActionEvent.ACTION, this);
			btnEliminar = new Button("Eliminar");
			btnEliminar.addEventHandler(ActionEvent.ACTION, this);
			btnEditar = new Button("Editar");
			btnEditar.addEventHandler(ActionEvent.ACTION, this);
			
			tbPrincipal.getItems().add(btnAgregar);
			tbPrincipal.getItems().add(btnEliminar);
			tbPrincipal.getItems().add(btnEditar);
		}
		return tbPrincipal;
	}
	public void handle(Event event){
		if(event instanceof ActionEvent){
			if(event.getSource().equals(btnAgregar)){
				
			}else if(event.getSource().equals(btnEliminar)){
				ObservableList<Pedido> pedidos = getContentPedidos().getSelectionModel().getSelectedItems();
				ArrayList<Pedido> listaNoObservable = new ArrayList<Pedido>(pedidos);
				for(Pedido pedido : listaNoObservable){
					mPedido.eliminarPedido(pedido);
				}
			}else if(event.getSource().equals(btnEditar)){
			
			}
		}
	}
}