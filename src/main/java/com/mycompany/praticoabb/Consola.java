/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.praticoabb;

import java.util.Scanner;

/**
 *
 * @author hp
 */
public class Consola {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       ArbolesBinarios<Integer,String>arbol1=new ArbolesBinarios<>();
       arbol1.insertar(50, "Dilker");
        arbol1.insertar(35, "Daniel");
        arbol1.insertar(28, "miguel");
       arbol1.insertar(25, "manuel");
        arbol1.insertar(30, "juana");
         arbol1.insertar(40, "carla");
          arbol1.insertar(90, "yenny");
           arbol1.insertar(95, "andrea");
            arbol1.insertar(93, "pedro");
             arbol1.insertar(60, "richard");
          
         ArbolesBinarios<Integer,String>arbol2=new ArbolesBinarios<>();
          arbol2.insertar(50, "Dilker");
        arbol2.insertar(35, "Daniel");
        arbol2.insertar(28, "miguel");
       arbol2.insertar(25, "manuel");
        arbol2.insertar(30, "juana");
         arbol2.insertar(40, "carla");
          arbol2.insertar(90, "yenny");
           arbol2.insertar(95, "andrea");
            arbol2.insertar(93, "pedro");
            arbol2.insertar(60, "richard");
        System.out.println("");    
        System.out.println("************************************************PRACTICO SOBRE ARBOLES BINARIOS DE BUSQUEDA************************************************************");    
        System.out.println("");
        System.out.println("");
        System.out.println("PREGUNTA 1: TODOS LOS METODOS DE LA INTERFAZ IARBOL ESTAN IMPLEMENTADOS.");
        System.out.println("PREGUNTA 2:  método recursivo que retorne la cantidad nodos que tienen solo hijo zquierdo no vacío en un árbol binario");
        System.out.println("PREGUNTA 3:  método iterativo que retorne la cantidad nodos que tienen solo hijo izquierdo vacío en un árbol binario");
        System.out.println("PREGUNTA 4:  método recursivo que retorne la cantidad nodos que tienen solo hijo izquierdo no vacío en un árbol binario, pero solo en el nivel N");  
        System.out.println("PREGUNTA 5:  método iterativo que retorne la cantidad nodos que tienen solo hijo izquierdo no vacío en un árbol binario, pero solo después del nivel N");
        System.out.println("PREGUNTA 6:  método iterativo que retorne la cantidad nodos que tienen solo hijo izquierdo no vacío en un árbol binario, pero solo antes del nivel N");
        System.out.println("PREGUNTA 7:  método recursivo que reciba como parámetro otro árbol binario de búsqueda que retorne verdadero, si el árbol binario es similar al árbol binario recibido como parámetro, falso en caso contrario");
        System.out.println("PREGUNTA 8:  método eliminar de un árbol AV");
        System.out.println("PREGUNTA 9 : método que retorne la cantidad de nodos que tienen ambos hijos desde el nivel N.");
        System.out.println("PREGUNTA 10: método que retorne un nuevo árbol binario de búsqueda invertido. ");
        System.out.println("PREGUNTA 11: método que retorne verdadero si un árbol binario esta lleno.");
        
        
        System.out.println("");
        System.out.println("");
        System.out.print("INGRESE EL NUMERO DE PREGUNTA : ");
        Scanner entrada =new Scanner(System.in);
        String pregunta=entrada.next();
        
         System.out.println("----------------------------------------------- ");
         
        switch(pregunta){
        
            case "2":
                 System.out.print(arbol1.cantidadDeNodosConHijoIzquierdo());
                 System.out.print(" es la cantidad de nodos con hijo izquierdo en el arbol recursivamente");
                 break;
            case "3":
                 System.out.print(arbol1.cantidadDeNodosConHijoIzquierdo());
                 System.out.print(" es la cantidad de nodos con hijo izquierdo en el arbol de manera iterativa ");
                 break;
            case "4":
                System.out.print("--------ingrese el nivel en el cual buscar los nodos solo con hijos izquierdo : ");
                 Scanner a=new Scanner(System.in);
                 int nivel=a.nextInt();
                 System.out.print(arbol1.cantidadDeNodosSoloConHijoIzquierdoEnUnNivel(nivel));
                 System.out.print(" es la cantidad de nodos con hijo izquierdo en el nivel "+ nivel);
                 break;
            case "5":
                 System.out.print("--------ingrese el nivel desde el cual buscar los nodos solo con hijos izquierdo : ");
                 Scanner b=new Scanner(System.in);
                 int nive=b.nextInt();
                 System.out.print(arbol1.cantidadDeNodosSoloConHijoIzquierdoDespuesDeUnNivel(nive));
                 System.out.print(" es la cantidad de nodos con hijo izquierdo despues del  nivel"+ nive);
                 break;
            case "6":
                 System.out.print("--------ingrese el nivel hasta el cual buscar los nodos solo con hijos izquierdo : ");
                 Scanner c=new Scanner(System.in);
                 int niv=c.nextInt();
                 System.out.print(arbol1.cantidadDeNodosSoloConHijoIzquierdoAntesDeUnNivel(niv));
                 System.out.print(" es la cantidad de nodos con hijo izquierdo antes del nivel " + niv);
                 break;
            case "7":
                 System.out.println("El arbol 1 es : ");
                 System.out.println(arbol1.mostrarNiveles());
                 System.out.println("El arbol 2 es : ");
                 System.out.print(arbol2.mostrarNiveles());
                 System.out.println("¿Los arboles son similares : ?"+ arbol1.verificarSiSonArbolesSimilares(arbol2));
                 
                 
                 break;
                 
            case "8":
                 System.out.print("--------ingrese la clave a eliminar : ");
                 Scanner d=new Scanner(System.in);
                 int claveAEliminar=d.nextInt();
                 System.out.println("Arbol antes de eliminar un dato");
                 System.out.println(arbol1.mostrarNiveles());
                 System.out.print(arbol1.eliminar(claveAEliminar));
                  System.out.println("Arbol despues de eliminar un dato");
                 System.out.println(arbol1.mostrarNiveles());
                 break;
            case "9":
                 System.out.print("--------ingrese el nivel desde donde contar los nodos con los 2 hijos no vacios : ");
                 Scanner e=new Scanner(System.in);
                 int ni=e.nextInt();
                 System.out.print(arbol1.cantidadDeNodosCompletosDesdeUnNivel(ni));
                 System.out.print(" es la cantidad de nodos con los 2 hijos no vacios a partir del nivel "+ ni);
                 break;
            case "10":
                 System.out.println("El arbol orinal es : ");
                 System.out.println(arbol1.mostrarNiveles());
                 System.out.println("El nuevo arbol invertido es : ");
                 arbol1.arbolInvertido(arbol2);
                 System.out.print(arbol2.mostrarNiveles());
                 break;
            case "11":
                 
                 System.out.println("¿El arbol esta lleno: ?"+arbol1.verificarSiElArbolEstaLleno());
                 break;     
            default :
                     System.out.println("INGRESE UN OPCION VALIDA.");
                     break;
        }
         
    }
    
}
