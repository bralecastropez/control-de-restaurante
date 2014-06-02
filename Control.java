package org.brandon.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.cell.PropertyValueFactory;
//import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.MouseEvent;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleListProperty;

import java.util.ArrayList;

import org.brandon.utilidades.BarraDeMenu;
import org.brandon.manejadores.ManejadorCliente;
import org.brandon.manejadores.ManejadorPedido;
import org.brandon.db.Conexion;
import org.brandon.beans.Cliente;
import org.brandon.beans.Pedido;
import org.brandon.sistema.Principal;

public class Control extends Application implements EventHandler<Event>{
	private Stage primaryStage;
	private Scene primaryScene;
	private BorderPane bpPrincipal, bpToolbar,bpTables;
	private GridPane gpContainerTables;
	public static Control instancia;
	private TabPane tpPrincipal;
	private TableView<Cliente> tvClientes;
	private TableView<Pedido> tvPedidos;
	private Tab tCliente, tPedido;
	private ManejadorCliente mCliente;
	private ManejadorPedido mPedido;
	private ToolBar tbPrincipal;
	private Conexion conexion;
	private Button btnAgregar, btnEliminar, btnModificar, btnDesconectar;
	
	
	public Stage getControl(){
		conexion = new Conexion();
		this.primaryStage=primaryStage;

		this.setMCliente(new ManejadorCliente(conexion));
		this.setMPedido(new ManejadorPedido(conexion));
		
		primaryScene = new Scene(this.getbpPrincipal());
		primaryScene.getStylesheets().add("Login.css");
		
		primaryStage = new Stage();
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Control de Restaurante");
		
		return primaryStage;
	}
	public BorderPane getbpPrincipal(){
		if(bpPrincipal==null){
			bpPrincipal = new BorderPane();
			bpPrincipal.setTop(BarraDeMenu.getInstancia().menuBar());
			bpPrincipal.setCenter(this.getbpToolbar());
		}
		return bpPrincipal;
	}
	public BorderPane getbpToolbar(){
		if(bpToolbar==null){
			bpToolbar = new BorderPane();
			bpToolbar.setTop(this.gettbToolbar());
			bpToolbar.setCenter(this.getbpTables());
		}
		return bpToolbar;
	}
	public ToolBar gettbToolbar(){
		if(tbPrincipal==null){
			btnAgregar = new Button("Agregar");
			btnEliminar = new Button("Eliminar");
			btnModificar = new Button("Modificar");
			btnDesconectar = new Button("Desconectar");
			btnDesconectar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
	
			tbPrincipal = new ToolBar();
			tbPrincipal.getItems().add(btnAgregar);
			tbPrincipal.getItems().add(btnEliminar);
			tbPrincipal.getItems().add(btnModificar);
			tbPrincipal.getItems().add(btnDesconectar);
		}
		return tbPrincipal;
	}
	public BorderPane getbpTables(){
		if(bpTables==null){
			bpTables = new BorderPane();
			bpTables.setTop(this.getTabPrincipal());
		}
		return bpTables;
	}
	public TabPane getTabPrincipal(){
		if(tpPrincipal==null){
			tpPrincipal = new TabPane();
			tpPrincipal.getTabs().add(this.getTabCliente());
			tpPrincipal.getTabs().add(this.getTabPedido());
		}
		return tpPrincipal;
	}
	public Tab getTabCliente(){
		if(tCliente==null){
			tCliente = new Tab("Cliente");
			tCliente.setContent(this.getContentCliente());
		}
		return tCliente;
	}
	public Tab getTabPedido(){
		if(tPedido==null){
			tPedido = new Tab("Pedidos");
			tPedido.setContent(this.getContentPedidos());
		}
		return tPedido;
	}
	public void setMCliente(ManejadorCliente mCliente){
		this.mCliente = mCliente;
	}
	public void setMPedido(ManejadorPedido mPedido){
		this.mPedido=mPedido;
	}
	public TableView<Cliente> getContentCliente(){
		if(tvClientes==null){
			tvClientes = new TableView<Cliente>();

			tvClientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TableColumn<Cliente, String> columnaNombre = new TableColumn<Cliente, String>("NOMBRE COMPLETO");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Cliente, String>("nombre"));

			TableColumn<Cliente, Integer> columnaIdCliente = new TableColumn<Cliente, Integer>("NIT");
			columnaIdCliente.setCellValueFactory(new PropertyValueFactory<Cliente, Integer>("idCliente"));

			tvClientes.getColumns().setAll(columnaNombre, columnaIdCliente);

			tvClientes.setItems(mCliente.getListaDeClientes());
		}
		return tvClientes;
	}
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

			tvPedidos.setItems(mPedido.getListaDePedidos());
		}
		return tvPedidos;
	}
	public static Control getInstancia(){
		if(instancia==null){
			instancia = new Control();
		}
		return instancia;
	}
	public void handle(Event event){
		if(event instanceof MouseEvent){
			if(event.getEventType()==MouseEvent.MOUSE_CLICKED){
				if(event.getSource().equals(btnDesconectar)){
					String [] args = new String[5];
					primaryStage.close();
					//this.main(args);
				}
			}
		}
	}
	public static void main(String [] args){
		Application.launch(Principal.class, args);
	}
	
	public void start(Stage arg){
	}
}