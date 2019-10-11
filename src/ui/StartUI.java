package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;



public class StartUI extends JFrame implements ActionListener {

	private JPanel panelOpciones;
	private JRadioButton principiante;
	private JRadioButton intermedio;
	private JRadioButton avanzado;
	private JButton comenzar;
	private JButton salir;
	private JLabel creditos;
	
	private JComboBox temas;
	
	private String tema;
	
	
	

	private int menuSeleccion;

	public StartUI() {
		this.setTitle("Concentrese");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridLayout layout = new GridLayout(5,1);
		this.setLayout(layout);
		this.setSize(400,300);
		iniciarTemas();
		iniciarOpcionesNivel();	
		
		this.setVisible(true);
		 
	}

	
	public void iniciarTemas()
	{
		
		String[] temasList = {"UN", "Futurama"};

		
		 temas = new JComboBox(temasList);
		 temas.setSelectedIndex(0);
		 temas.addActionListener(this);
		 add(temas);
		 tema="UN";
	}
	
	
	public void iniciarOpcionesNivel()
	{
		
		
		panelOpciones = new JPanel();
		principiante = new JRadioButton("Principiante");
        principiante.setMnemonic(KeyEvent.VK_P);
        principiante.setActionCommand("1");
        principiante.setSelected(true);

        intermedio = new JRadioButton("Intermedio");
        intermedio.setMnemonic(KeyEvent.VK_I);
        intermedio.setActionCommand("2");

        avanzado = new JRadioButton("Avanzado");
        avanzado.setMnemonic(KeyEvent.VK_A);
        avanzado.setActionCommand(("3"));

        ButtonGroup group = new ButtonGroup();
        group.add(principiante);
        group.add(intermedio);
        group.add(avanzado);
        
        principiante.addActionListener(this);
        intermedio.addActionListener(this);
        avanzado.addActionListener(this);
        
        panelOpciones.add(principiante);
        panelOpciones.add(intermedio);
        panelOpciones.add(avanzado);

        add(panelOpciones, BorderLayout.LINE_START);
        
        comenzar = new JButton("Jugar");
        salir = new JButton("Salir");  
        creditos = new JLabel("Hecho por Johanna Arias y Rafael Cruz.");
        
        add(comenzar);
        add(salir);
        add(creditos);
        
        comenzar.addActionListener(new ActionListener()
        		
        		{
					public void actionPerformed(ActionEvent e) {
						
						menuSeleccion =   Integer.parseInt(group.getSelection().getActionCommand());
						System.out.println(temas.getSelectedItem().toString());
						tema= temas.getSelectedItem().toString();
					}
        			
        	
        		}
        		
        		
        		);
        
        salir.addActionListener(new ActionListener()
		
		{
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
				
		}
		
		
		);
        
        
		
	}
	
	
	public int getMenuOption() {
		int response;
		
		menuSeleccion = -1;

		while (this.menuSeleccion == -1) {
			this.pause();
		}

		response = this.menuSeleccion;
		this.menuSeleccion = -1;
		this.setVisible(false);
		return response;
	
	}

	private void pause() {
		try {
			Thread.sleep(50);

		} catch (InterruptedException ex) {
			Logger.getLogger(StartUI.class.getName()).log(Level.SEVERE, null, ex);
		}

	}


	
	public void actionPerformed(ActionEvent e) {
		
		
	}


	public String getTema() {
		
		return tema;
	}


	
}
