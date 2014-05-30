package org.brandon.ui;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.control.TabPane;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.TableView;
import javafx.scene.control.Button;
import javafx.scene.Scene;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.Event;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.KeyCode;
import javafx.stage.Modality;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.ToolBar;
import javafx.event.ActionEvent;
import javafx.scene.control.SelectionMode;
import javafx.beans.property.SimpleListProperty;

import java.util.ArrayList;

import org.brandon.beans.Usuario;
import org.brandon.beans.Libro;
import org.brandon.manejadores.ManejadorUsuario;
import org.brandon.manejadores.ManejadorLibro;
import org.brandon.db.Conexion;

public class Principal extends Application implements EventHandler<Event>{
	private TextField tfNombre,tfNombreA, tfAutorA, tfPrecioA, tfCantidadA;
	private PasswordField pfPass;
	private Label lblNombre, lblPass, lbNombreA, lbAutorA, lbPrecioA, lbCantidadA;
	private TabPane tpPrincipal;
	private Tab tbLogin, tbUsuarios, tbLibros, tbCRUD;
	private GridPane gpLogin, gpContentCRUD;
	private BorderPane bpUsuarios;
	private TableView<Libro> tvLibros;
	private ManejadorUsuario mUsuario;
	private ManejadorLibro mLibro;
	private Scene primaryScene;
	private Stage primaryStage;
	private Conexion conexion;
	private ToolBar tbPrincipal;
	private Button btnA, btnD, btnU, btnL;
	//FALSE=AGREGAR, TRUE=MODIFICAR
	private boolean estadoMantenimiento;
	private BorderPane bpPrincipal;
	private Libro libroModificar;

	public void start(Stage primaryStage){
		this.primaryStage=primaryStage;
		conexion = new Conexion();

		this.setMUsuario(new ManejadorUsuario(conexion));
		this.setMLibro(new ManejadorLibro(conexion));

		primaryScene = new Scene(this.getRoot());
		primaryStage.setScene(primaryScene);
		primaryStage.show();
	}
	public BorderPane getRoot(){
		if(bpPrincipal==null){
			bpPrincipal = new BorderPane();
			bpPrincipal.setCenter(this.getTabPane());
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
		}
		
		return tbLogin;
	}
	public Tab getTabLibros(){
		if(tbLibros==null){
			tbLibros=new Tab("Libros");
			tbLibros.setContent(this.getContentLibros());
		}
		return tbLibros;
	}
	public Tab getTabCRUD(){
		if(tbCRUD==null){
			tbCRUD = new Tab("Mantenimiento");
			tbCRUD.setContent(getContentCRUD());
		}
		return tbCRUD;
	}
	public GridPane getContentCRUD(){
		if(gpContentCRUD==null){
			gpContentCRUD = new GridPane();
			
			tfNombreA = new TextField();
			tfNombreA.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfAutorA = new TextField();
			tfAutorA.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfPrecioA = new TextField();
			tfPrecioA.addEventHandler(KeyEvent.KEY_RELEASED, this);
			tfCantidadA = new TextField();
			tfCantidadA.addEventHandler(KeyEvent.KEY_RELEASED, this);

			lbNombreA = new Label("Nombre: ");
			lbAutorA = new Label("Autor: ");
			lbPrecioA = new Label("Precio: ");
			lbCantidadA = new Label("Cantidad: ");

			gpContentCRUD.add(lbNombreA, 0,0);
			gpContentCRUD.add(lbAutorA, 0,1);
			gpContentCRUD.add(lbPrecioA, 0,2);
			gpContentCRUD.add(lbCantidadA, 0,3);			
		
			gpContentCRUD.add(tfNombreA, 1,0);
			gpContentCRUD.add(tfAutorA, 1,1);
			gpContentCRUD.add(tfPrecioA, 1,2);
			gpContentCRUD.add(tfCantidadA, 1,3);
		}
		return gpContentCRUD;
	}
	public TableView<Libro> getContentLibros(){
		if(tvLibros==null){
			tvLibros = new TableView<Libro>();

			tvLibros.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

			TableColumn<Libro, String> columnaNombre = new TableColumn<Libro, String>("NOMBRE COMPLETO");
			columnaNombre.setCellValueFactory(new PropertyValueFactory<Libro, String>("nombre"));

			TableColumn<Libro, String> columnaAutor = new TableColumn<Libro, String>("AUTOR");
			columnaAutor.setCellValueFactory(new PropertyValueFactory<Libro, String>("autor"));

			TableColumn<Libro, Integer> columnaPrecio = new TableColumn<Libro, Integer>("PRECIO TOTAL");
			columnaPrecio.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("precio"));

			TableColumn<Libro, Integer> columnaCantidad = new TableColumn<Libro, Integer>("CANTIDAD");
			columnaCantidad.setCellValueFactory(new PropertyValueFactory<Libro, Integer>("cantidad"));

			tvLibros.getColumns().setAll(columnaNombre, columnaAutor, columnaPrecio, columnaCantidad);

			tvLibros.setItems(mLibro.getListaDeLibros());
		}
		return tvLibros;
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
	public ToolBar getToolBar(){
		if(tbPrincipal==null){
			btnA = new Button("Agregar");
			btnA.addEventHandler(ActionEvent.ACTION, this);
			btnD = new Button("Eliminar");
			btnD.addEventHandler(ActionEvent.ACTION, this);
			btnU = new Button("Modificar");
			btnU.addEventHandler(ActionEvent.ACTION, this);
			btnL = new Button("Desconectar");
			btnL.addEventHandler(ActionEvent.ACTION, this);
	
			tbPrincipal = new ToolBar();
			tbPrincipal.getItems().add(btnA);
			tbPrincipal.getItems().add(btnD);
			tbPrincipal.getItems().add(btnU);
			tbPrincipal.getItems().add(btnL);
		}
		return tbPrincipal;
	}
	public void setMUsuario(ManejadorUsuario mUsuario){
		this.mUsuario = mUsuario;
	}
	public void setMLibro(ManejadorLibro mLibro){
		this.mLibro=mLibro;
	}
	public void setLibro(Libro libro){
		tfNombreA.setText(libro.getNombre());
		tfAutorA.setText(libro.getAutor());
		tfCantidadA.setText(String.valueOf(libro.getCantidad()));
		tfPrecioA.setText(String.valueOf(libro.getPrecio()));
	}
	public boolean validarDatos(){
		return !tfNombreA.getText().trim().equals("") && !tfAutorA.getText().trim().equals("") && !tfPrecioA.getText().trim().equals("") && !tfCantidadA.getText().trim().equals("");
	}
	public void handle(Event event){
		if(event instanceof KeyEvent){
			KeyEvent keyEvent = (KeyEvent)event;
			if(keyEvent.getCode()==KeyCode.ENTER){
				if(event.getSource().equals(tfNombre) || event.getSource().equals(pfPass)){
					if(!tfNombre.getText().trim().equals("") & !pfPass.getText().trim().equals("")){
						if(mUsuario.conectar(tfNombre.getText(), pfPass.getText())){
							Stage dialog = new Stage();
							dialog.setScene(new Scene(new Label("Bienvenido!")));
							dialog.initOwner(primaryStage);
							dialog.initModality(Modality.WINDOW_MODAL);
							dialog.show();
							getTabPane().getTabs().clear();
							getTabPane().getTabs().add(getTabLibros());
							getRoot().setTop(getToolBar());
						
						}else{
							Stage dialog = new Stage();
							dialog.setScene(new Scene(new Label("Verifique sus credenciales :(")));
							dialog.initOwner(primaryStage);
							dialog.initModality(Modality.WINDOW_MODAL);
							dialog.show();
						}
					}
				}else if(event.getSource().equals(tfNombreA) || event.getSource().equals(tfAutorA) || event.getSource().equals(tfPrecioA) || event.getSource().equals(tfCantidadA)){
					if(validarDatos()){
						Libro libro = new Libro(0, Integer.parseInt(tfPrecioA.getText()), Integer.parseInt(tfCantidadA.getText()), tfNombreA.getText(), tfAutorA.getText());
						if(estadoMantenimiento){
							libro.setIdLibro(libroModificar.getIdLibro());
							mLibro.modificarLibro(libro);
						}else{
							mLibro.agregarLibro(libro);
							
						}
						getTabPane().getTabs().remove(getTabCRUD());
					}
				}
			}
		}else if(event instanceof ActionEvent){
			if(event.getSource().equals(btnD)){
				ObservableList<Libro> libros = getContentLibros().getSelectionModel().getSelectedItems();
				ArrayList<Libro> listaNoObservable = new ArrayList<Libro>(libros);
				for(Libro libro : listaNoObservable){
					mLibro.eliminarLibro(libro);
				}
			}else if(event.getSource().equals(btnL)){
				getTabPane().getTabs().clear();
				getTabPane().getTabs().add(getTabContent());
				getRoot().setTop(null);
			}else if(event.getSource().equals(btnA)){
				estadoMantenimiento = false;
				if(!getTabPane().getTabs().contains(getTabCRUD()))
					getTabPane().getTabs().add(getTabCRUD());
				getTabPane().getSelectionModel().select(getTabCRUD());
				setLibro(new Libro());
			}
			else if(event.getSource().equals(btnU)){
				estadoMantenimiento = true;
				if(!getTabPane().getTabs().contains(getTabCRUD()))
					getTabPane().getTabs().add(getTabCRUD());
				getTabPane().getSelectionModel().select(getTabCRUD());
				libroModificar = getContentLibros().getSelectionModel().getSelectedItem();
				setLibro(libroModificar);
			}
		}
	}
	
}
