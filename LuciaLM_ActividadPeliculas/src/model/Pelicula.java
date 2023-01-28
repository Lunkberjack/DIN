package model;

import java.io.FileInputStream;

public class Pelicula {
	// Para llevar la cuenta de películas introducidas (y el id de cada una)
	private static int contadorPeliculas = 0;
	
	private int id;
	private String titulo, genero, duracion, sinopsis, pais, idioma, actores;
	private FileInputStream imagen;

	public Pelicula(String titulo, String genero, String duracion, String sinopsis, String pais, String idioma,
			String actores, FileInputStream imagen) {
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
	
	// Solo getters para aumentar la encapsulación y evitar
	// la modificación indeseada --------------------------
	
	public static int getContadorPeliculas() {
		return contadorPeliculas;
	}

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

	public FileInputStream getImagen() {
		return imagen;
	}

	public void setImagen(FileInputStream imagen) {
		this.imagen = imagen;
	}
}
