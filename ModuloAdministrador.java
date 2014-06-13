package org.brandon.sistema;

import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;

import org.brandon.manejadores.ManejadorUsuario;
import org.brandon.db.Conexion;

/**
*	@author Brandon Castro
*/
public class ModuloAdministrador{
	private Tab tPrincipalAdministrador, tUsuarios, tIngredientes, tBebidas, tPlatillos;
	private TabPane tpPrincipalAdministrador;
	private BorderPane bpUsuarios, bpIngredientes, bpBebidas, bpPlatillos;
	private Conexion conexion;
	private ManejadorUsuario mUsuario;
	private ModuloEmpleado mEmpleado;
	//Contenido de la Tabla Usuarios
	private ToolBar tbUsuarios;
	private Button btnAgregarUsuarios, btnEliminarUsuarios,btnModificarUsuarios,btnActualizarUsuarios;
	//Contenido de la Tabla Ingredientes
	private ToolBar tbIngredientes;
	private Button btnAgregarIngredientes, btnEliminarIngredientes, btnModificarIngredientes, btnActualizarIngredientes;
	//Contenido de la Tabla Bebidas
	private ToolBar tbBebidas;
	private Button btnAgregarBebidas, btnEliminarBebidas, btnModificarBebidas, btnActualizarBebidas;
	//Contenido de la Tabla Platillo
	private ToolBar tbPlatillos;
	private Button btnAgregarPlatillos, btnEliminarPlatillos, btnModificarPlatillos, btnActualizarPlatillos;
	
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
	 * @param mEmpleado Modulo Empleado usando inyecccion de dependencias.
	 */
	public void setMEmpleado(ModuloEmpleado mEmpleado){
		this.mEmpleado=mEmpleado;
	}
	/**
	*	@return TabPane contendor
	*/
	public TabPane getTabPanePrincipal(){
		if(tpPrincipalAdministrador==null){
			conexion = new Conexion();
			this.setMUsuario(new ManejadorUsuario(conexion));
			this.setMEmpleado(new ModuloEmpleado());
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
	
}