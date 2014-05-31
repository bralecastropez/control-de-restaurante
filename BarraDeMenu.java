package org.brandon.utilidades;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TitledPane;
import javafx.stage.Stage;

public class BarraDeMenu extends Application implements EventHandler<Event>{

	private static BarraDeMenu instancia;
	private MenuBar mbUno;
	private Menu mUno, mDos, mTres;
	private MenuItem miUno,miDos,miTres,miCuatro,miCinco;
	private Label lblUno;
	private Stage pStageUno;
	
	public static BarraDeMenu getInstancia(){
		if(instancia==null){
			instancia = new BarraDeMenu();
		}
		return instancia;
	}
	
	public MenuBar menuBar(){
		
		miUno = new MenuItem("Cerrar");
		miUno.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		        System.exit(0);
		    }
		});
		//miDos = new MenuItem("¿Te gusto?");
		//miTres = new MenuItem("Tema");
		//miCuatro = new MenuItem("Contactanos");
		miCinco = new MenuItem("Acerca de...");
		miCinco.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	AcercaDe.getInstancia().getAcercaDe().show();
		    }
		});
		
		mUno= new Menu("Aplicacion");
		mUno.getItems().add(miUno);
		//mUno.getItems().add(miDos);
		mDos= new Menu("Herramientas");
		//mDos.getItems().add(miTres);
		mTres= new Menu("Ayuda");
		//mTres.getItems().add(miCuatro);
		mTres.getItems().add(miCinco);
		
		mbUno = new MenuBar();
		mbUno.getMenus().addAll(mUno,mDos,mTres);
				
		return mbUno;
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
			
	}

	@Override
	public void handle(Event arg0) {
	
	}
	

}
