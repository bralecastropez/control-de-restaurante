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
	private TitledPane tpUno, tpDos, tpTres;
	private Accordion aUno;
	private MenuItem miUno,miDos,miTres,miCuatro,miCinco;
	private Label lblUno;
	private Stage pStageUno;
	
	public static BarraDeMenu getInstacia(){
		if(instancia==null){
			instancia = new BarraDeMenu();
		}
		return instancia;
	}
	
	public Node start(){
		
		tpUno = new TitledPane("T1", new Button("B1"));
		tpDos = new TitledPane("T2", new Button("B2"));
		tpTres = new TitledPane("T3", new Button("B3"));
		 
		aUno = new Accordion();
		aUno.getPanes().addAll(tpUno, tpDos, tpTres);
		
		miUno = new MenuItem("Cerrar");
		miUno.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		        System.exit(0);
		    }
		});
		miDos = new MenuItem("¿Te gusto?");
		miTres = new MenuItem("Tema");
		miCuatro = new MenuItem("Contactanos");
		miCinco = new MenuItem("Acerca de...");
		miCinco.setOnAction(new EventHandler<ActionEvent>() {
		    public void handle(ActionEvent t) {
		    	pStageUno = new Stage();
		    	
		    	lblUno = new Label("Creado Por Brandon Castro");
		    	lblUno.setStyle("-fx-background-color: #000000,"
		    		+	"linear-gradient(#7ebcea, #2f4b8f),"
		    		+	"linear-gradient(#426ab7, #263e75),"
		    		+	"linear-gradient(#395cab, #223768);"
		    		+	"-fx-background-insets: 0,1,2,3;"
		    		+	"-fx-background-radius: 3,2,2,2;"
		    		+	"-fx-padding: 12 30 12 30;"
		    		+	"-fx-text-fill: white;"
		    		+	"-fx-font-size: 12px;");
		    	
		    	pStageUno.setTitle("Acerca de...");
		        pStageUno.setScene(new Scene(lblUno));
		        pStageUno.show();
		        pStageUno.setMaxHeight(150);
		        pStageUno.setMaxWidth(215);
		        pStageUno.setMinHeight(150);
		        pStageUno.setMinWidth(215);
		        pStageUno.setResizable(false);
		    }
		});
		
		mUno= new Menu("Aplicacion");
		mUno.getItems().add(miUno);
		mUno.getItems().add(miDos);
		mDos= new Menu("Herramientas");
		mDos.getItems().add(miTres);
		mTres= new Menu("Ayuda");
		mTres.getItems().add(miCuatro);
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
		// TODO Auto-generated method stub
		
	}
	

}
