package org.brandon.sistema;

import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.layout.BorderPane;

import org.brandon.manejadores.ManejadorUsuario;
import org.brandon.db.Conexion;

/**
*	@author Brandon Castro
*/
public class ModuloAdministrador{
	private Tab tPrincipalAdministrador, tUsuarios, tIngredientes, tBebidas, tComidas;
	private TabPane tpPrincipalAdministrador;
	private BorderPane bpUsuarios, bpIngredientes, bpBebidas, bpComidas;
	private ManejadorUsuario mUsuario;
	private Conexion conexion;
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
			tpPrincipalAdministrador.getTabs().add(this.getTabComidas());
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
			tIngredientes.setContent(this.getBorderPaneUsuarios());
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
	public Tab getTabComidas(){
		if(tComidas==null){
			tComidas = new Tab("Comida");
			tComidas.setContent(this.getBorderPaneComidas());
			tComidas.setClosable(false);
		}
		return tComidas;
	}
	/**
	*	@return BorderPane de Comidas
	*/
	public BorderPane getBorderPaneComidas(){
		if(bpComidas==null){
			bpComidas = new BorderPane();
		}
		return bpComidas;
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