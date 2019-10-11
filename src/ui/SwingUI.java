package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.SwingUtilities;

import dataLayer.Tablero;
import dataLayer.Casilla; 

public class SwingUI extends JFrame{

	
	private JPanel panelTablero;
	private JPanel panelInfo;
	
	private JPanel panelControl;
	private JPanel panelBotones;
	private JButton[] casillas;
	private JLabel lblPuntaje;
	private JLabel lblMovimientos;
	private JLabel lblDuracion;
	private JLabel lblResultado;
	
	private int conteoSelecciones;
	int[] duplaSeleccionada;
	private boolean isCerrar;
	final ImageIcon iconDefault = createImageIcon("images/logo.gif");
	
	private JTextField puntaje;
	private JTextField movimientos;
	private JTextField duracion;
	private JButton jugar;
	private JButton salir;
	
	private boolean nuevoJuego;
	
	private String tema;
	
	public SwingUI(Tablero tablero, String tema){	
		this.setTitle("Concentrese");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.tema=tema;		
		iniciarComponentes(tablero);
		this.setVisible(true);
	}
	
	
	public JPanel crearTablero(Tablero tablero)
	{
		
		
		JPanel tmppanelTablero = new JPanel();						
		GridLayout layoutTablero = new GridLayout(tablero.getDimension(),tablero.getDimension());
		
		
		tmppanelTablero.setLayout(layoutTablero);
				
		casillas = new JButton[tablero.getNumeroCasillas()];
		
		 for (Casilla c: tablero.getCasillas()) {
		        
			 //ImageIcon icon = createImageIcon("images/" + c.getNombreImagen());
			 ImageIcon icon = createImageIcon(tema + "/" + c.getNombreImagen());
				
				casillas[c.getOrden()] = new JButton(icon);
				casillas[c.getOrden()].setIcon(iconDefault);
				
				casillas[c.getOrden()].setName(String.valueOf(c.getOrden()));
							
				casillas[c.getOrden()].addActionListener(new ActionListener(){
				
					
				public void actionPerformed(ActionEvent e) {
					
					JButton aux =(JButton) e.getSource();
					
					 
			
						duplaSeleccionada[conteoSelecciones]= Integer.parseInt(aux.getName());
						conteoSelecciones++;
						System.out.println(conteoSelecciones);
			
					
					if (aux.getIcon().toString().contains("logo"))
					{
						aux.setIcon(icon);					
					  
					}
					else
					{
						aux.setIcon(iconDefault);
					   
					}
				}
				}	);
				
				tmppanelTablero.add(casillas[c.getOrden()]);
			 
		    }
		
		 
		 return tmppanelTablero;
		
	}
	
	
	   protected static ImageIcon createImageIcon(String path) {
	        java.net.URL imgURL = SwingUI.class.getResource(path);
	        if (imgURL != null) {
	            return new ImageIcon(imgURL);
	        } else {
	            System.err.println("No se encontro el archivo: " + path);
	            return null;
	        }
	    }
	
	
	private void iniciarComponentes(Tablero tablero) {
		
		GridLayout layout = new GridLayout(1,2);
		this.setLayout(layout);
		this.setSize(1000,700);
		
		panelTablero = crearTablero(tablero);
		
		panelControl= new JPanel();
		
		GridLayout layoutControl = new GridLayout(4,2);
				
		panelControl.setLayout(layoutControl);
		
		
		panelBotones = new JPanel();
		GridLayout layoutBotones = new GridLayout(2,1);
		panelBotones.setLayout(layoutBotones);
		
		
		
		Font fuente=new Font("Dialog", Font.BOLD, 72);
		Font lblfuente=new Font("Dialog", Font.BOLD, 20);
		
		
		 lblResultado=new JLabel("");
		 lblResultado.setFont(lblfuente);
	
		 
		 lblPuntaje=new JLabel("Puntaje");
		 lblPuntaje.setFont(lblfuente);
		 
		 
		 lblMovimientos=new JLabel("Movimientos");
		 lblMovimientos.setFont(lblfuente);
		 lblDuracion=new JLabel("Duracion");
		 lblDuracion.setFont(lblfuente);
		
		 puntaje = new JTextField(4);	
		 puntaje.setFont(fuente);
		 puntaje.setHorizontalAlignment(JTextField.CENTER);
		 movimientos = new JTextField(4);
		 movimientos.setFont(fuente);
		 movimientos.setHorizontalAlignment(JTextField.CENTER);
		 
		 duracion = new JTextField(4);
		 duracion.setFont(lblfuente);
		 duracion.setHorizontalAlignment(JTextField.CENTER);
		 
		 
		 lblPuntaje.setLabelFor(puntaje);
		 lblMovimientos.setLabelFor(movimientos);
		 lblDuracion.setLabelFor(duracion);
		 
		
		panelControl.add(lblPuntaje);
		panelControl.add(puntaje);
		panelControl.add(lblMovimientos);
		panelControl.add(movimientos);
		panelControl.add(lblDuracion);
		panelControl.add(duracion);
		panelControl.add(lblResultado);
		
		
		
		
		jugar = new JButton("Jugar otra vez");
		salir = new JButton("Salir");
		
		panelBotones.add(jugar);
		panelBotones.add(salir);
		panelControl.add(panelBotones);
		
		add(panelTablero);		
		add(panelControl);
		
		jugar.addActionListener(new ActionListener(){
			
			public void actionPerformed(ActionEvent arg0) {
				nuevoJuego= true;				
				
			}});
		  salir.addActionListener(new ActionListener()
			
			{
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
					
			}
			
			
			);
		
				
	}
	
	public int getStatus()
	{
		int status= -1;
		
		while((this.conteoSelecciones < 2) && (!nuevoJuego)){
			this.pause();			
		}
		
		if(conteoSelecciones == 2)
		{
			status=1;
			
		}else if(nuevoJuego)
		{
			status=2;
			
		}
		return status;
		
		
	}
	
	
	public int[] getDupla() {
		
		return duplaSeleccionada;
	}
	
	public void iniciarDupla()
	{
		duplaSeleccionada = new int[2]; 
		conteoSelecciones = 0;
		
	}
	
	

	private void pause() {
		try{
			Thread.sleep(50);
			
			} catch (InterruptedException ex){
				Logger.getLogger(SwingUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		
	}

	public void cerrarDupla(int[] duplaSeleccionada) {
		try{
			Thread.sleep(1000);
			
			} catch (InterruptedException ex){
				Logger.getLogger(SwingUI.class.getName()).log(Level.SEVERE, null, ex);
			}
		
		this.casillas[duplaSeleccionada[0]].setIcon(iconDefault);
		this.casillas[duplaSeleccionada[1]].setIcon(iconDefault);
					
		
	}


	public void actualizarInfo(int puntaje, int movimientos) {
		
		this.puntaje.setText(String.valueOf(puntaje));
		this.movimientos.setText(String.valueOf(movimientos));
		
		
	}



	public void actualizarResultado() {
		
		String resultado = "Finalizaste! :)";
		lblResultado.setText(resultado);
		
	}


	public void inhabilitarDupla(int[] duplaSeleccionada) {
		
		this.casillas[duplaSeleccionada[0]].setEnabled(false);
		this.casillas[duplaSeleccionada[1]].setEnabled(false);
	}


	public void actualizarInfo(int puntaje, int movimientos, long duracion) {
		this.puntaje.setText(String.valueOf(puntaje));
		this.movimientos.setText(String.valueOf(movimientos));
		this.duracion.setText(String.valueOf(duracion)+ " Segundos");
		
	}
	
	
	
}
