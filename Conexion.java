package org.brandon.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

public class Conexion{
	private Connection cnx;
	private Statement stm;
	public Conexion(){
		try{		
			//Cargamos el driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//cargamos la conexion a partir del driver
			//------Driver de Casa
			cnx = DriverManager.getConnection("jdbc:sqlserver://BRANDON-PC\\SQLEXPRESS;databaseName=dbRestauranteProyecto2013155", "brandon", "brandon");
			//------Driver de Kinal
			//cnx = DriverManager.getConnection("jdbc:sqlserver://169.254.182.190:1433;databaseName=dbRestauranteProyecto2013155", "SQLServerBrowser", "brandon");
			//creamos el statement a partir de la conexion para ejecutar consultas y sentencias en sql
			stm = cnx.createStatement();
		}catch(ClassNotFoundException cl){
			cl.printStackTrace();
		}catch(SQLException sql){
			sql.printStackTrace();
		}
	}
	public boolean ejecutarSentencia(String sentencia){
		boolean resultado = false;
		try{
			resultado = stm.execute(sentencia);
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return resultado;
	}
	public ResultSet ejecutarConsulta(String consulta){
		ResultSet resultado = null;
		try{
			resultado = stm.executeQuery(consulta);
		}catch(SQLException sql){
			sql.printStackTrace();
		}
		return resultado;
	}
}