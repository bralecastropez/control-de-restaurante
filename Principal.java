package org.brandon.sistema;

import javafx.application.Application;
import javafx.stage.Stage;

import org.brandon.ui.Login;

/**
*	@author Brandon Castro
*/

public class Principal extends Application{

	private Stage primaryStage;
	public void start(Stage primaryStage){
		this.primaryStage=primaryStage;
	
		Login.getInstancia().start().show();
	}
}