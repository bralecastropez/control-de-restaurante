package org.brandon.sistema;

import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;	
import javafx.scene.layout.VBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.util.ArrayList;

import org.brandon.db.Conexion;
import org.brandon.manejadores.ManejadorPedido;
import org.brandon.beans.Pedido;

/**
*	@author Brandon Castro
*/

@SuppressWarnings("unused")
public class ModuloEmpleado implements EventHandler<Event>{
	private Tab tPrincipalEmpleado;
	private TabPane tpPrincipalEmpleado;
	private ToolBar tbPrincipal;
	private BorderPane bpPedidos;
	private Conexion conexion;
	private TableView<Pedido> tvPedidos;
	private ManejadorPedido mPedido;
	private Button btnActualizarLista;
	//Comprar Pedidos
	private VBox pago, pagosTarjeta;
	private TextField tfTarjeta;
	private Button pagoTarjeta, pagoEfectivo, btnTarjeta;
	//Agregar,Eliminar y Editar Pedido
	private boolean estadoMantenimiento;
	private Button btnAgregar, btnEditar, btnEliminar;

	private Tab tAgregar, tModificar, tPedidos;
	private BorderPane bpAgregar, bpModificarPrincipal;
	private GridPane bpModificar;
	private TextField tfEstado;
	private Button btnAgregarPedido,btnEstadoCancelado,btnEstadoPagado,btnEstadoEspera;
	private Pedido pedidoModificar;
	
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
			tAgregar.setContent(this.getContentAgregar());
		}
		return tAgregar;
	}
	/**
	*	@return Tabla de Pedidos Modificar
	*/
	public Tab getTabPedidosModificar(){
		if(tModificar==null){
			tModificar = new Tab("Pedidos");
			tModificar.setContent(this.getContentModificar());
		}
		return tModificar;
	}
	/**
	*	@return BorderPane para Pagos y Pedidos
	*/
	public BorderPane getContentModificar(){
		if(bpModificarPrincipal==null){
			bpModificarPrincipal = new BorderPane();
			bpModificarPrincipal.setTop(this.getGPContentModificar());
			bpModificarPrincipal.setLeft(null);
			bpModificarPrincipal.setCenter(null);
			pagoTarjeta 	= new Button("Tarjeta de Credito");
			pagoTarjeta.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			pagoTarjeta.setAlignment(Pos.CENTER);
			pagoEfectivo 	= new Button("Pago en Efectivo  ");
			pagoEfectivo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			pagoEfectivo.setAlignment(Pos.CENTER);
			pago 			= new VBox();
			pagosTarjeta	= new VBox();
			tfTarjeta		= new TextField();
			tfTarjeta.setPromptText("Ingrese su tarjeta de Credito");
			tfTarjeta.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfTarjeta.setAlignment(Pos.CENTER);
			btnTarjeta		= new Button("Pagar");
			btnTarjeta.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnTarjeta.setAlignment(Pos.CENTER);
			pagosTarjeta.getChildren().addAll(tfTarjeta, btnTarjeta);
			
			
			pago.getChildren().add(pagoTarjeta);
			pago.getChildren().add(pagoEfectivo);
		}
		return bpModificarPrincipal;
	}
	/**
	*	@return Contenido para Modifcar Pedidos
	*/
	public GridPane getGPContentModificar(){
		if(bpModificar==null){
			bpModificar = new GridPane();
			btnEstadoCancelado = new Button("Cancelado");
			btnEstadoCancelado.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEstadoEspera = new Button("Espera");
			btnEstadoEspera.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEstadoPagado = new Button("Pagado");
			btnEstadoPagado.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			bpModificar.add(btnEstadoEspera,		0,0);
			bpModificar.add(btnEstadoPagado,		1,0);
			bpModificar.add(btnEstadoCancelado,		2,0);
			bpModificar.setPadding(new Insets(10, 50, 50, 50));
		}
		return bpModificar;
	}
	/**
	*	@return Agregar Contenido de Pedidos
	*/
	public BorderPane getContentAgregar(){
		if(bpAgregar==null){
			bpAgregar = new BorderPane();
			tfEstado = new TextField("Estado del Pedido");
			tfEstado.addEventHandler(KeyEvent.KEY_RELEASED, this);
			btnAgregarPedido = new Button("Agregar Pedido");
			btnAgregarPedido.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			bpAgregar.setTop(tfEstado);
			bpAgregar.setRight(btnAgregarPedido);
		}
		return bpAgregar;
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

			tvPedidos.getColumns().setAll(columnaEstado, columnaIdPedido);
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
			btnEditar = new Button("Cambiar Estado");
			btnEditar.addEventHandler(ActionEvent.ACTION, this);
			btnActualizarLista = new Button("Actualizar");
			btnActualizarLista.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			
			tbPrincipal.getItems().add(btnAgregar);
			tbPrincipal.getItems().add(btnEditar);
			tbPrincipal.getItems().add(btnActualizarLista);
		}
		return tbPrincipal;
	}
	public void setPedido(Pedido pedido){
		tfEstado.setText(pedido.getEstado());
	}
	public boolean validarDatos(){
		return !tfEstado.getText().trim().equals("");
	}
	public boolean validarTarjeta(){
		return !tfTarjeta.getText().trim().equals("");
	}
	public void cerrarPago(){
		getTabPanePrincipal().getTabs().remove(getTabPedidosModificar());
		bpModificarPrincipal.setTop(this.getGPContentModificar());
		bpModificarPrincipal.setLeft(null);
		bpModificarPrincipal.setCenter(null);
		tfTarjeta.clear();
	}
	public void handle(Event event){
		if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				if(event.getSource().equals(tfEstado)){
					if(validarDatos()){
						Pedido pedido = new Pedido(0, tfEstado.getText());
						mPedido.agregarPedido(pedido);
						getTabPanePrincipal().getTabs().remove(getTabPedidosAgregar());
					}
				}else if(event.getSource().equals(tfTarjeta)){
					if(validarTarjeta()){
						if(tfTarjeta.getLength()==16){
							this.cerrarPago();
						}else{
							Label lblTarjeta = new Label("Tarjeta Ingresada no valida");
							tfTarjeta.setStyle("-fx-background-color: #0093EF;");
							bpModificarPrincipal.setTop(lblTarjeta);
						}
					}
				}
			}
		}else if(event instanceof ActionEvent){
			if(event.getSource().equals(btnAgregar)){
				if(!getTabPanePrincipal().getTabs().contains(getTabPedidosAgregar())){
					getTabPanePrincipal().getTabs().add(getTabPedidosAgregar());
				}
				getTabPanePrincipal().getSelectionModel().select(getTabPedidosAgregar());
				setPedido(new Pedido());
				
			}else if(event.getSource().equals(btnEditar)){
				if(!getTabPanePrincipal().getTabs().contains(getTabPedidosModificar())){
					getTabPanePrincipal().getTabs().add(getTabPedidosModificar());
				}
				getTabPanePrincipal().getSelectionModel().select(getTabPedidosModificar());
				pedidoModificar = getContentPedidos().getSelectionModel().getSelectedItem();
			}
		}else if(event instanceof MouseEvent){
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
				if(event.getSource().equals(btnEstadoCancelado)){
					Pedido pedido = new Pedido(pedidoModificar.getIdPedido(), "cancelado");
					mPedido.modificarPedido(pedido);
					
					getTabPanePrincipal().getTabs().remove(getTabPedidosModificar());
					
				}else if(event.getSource().equals(btnEstadoEspera)){
					Pedido pedido = new Pedido(pedidoModificar.getIdPedido(), "espera");
					mPedido.modificarPedido(pedido);
					
					getTabPanePrincipal().getTabs().remove(getTabPedidosModificar());
				}else if(event.getSource().equals(btnEstadoPagado)){
					Pedido pedido = new Pedido(pedidoModificar.getIdPedido(), "pagado");
					mPedido.modificarPedido(pedido);
					bpModificarPrincipal.setLeft(pago);
					bpModificarPrincipal.setTop(null);
				}else if(event.getSource().equals(btnAgregarPedido)){
					if(validarDatos()){
						Pedido pedido = new Pedido(0, tfEstado.getText());
						mPedido.agregarPedido(pedido);
						getTabPanePrincipal().getTabs().remove(getTabPedidosAgregar());
					}
				}else if(event.getSource().equals(btnActualizarLista)){
					mPedido.actualizarListaDePedidos();
				}else if(event.getSource().equals(pagoTarjeta)){
					bpModificarPrincipal.setLeft(null);
					bpModificarPrincipal.setTop(null);
					bpModificarPrincipal.setCenter(pagosTarjeta);
				}else if(event.getSource().equals(pagoEfectivo)){
					this.cerrarPago();
				}else if(event.getSource().equals(btnTarjeta)){
					this.cerrarPago();
				}
			}
		}
	}
}