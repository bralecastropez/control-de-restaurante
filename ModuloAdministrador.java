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
	*	@return TabPane contendor
	*/
	public TabPane getTabPanePrincipal(){
		if(tpPrincipalAdministrador==null){
			conexion = new Conexion();
			this.setMUsuario(new ManejadorUsuario(conexion));
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
		}
		return bpUsuarios;
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
		}
		return bpIngredientes;
	}
	/**
	*	@return Tabla de Comidas
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
	*	@return BorderPane de Comidas
	*/
	public BorderPane getBorderPanePlatillos(){
		if(bpPlatillos==null){
			bpPlatillos = new BorderPane();
		}
		return bpPlatillos;
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
		}
		return bpBebidas;
	}
	
}