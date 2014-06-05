package org.brandon.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;

import org.brandon.utilidades.BarraDeMenu;
import org.brandon.utilidades.AcercaDe;
import org.brandon.sistema.ModuloChef;
import org.brandon.sistema.ModuloAdministrador;
import org.brandon.sistema.ModuloEmpleado;
import org.brandon.manejadores.ManejadorUsuario;
import org.brandon.db.Conexion;

/**
*	@author Brandon Castro
*/

public class Principal extends Application implements EventHandler<Event>{
	private Stage primaryStage;
	private Scene primaryScene;
	private GridPane gpContainerLogin;
	private BorderPane bpContainerPrincipal;
	private static Principal instancia;
	private TextField tfNombre;
	private Label lblNombre, lblPass,lblLogin;
	private PasswordField pfPass;
	private ManejadorUsuario mUsuario;
	private Conexion conexion;
	private Button btnLogin;
	
	
	/**
	* @return La instancia de la Clase Principal
	*/
	public static Principal getInstancia(){
		if(instancia==null){
			instancia = new Principal();
		}
		return instancia;
	}
	public void start(Stage primaryStage){
		this.primaryStage=primaryStage;
		
		conexion = new Conexion();
	
		this.setMUsuario(new ManejadorUsuario(conexion));
		
		primaryScene = new Scene(this.getContenedorPrincipal());
		primaryScene.getStylesheets().add("Login.css");
		
		primaryStage = new Stage();
		//Alto
		primaryStage.setMaxHeight(350);
		primaryStage.setMinHeight(350);
		//Largo
		primaryStage.setMaxWidth(250);
		primaryStage.setMinWidth(250);
		primaryStage.setResizable(false);
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Bienvenido");
		primaryStage.show();
	}
	public BorderPane getContenedorPrincipal(){
		if(bpContainerPrincipal==null){
			bpContainerPrincipal = new BorderPane();
			bpContainerPrincipal.setTop(BarraDeMenu.getInstancia().menuBar());
			bpContainerPrincipal.setCenter(this.getContainerLogin());
			bpContainerPrincipal.setId("font");
			
			btnLogin = new Button("Iniciar Sesion");
			btnLogin.addEventHandler(ActionEvent.ACTION, this);
			btnLogin.setAlignment(Pos.BOTTOM_RIGHT);
			btnLogin.setId("ButtonLogin");
			
			bpContainerPrincipal.setBottom(btnLogin);
		}
		return bpContainerPrincipal;
	}
	public GridPane getContainerLogin(){
		if(gpContainerLogin==null){
			gpContainerLogin = new GridPane();
			gpContainerLogin.setId("font");
			gpContainerLogin.setAlignment(Pos.CENTER);
			
			lblNombre = new Label("Nombre: ");
			lblPass = new Label("Clave: ");
			tfNombre = new TextField();
			tfNombre.setPromptText("Nombre de usuario");
			tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfNombre.clear();
			pfPass = new PasswordField();
			pfPass.setPromptText("Clave de usuario");
			pfPass.addEventHandler(KeyEvent.KEY_RELEASED, this);
			pfPass.clear();
			
			lblLogin = new Label("Bienvenido");
			lblLogin.setAlignment(Pos.TOP_CENTER);
			lblLogin.setId("Logintext");
			
			gpContainerLogin.add(lblLogin, 		0, 0, 2, 1);
			gpContainerLogin.add(lblNombre, 	0, 1);
			gpContainerLogin.add(lblPass, 		0, 2);
			gpContainerLogin.add(tfNombre, 		1, 1);
			gpContainerLogin.add(pfPass, 		1, 2);
		}
		
		return gpContainerLogin;
	}
	public void setMUsuario(ManejadorUsuario mUsuario){
		this.mUsuario = mUsuario;
	}
	public void handle(Event event){
		if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				if(event.getSource().equals(tfNombre) || event.getSource().equals(pfPass) || event.getSource().equals(btnLogin)){
					if(!tfNombre.getText().trim().equals("") & !pfPass.getText().trim().equals("")){
						if(mUsuario.conectar(tfNombre.getText(), pfPass.getText())){
							switch(mUsuario.getRol(tfNombre.getText(), pfPass.getText())){
							case 1:
								//AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
								tfNombre.clear();
								pfPass.clear();
								System.out.println("Bienvenido Administrador");
								break;
							case 2:
								//AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
								tfNombre.clear();
								pfPass.clear();
								System.out.println("Bienvenido Chef");
								break;
							case 3:
								//AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
								tfNombre.clear();
								pfPass.clear();
								System.out.println("Bienvenido Empleado");
								break;
							default:
								System.out.println("Rol no concuerda");
								break;
							}
						}else{
							AcercaDe.getInstancia().getDialogFalse(primaryStage).show();
							tfNombre.clear();
							pfPass.clear();
						}
					}
				}
			}
		}else if(event instanceof ActionEvent){
			if(event.getSource().equals(btnLogin)){
				if(!tfNombre.getText().trim().equals("") & !pfPass.getText().trim().equals("")){
					if(mUsuario.conectar(tfNombre.getText(), pfPass.getText())){
						switch(mUsuario.getRol(tfNombre.getText(), pfPass.getText())){
							case 1:
								//AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
								tfNombre.clear();
								pfPass.clear();
								System.out.println("Bienvenido Administrador");
								break;
							case 2:
								//AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
								tfNombre.clear();
								pfPass.clear();
								System.out.println("Bienvenido Chef");
								break;
							case 3:
								//AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
								tfNombre.clear();
								pfPass.clear();
								System.out.println("Bienvenido Empleado");
								break;
							default:
								System.out.println("Rol no concuerda");
								break;
							}
					}else{
						AcercaDe.getInstancia().getDialogFalse(primaryStage).show();
						tfNombre.clear();
						pfPass.clear();
					}
				}
			}
		}
	}
}