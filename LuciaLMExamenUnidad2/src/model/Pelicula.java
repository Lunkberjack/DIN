package model;

/**
 * Clase que representa una película.
 * 
 * De ella se almacena el título, año de salida y 
 * género. Consta de un método toString() que imita
 * el formato del enunciado del examen.
 * 
 * @author LuciaLM
 */
public class Pelicula {
	private String titulo;
	private String anio;
	private String genero;
	
	public Pelicula(String titulo, String anio, String genero) {
		this.titulo = titulo;
		this.anio = anio;
		this.genero = genero;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAnio() {
		return anio;
	}

	public void setAnio(String anio) {
		this.anio = anio;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return titulo + " | " + anio + " | " + genero + "\n";
	}
}