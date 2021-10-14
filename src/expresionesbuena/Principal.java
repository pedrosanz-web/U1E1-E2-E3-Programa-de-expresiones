
package expresionesbuena;

import java.io.IOException;
import java.util.Scanner;


public class Principal {


   
    public static void main(String[] args) throws IOException {
        Scanner leer=new Scanner(System.in);
	boolean Continuar= true;
	int opcion = 0;
        System.out.println("");
	System.out.println("1.- Expresion Aritmetica");
	System.out.println("2.- Expresion Logica");
	System.out.println("3.- Expresion Logica V2");
	System.out.println("4.- Salir");
	opcion=leer.nextInt();
        
//        (9,5,+,19,)*.2,/,6.6,^,4,-7,.46
	while(Continuar) {
		switch (opcion) {
		case 1:
			ExpresionAritmeticaBuena x=new ExpresionAritmeticaBuena();
			 break;
		case 2:
			ExpresionLogica1 y=new ExpresionLogica1();
			break;
		case 3:
			ExpresionLogica2 x2=new ExpresionLogica2();
			break;
		case 4:
			Continuar = false;
			break;
		default:
			System.out.println("La opcion que elegiste es incorrecta, favor de seleccionar una valida");
			break;
		}
		break;
	}
	
    }
    
    
}
