package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionController {
	private Connection conexion;
	
	public boolean conectar(String conexionString) {
		try {
			this.conexion = DriverManager.getConnection(conexionString);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conexion == null ? false : true;
	}
	/**
	 * Cierra la conexión a la base de datos.
	 * @return
	 */
	public boolean desconectar() {
		try {
			this.conexion.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	/**
	 * Devuelve un objeto de tipo Connection (si se ha creado con éxito)
	 * a partir de la cadena de conexión.
	 * @return
	 */
	public Connection getConexion() {
		return this.conexion;
	}
}