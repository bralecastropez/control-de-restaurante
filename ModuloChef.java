package org.brandon.sistema;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.collections.ObservableList;




import org.brandon.beans.Pedido;
import org.brandon.manejadores.ManejadorPedido;
import org.brandon.db.Conexion;

/**
*	@author Brandon Castro
*/

@SuppressWarnings("unused")
public class ModuloChef implements EventHandler<Event>{

	private TabPane tpPrincipalChef;
	private ToolBar tbPrincipalChef;
	private Button btnEstado;
	private Conexion conexion;
	private TableView<Pedido> tvPedidos;
	private ManejadorPedido mPedido;
	private Tab tEstado, tCRUD, tbPrincipal;
	private BorderPane gpModuloChef;
	//Cambiar Pedido
	private Pedido pedidoModificar;
	private GridPane gpContentCRUD;
	private TextField tfEstado;
	private Label lblEstado;
	private Button sendEstado;
	
	/**
	* @return El Tab de ModuloChef
	*/
	public Tab getTabPrincipalChef(){
		if(tbPrincipal==null){
			tbPrincipal = new Tab("Modulo Chef");
			tbPrincipal.setContent(this.getTBPrincipalChef());
			tbPrincipal.setClosable(false);
		}
		return tbPrincipal;
	}
	/**
	* @return El TabPane para el Tab de ModuloChef
	*/
	public TabPane getTBPrincipalChef(){
		if(tpPrincipalChef==null){
			conexion = new Conexion();
			this.setMPedido(new ManejadorPedido(conexion));
			tpPrincipalChef = new TabPane();
			tpPrincipalChef.getTabs().add(this.getTabChef());
			tpPrincipalChef.getStylesheets().add("Login.css");
		}
		return tpPrincipalChef;
	}
	public void setMPedido(ManejadorPedido mPedido){
		this.mPedido=mPedido;
	}
	/**
	* @return La tabla para modificar los pedidos.
	*/
	public Tab getTabChef(){
		if(tEstado==null){
			tEstado = new Tab("Pedidos");
			tEstado.setContent(this.getContentChef());
			tEstado.setClosable(false);
		}
		return tEstado;
	}
	/**
	* @return BorderPane para el contenido de la tabla.
	*/
	public BorderPane getContentChef(){
		if(gpModuloChef==null){
			gpModuloChef = new BorderPane();
			gpModuloChef.setTop(this.getToolbarChef());
			gpModuloChef.setCenter(this.getContentPedidos());
			gpModuloChef.getStylesheets().add("Login.css");
		}
		return gpModuloChef;
	}
	/**
	* @return Herramientas para modificar las tablas.
	*/
	public ToolBar getToolbarChef(){
		if(tbPrincipalChef==null){
			tbPrincipalChef = new ToolBar();
			btnEstado = new Button("Cambiar Estado");
			btnEstado.setId("ModuloChef");
			btnEstado.addEventHandler(ActionEvent.ACTION, this);
			
			tbPrincipalChef.getItems().add(btnEstado);
			
		}
		return tbPrincipalChef;
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
	* @return Tabla que contiene el modulo crud.
	*/
	public Tab getTabCRUD(){
		if(tCRUD==null){
			tCRUD = new Tab("Cambio de Estado");
			tCRUD.setContent(getContentCRUD());
		}
		return tCRUD;
	}
	/**
	* @return Contenido para modificar el Pedido.
	*/
	public GridPane getContentCRUD(){
		if(gpContentCRUD==null){
			gpContentCRUD = new GridPane();
			
			sendEstado = new Button("Cambiar");
			sendEstado.addEventHandler(ActionEvent.ACTION, this);
			tfEstado = new TextField();
			tfEstado.addEventHandler(KeyEvent.KEY_RELEASED, this);
			lblEstado = new Label("Estado: ");

			gpContentCRUD.add(lblEstado, 		0,0);
			gpContentCRUD.add(tfEstado, 		1,0);
			gpContentCRUD.add(sendEstado,		2,0 );
			
			tfEstado.clear();
		}
		return gpContentCRUD;
	}
	public void setPedido(Pedido pedido){
		tfEstado.setText(pedido.getEstado());
	}
	/**
	* @return Si los datos son validos
	*/
	public boolean validarDatos(){
		return !tfEstado.getText().trim().equals("");
	}
	public void handle(Event event){
		if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				if(event.getSource().equals(tfEstado)){
					if(validarDatos()){
						Pedido pedido = new Pedido(0, 0, tfEstado.getText());
						pedido.setIdFactura(pedidoModificar.getIdFactura());
						pedido.setIdPedido(pedidoModificar.getIdPedido());
						mPedido.modificarPedido(pedido);
						
						tpPrincipalChef.getTabs().remove(getTabCRUD());
						tfEstado.clear();
					}
				}
			}
		}else if(event instanceof ActionEvent){
			if(event.getSource().equals(btnEstado)){
				if(!tpPrincipalChef.getTabs().contains(getTabCRUD())){
					tpPrincipalChef.getTabs().add(getTabCRUD());
				}
				tpPrincipalChef.getSelectionModel().select(getTabCRUD());
				pedidoModificar = getContentPedidos().getSelectionModel().getSelectedItem();
				setPedido(pedidoModificar);
				tfEstado.clear();
			}else if (event.getSource().equals(sendEstado)){
				if(validarDatos()){
					Pedido pedido = new Pedido(0, 0, tfEstado.getText());
					pedido.setIdFactura(pedidoModificar.getIdFactura());
					pedido.setIdPedido(pedidoModificar.getIdPedido());
					mPedido.modificarPedido(pedido);
					
					tpPrincipalChef.getTabs().remove(getTabCRUD());
					tfEstado.clear();
				}
			}
		}
	}
}