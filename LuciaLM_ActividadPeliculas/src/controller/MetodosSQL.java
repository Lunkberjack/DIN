package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/**
 * Clase especializada en realizar operaciones con la 
 * base de datos (en este caso, SQLite)
 * 
 * @author LuciaLM
 */
public class MetodosSQL {
	private Connection connection = null;
	private Statement statement = null;
	
	public MetodosSQL(Connection connection) {
		this.connection = connection;
		try {
			this.statement = (Statement) connection.createStatement();
		} catch (SQLException e) {			
			e.printStackTrace();
		}
	}
	
	/**
	 * Devuelve el ResultSet obtenido tras realizar la consulta de todos
	 * los campos de todas las películas que existen en la base de datos.
	 * 
	 * @return
	 */
	public ResultSet recibirTabla() {
		ResultSet result = null;
		try {
			result = statement.executeQuery("SELECT * FROM peliculas");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * Hace que la base de datos se limpie cada vez que reiniciamos la
	 * aplicación (este método se llama en inicializar()). Así, si hemos
	 * cometido cualquier error en las pruebas, podemos deshacerlo sin
	 * ningún problema en la siguiente sesión.
	 */
	public void tablaDeCero() {
		try {
			this.statement.executeUpdate("DELETE FROM peliculas;");
		} catch (SQLException e) {
			e.printStackTrace();
		} 
	}
	
	/**
	 * Añade una nueva película a la base de datos.
	 * El contador (propiedad estática) será el id de cada una de ellas.
	 * 
	 * @param contadorPeliculas
	 * @param titulo
	 * @param genero
	 * @param duracion
	 * @param sinopsis
	 * @param pais
	 * @param idioma
	 * @param actores
	 * @param fotografia
	 * @return
	 */
	public boolean aniadirPelicula(int contadorPeliculas, String titulo, String genero, String duracion,
			String sinopsis, String pais, String idioma, String actores, byte[] fotografia) {
		boolean aniadido = false;
		try {
			// PreparedStatement: más seguro, protege contra inyección de SQL.
			PreparedStatement prep = connection.prepareStatement("INSERT INTO peliculas VALUES(?,?,?,?,?,?,?,?,?);");
			prep.setInt(1, contadorPeliculas);
			prep.setString(2, titulo);
			prep.setString(3, genero);
			prep.setString(4, duracion);
			prep.setString(5, sinopsis);
			prep.setString(6, pais);
			prep.setString(7, idioma);
			prep.setString(8, actores);
			if(fotografia != null) {
				prep.setBytes(9, fotografia);
			}
			prep.execute(); // Tras añadir todos los parámetros, ejecutamos.
			System.out.println("Se ha añadido");
			return aniadido;
		} catch (SQLException e) {
			e.printStackTrace();
			return aniadido;
		}
	}

	/**
	 * Elimina una película de la base de datos, recibiendo su id como parámetro.
	 * 
	 * @param id
	 * @return
	 */
	public boolean eliminar(int id) {
		boolean eliminado = false;
		try {
			this.statement.executeUpdate("DELETE FROM peliculas WHERE id=" + id  + ";");
			return eliminado;
		} catch (SQLException e) {
			e.printStackTrace();
			return eliminado;
		}
	}

	/**
	 * Ejecuta un UPDATE de todos los datos que hubiera en los TextField para la Película
	 * seleccionada. Los cambios se pueden ver en la base de datos y en la interfaz a
	 * tiempo real (gracias al método actualizar()).
	 * 
	 * @param id
	 * @param titulo
	 * @param genero
	 * @param duracion
	 * @param sinopsis
	 * @param pais
	 * @param idioma
	 * @param actores
	 * @param imagen
	 * @return
	 */
	public boolean modificar(int id, String titulo, String genero, String duracion, String sinopsis, String pais,
			String idioma, String actores, byte[] imagen) {
		boolean modificado = false;
		try {
			PreparedStatement prep = connection.prepareStatement("UPDATE peliculas SET titulo= ?, genero=?, duracion=?, sinopsis=?, pais=?,"
					+ "idioma=?, actores=?, imagen=? WHERE id=?;");
	        prep.setString(1, titulo);
	        prep.setString(2, genero);
	        prep.setString(3, duracion);
	        prep.setString(4, sinopsis);
	        prep.setString(5, pais);
	        prep.setString(6, idioma);
	        prep.setString(7, actores);
	        if(imagen != null) {
	        	prep.setBytes(8, imagen);
	        }
	        prep.setInt(9, id);
	        prep.execute();
			return modificado;
		} catch (SQLException e) {
			e.printStackTrace();
			return modificado;
		}
	}
}