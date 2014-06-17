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
	private Button btnAgregarUsuarios, btnEliminarUsuarios,btnModificarUsuarios,btnActualizarUsuarios, btnAceptar;
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
	private Button btnAgregarIngredientes, btnEliminarIngredientes, btnModificarIngredientes, btnActualizarIngredientes;
	private TableView tvIngredientes;
	private GridPane CRUDIngredientes;
	//--Contenido de la Tabla Bebidas--
	//Estado Mantenimiento TRUE=Modificar FALSE=Agregar
	private boolean estadoMantenimientoBebida;
	private Tab tbCRUDBebida;
	private Bebida bebidaModificar;
	private ToolBar tbBebidas;
	private Button btnAgregarBebidas, btnEliminarBebidas, btnModificarBebidas, btnActualizarBebidas;
	private TableView tvBebidas;
	private GridPane CRUDBebidas;
	//--Contenido de la Tabla Platillo--
	//Estado Mantenimiento TRUE=Modificar FALSE=Agregar
	private boolean estadoMantenimientoPlatillo;
	private Tab tbCRUDPlatillo;
	private Platillo platilloModificar;
	private ToolBar tbPlatillos;
	private Button btnAgregarPlatillos, btnEliminarPlatillos, btnModificarPlatillos, btnActualizarPlatillos;
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
	 * @param usuario Usuario que vamos a utilizar
	 */
	public void setUsuario(Usuario usuario){
		tfAgregarNombre.setText(usuario.getNombre());
		tfAgregarPass.setText(usuario.getPass());
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
	private GridPane getContentCRUDUsuario() {
		if(CRUDUsuarios==null){
			CRUDUsuarios = new GridPane();
			tgRol				= new ToggleGroup();
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
			btnAceptar			= new Button("Aceptar");
			btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, this);
			
			
			CRUDUsuarios.add(lblNombreUsuarios, 			0, 0);
			CRUDUsuarios.add(tfAgregarNombre, 				1, 0);
			CRUDUsuarios.add(lblPassUsuarios,				0, 1);
			CRUDUsuarios.add(tfAgregarPass, 				1, 1);
			CRUDUsuarios.add(lblRol, 						0, 2, 3, 1);
			CRUDUsuarios.add(tbRolAdministrador, 			0, 3);
			CRUDUsuarios.add(tbRolChef, 					1, 3);
			CRUDUsuarios.add(tbRolEmpleado, 				2, 3);
			CRUDUsuarios.add(btnAceptar,					0, 4, 3, 1);
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
	 * @return Si los datos de usuario ingresados son correctos
	 */
	public boolean validarDatosDeUsuarios(){
		return !tfAgregarNombre.getText().trim().equals("") && !tfAgregarPass.getText().trim().equals("");
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
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDUsuario()))
						getTabPanePrincipal().getTabs().add(getTabCRUDUsuario());
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
					if(!getTabPanePrincipal().getTabs().contains(getTabCRUDUsuario()))
						getTabPanePrincipal().getTabs().add(getTabCRUDUsuario());
					getTabPanePrincipal().getSelectionModel().select(getTabCRUDUsuario());
					usuarioModificar = getContentUsuarios().getSelectionModel().getSelectedItem();
					setUsuario(usuarioModificar);
				}else if(event.getSource().equals(btnActualizarUsuarios)){
					mUsuario.actualizarListaDeUsuarios();
				}else if(event.getSource().equals(btnAceptar)){
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
					
				}else if(event.getSource().equals(btnEliminarIngredientes)){
					ObservableList<Ingrediente> ingredientes = getContentIngredientes().getSelectionModel().getSelectedItems();
					ArrayList<Ingrediente> listaNoObservable = new ArrayList<Ingrediente>(ingredientes);
					for(Ingrediente ingrediente : listaNoObservable){
						mIngrediente.eliminarIngrediente(ingrediente);
					}
				}else if(event.getSource().equals(btnModificarIngredientes)){
					
				}else if(event.getSource().equals(btnActualizarIngredientes)){
					mIngrediente.actualizarListaDeIngredientes();
				}
				///EVENTOS DE PLATILLOS
				if(event.getSource().equals(btnAgregarPlatillos)){
					
				}else if(event.getSource().equals(btnEliminarPlatillos)){
					ObservableList<Platillo> platillos = getContentPlatillos().getSelectionModel().getSelectedItems();
					ArrayList<Platillo> listaNoObservable = new ArrayList<Platillo>(platillos);
					for(Platillo platillo : listaNoObservable){
						mPlatillo.eliminarPlatillo(platillo);
					}
				}else if(event.getSource().equals(btnModificarPlatillos)){
					
				}else if(event.getSource().equals(btnActualizarPlatillos)){
					mPlatillo.actualizarListaDePlatillos();
				}
				///EVENTOS DE BEBIDAS
				if(event.getSource().equals(btnAgregarBebidas)){
					
				}else if(event.getSource().equals(btnEliminarBebidas)){
					ObservableList<Bebida> bebidas = getContentBebidas().getSelectionModel().getSelectedItems();
					ArrayList<Bebida> listaNoObservable = new ArrayList<Bebida>(bebidas);
					for(Bebida bebida : listaNoObservable){
						mBebida.eliminarBebida(bebida);
					}				
				}else if(event.getSource().equals(btnModificarBebidas)){
					
				}else if(event.getSource().equals(btnActualizarBebidas)){
					mBebida.actualizarListaDeBebidas();
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
				///EVENTOS DE PLATILLOS
				///EVENTOS DE BEBIDAS
			}
		}
	}
	
}