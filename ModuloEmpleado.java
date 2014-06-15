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
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.util.ArrayList;

import org.brandon.db.Conexion;
import org.brandon.manejadores.ManejadorBebida;
import org.brandon.manejadores.ManejadorIngrediente;
import org.brandon.manejadores.ManejadorPedido;
import org.brandon.manejadores.ManejadorPlatillo;
import org.brandon.beans.Bebida;
import org.brandon.beans.Ingrediente;
import org.brandon.beans.Pedido;
import org.brandon.beans.Platillo;

/**
*	@author Brandon Castro
*/
@SuppressWarnings({ "unchecked","rawtypes", "unused"})
public class ModuloEmpleado implements EventHandler<Event>{
	private String estadoPagado;
	private Tab tPrincipalEmpleado;
	private TabPane tpPrincipalEmpleado;
	private ToolBar tbPrincipal;
	private BorderPane bpPedidos;
	private Conexion conexion;
	private TableView<Pedido> tvPedidos;
	private ManejadorPedido mPedido;
	private ManejadorIngrediente mIngrediente;
	private ManejadorBebida mBebida;
	private ManejadorPlatillo mPlatillo;
	private Button btnActualizarLista;
	//Comprar Pedidos
	private VBox pago, pagosTarjeta;
	private TextField tfTarjeta;
	private Button pagoTarjeta, pagoEfectivo, btnTarjeta;
	//Agregar,Eliminar y Editar Pedido
	private boolean estadoMantenimiento;
	private Button btnAgregar, btnEditar, btnEliminar;
	private TableView tvIngredientes, tvPlatillos, tvBebidas;
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
	 * @param mPlatillo Manejador Platillo usando inyecccion de dependencias.
	 */
	public void setMPlatillo(ManejadorPlatillo mPlatillo){
		this.mPlatillo=mPlatillo;
	}
	/**
	 * @param mIngrediente Manejador Ingrediente usando inyecccion de dependencias.
	 */
	public void setMIngrediente(ManejadorIngrediente mIngrediente){
		this.mIngrediente=mIngrediente;
	}
	/**
	 * @param mBebida Manejador Bebida usando inyecccion de dependencias.
	 */
	public void setMBebida(ManejadorBebida mBebida){
		this.mBebida=mBebida;
	}
	/**
	*	@return TabPane contendor
	*/
	public TabPane getTabPanePrincipal(){
		if(tpPrincipalEmpleado==null){
			conexion = new Conexion();
			this.setMPedido(new ManejadorPedido(conexion));
			this.setMIngrediente(new ManejadorIngrediente(conexion));
			this.setMPlatillo(new ManejadorPlatillo(conexion));
			this.setMBebida(new ManejadorBebida(conexion));
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
	public void cerrarPago(){
		getTabPanePrincipal().getTabs().remove(getTabPedidosModificar());
		bpModificarPrincipal.setTop(this.getGPContentModificar());
		bpModificarPrincipal.setLeft(null);
		bpModificarPrincipal.setCenter(null);
		tfTarjeta.clear();
	}
	/**
	*	@return Tabla de Pedidos Modificar
	*/
	public Tab getTabPedidosModificar(){
		if(tModificar==null){
			tModificar = new Tab("Pedidos");
			tModificar.setContent(this.getContentModificar());
			tModificar.setOnClosed(new EventHandler<Event>() {
			     public void handle(Event event) {
			        cerrarPago();
			     }
			});
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
			btnEstadoCancelado = new Button("Cancelar");
			btnEstadoCancelado.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEstadoEspera = new Button("Espera");
			btnEstadoEspera.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEstadoPagado = new Button("Pagar");
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
			GridPane gpPrincipal 		= new GridPane();
			VBox vbIngredientes 		= new VBox();
			VBox vbPlatillos 			= new VBox();
			VBox vbBebidas 				= new VBox();
			Label lblPrincipal 			= new Label("Tabla de Pedidos");
			lblPrincipal.setTextAlignment(TextAlignment.CENTER);
			Label lblIngredientes 		= new Label("Ingredientes");
			Label lblPlatillos 		= new Label("Platillos");
			Label lblBebidas 			= new Label("Bebidas");
			tfEstado = new TextField("Estado del Pedido");
			tfEstado.addEventHandler(KeyEvent.KEY_RELEASED, this);
			btnAgregarPedido = new Button("Agregar Pedido");
			btnAgregarPedido.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			bpAgregar.setTop(tfEstado);
			bpAgregar.setRight(btnAgregarPedido);
			bpAgregar.setCenter(gpPrincipal);
			
			vbIngredientes.getChildren().add(this.getContentIngredientes());
			vbPlatillos.getChildren().add(this.getContentPlatillos());
			vbBebidas.getChildren().add(this.getContentBebidas());
			
			gpPrincipal.add(lblPrincipal, 0	, 0, 3, 1);
			gpPrincipal.add(lblIngredientes, 	0, 1);
			gpPrincipal.add(vbIngredientes, 	0, 2);
			gpPrincipal.add(lblPlatillos, 		1, 1);
			gpPrincipal.add(vbPlatillos, 		1, 2);
			gpPrincipal.add(lblBebidas, 		2, 1);
			gpPrincipal.add(vbBebidas, 		2, 2);
			
		}
		return bpAgregar;
	}
	/**
	*	@return TableView de Ingredientes
	*/
	public TableView<Ingrediente> getContentIngredientes(){
		if(tvIngredientes==null){
			tvIngredientes = new TableView<Ingrediente>();

			tvIngredientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			TableColumn<Ingrediente, Integer> columnaIdIngrediente = new TableColumn<Ingrediente, Integer>("Identificador ");
			columnaIdIngrediente.setCellValueFactory(new PropertyValueFactory<Ingrediente, Integer>("idIngrediente"));

			TableColumn<Ingrediente, String> columnaNombre = new TableColumn<Ingrediente, String>("Nombre del Ingrediente");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Ingrediente, String>("nombre"));

			TableColumn<Ingrediente, Integer> columnaPrecio = new TableColumn<Ingrediente, Integer>("Precio ");
			columnaPrecio.setCellValueFactory(new PropertyValueFactory<Ingrediente, Integer>("precio"));

			tvIngredientes.getColumns().setAll(columnaIdIngrediente, columnaNombre, columnaPrecio);
			tvIngredientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvIngredientes.setItems(mIngrediente.getListaDeIngredientes());
		}
		return tvIngredientes;
	}
	/**
	*	@return TableView de Platillos
	*/
	public TableView<Platillo> getContentPlatillos(){
		if(tvPlatillos==null){
			tvPlatillos = new TableView<Platillo>();

			tvPlatillos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			TableColumn<Platillo, Integer> columnaIdPlatillo = new TableColumn<Platillo, Integer>("Identificador ");
			columnaIdPlatillo.setCellValueFactory(new PropertyValueFactory<Platillo, Integer>("idPlatillo"));

			TableColumn<Platillo, String> columnaNombre = new TableColumn<Platillo, String>("Nombre del Platillo");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Platillo, String>("nombre"));

			TableColumn<Platillo, Integer> columnaPrecio = new TableColumn<Platillo, Integer>("Precio ");
			columnaPrecio.setCellValueFactory(new PropertyValueFactory<Platillo, Integer>("precio"));

			tvPlatillos.getColumns().setAll(columnaIdPlatillo,columnaNombre, columnaPrecio);
			tvPlatillos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvPlatillos.setItems(mPlatillo.getListaDePlatillos());
		}
		return tvPlatillos;
	}
	/**
	*	@return TableView de Bebidas
	*/
	public TableView<Bebida> getContentBebidas(){
		if(tvBebidas==null){
			tvBebidas = new TableView<Bebida>();

			tvBebidas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
			
			TableColumn<Bebida, Integer> columnaIdBebida = new TableColumn<Bebida, Integer>("Identificador ");
			columnaIdBebida.setCellValueFactory(new PropertyValueFactory<Bebida, Integer>("idBebida"));
			
			TableColumn<Bebida, String> columnaNombre = new TableColumn<Bebida, String>("Nombre del Bebida");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Bebida, String>("nombre"));

			TableColumn<Bebida, Integer> columnaPrecio = new TableColumn<Bebida, Integer>("Precio ");
			columnaPrecio.setCellValueFactory(new PropertyValueFactory<Bebida, Integer>("precio"));

			tvBebidas.getColumns().setAll(columnaIdBebida, columnaNombre, columnaPrecio);
			tvBebidas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvBebidas.setItems(mBebida.getListaDeBebidas());
		}
		return tvBebidas;
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
			
			btnAgregar = new Button("Agregar Nuevo Pedido");
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
	/**
	 * @param event El tipo de evento que se utilizara
	 */
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
					Pedido estado = new Pedido(pedidoModificar.getIdPedido(), pedidoModificar.getEstado());
					switch(mPedido.obtenerEstado(estado)){
						case "entregado":
							Pedido pedido = new Pedido(pedidoModificar.getIdPedido(), "pagado");
							mPedido.modificarPedido(pedido);
							bpModificarPrincipal.setLeft(pago);
							bpModificarPrincipal.setTop(null);
							break;
						case "espera":
							Label estadoEspera = new Label("El pedido debe estar Entregado para poder se Pagado");
							bpModificarPrincipal.setTop(estadoEspera);
							break;
						case "cancelado":
							Label estadoCancelado = new Label("El pedido se ha cancelado");
							bpModificarPrincipal.setTop(estadoCancelado);
							break;
						case "pagado":
							Label estadoPagado = new Label("El pedido ya se ha Pagado");
							bpModificarPrincipal.setTop(estadoPagado);
							break;
						default:
							this.cerrarPago();
							break;
					}
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