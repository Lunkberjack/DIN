package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
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

	public void actualizar() {
		
	}
	
	public boolean aniadirPelicula(int contadorPeliculas, String titulo, String genero, String duracion, String sinopsis, String pais, String idioma, String actores, FileInputStream fotografia) {
		boolean aniadido = false;
		try {
			this.statement.executeUpdate("INSERT INTO peliculas VALUES(" + contadorPeliculas + "," + "\"" + titulo + "\"" + "," +  "\"" + genero + "\"" + "," + "\"" + duracion + "\"" + "," + "\"" + sinopsis + "\"" + "," + "\"" + pais + "\"" + "," + "\"" + idioma + "\"" + "," + "\"" + actores + "\"" + "," + "\"" + fotografia + "\"" + ")");
			System.out.println("Se ha añadido");
			return aniadido;
		} catch (SQLException e) {
			e.printStackTrace();
			return aniadido;
		}
	}

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

	public boolean modificar(int id, String titulo, String genero, String duracion, String sinopsis, String pais,
			String idioma, String actores, File imagen) throws FileNotFoundException {
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
	        		        FileInputStream fis = new FileInputStream(imagen);
	        prep.setBinaryStream(8, (InputStream)fis, (int)imagen.length());
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