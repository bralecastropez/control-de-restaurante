package org.brandon.sistema;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;

import org.brandon.ui.Login;

public class Principal extends Application{
	private Stage primaryStage;
	public void start(Stage primaryStage){
		this.primaryStage=primaryStage;
	
		Login.getInstancia().start().show();
	}
}