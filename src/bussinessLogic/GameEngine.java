package bussinessLogic;

import dataLayer.Casilla;
import dataLayer.Tablero;
import ui.StartUI;
import ui.SwingUI;

public class GameEngine {

	private static SwingUI ventanaJuego ;
	private static StartUI ventanaInicio;
	private static Tablero tablero;
	private static int puntaje;
	private static int movimientos;
	private static long duracion;
	private static long begin;
	//private static int estadoJuego;
	private static long end;
	
	
	public static void main(String[] args) {
				
		ventanaInicio = new StartUI();					
		seleccionarJuego();
		
	}

		
	private static void seleccionarJuego()
	{
	String tema="";
		
		int menuSeleccion = -1;
		while(menuSeleccion == -1){
			menuSeleccion = ventanaInicio.getMenuOption();
			tema=ventanaInicio.getTema();
			
		}
	
		
		if(menuSeleccion == 1){
			tablero = new Tablero(2);
			
		}
		else if (menuSeleccion == 2)
		{
			tablero = new Tablero(4);	
		}
		else if (menuSeleccion == 3)
		{
			tablero = new Tablero(6);
		}
		
		
		ventanaJuego = new SwingUI(tablero,tema);		
		iniciarJuego();
	}
	
	private static void iniciarJuego() {
				
		begin = System.currentTimeMillis();
		
		boolean IsFinal = false;

		//estadoJuego=-1;
		
		puntaje=0;
		movimientos=0;
		while (!IsFinal)
		{
			IsFinal = jugar();
		}
		
		end = System.currentTimeMillis();
		duracion =(end-begin)/1000;
		ventanaJuego.actualizarInfo(puntaje,movimientos,duracion);
		ventanaJuego.actualizarResultado();
				
		ventanaJuego.iniciarDupla();
		int estadoJuego=ventanaJuego.getStatus();
		if (estadoJuego==2)
		{
			puntaje=0;
			movimientos=0;
			ventanaJuego.dispose();
			ventanaInicio.setVisible(true);		
			seleccionarJuego();
		}					
	}

	
	
	private static boolean jugar() {

		int estadoJuego=-1;
		
		ventanaJuego.iniciarDupla();
		estadoJuego=ventanaJuego.getStatus();
				
		//dupla seleccionada
		if(estadoJuego==1)
		{
			int[] duplaSeleccionada= ventanaJuego.getDupla();
			
			if (validarDupla(duplaSeleccionada))
			{
				actualizarTablero(duplaSeleccionada);
				ventanaJuego.inhabilitarDupla(duplaSeleccionada);
			    puntaje = puntaje+10;	
			}
			else
			{
				
				ventanaJuego.cerrarDupla(duplaSeleccionada);
			}
			movimientos= movimientos+1;	
			ventanaJuego.actualizarInfo(puntaje,movimientos);
			
		}
		
		//Iniciar un nuevo juego
		else if (estadoJuego==2)
		{
			puntaje=0;
			movimientos=0;
			ventanaJuego.dispose();
			ventanaInicio.setVisible(true);		
			seleccionarJuego();
		}
		
		return validarFinal();
	}
	

	private static boolean validarFinal() {
		
		for (Casilla c:tablero.getCasillas())
		{
			if (!c.isJugada())
				return false;			  
		}
		
		return true;
		
	}

	private static void actualizarTablero(int[] duplaSeleccionada) {
		tablero.getCasillas()[duplaSeleccionada[0]].setJugada(true);
		tablero.getCasillas()[duplaSeleccionada[1]].setJugada(true);
		
	}

	private static boolean validarDupla(int[] duplaSeleccionada) {
		
		if(tablero.getCasillas()[duplaSeleccionada[0]].getOrden() == tablero.getCasillas()[duplaSeleccionada[1]].getOrden())
			return false;
		else if(tablero.getCasillas()[duplaSeleccionada[0]].getValor() == tablero.getCasillas()[duplaSeleccionada[1]].getValor())
			return true;
		else			
			return false;
	}

	
	

}
