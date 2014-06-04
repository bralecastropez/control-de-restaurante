package org.brandon.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.Statement;

/**
*	@author Brandon Castro
*/

public class Conexion{
	private Connection cnx;
	private Statement stm;
	public Conexion(){
		try{		
			//Cargamos el driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			//cargamos la conexion a partir del driver
			
			//Driver Kinal
			cnx = DriverManager.getConnection("jdbc:sqlserver://LABI23-17;databaseName=dbRestauranteProyecto", "SQLServerBrowser", "brandon");
			//Driver Casa
			//cnx = DriverManager.getConnection("jdbc:sqlserver://BRANDON-PC\\SQLEXPRESS;databaseName=dbRestauranteProyecto", "brandon", "brandon");
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
