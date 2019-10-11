package dataLayer;

import java.util.Random;

public class Tablero {

	private Casilla[] casillas;
	private int dimension;
	private int numeroCasillas;
	
	public Tablero(int dimension)
	{
		this.dimension = dimension;
		
		numeroCasillas= dimension*dimension;
		casillas = new Casilla[numeroCasillas];
		
		
	    int duplas[]=new int[numeroCasillas/2];
		
	    
		Random r = new Random();											
		int valorImagen;
		
		for (int i=0; i<numeroCasillas;i++)
					
		{
			valorImagen=r.nextInt(numeroCasillas/2);
						
			if(duplas[valorImagen]<2){               
				casillas[i] = new Casilla(valorImagen + ".jpg",valorImagen,i);
				duplas[valorImagen]++;
            }else{
                i--;           
            }
			
					
		}
		
		
	}

	public int getDimension() {
		return dimension;
	}

	public int getNumeroCasillas() {
		return numeroCasillas;
	}

	public Casilla[] getCasillas() {
		return casillas;
	}
	
	
	
	
}
