package org.brandon.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.Scene;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.beans.property.SimpleListProperty;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import org.brandon.utilidades.BarraDeMenu;
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
		primaryStage.setScene(primaryScene);
		primaryStage.setTitle("Iniciar Sesion");
		primaryStage.show();
		
		return primaryStage;
	}
	public BorderPane getBPContainer(){
		if(bpContainer==null){
			bpContainer = new BorderPane();
			bpContainer.setCenter(this.getGPContainerLogin());
			bpContainer.setTop(BarraDeMenu.getInstancia().start());
		}
		
		return bpContainer;
	}
	public GridPane getGPContainerLogin(){
		if(gpContainerLogin==null){
			gpContainerLogin = new GridPane();
			gpContainerLogin.setId("font");
			
			lblNombre = new Label("Nombre: ");
			lblPass = new Label("Clave: ");
			tfNombre = new TextField();
			tfNombre.setPromptText("Nombre de usuario");
			tfNombre.addEventHandler(KeyEvent.KEY_RELEASED, this);
			pfPass = new PasswordField();
			pfPass.setPromptText("Clave de usuario");
			pfPass.addEventHandler(KeyEvent.KEY_RELEASED, this);
			
			lblLogin = new Label("Login");
			lblLogin.setId("Logintext");
			
			gpContainerLogin.add(lblLogin, 		2, 1);
			gpContainerLogin.add(lblNombre, 	4, 3);
			gpContainerLogin.add(lblPass, 		4, 4);
			gpContainerLogin.add(tfNombre, 		5, 3);
			gpContainerLogin.add(pfPass, 		5, 4);
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
				if(event.getSource().equals(tfNombre) || event.getSource().equals(pfPass)){
					if(!tfNombre.getText().trim().equals("") & !pfPass.getText().trim().equals("")){
						if(mUsuario.conectar(tfNombre.getText(), pfPass.getText())){
							Stage dialog = new Stage();
							Label lblBienvenido = new Label("Bienvenido!");
							lblBienvenido.setId("Verificar");
							dialog.setScene(new Scene(lblBienvenido));
							dialog.initOwner(primaryStage);
							dialog.initModality(Modality.WINDOW_MODAL);
							dialog.show();
						
						}else{
							Stage dialog = new Stage();
							Label lblVerifique = new Label("Verifique sus credenciales.");
							lblVerifique.setId("Verificar");
							dialog.setScene(new Scene(lblVerifique));
							dialog.initOwner(primaryStage);
							dialog.initModality(Modality.WINDOW_MODAL);
							dialog.show();
						}
					}
				}
			}
		}
	}
	public void start(Stage sStage){
	}

}