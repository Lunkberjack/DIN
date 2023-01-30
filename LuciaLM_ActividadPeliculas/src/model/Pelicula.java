package model;

import java.io.FileInputStream;
import java.io.InputStream;

public class Pelicula {
	// Para llevar la cuenta de películas introducidas (y el id de cada una)
	private static int contadorPeliculas = 0;
	
	private int id;
	private String titulo, genero, duracion, sinopsis, pais, idioma, actores;
	private byte[] imagen;

	public Pelicula(String titulo, String genero, String duracion, String sinopsis, String pais, String idioma,
			String actores, byte[] imagen) {
		this.id = ++contadorPeliculas; // automáticamente aumenta cada vez que instanciamos una peli
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.sinopsis = sinopsis;
		this.pais = pais;
		this.idioma = idioma;
		this.actores = actores;
		this.imagen = imagen;
	}
	
	public Pelicula(int id, String titulo, String genero, String duracion, String sinopsis, String pais, String idioma,
			String actores, byte[] imagen) {
		this.id = id;
		this.titulo = titulo;
		this.genero = genero;
		this.duracion = duracion;
		this.sinopsis = sinopsis;
		this.pais = pais;
		this.idioma = idioma;
		this.actores = actores;
		this.imagen = imagen;
	}
	
	public static int getContadorPeliculas() {
		return contadorPeliculas;
	}
	
	public static void setContadorPeliculas(int contador) {
		Pelicula.contadorPeliculas = contador;
	}
	
	// Solo getter para aumentar la encapsulación y evitar
	// la modificación indeseada --------------------------

	public int getId() {
		return id;
	}
	// ----------------------------------------------------

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public String getDuracion() {
		return duracion;
	}

	public void setDuracion(String duracion) {
		this.duracion = duracion;
	}

	public String getSinopsis() {
		return sinopsis;
	}

	public void setSinopsis(String sinopsis) {
		this.sinopsis = sinopsis;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public String getIdioma() {
		return idioma;
	}

	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}

	public String getActores() {
		return actores;
	}

	public void setActores(String actores) {
		this.actores = actores;
	}

	public byte[] getImagen() {
		return imagen;
	}

	public void setImagen(byte[] imagen) {
		this.imagen = imagen;
	}
}
