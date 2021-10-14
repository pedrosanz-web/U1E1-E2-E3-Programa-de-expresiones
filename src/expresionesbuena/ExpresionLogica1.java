
package expresionesbuena;

import java.util.ArrayList;
import java.util.Stack;


public class ExpresionLogica1 {
    
    ArrayList<String> expresionSeparada = new ArrayList();
    ArrayList<String> expresionPosfija = new ArrayList();
    Stack<String> pilaOperadores = new Stack();
    Stack pilaEvaluacion = new Stack();
    Stack <String> pilaAux = new Stack();
    boolean[] p = {true,true,false,false};
    boolean[] q = {true,false,true,false};
     String expresion = "[(p->q)^p]->q";
     
    ExpresionLogica1(){
        separar(expresion);
        convertir(expresionSeparada);
        evaluar();
        imprimirResultados();
    }
    
    private void separar(String cadena){
    System.out.println("La expresión Original es: " + expresion);
    String[] separar = cadena.split("");
    int posicion = 0;
    String caracter = "";
    while (true){
        try{
            caracter = separar[posicion];
        }catch(ArrayIndexOutOfBoundsException e){
            break;
        }
        if(caracter.equals("-")){
            posicion += 1; 
            String juntar = caracter + separar[posicion];
            expresionSeparada.add(juntar);
        }else if(caracter.equals("<")){
            posicion += 1; 
            String juntar = caracter + separar[posicion];
            posicion += 1;
            juntar = juntar + separar[posicion];
            expresionSeparada.add(juntar);
        }else{
            expresionSeparada.add(caracter); 
        }
        
        posicion += 1;
    }
    
}
    private void evaluar(){
    for (String elemento : expresionPosfija) {
        if(elemento.equals("p")){
            pilaEvaluacion.push(p);
        }else if(elemento.equals("q")){
            pilaEvaluacion.push(q);
        }else{
            boolean[] Derecho = (boolean[])pilaEvaluacion.pop();
            boolean[] Izquierdo = (boolean[])pilaEvaluacion.pop();
            boolean[] aux = realizarComparaciones(Izquierdo, elemento, Derecho);
            pilaEvaluacion.push(aux);
        }
    }     
       
}
    
    private boolean[] realizarComparaciones(boolean[] arregloA, String operador ,boolean[] arregloB){
    boolean[] aux = new boolean[4];
    switch(operador){
        case "->":
            for(int i=0; i<arregloA.length; i++){
                boolean elementoA = arregloA[i];
                boolean elementoB = arregloB[i];
                aux[i] = tablaIf(elementoA, elementoB);
            }
            return aux;
        case "^":
            for(int i=0; i<arregloA.length; i++){
                boolean elementoA = arregloA[i];
                boolean elementoB = arregloB[i];
                aux[i] = tablaConjuncion(elementoA, elementoB);
            }
            return aux;
        default:
            return aux;
        }
    }
    
    void imprimirResultados(){
        System.out.println("El resultado es: ");
        boolean[] arreglo = (boolean[])pilaEvaluacion.pop();
        for(boolean elemnt: arreglo){
            System.out.println(elemnt);
        } 
    }
    
    //Convierte de infija a posfija
    private void convertir(ArrayList<String> arreglo){
    
    for (String caracter : arreglo) {
        evaluarCondicion(caracter);
    }       
    if(!pilaOperadores.isEmpty()){     
        vaciarPila();
    } 
    
    String posfijo = expresionPosfija.toString();
    System.out.println("Expresión en posfijo: " + posfijo);
}
    
    private void evaluarCondicion(String caracter){
    if(caracter.equals("p") || caracter.equals("q")){
        expresionPosfija.add(caracter);
    }else{
        if(caracter.equals(")")){
            while(true){
                String sacado = pilaOperadores.pop();
                if(!sacado.equals("(")){
                    expresionPosfija.add(sacado);
                }else{
                    break;
                }                     
            }
        }else if(caracter.equals("]")){
            while(true){
                String sacado = pilaOperadores.pop();
                if(!sacado.equals("[")){
                    expresionPosfija.add(sacado);
                }else{
                    break;
                }
            }
        }else{
            pilaOperadores.push(caracter);
        }
    }
}
        
    private boolean tablaIf(boolean a, boolean b){
        return (a == true && b == false) ? false : true;
    }
    
    private boolean tablaConjuncion(boolean a, boolean b){
        return (a == true && b == true) ? true : false;
    }
    
    private void vaciarPila(){
    while(!pilaOperadores.isEmpty()){
        String sacado = String.valueOf(pilaOperadores.pop());
        if(!sacado.equals("(") || !sacado.equals("[") ){
            expresionPosfija.add(sacado);
        }
    }
}
    
}
