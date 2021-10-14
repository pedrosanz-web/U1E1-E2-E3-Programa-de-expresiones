
package expresionesbuena;

import java.util.Scanner;


public class ExpresionLogica2 {
    
    static String palabra;
    static String caracter = "";
    static String cadena = "";
    static String numeros = "";
    static String numero2digitos = "";
    
    //Contadores
    static int contadorNumero = 0;
    static int contadorCaracter = 0;
    static int contadorCadena = 0;
    static int auxInt = 0;
    
    public ExpresionLogica2(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingresar expresion a separar: ");
        String entrada = sc.nextLine();
        String ArrayExpresion [] = entrada.split(",");
        
        for (int i = 0; i < ArrayExpresion.length; i++) {
//            System.out.println("arrayExpresion: " + ArrayExpresion[i]);
            palabra = ArrayExpresion[i];
            evaluar(palabra);
    
        }
        
        System.out.println("String("+ contadorCadena +"): " + cadena );
        System.out.println("Integer("+contadorNumero +"): " + numeros + numero2digitos);
        System.out.println("Char("+contadorCaracter +"): " + caracter);
    }

    private static void evaluar(String palabra){
//        System.out.println("palabra: " +palabra);
        if(palabra.length() == 1){
            char letra = palabra.charAt(0);
            if(Character.isLetter(letra)){
                contadorCaracter ++;
                caracter += letra+", ";
            }if(Character.isDigit(letra)){
                contadorNumero++;
                numeros += letra+", ";
            }else{
                contadorCaracter ++;
                caracter += letra+", ";
            }
        }
        if(palabra.length() >=2){
            contadorCadena++;
            cadena += palabra+", "; 
        }
            
           
    }
    
    private static boolean esOperador(char letra){
        if(letra == '*' || letra == '/' || letra == '+' || 
                letra == '-' || letra == '^' || letra == '(' || letra == ')'){
             return true;
        }
        return false;
    }
       

}
    

