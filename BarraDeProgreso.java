package org.brandon.utilidades;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class BarraDeProgreso extends Application implements EventHandler<Event> {
	
	private static BarraDeProgreso instancia;
	private ProgressBar pBarUno;
	private ProgressIndicator pIndUno;
	private GridPane gpContainerUno;
	private Button btnIn, btnOut;
	
	public static BarraDeProgreso getInstancia(){
		if(instancia==null){
			instancia = new BarraDeProgreso();
		}
		return instancia;
	}
	
	public Node start(){
		
		gpContainerUno = new GridPane();
		
		pBarUno = new ProgressBar();
		pBarUno.setProgress(-0.1);
		pBarUno.setPrefSize(1000, 50);
		
		
		pIndUno = new ProgressIndicator();
		pIndUno.setPrefSize(80, 80);
		pIndUno.setProgress(pBarUno.getProgress());
		
		btnIn = new Button("+");
		btnIn.addEventHandler(ActionEvent.ACTION, 
				new EventHandler<Event>(){
				@Override public void handle(Event event){
					if(event instanceof ActionEvent){
						if(event.getSource().equals(btnIn)){
							pBarUno.setProgress(pBarUno.getProgress() + 0.1);
							pIndUno.setProgress(pBarUno.getProgress());
							
						}
					}
				}
				});
		btnIn.setPrefSize(50, 50);
		btnOut = new Button("-");
		btnOut.addEventHandler(ActionEvent.ACTION, 
				new EventHandler<Event>(){
				@Override public void handle(Event event){
					if(event instanceof ActionEvent){
						if(event.getSource().equals(btnOut)){
							pBarUno.setProgress(pBarUno.getProgress() - 0.1);
							pIndUno.setProgress(pBarUno.getProgress());
						}
					}
				}
				});
		btnOut.setPrefSize(50, 50);
		
		gpContainerUno.add(btnOut, 			0, 0);
		gpContainerUno.add(pBarUno,			1, 0);
		gpContainerUno.add(pIndUno,			2, 0);
		gpContainerUno.add(btnIn,			3, 0);
		gpContainerUno.setAlignment(Pos.BOTTOM_CENTER);
		
		return gpContainerUno;
	}
	public void handle(Event event){
		
	}

	@Override
	public void start(Stage primaryStage){
		
	}
	
}