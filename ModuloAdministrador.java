package org.brandon.sistema;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.control.TableView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.SelectionMode;
import javafx.scene.layout.BorderPane;

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
public class ModuloAdministrador{
	private Tab tPrincipalAdministrador, tUsuarios, tIngredientes, tBebidas, tPlatillos;
	private TabPane tpPrincipalAdministrador;
	private BorderPane bpUsuarios, bpIngredientes, bpBebidas, bpPlatillos;
	private Conexion conexion;
	private ManejadorUsuario mUsuario;
	private ManejadorIngrediente mIngrediente;
	private ManejadorBebida mBebida;
	private ManejadorPlatillo mPlatillo;
	//Contenido de la Tabla Usuarios
	private ToolBar tbUsuarios;
	private Button btnAgregarUsuarios, btnEliminarUsuarios,btnModificarUsuarios,btnActualizarUsuarios;
	private TableView tvUsuarios;
	//Contenido de la Tabla Ingredientes
	private ToolBar tbIngredientes;
	private Button btnAgregarIngredientes, btnEliminarIngredientes, btnModificarIngredientes, btnActualizarIngredientes;
	private TableView tvIngredientes;
	//Contenido de la Tabla Bebidas
	private ToolBar tbBebidas;
	private Button btnAgregarBebidas, btnEliminarBebidas, btnModificarBebidas, btnActualizarBebidas;
	private TableView tvBebidas;
	//Contenido de la Tabla Platillo
	private ToolBar tbPlatillos;
	private Button btnAgregarPlatillos, btnEliminarPlatillos, btnModificarPlatillos, btnActualizarPlatillos;
	private TableView tvPlatillos;
	
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
			btnAgregarUsuarios 		= new Button("Agregar Usuarios"); 
			btnEliminarUsuarios 	= new Button("Eliminar Usuarios");
			btnModificarUsuarios 	= new Button("Modificar Usuarios");
			btnActualizarUsuarios 	= new Button("Actualizar Lista de Usuarios");
			
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
			btnAgregarIngredientes 		= new Button("Agregar Ingredientes"); 
			btnEliminarIngredientes 	= new Button("Eliminar Ingredientes");
			btnModificarIngredientes 	= new Button("Modificar Ingredientes");
			btnActualizarIngredientes 	= new Button("Actualizar Lista de Ingredientes");
			
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
			btnAgregarPlatillos 		= new Button("Agregar Platillos"); 
			btnEliminarPlatillos 		= new Button("Eliminar Platillos");
			btnModificarPlatillos 		= new Button("Modificar Platillos");
			btnActualizarPlatillos 		= new Button("Actualizar Lista de Platillos");
			
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
			btnAgregarBebidas 		= new Button("Agregar Bebidas"); 
			btnEliminarBebidas 		= new Button("Eliminar Bebidas");
			btnModificarBebidas 		= new Button("Modificar Bebidas");
			btnActualizarBebidas 		= new Button("Actualizar Lista de Bebidas");
			
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
	
}