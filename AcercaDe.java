package org.brandon.utilidades;

import javafx.stage.Stage;
import javafx.stage.Modality;
import javafx.scene.Scene;
import javafx.scene.control.Label;
/**
*	@author Brandon Castro
*/

public class AcercaDe{
	private Stage acercaDe, dialogTrue, dialogFalse;
	private Label lblVerifique, lblBienvenido, lblUno;
	private Scene sceneTrue, sceneFalse, sceneUno;
	private static AcercaDe instancia;
	
	public static AcercaDe getInstancia(){
		if(instancia==null){
			instancia = new AcercaDe();
		}
		return instancia;
	}
	
	public Stage getAcercaDe(){
		if(acercaDe==null){
			acercaDe = new Stage();
		    	
		    lblUno = new Label("Creado Por Brandon Castro");
			
			sceneUno = new Scene(lblUno);
			sceneUno.getStylesheets().add("Login.css");
			
			lblUno.setId("Verificar");
			 
		    acercaDe.setTitle("Acerca de...");
		    acercaDe.setScene(sceneUno);
		    acercaDe.setMaxHeight(150);
		    acercaDe.setMaxWidth(220);
		    acercaDe.setMinHeight(150);
		    acercaDe.setMinWidth(220);
		    acercaDe.setResizable(false);
		}
		return acercaDe;
	}
	
	public Stage getDialogTrue(Stage sUno){
		if(dialogTrue==null){
			lblBienvenido = new Label("Bienvenido!");
			
			sceneTrue = new Scene(lblBienvenido);
			sceneTrue.getStylesheets().add("Login.css");
			
			lblBienvenido.setId("Verificar");
			
			dialogTrue = new Stage();
			dialogTrue.setScene(sceneTrue);
			dialogTrue.initOwner(sUno);
			dialogTrue.initModality(Modality.WINDOW_MODAL);
			dialogTrue.setMaxHeight(100);
		    dialogTrue.setMaxWidth(135);
		    dialogTrue.setMinHeight(100);
		    dialogTrue.setMinWidth(135);
		    dialogTrue.setResizable(false);
		}
		return dialogTrue;
	}
	
	public Stage getDialogFalse(Stage sUno){
		if(dialogFalse==null){
			lblVerifique = new Label("Verifique sus datos.");			
			
			sceneFalse = new Scene(lblVerifique);
			sceneFalse.getStylesheets().add("Login.css");
			
			lblVerifique.setId("Verificar");
			
			dialogFalse = new Stage();
			dialogFalse.setScene(sceneFalse);
			dialogFalse.initOwner(sUno);
			dialogFalse.initModality(Modality.WINDOW_MODAL);
			dialogFalse.setMaxHeight(150);
		    dialogFalse.setMaxWidth(220);
		    dialogFalse.setMinHeight(150);
		    dialogFalse.setMinWidth(220);
		    dialogFalse.setResizable(false);
		}
		return dialogFalse;
	}

}