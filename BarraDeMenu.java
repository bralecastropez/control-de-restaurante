package org.brandon.utilidades;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import org.brandon.utilidades.AcercaDe;

public class BarraDeMenu extends Application implements EventHandler<Event>{

	private static BarraDeMenu instancia;
	private MenuBar mbUno;
	private Menu mUno, mDos, mTres;
	@SuppressWarnings("unused")
	private MenuItem miUno,miDos,miTres,miCuatro,miCinco;
	
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
	public void start(Stage primaryStage){
			
	}

	@Override
	public void handle(Event arg0) {
	
	}
	

}

