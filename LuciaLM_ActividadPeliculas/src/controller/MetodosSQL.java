package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * 
 * @author LuciaLM
 */
public class MetodosSQL {
	private Connection connection=null;
	private Statement statement=null;
	
	/**
	 * Constructor especializado en inicializar objetos
	 * de tipo DatabaseManager a partir de un objeto de conexión
	 * que no puede ser nulo.
	 * @param connection - Objeto de conexión
	 */
	public MetodosSQL(Connection connection) {
		this.connection = connection;
		try {
			this.statement = connection.createStatement();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}

	/**
	 * Añade una película pasando valores para sus campos.
	 * @param id
	 * @param modelo
	 * @param precio
	 * @param anio
	 * @param km
	 * @param codigoFab
	 * @return
	 */
	public boolean aniadirPelicula(int contadorPeliculas, String titulo, String genero, int duracion, String sinopsis, String pais, String idioma, String actores, String fotografia) {
		boolean aniadido = false;
		try {
			//this.statement.executeUpdate("INSERT INTO peliculas VALUES(" + contadorPeliculas + "," + "\"" + titulo + "\"" + "," +  "\"" + genero + "\"" + "," + duracion + "," + "\"" + sinopsis + "\"" + "," + "\"" + pais + "\"" + "," + "\"" + idioma + "\"" + "," + "\"" + actores + "\"" + "," + "\"" + fotografia + "\"" + ")");
			this.statement.executeUpdate("INSERT INTO peliculas(id,titulo,genero) VALUES (1,\"si\", \"lucha\");");
			return aniadido;
		} catch (SQLException e) {
			e.printStackTrace();
			return aniadido;
		}
	}
	/**
	 * Elimina un registro de la una tabla pasando tanto la tabla como el campo como la condición.
	 * @param tabla
	 * @param campo
	 * @param condicion
	 * @return
	 */
	public boolean eliminarRegistro(String tabla, String campo, String condicion) {
		boolean eliminado = false;
		try {
			this.statement.executeUpdate("DELETE FROM " + tabla + " WHERE " + tabla + "." +
					campo + "=\"" + condicion + "\"" + ";"); 
			return eliminado;
		} catch (SQLException e) {
			e.printStackTrace();
			return eliminado;
		}
	}
	/**
	 * Elimina un registro de la una tabla pasando tanto la tabla como el campo como la condición.
	 * @param tabla
	 * @param campo
	 * @param condicion
	 * @return
	 */
	public boolean eliminarRegistro(String tabla, String campo, int condicion) {
		boolean eliminado = false;
		try {
			this.statement.executeUpdate("DELETE FROM " + tabla + " WHERE " + tabla + "." + campo +
					"=" + condicion + ";"); 
			return eliminado;
		} catch (SQLException e) {
			e.printStackTrace();
			return eliminado;
		}
	}
	/**
	 * Modifica un registro tomando varios parámetros para saber en qué 
	 * tabla se debe modificar y la condición que se debe cumplir.
	 * 
	 * @param tabla
	 * @param campoModificar - campo que se quiere modificar
	 * @param valorModificar - valor que se quiere dar a ese campo
	 * @param campoCondicion - campo en el que buscar la condicion
	 * @param condicion - condicion que se debe cumplir
	 * @return
	 */
	public boolean modificarRegistro(String tabla, String campoModificar, int valorModificar,
			String campoCondicion, String condicion) {
		boolean modificado = false;
		try {
			this.statement.executeUpdate("UPDATE " + tabla + " SET " + campoModificar + "=" + 
					valorModificar + " WHERE " + campoCondicion + "=\"" + condicion + "\"" + ";"); 
			return modificado;
		} catch (SQLException e) {
			e.printStackTrace();
			return modificado;
		}
	}
	/**
	 * Modifica un registro tomando varios parámetros para saber en qué 
	 * tabla se debe modificar y la condición que se debe cumplir.
	 * 
	 * @param tabla
	 * @param campoModificar - campo que se quiere modificar
	 * @param valorModificar - valor que se quiere dar a ese campo
	 * @param campoCondicion - campo en el que buscar la condicion
	 * @param condicion - condicion que se debe cumplir
	 * @return
	 */
	public boolean modificarRegistro(String tabla, String campoModificar, int valorModificar,
			String campoCondicion, int condicion) {
		boolean modificado = false;
		try {
			this.statement.executeUpdate("UPDATE " + tabla + " SET " + campoModificar + "=" + 
					valorModificar + " WHERE " + campoCondicion + "=" + condicion + ";"); 
			return modificado;
		} catch (SQLException e) {
			e.printStackTrace();
			return modificado;
		}
	}
	/**
	 * Modifica un registro tomando varios parámetros para saber en qué 
	 * tabla se debe modificar y la condición que se debe cumplir.
	 * 
	 * @param tabla
	 * @param campoModificar - campo que se quiere modificar
	 * @param valorModificar - valor que se quiere dar a ese campo
	 * @param campoCondicion - campo en el que buscar la condicion
	 * @param condicion - condicion que se debe cumplir
	 * @return
	 */
	public boolean modificarRegistro(String tabla, String campoModificar, String valorModificar,
			String campoCondicion, int condicion) {
		boolean modificado = false;
		try {
			this.statement.executeUpdate("UPDATE " + tabla + " SET " + campoModificar + "=\"" + 
					valorModificar + "\"" + " WHERE " + campoCondicion + "=" + condicion + ";"); 
			return modificado;
		} catch (SQLException e) {
			e.printStackTrace();
			return modificado;
		}
	}
	/**
	 * Modifica un registro tomando varios parámetros para saber en qué 
	 * tabla se debe modificar y la condición que se debe cumplir.
	 * 
	 * @param tabla
	 * @param campoModificar - campo que se quiere modificar
	 * @param valorModificar - valor que se quiere dar a ese campo
	 * @param campoCondicion - campo en el que buscar la condicion
	 * @param condicion - condicion que se debe cumplir
	 * @return
	 */
	public boolean modificarRegistro(String tabla, String campoModificar, String valorModificar,
			String campoCondicion, String condicion) {
		boolean modificado = false;
		try {
			this.statement.executeUpdate("UPDATE " + tabla + " SET " + campoModificar + "=\"" + 
					valorModificar + "\"" + " WHERE " + campoCondicion + "=\"" + condicion + "\"" + ";"); 
			return modificado;
		} catch (SQLException e) {
			e.printStackTrace();
			return modificado;
		}
	}
	/**
	 * Seleccionar todos los registros ordenados por el campo y el orden deseado.
	 * @param tabla
	 * @param campo 
	 * @param orden - asc o desc
	 */
	public void mostrarRegistrosSinFiltro(String tabla, String campo, String orden) {
		try {
			ResultSet setResultado = statement.executeQuery("SELECT * FROM " + tabla + " ORDER BY " + campo + " " + orden +";");
			if(tabla.equalsIgnoreCase("coche")) {
				while(setResultado.next()) {
					System.out.print(setResultado.getInt(1) + ", " + setResultado.getString(2));
					System.out.print(", " + setResultado.getInt(3) + ", " + setResultado.getInt(4));
					System.out.println(", " + setResultado.getInt(5) + ", " + setResultado.getInt(6));
				}
			} else if(tabla.equalsIgnoreCase("fabricante")) {
				while(setResultado.next()) {
					System.out.println(setResultado.getInt(1) + ". " + setResultado.getString(2));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Muestra registros de una tabla filtrando por campos.
	 * Introducir la tabla, los campos (si son varios, separados por una coma, ej: "id, modelo"),
	 * el campo o campos por los que se quiere ordenar y el orden (asc o desc).
	 * @param tabla
	 * @param campos
	 * @param campoOrdenar
	 * @param orden
	 */
	public void mostrarRegistrosFiltrando(String tabla, String campo, String filtro, String campoOrdenar, String orden) {
		try {
			// SELECT * FROM coche WHERE modelo = "Prius" ORDER BY "id" ASC;
			// No sé por qué da error.
			PreparedStatement ps = this.connection.prepareStatement("SELECT * FROM ? WHERE ?.? = ? ORDER BY ? ?");
			ps.setString(1, tabla);
			ps.setString(2, tabla);
			ps.setString(3, campo);
			ps.setString(4, filtro);
			ps.setString(5, campoOrdenar);
			ps.setString(6, orden);
			ResultSet rs = ps.executeQuery();
			// Se borran los parámetros
			while(rs.next()) {
				System.out.println(rs.getInt(1) + ", " + rs.getString(2));
				System.out.print(", " + rs.getInt(3) + ", " + rs.getInt(4));
				System.out.println(", " + rs.getInt(5) + ", " + rs.getInt(6));
			}
			ps.clearParameters();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}