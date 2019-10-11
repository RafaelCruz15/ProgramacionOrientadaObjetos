package dataLayer;

public class Casilla {
	
	boolean jugada;
	int orden;
	int valor;
	String nombreImagen;
	
	
	public Casilla(String nombreImagen, int valor, int orden)
	{
		this.nombreImagen= nombreImagen;
		this.valor= valor;
		this.orden = orden;
		
		
	}
	
	public boolean isJugada() {
		return jugada;
	}
	public void setJugada(boolean jugada) {
		this.jugada = jugada;
	}

	public int getValor() {
		return valor;
	}
	public String getNombreImagen() {
		return nombreImagen;
	}

	public int getOrden() {
		
		return orden;
	}

	
}
