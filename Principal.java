package org.brandon.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;

import javafx.beans.property.SimpleListProperty;

import org.brandon.beans.Usuario;
import org.brandon.manejadores.ManejadorUsuario;
import org.brandon.db.Conexion;

public class Principal extends Application implements EventHandler<Event>{
	private TextField tfNombre;
	private PasswordField pfPass;
	private Label lblNombre, lblPass;
	private TabPane tpPrincipal;
	private Tab tbLogin, tbUsuarios;
	private GridPane gpLogin,gpContainerUsuarios;
	private BorderPane bpUsuarios;
	private TableView<Usuario> tvUsuarios;
	private TableColumn tvNombre, tvRol;
	private ManejadorUsuario mUsuario;
	private Scene primaryScene;
	private TableColumn tcColumnNombre, tcColumnRol;
	
	public void start(Stage primaryStage){
		this.setMUsuario(new ManejadorUsuario(new Conexion()));

		primaryScene = new Scene(this.getTabPane());
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
	public TabPane getTabPane(){
		if(tpPrincipal == null){
			tpPrincipal = new TabPane();
			tpPrincipal.getTabs().add(this.getTabContent());
		}
		return tpPrincipal;
	}
	public Tab getTabContent(){
		if(tbLogin==null){
			tbLogin = new Tab("Login");
			tbLogin.setContent(this.getContentLogin());
			tbLogin.setClosable(false);
		}
		return tbLogin;
	}
	public Tab getContentUsuarios(){
		if(tvUsuarios==null && tbUsuarios==null){
			tbUsuarios = new Tab("Lista Usuarios");
			tvNombre = new TableColumn("Nombre");
			tvRol = new TableColumn("Rol");
			tvUsuarios = new TableView<Usuario>();
			tvUsuarios.getColumns().addAll(tvNombre, tvRol);
			tbUsuarios.setClosable(false);
			tbUsuarios.setContent(tvUsuarios);
		}
		return tbUsuarios;
	}
	public GridPane getContentLogin(){
		if(gpLogin == null){
			gpLogin = new GridPane();
	
			lblNombre = new Label("Nombre: ");
			lblPass = new Label("Clave: ");
			tfNombre = new TextField();
			tfNombre.setPromptText("Nombre de usuario");
			tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			pfPass = new PasswordField();
			pfPass.setPromptText("Clave de usuario");
			pfPass.addEventHandler(KeyEvent.KEY_RELEASED, this);

			gpLogin.add(lblNombre, 0, 0);
			gpLogin.add(lblPass, 0, 1);
			gpLogin.add(tfNombre, 1, 0);
			gpLogin.add(pfPass, 1, 1);
		}
		return gpLogin;
	}
	public void setMUsuario(ManejadorUsuario mUsuario){
		this.mUsuario = mUsuario;
	}
	public void handle(Event event){
		if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				if(!tfNombre.getText().trim().equals("") & !pfPass.getText().trim().equals("")){
					if(mUsuario.conectar(tfNombre.getText(), pfPass.getText())){
						tpPrincipal.getTabs().remove(tbLogin);
						tpPrincipal.getTabs().add(this.getContentUsuarios());
					}else{
						System.out.println("Verifique sus credenciales!");
					}
				}
			}
		}
	}
	
}