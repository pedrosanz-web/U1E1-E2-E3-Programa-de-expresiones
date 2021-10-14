
package expresionesbuena;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class ExpresionAritmeticaBuena {
    
    String[] operadores = {"+","-","*","/","^"};
//    (6+2)*3/2^2-4
//    String entrada="(8+9)*8/9+2";
    
    public ExpresionAritmeticaBuena() throws IOException{
        Scanner leer=new Scanner(System.in);
        System.out.println("Ingrese expresion a evaluar: ");
        String entrada = leer.nextLine();
        ArrayList<String> salida = new ArrayList<String>();
        System.out.println("La Expresion es: "+entrada);
    
     String numeros = "";
     for (int i = 0; i < entrada.length(); i++) {
        if( Character.isDigit( entrada.charAt(i) ) ){
            numeros += entrada.charAt(i);
            } else {
            if(!numeros.equals(""))
                salida.add(numeros);
            salida.add( String.valueOf(entrada.charAt(i)) );
            numeros = "";
        }
    }
    if(!numeros.equals("")){
        
        salida.add(numeros);
    }
    convertirPosfija(salida);
 }
 
    public int precedencia(String operador){
    if(operador.equals("(") || operador.equals(")")){return 5;
    }else if(operador.equals("^")){return 4;
    }else if(operador.equals("*") || operador.equals("/")){return 3;
    }else if(operador.equals("+") || operador.equals("-")){return 2;
    }
     return 0;
    }
 
 public float resultados(float A, float B, String operador){
    if(operador.equals("+")){return A+B;
    }else if(operador.equals("-")){return A-B;
    }else if(operador.equals("*")){return A*B;
    }else if(operador.equals("/")){return A/B;
    }else if(operador.equals("^")){return (float) Math.pow((int)A,(int)B);
    }
     return 0;
 }
 
    public void convertirPosfija(ArrayList<String> operacion){
        ArrayList<String> salida = new ArrayList<String>();
        ArrayList<String> pila = new ArrayList<String>();
        while(operacion.size() > 0){
            String elemento = operacion.remove(0);
            if(Character.isDigit(elemento.charAt(0)) ){
                salida.add(elemento);
            }else if(elemento.equals("(")){
                pila.add(elemento);
            }else if( elemento.equals(")") ){
                while(pila.size() > 0 && !pila.get(pila.size()-1).equals("(")){
                 salida.add(pila.remove(pila.size()-1));
                }
                if(pila.size() > 0)
                    if(pila.get(pila.size()-1).equals("("))
                        pila.remove(pila.size()-1);
            }else if(existesOperador(elemento)){
                while(pila.size() > 0 && ((precedencia(pila.get(pila.size()-1)) >= precedencia(elemento) ) && !pila.get(pila.size()-1).equals("(")) ){
                 salida.add(pila.remove(pila.size()-1));
               
            }
            pila.add(elemento);
   
        } 
    }
    while(pila.size() > 0){
        salida.add(pila.remove(pila.size()-1));
    }
    operaciones(salida);
}

    public boolean existesOperador(String operador){
        for(String elemento: operadores){
         if(elemento.equals(operador)){
            return true;
        }
    }
    return false;
 }

    public void operaciones(ArrayList<String> operacion){
        ArrayList<String> pila = new ArrayList<String>();
        System.out.println("Expresion en Posfijo: "+operacion);
        for(String valor: operacion){
            if(Character.isDigit(valor.charAt(0))){
                pila.add(valor);
            }else{
                String B = pila.remove(pila.size()-1);
                String A = pila.remove(pila.size()-1);
                String resultado = String.valueOf( resultados(Float.parseFloat(A), Float.parseFloat(B), valor) );
                pila.add(resultado);
            }
        }
        System.out.println("Resultado: "+pila.get(0));
    }
}
