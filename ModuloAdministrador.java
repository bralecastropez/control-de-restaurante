package org.brandon.sistema;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import org.brandon.manejadores.ManejadorUsuario;
import org.brandon.manejadores.ManejadorIngrediente;
import org.brandon.manejadores.ManejadorPlatillo;
import org.brandon.manejadores.ManejadorBebida;
import org.brandon.beans.Usuario;
import org.brandon.beans.Ingrediente;
import org.brandon.beans.Bebida;
import org.brandon.beans.Platillo;
import org.brandon.db.Conexion;

/**
*	@author Brandon Castro
*/
@SuppressWarnings({ "unchecked", "rawtypes", "unused"})
public class ModuloAdministrador implements EventHandler<Event>{
	private Tab tPrincipalAdministrador, tUsuarios, tIngredientes, tBebidas, tPlatillos;
	private TabPane tpPrincipalAdministrador;
	private BorderPane bpUsuarios, bpIngredientes, bpBebidas, bpPlatillos;
	private Conexion conexion;
	private ManejadorUsuario mUsuario;
	private ManejadorIngrediente mIngrediente;
	private ManejadorBebida mBebida;
	private ManejadorPlatillo mPlatillo;
	//--Contenido de la Tabla Usuarios--
	//Estado Mantenimiento TRUE=Modificar FALSE=Agregar
	private boolean estadoMantenimientoUsuario;
	private Tab tbCRUDUsuario;
	private Usuario usuarioModificar;
	private ToolBar tbUsuarios;
	private Button btnAgregarUsuarios, btnEliminarUsuarios,btnModificarUsuarios,btnActualizarUsuarios, btnAceptarUsuario;
	private TableView tvUsuarios;
	private GridPane CRUDUsuarios;
	private Label lblNombreUsuarios,lblPassUsuarios, lblRol;
	private TextField tfAgregarNombre, tfAgregarPass;
	private ToggleGroup tgRol;
	private ToggleButton tbRolAdministrador, tbRolEmpleado, tbRolChef;
	//--Contenido de la Tabla Ingredientes--
	//Estado Mantenimiento TRUE=Modificar FALSE=Agregar
	private boolean estadoMantenimientoIngrediente;
	private Tab tbCRUDIngrediente;
	private Ingrediente ingredienteModificar;
	private ToolBar tbIngredientes;
	private Button btnAgregarIngredientes, btnEliminarIngredientes, btnModificarIngredientes, btnActualizarIngredientes, btnAceptarIngrediente;
	private TableView tvIngredientes;
	private Label lblNombre, lblCantidad, lblPrecio;
	private TextField tfNombre, tfCantidad, tfPrecio;
	private GridPane CRUDIngredientes;
	//--Contenido de la Tabla Bebidas--
	//Estado Mantenimiento TRUE=Modificar FALSE=Agregar
	private boolean estadoMantenimientoBebida;
	private Tab tbCRUDBebida;
	private Bebida bebidaModificar;
	private ToolBar tbBebidas;
	private Button btnAgregarBebidas, btnEliminarBebidas, btnModificarBebidas, btnActualizarBebidas, btnAceptarBebida;
	private Label lblNombreBebida, lblPrecioBebida;
	private TextField tfNombreBebida, tfPrecioBebida;
	private TableView tvBebidas;
	private GridPane CRUDBebidas;
	//--Contenido de la Tabla Platillo--
	//Estado Mantenimiento TRUE=Modificar FALSE=Agregar
	private boolean estadoMantenimientoPlatillo;
	private Tab tbCRUDPlatillo;
	private Platillo platilloModificar;
	private ToolBar tbPlatillos;
	private Button btnAgregarPlatillos, btnEliminarPlatillos, btnModificarPlatillos, btnActualizarPlatillos, btnAceptarPlatillo;
	private Label lblNombrePlatillo, lblPrecioPlatillo;
	private TextField tfNombrePlatillo, tfPrecioPlatillo;
	private TableView tvPlatillos;
	private GridPane CRUDPlatillos;
	
	/**
	*	@return Tabla Principal del Administrador
	*/
	public Tab getTabPrincipalAdministrador(){
		if(tPrincipalAdministrador==null){
			tPrincipalAdministrador = new Tab("Modulo Administrador");
			tPrincipalAdministrador.setContent(this.getTabPanePrincipal());
			tPrincipalAdministrador.setClosable(false);
		}
		return tPrincipalAdministrador;
	}
	/**
	 * @param mUsuario Manejador del Usuario usando inyecccion de dependencias.
	 */
	public void setMUsuario(ManejadorUsuario mUsuario){
		this.mUsuario=mUsuario;
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
		if(tpPrincipalAdministrador==null){
			conexion = new Conexion();
			this.setMUsuario(new ManejadorUsuario(conexion));
			this.setMIngrediente(new ManejadorIngrediente(conexion));
			this.setMPlatillo(new ManejadorPlatillo(conexion));
			this.setMBebida(new ManejadorBebida(conexion));
			tpPrincipalAdministrador = new TabPane();
			tpPrincipalAdministrador.getTabs().add(this.getTabUsuarios());
			tpPrincipalAdministrador.getTabs().add(this.getTabIngredientes());
			tpPrincipalAdministrador.getTabs().add(this.getTabBebidas());
			tpPrincipalAdministrador.getTabs().add(this.getTabPlatillos());
			tpPrincipalAdministrador.getStylesheets().add("Login.css");
		}
		return tpPrincipalAdministrador;
	}
	/**
	*	@return Tabla de Usuarios
	*/
	public Tab getTabUsuarios(){
		if(tUsuarios==null){
			tUsuarios = new Tab("Usuarios");
			tUsuarios.setContent(this.getBorderPaneUsuarios());
			tUsuarios.setClosable(false);
		}
		return tUsuarios;
	}
	/**
	 * @return Tab para modificar o agregar Usuarios
	 */
	public Tab getTabCRUDUsuario(){
		if(tbCRUDUsuario==null){
			tbCRUDUsuario = new Tab("Mantenimiento");
			tbCRUDUsuario.setContent(getContentCRUDUsuario());
		}
		return tbCRUDUsuario;
	}
	/**
	 * @return GridPane para el CRUDUsuario
	 */
	private GridPane getContentCRUDUsuario() {
		if(CRUDUsuarios==null){
			CRUDUsuarios = new GridPane();
			tgRol				= new ToggleGroup();
			HBox vbRol 			= new HBox();
			lblNombreUsuarios	= new Label("Nombre:");
			lblPassUsuarios 	= new Label("Contraseña:");
			lblRol				= new Label("Rol");
			lblRol.setTextAlignment(TextAlignment.CENTER);
			tfAgregarNombre 	= new TextField();
			tfAgregarNombre.setPromptText("Nombre de Usuario");
			tfAgregarNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfAgregarPass 		= new TextField();
			tfAgregarPass.setPromptText("Clave de Usuario");
			tfAgregarPass.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tbRolAdministrador	= new ToggleButton("Administrador");
			tbRolAdministrador.setToggleGroup(tgRol);
			tbRolChef 			= new ToggleButton("    Chef     ");
			tbRolChef.setToggleGroup(tgRol);
			tbRolEmpleado 		= new ToggleButton("   Empleado  ");
			tbRolEmpleado.setToggleGroup(tgRol);
			btnAceptarUsuario			= new Button("Aceptar");
			btnAceptarUsuario.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			vbRol.getChildren().add(tbRolAdministrador);
			vbRol.getChildren().add(tbRolChef);
			vbRol.getChildren().add(tbRolEmpleado);
			
			
			CRUDUsuarios.add(lblNombreUsuarios, 			0, 0);
			CRUDUsuarios.add(tfAgregarNombre, 				1, 0);
			CRUDUsuarios.add(lblPassUsuarios,				0, 1);
			CRUDUsuarios.add(tfAgregarPass, 				1, 1);
			CRUDUsuarios.add(lblRol, 						0, 2, 3, 1);
			CRUDUsuarios.add(vbRol,							0, 3, 3, 1);
			CRUDUsuarios.add(btnAceptarUsuario,					0, 4, 3, 1);
		}
		return CRUDUsuarios;
	}
	/**
	*	@return BorderPane de Usuarios
	*/
	public BorderPane getBorderPaneUsuarios(){
		if(bpUsuarios==null){
			bpUsuarios = new BorderPane();
			bpUsuarios.setTop(this.getToolBarUsuarios());
			bpUsuarios.setCenter(this.getContentUsuarios());
		}
		return bpUsuarios;
	}
	/**
	*	@return Toolbar de Usuarios
	*/
	public ToolBar getToolBarUsuarios(){
		if(tbUsuarios==null){
			tbUsuarios = new ToolBar();
			btnAgregarUsuarios 		= new Button("Agregar Usuario"); 
			btnAgregarUsuarios.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEliminarUsuarios 	= new Button("Eliminar Usuario");
			btnEliminarUsuarios.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnModificarUsuarios 	= new Button("Modificar Usuario");
			btnModificarUsuarios.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnActualizarUsuarios 	= new Button("Actualizar Lista de Usuarios");
			btnActualizarUsuarios.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			tbUsuarios.getItems().add(btnAgregarUsuarios);
			tbUsuarios.getItems().add(btnEliminarUsuarios);
			tbUsuarios.getItems().add(btnModificarUsuarios);
			tbUsuarios.getItems().add(btnActualizarUsuarios);
		}
		return tbUsuarios;
	}
	/**
	*	@return TableView de Usuarios
	*/
	public TableView<Usuario> getContentUsuarios(){
		if(tvUsuarios==null){
			tvUsuarios = new TableView<Usuario>();

			tvUsuarios.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TableColumn<Usuario, String> columnaNombre = new TableColumn<Usuario, String>("Nombre de Usuarios");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Usuario, String>("nombre"));

			TableColumn<Usuario, String> columnaPass = new TableColumn<Usuario, String>("Clave de Usuario");
			columnaPass.setCellValueFactory(new PropertyValueFactory<Usuario, String>("pass"));

			TableColumn<Usuario, Integer> columnaIdRol = new TableColumn<Usuario, Integer>("Rol ");
			columnaIdRol.setCellValueFactory(new PropertyValueFactory<Usuario, Integer>("idRol"));

			tvUsuarios.getColumns().setAll(columnaNombre, columnaPass, columnaIdRol);
			tvUsuarios.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvUsuarios.setItems(mUsuario.getListaDeUsuarios());
		}
		return tvUsuarios;
	}
	/**
	 * @param usuario Usuario que vamos a utilizar
	 */
	public void setUsuario(Usuario usuario){
		tfAgregarNombre.setText(usuario.getNombre());
		tfAgregarPass.setText(usuario.getPass());
	}
	/**
	*	@return Tabla de Ingredientes
	*/
	public Tab getTabIngredientes(){
		if(tIngredientes==null){
			tIngredientes = new Tab("Ingredientes");
			tIngredientes.setContent(this.getBorderPaneIngredientes());
			tIngredientes.setClosable(false);
		}
		return tIngredientes;
	}
	/**
	 * @return Tab para modificar o agregar Ingredientes
	 */
	public Tab getTabCRUDIngrediente(){
		if(tbCRUDBebida==null){
			tbCRUDBebida = new Tab("Mantenimiento");
			tbCRUDBebida.setContent(getContentCRUDIngrediente());
		}
		return tbCRUDBebida;
	}
	/**
	 * @return GridPane para el CRUDIngredientes
	 */
	private GridPane getContentCRUDIngrediente() {
		if(CRUDIngredientes==null){
			CRUDIngredientes 		= new GridPane();
			lblNombre				= new Label("Nombre:  ");
			lblCantidad				= new Label("Cantidad:");
			lblPrecio				= new Label("Precio:  ");
			tfNombre				= new TextField();
			tfNombre.setPromptText("Nombre del Ingrediente");
			tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfCantidad				= new TextField();
			tfCantidad.setPromptText("Cantidad de Ingredientes");
			tfCantidad.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfPrecio				= new TextField();
			tfPrecio.setPromptText("Precio del Ingrediente");
			tfPrecio.addEventHandler(KeyEvent.KEY_RELEASED, this);
			btnAceptarIngrediente	= new Button("Aceptar");
			btnAceptarIngrediente.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			CRUDIngredientes.add(lblNombre, 			0, 0);
			CRUDIngredientes.add(tfNombre,				1, 0);
			CRUDIngredientes.add(lblCantidad, 			0, 1);
			CRUDIngredientes.add(tfCantidad, 			1, 1);
			CRUDIngredientes.add(lblPrecio, 			0, 2);
			CRUDIngredientes.add(tfPrecio, 				1, 2);
			CRUDIngredientes.add(btnAceptarIngrediente, 0, 3, 2, 1);
		}
		return CRUDIngredientes;
	}
	/**
	*	@return BorderPane de Ingredientes
	*/
	public BorderPane getBorderPaneIngredientes(){
		if(bpIngredientes==null){
			bpIngredientes = new BorderPane();
			bpIngredientes.setTop(this.getToolBarIngredientes());
			bpIngredientes.setCenter(this.getContentIngredientes());
		}
		return bpIngredientes;
	}
	/**
	*	@return Toolbar de Ingredientes
	*/
	public ToolBar getToolBarIngredientes(){
		if(tbIngredientes==null){
			tbIngredientes = new ToolBar();
			btnAgregarIngredientes 		= new Button("Agregar Ingrediente"); 
			btnAgregarIngredientes.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEliminarIngredientes 	= new Button("Eliminar Ingrediente");
			btnEliminarIngredientes.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnModificarIngredientes 	= new Button("Modificar Ingrediente");
			btnModificarIngredientes.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnActualizarIngredientes 	= new Button("Actualizar Lista de Ingredientes");
			btnActualizarIngredientes.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			tbIngredientes.getItems().add(btnAgregarIngredientes);
			tbIngredientes.getItems().add(btnEliminarIngredientes);
			tbIngredientes.getItems().add(btnModificarIngredientes);
			tbIngredientes.getItems().add(btnActualizarIngredientes);
		}
		return tbIngredientes;
	}
	/**
	*	@return TableView de Ingredientes
	*/
	public TableView<Ingrediente> getContentIngredientes(){
		if(tvIngredientes==null){
			tvIngredientes = new TableView<Ingrediente>();

			tvIngredientes.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TableColumn<Ingrediente, String> columnaNombre = new TableColumn<Ingrediente, String>("Nombre del Ingrediente");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Ingrediente, String>("nombre"));

			TableColumn<Ingrediente, Integer> columnaPrecio = new TableColumn<Ingrediente, Integer>("Precio ");
			columnaPrecio.setCellValueFactory(new PropertyValueFactory<Ingrediente, Integer>("precio"));

			tvIngredientes.getColumns().setAll(columnaNombre, columnaPrecio);
			tvIngredientes.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvIngredientes.setItems(mIngrediente.getListaDeIngredientes());
		}
		return tvIngredientes;
	}
	/**
	 * @param ingrediente Para agregar o modificar Ingredientes
	 */
	public void setIngrediente(Ingrediente ingrediente){
		tfNombre.setText(ingrediente.getNombre());
		tfCantidad.setText(String.valueOf(ingrediente.getCantidad()));
		tfPrecio.setText(String.valueOf(ingrediente.getPrecio()));
	}
	/**
	*	@return Tabla de Platillos
	*/
	public Tab getTabPlatillos(){
		if(tPlatillos==null){
			tPlatillos = new Tab("Platillos");
			tPlatillos.setContent(this.getBorderPanePlatillos());
			tPlatillos.setClosable(false);
		}
		return tPlatillos;
	}
	/**
	 * @return Tab para modificar o agregar Platillos
	 */
	public Tab getTabCRUDPlatillo(){
		if(tbCRUDPlatillo==null){
			tbCRUDPlatillo = new Tab("Mantenimiento");
			tbCRUDPlatillo.setContent(getContentCRUDPlatillo());
		}
		return tbCRUDPlatillo;
	}
	/**
	 * @return GridPane para el CRUDPlatilo
	 */
	private GridPane getContentCRUDPlatillo() {
		if(CRUDPlatillos==null){
			CRUDPlatillos = new GridPane();
			lblNombrePlatillo	= new Label("Nombre:");
			lblPrecioPlatillo	= new Label("Precio:");
			tfNombrePlatillo	= new TextField();
			tfNombrePlatillo.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfNombrePlatillo.setPromptText("Ingrese el nombre");
			tfPrecioPlatillo	= new TextField();
			tfPrecioPlatillo.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfPrecioPlatillo.setPromptText("Ingrese el precio");
			btnAceptarPlatillo	= new Button("Aceptar");
			btnAceptarPlatillo.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			CRUDPlatillos.add(lblNombrePlatillo, 	0, 0);
			CRUDPlatillos.add(tfNombrePlatillo, 	1, 0);
			CRUDPlatillos.add(lblPrecioPlatillo, 	0, 1);
			CRUDPlatillos.add(tfPrecioPlatillo, 	1, 1);
			CRUDPlatillos.add(btnAceptarPlatillo, 	0, 2, 2, 1);
		}
		return CRUDPlatillos;
	}
	/**
	*	@return BorderPane de Platillos
	*/
	public BorderPane getBorderPanePlatillos(){
		if(bpPlatillos==null){
			bpPlatillos = new BorderPane();
			bpPlatillos.setTop(this.getToolBarPlatillos());
			bpPlatillos.setCenter(this.getContentPlatillos());
		}
		return bpPlatillos;
	}
	/**
	*	@return Toolbar de Platillos
	*/
	public ToolBar getToolBarPlatillos(){
		if(tbPlatillos==null){
			tbPlatillos = new ToolBar();
			btnAgregarPlatillos 		= new Button("Agregar Platillo"); 
			btnAgregarPlatillos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEliminarPlatillos 		= new Button("Eliminar Platillo");
			btnEliminarPlatillos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnModificarPlatillos 		= new Button("Modificar Platillo");
			btnModificarPlatillos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnActualizarPlatillos 		= new Button("Actualizar Lista de Platillos");
			btnActualizarPlatillos.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			tbPlatillos.getItems().add(btnAgregarPlatillos);
			tbPlatillos.getItems().add(btnEliminarPlatillos);
			tbPlatillos.getItems().add(btnModificarPlatillos);
			tbPlatillos.getItems().add(btnActualizarPlatillos);
		}
		return tbPlatillos;
	}
	/**
	*	@return TableView de Platillos
	*/
	public TableView<Platillo> getContentPlatillos(){
		if(tvPlatillos==null){
			tvPlatillos = new TableView<Platillo>();

			tvPlatillos.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TableColumn<Platillo, String> columnaNombre = new TableColumn<Platillo, String>("Nombre del Platillo");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Platillo, String>("nombre"));

			TableColumn<Platillo, Integer> columnaPrecio = new TableColumn<Platillo, Integer>("Precio ");
			columnaPrecio.setCellValueFactory(new PropertyValueFactory<Platillo, Integer>("precio"));

			tvPlatillos.getColumns().setAll(columnaNombre, columnaPrecio);
			tvPlatillos.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvPlatillos.setItems(mPlatillo.getListaDePlatillos());
		}
		return tvPlatillos;
	}
	/**
	 * @param platillo Para agregar o modificar Platillos
	 */
	public void setPlatillo(Platillo platillo){
		tfNombrePlatillo.setText(platillo.getNombre());
		tfPrecioPlatillo.setText(String.valueOf(platillo.getPrecio()));
	}
	/**
	*	@return Tabla de Bebidas
	*/
	public Tab getTabBebidas(){
		if(tBebidas==null){
			tBebidas = new Tab("Bebidas");
			tBebidas.setContent(this.getBorderPaneBebidas());
			tBebidas.setClosable(false);
		}
		return tBebidas;
	}
	/**
	 * @return Tab para modificar o agregar Bebidas
	 */
	public Tab getTabCRUDBebida(){
		if(tbCRUDBebida==null){
			tbCRUDBebida = new Tab("Mantenimiento");
			tbCRUDBebida.setContent(getContentCRUDBebida());
		}
		return tbCRUDBebida;
	}
	/**
	 * @return GridPane para el CRUDBebida
	 */
	private GridPane getContentCRUDBebida() {
		if(CRUDBebidas==null){
			CRUDBebidas = new GridPane();
			lblNombreBebida		= new Label("Nombre:");
			lblPrecioBebida		= new Label("Precio:");
			tfNombreBebida		= new TextField();
			tfNombreBebida.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfNombreBebida.setPromptText("Ingrese el nombre");
			tfPrecioBebida		= new TextField();
			tfPrecioBebida.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfPrecioBebida.setPromptText("Ingrese el precio");
			btnAceptarBebida	= new Button("Aceptar");
			btnAceptarBebida.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			CRUDBebidas.add(lblNombreBebida, 	0, 0);
			CRUDBebidas.add(tfNombreBebida, 	1, 0);
			CRUDBebidas.add(lblPrecioBebida, 	0, 1);
			CRUDBebidas.add(tfPrecioBebida, 	1, 1);
			CRUDBebidas.add(btnAceptarBebida, 	0, 2, 2, 1);
		}
		return CRUDBebidas;
	}
	/**
	*	@return BorderPane de Bebidas
	*/
	public BorderPane getBorderPaneBebidas(){
		if(bpBebidas==null){
			bpBebidas = new BorderPane();
			bpBebidas.setTop(this.getToolBarBebidas());
			bpBebidas.setCenter(this.getContentBebidas());
		}
		return bpBebidas;
	}
	/**
	*	@return Toolbar de Bebidas
	*/
	public ToolBar getToolBarBebidas(){
		if(tbBebidas==null){
			tbBebidas = new ToolBar();
			btnAgregarBebidas 			= new Button("Agregar Bebida"); 
			btnAgregarBebidas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnEliminarBebidas 			= new Button("Eliminar Bebida");
			btnEliminarBebidas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnModificarBebidas 		= new Button("Modificar Bebida");
			btnModificarBebidas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			btnActualizarBebidas 		= new Button("Actualizar Lista de Bebidas");
			btnActualizarBebidas.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			tbBebidas.getItems().add(btnAgregarBebidas);
			tbBebidas.getItems().add(btnEliminarBebidas);
			tbBebidas.getItems().add(btnModificarBebidas);
			tbBebidas.getItems().add(btnActualizarBebidas);
		}
		return tbBebidas;
	}
	/**
	*	@return TableView de Bebidas
	*/
	public TableView<Bebida> getContentBebidas(){
		if(tvBebidas==null){
			tvBebidas = new TableView<Bebida>();

			tvBebidas.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TableColumn<Bebida, String> columnaNombre = new TableColumn<Bebida, String>("Nombre del Bebida");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Bebida, String>("nombre"));

			TableColumn<Bebida, Integer> columnaPrecio = new TableColumn<Bebida, Integer>("Precio ");
			columnaPrecio.setCellValueFactory(new PropertyValueFactory<Bebida, Integer>("precio"));

			tvBebidas.getColumns().setAll(columnaNombre, columnaPrecio);
			tvBebidas.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
			tvBebidas.setItems(mBebida.getListaDeBebidas());
		}
		return tvBebidas;
	}
	/**
	 * @param bebida Para agregar o modificar Bebidas
	 */
	public void setBebida(Bebida bebida){
		tfNombreBebida.setText(bebida.getNombre());
		tfPrecioBebida.setText(String.valueOf(bebida.getPrecio()));
	}
	/**
	 * @return Si los datos de usuario ingresados son correctos
	 */
	public boolean validarDatosDeUsuarios(){
		return !tfAgregarNombre.getText().trim().equals("") && !tfAgregarPass.getText().trim().equals("");
	}
	/**
	 * @return Si los datos de Ingrediente ingresados son correctos
	 */
	public boolean validarDatosDeIngredientes(){
		return !tfNombre.getText().trim().equals("") && !tfCantidad.getText().trim().equals("") && !tfPrecio.getText().trim().equals("");
	}
	/**
	 * @return Si los datos de Platillo ingresados son correctos
	 */
	public boolean validarDatosDePlatillos(){
		return !tfNombrePlatillo.getText().trim().equals("") && !tfPrecioPlatillo.getText().trim().equals("");
	}
	/**
	 * @return Si los datos de Bebida ingresados son correctos
	 */
	public boolean validarDatosDeBebidas(){
		return !tfNombreBebida.getText().trim().equals("") && !tfPrecioBebida.getText().trim().equals("");
	}
	/**
	 * @param event El tipo de evento que se esta utilizando
	 */
	public void handle(Event event) {
		if(event instanceof MouseEvent){
			if(event.getEventType() == MouseEvent.MOUSE_CLICKED){
				///EVENTOS DE USUARIOS
				if(event.getSource().equals(btnAgregarUsuarios)){
					estadoMantenimientoUsuario = false; 
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDUsuario())){
						getTabPanePrincipal().getTabs().add(getTabCRUDUsuario());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDUsuario());
					setUsuario(new Usuario());
				}else if(event.getSource().equals(btnEliminarUsuarios)){
					ObservableList<Usuario> usuarios = getContentUsuarios().getSelectionModel().getSelectedItems();
					ArrayList<Usuario> listaNoObservable = new ArrayList<Usuario>(usuarios);
					for(Usuario usuario : listaNoObservable){
						mUsuario.eliminarUsuario(usuario);
					}
				}else if(event.getSource().equals(btnModificarUsuarios)){
					estadoMantenimientoUsuario = true;
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDUsuario())){
						getTabPanePrincipal().getTabs().add(getTabCRUDUsuario());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDUsuario());
					usuarioModificar = getContentUsuarios().getSelectionModel().getSelectedItem();
					setUsuario(usuarioModificar);
				}else if(event.getSource().equals(btnActualizarUsuarios)){
					mUsuario.actualizarListaDeUsuarios();
				}else if(event.getSource().equals(btnAceptarUsuario)){
					if(validarDatosDeUsuarios()){
						Usuario usuario = new Usuario(0, 1, tfAgregarNombre.getText(), tfAgregarPass.getText());
						if(estadoMantenimientoUsuario){
							usuario.setIdUsuario(usuarioModificar.getIdUsuario());
							mUsuario.modificarUsuario(usuario);
						}else{
							if(tbRolAdministrador.isSelected()){
								usuario.setIdRol(1);
								mUsuario.agregarUsuario(usuario);
							}else if(tbRolChef.isSelected()){
								usuario.setIdRol(2);
								mUsuario.agregarUsuario(usuario);
							}else if(tbRolEmpleado.isSelected()){
								usuario.setIdRol(3);
								mUsuario.agregarUsuario(usuario);
							}
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDUsuario());
					}
				}
				//EVENTOS DE INGREDIENTES
				if(event.getSource().equals(btnAgregarIngredientes)){
					estadoMantenimientoIngrediente = false; 
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDIngrediente())){
						getTabPanePrincipal().getTabs().add(getTabCRUDIngrediente());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDIngrediente());
					setIngrediente(new Ingrediente());
				}else if(event.getSource().equals(btnEliminarIngredientes)){
					ObservableList<Ingrediente> ingredientes = getContentIngredientes().getSelectionModel().getSelectedItems();
					ArrayList<Ingrediente> listaNoObservable = new ArrayList<Ingrediente>(ingredientes);
					for(Ingrediente ingrediente : listaNoObservable){
						mIngrediente.eliminarIngrediente(ingrediente);
					}
				}else if(event.getSource().equals(btnModificarIngredientes)){
					estadoMantenimientoIngrediente = true;
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDIngrediente())){
						getTabPanePrincipal().getTabs().add(getTabCRUDIngrediente());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDIngrediente());
					ingredienteModificar = getContentIngredientes().getSelectionModel().getSelectedItem();
					setIngrediente(ingredienteModificar);
				}else if(event.getSource().equals(btnActualizarIngredientes)){
					mIngrediente.actualizarListaDeIngredientes();
				}else if(event.getSource().equals(btnAceptarIngrediente)){
					if(validarDatosDeIngredientes()){
						Ingrediente ingrediente = new Ingrediente(0, Integer.parseInt(tfPrecio.getText()), Integer.parseInt(tfCantidad.getText()), tfNombre.getText());
						if(estadoMantenimientoIngrediente){
							ingrediente.setIdIngrediente(ingredienteModificar.getIdIngrediente());
							mIngrediente.modificarIngrediente(ingrediente);
						}else{
							mIngrediente.agregarIngrediente(ingrediente);
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDIngrediente());
					}
				}
				///EVENTOS DE PLATILLOS
				if(event.getSource().equals(btnAgregarPlatillos)){
					estadoMantenimientoPlatillo = false; 
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDPlatillo())){
						getTabPanePrincipal().getTabs().add(getTabCRUDPlatillo());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDPlatillo());
					setPlatillo(new Platillo());
				}else if(event.getSource().equals(btnEliminarPlatillos)){
					ObservableList<Platillo> platillos = getContentPlatillos().getSelectionModel().getSelectedItems();
					ArrayList<Platillo> listaNoObservable = new ArrayList<Platillo>(platillos);
					for(Platillo platillo : listaNoObservable){
						mPlatillo.eliminarPlatillo(platillo);
					}
				}else if(event.getSource().equals(btnModificarPlatillos)){
					estadoMantenimientoPlatillo = true;
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDPlatillo())){
						getTabPanePrincipal().getTabs().add(getTabCRUDPlatillo());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDPlatillo());
					platilloModificar = getContentPlatillos().getSelectionModel().getSelectedItem();
					setPlatillo(platilloModificar);
				}else if(event.getSource().equals(btnActualizarPlatillos)){
					mPlatillo.actualizarListaDePlatillos();
				}else if(event.getSource().equals(btnAceptarPlatillo)){
					if(validarDatosDePlatillos()){
						Platillo platillo = new Platillo(0, Integer.parseInt(tfPrecioPlatillo.getText()),tfNombrePlatillo.getText());
						if(estadoMantenimientoPlatillo){
							platillo.setIdPlatillo(platilloModificar.getIdPlatillo());
							mPlatillo.modificarPlatillo(platillo);
						}else{
							mPlatillo.agregarPlatillo(platillo);
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDPlatillo());
					}
				}
				///EVENTOS DE BEBIDAS
				if(event.getSource().equals(btnAgregarBebidas)){
					estadoMantenimientoBebida = false; 
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDBebida())){
						getTabPanePrincipal().getTabs().add(getTabCRUDBebida());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDBebida());
					setBebida(new Bebida());
				}else if(event.getSource().equals(btnEliminarBebidas)){
					ObservableList<Bebida> bebidas = getContentBebidas().getSelectionModel().getSelectedItems();
					ArrayList<Bebida> listaNoObservable = new ArrayList<Bebida>(bebidas);
					for(Bebida bebida : listaNoObservable){
						mBebida.eliminarBebida(bebida);
					}				
				}else if(event.getSource().equals(btnModificarBebidas)){
					estadoMantenimientoBebida = true;
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDBebida())){
						getTabPanePrincipal().getTabs().add(getTabCRUDBebida());
					}
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDBebida());
					bebidaModificar = getContentBebidas().getSelectionModel().getSelectedItem();
					setBebida(bebidaModificar);
				}else if(event.getSource().equals(btnActualizarBebidas)){
					mBebida.actualizarListaDeBebidas();
				}else if(event.getSource().equals(btnAceptarBebida)){
					if(validarDatosDeBebidas()){
						Bebida bebida = new Bebida(0, Integer.parseInt(tfPrecioBebida.getText()),tfNombreBebida.getText());
						if(estadoMantenimientoBebida){
							bebida.setIdBebida(bebidaModificar.getIdBebida());
							mBebida.modificarBebida(bebida);
						}else{
							mBebida.agregarBebida(bebida);
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDBebida());
					}
				}
			}
		}else if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				///EVENTOS DE USUARIOS
				if(event.getSource().equals(tfAgregarNombre) || event.getSource().equals(tfAgregarPass)){
					if(validarDatosDeUsuarios()){
						Usuario usuario = new Usuario(0, 1, tfAgregarNombre.getText(), tfAgregarPass.getText());
						if(estadoMantenimientoUsuario){
							usuario.setIdUsuario(usuarioModificar.getIdUsuario());
							mUsuario.modificarUsuario(usuario);
						}else{
							if(tbRolAdministrador.isSelected()){
								usuario.setIdRol(1);
								mUsuario.agregarUsuario(usuario);
							}else if(tbRolChef.isSelected()){
								usuario.setIdRol(2);
								mUsuario.agregarUsuario(usuario);
							}else if(tbRolEmpleado.isSelected()){
								usuario.setIdRol(3);
								mUsuario.agregarUsuario(usuario);
							}
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDUsuario());
					}
				}
				///EVENTOS DE INGREDIENTES
				if(event.getSource().equals(tfNombre) || event.getSource().equals(tfCantidad) || event.getSource().equals(tfPrecio)){
					if(validarDatosDeIngredientes()){
						Ingrediente ingrediente = new Ingrediente(0, Integer.parseInt(tfPrecio.getText()), Integer.parseInt(tfCantidad.getText()), tfNombre.getText());
						if(estadoMantenimientoIngrediente){
							ingrediente.setIdIngrediente(ingredienteModificar.getIdIngrediente());
							mIngrediente.modificarIngrediente(ingrediente);
						}else{
							mIngrediente.agregarIngrediente(ingrediente);
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDIngrediente());
					}
				}
				///EVENTOS DE PLATILLOS
				if(event.getSource().equals(tfNombrePlatillo) || event.getSource().equals(tfPrecioPlatillo)){
					if(validarDatosDePlatillos()){
						Platillo platillo = new Platillo(0, Integer.parseInt(tfPrecioPlatillo.getText()),tfNombrePlatillo.getText());
						if(estadoMantenimientoPlatillo){
							platillo.setIdPlatillo(platilloModificar.getIdPlatillo());
							mPlatillo.modificarPlatillo(platillo);
						}else{
							mPlatillo.agregarPlatillo(platillo);
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDPlatillo());
					}
				}
				///EVENTOS DE BEBIDAS
				if(event.getSource().equals(tfNombreBebida) || event.getSource().equals(tfPrecioBebida)){
					if(validarDatosDeBebidas()){
						Bebida bebida = new Bebida(0, Integer.parseInt(tfPrecioBebida.getText()),tfNombreBebida.getText());
						if(estadoMantenimientoBebida){
							bebida.setIdBebida(bebidaModificar.getIdBebida());
							mBebida.modificarBebida(bebida);
						}else{
							mBebida.agregarBebida(bebida);
						}
						getTabPanePrincipal().getTabs().remove(getTabCRUDBebida());
					}
				}
			}
		}
	}
	
}