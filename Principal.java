package org.brandon.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.image.Image;
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

import org.brandon.beans.Persona;
import org.brandon.manejadores.ManejadorPersona;
import org.brandon.db.Conexion;
import org.brandon.utilidades.BarraDeProgreso;
import org.brandon.utilidades.BarraDeMenu;

public class Principal extends Application implements EventHandler<Event>{
	private BorderPane bpPrincipal;
	private TextField tfNombre;
	private PasswordField pfPass;
	private Label lblNombre, lblPass;
	private TabPane tpPrincipal;
	private Tab tbLogin, tbPersonas;
	private GridPane gpLogin;
	private TableView<Persona> tvPersonas;
	private TableColumn tvNombre, tvRol;
	private ManejadorPersona mPersona;
	private Scene primaryScene;
	private TableColumn tcColumnNombre, tcColumnRol;
	
	public void start(Stage primaryStage){
		this.setMPersona(new ManejadorPersona(new Conexion()));
		
		//primaryScene.getStylesheets().add("J:\\Proyecto III Bimestre\\recursos\\application.css");
		primaryScene = new Scene(this.getTabPane());
		
		//primaryStage.getIcons().add(new Image("J:\\Proyecto III Bimestre\\recursos\\icon.png"));
		primaryStage.setMaxHeight(800);
		primaryStage.setMaxWidth(1250);
		primaryStage.setMinHeight(800);
		primaryStage.setMinWidth(1250);
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
	public BorderPane getBorderRoot(){
		if(bpPrincipal == null){
			//bpPrincipal.setBottom(BarraDeProgreso.getInstancia().start());
			//bpPrincipal.setCenter(this.getTabPane());
			//bpPrincipal.setTop(BarraDeMenu.getInstacia().start());
		}
		return bpPrincipal;
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
		if(tvPersonas==null && tbPersonas==null){
			tbPersonas = new Tab("Lista Usuarios");
			tvNombre = new TableColumn("Nombre");
			tvRol = new TableColumn("Rol");
			tvPersonas = new TableView<Persona>();
			tvPersonas.getColumns().addAll(tvNombre, tvRol);
			tbPersonas.setClosable(false);
			tbPersonas.setContent(tvPersonas);
		}
		return tbPersonas;
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
	public void setMPersona(ManejadorPersona mPersona){
		this.mPersona = mPersona;
	}
	public void handle(Event event){
		if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				if(!tfNombre.getText().trim().equals("") & !pfPass.getText().trim().equals("")){
					if(mPersona.conectar(tfNombre.getText(), pfPass.getText())){
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