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
import org.brandon.manejadores.ManejadorUsuario;
import org.brandon.db.Conexion;

public class Login extends Application implements EventHandler<Event>{
	private Stage primaryStage;
	private Scene primaryScene;
	private GridPane gpContainerLogin;
	private BorderPane bpContainer;
	private static Login instancia;
	private TextField tfNombre;
	private Label lblNombre, lblPass,lblLogin;
	private PasswordField pfPass;
	private ManejadorUsuario mUsuario;
	private Conexion conexion;
	private Button btnLogin;
	
	public static Login getInstancia(){
		if(instancia==null){
			instancia = new Login();
		}
		return instancia;
	}
	
	
	public Stage start(){
		conexion = new Conexion();
	
		this.setMUsuario(new ManejadorUsuario(conexion));
		
		primaryScene = new Scene(this.getBPContainer());
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
		
		return primaryStage;
	}
	public BorderPane getBPContainer(){
		if(bpContainer==null){
			bpContainer = new BorderPane();
			bpContainer.setCenter(this.getGPContainerLogin());
			bpContainer.setTop(BarraDeMenu.getInstancia().menuBar());
			bpContainer.setId("font");
			btnLogin = new Button("Iniciar Sesion");
			btnLogin.addEventHandler(ActionEvent.ACTION, this);
			btnLogin.setAlignment(Pos.BOTTOM_RIGHT);
			//btnLogin.setPrefSize(300, 50);
			btnLogin.setId("ButtonLogin");
			
			bpContainer.setBottom(btnLogin);
		}
		
		return bpContainer;
	}
	public GridPane getGPContainerLogin(){
		if(gpContainerLogin==null){
			gpContainerLogin = new GridPane();
			gpContainerLogin.setId("font");
			gpContainerLogin.setAlignment(Pos.CENTER);
			
			lblNombre = new Label("Nombre: ");
			lblPass = new Label("Clave: ");
			tfNombre = new TextField();
			tfNombre.setPromptText("Nombre de usuario");
			tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			pfPass = new PasswordField();
			pfPass.setPromptText("Clave de usuario");
			pfPass.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
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
							AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
							primaryStage.hide();
							AcercaDe.getInstancia().getDialogTrue(primaryStage).hide();
							Control.getInstancia().getControl().show();
						
						}else{
							AcercaDe.getInstancia().getDialogFalse(primaryStage).show();
						}
					}
				}
			}
		}else if(event instanceof ActionEvent){
			if(event.getSource().equals(btnLogin)){
				if(!tfNombre.getText().trim().equals("") & !pfPass.getText().trim().equals("")){
					if(mUsuario.conectar(tfNombre.getText(), pfPass.getText())){
						AcercaDe.getInstancia().getDialogTrue(primaryStage).show();
						primaryStage.close();
						AcercaDe.getInstancia().getDialogTrue(primaryStage).close();
						Control.getInstancia().getControl().show();
					}else{
						AcercaDe.getInstancia().getDialogFalse(primaryStage).show();
					}
				}
			}
		}
	}
	public void start(Stage sStage){
	}

}