/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.praticoabb;

/**
 *
 * @author hp
 */
public class ArbolesAVL<K extends Comparable<K>,V> extends ArbolesBinarios<K,V> {
    public static final byte DIFERENCIA_MAXIMA=1;
    
    @Override
    public void insertar(K claveAInsertar,V valorAInsertar){
        
        if(claveAInsertar==null){
            throw new IllegalArgumentException("La clave es null insertar una clave valida");
        }
        if(valorAInsertar==null){
             throw new IllegalArgumentException("La clave es null insertar una clave valida");
        }
        
        super.raiz=insertar(this.raiz,claveAInsertar,valorAInsertar);
    }
    public NodoG<K,V>insertar(NodoG<K,V>nodoActual,K claveAInsertar,V valorAInsertar){
        if(NodoG.esNodoVacio(nodoActual)){
            NodoG<K,V>nuevo=new NodoG<K,V>(claveAInsertar,valorAInsertar);
            return nuevo;
        }
        K claveActual=nodoActual.getClave();
            if(claveAInsertar.compareTo(claveActual)>0){
                NodoG<K,V>derecho=insertar(nodoActual.getHijoDerecho(),claveAInsertar,valorAInsertar);
                nodoActual.setHijoDerecho(derecho);
                return balancear(nodoActual);
            }
            if(claveAInsertar.compareTo(claveActual)<0){
                NodoG<K,V>izquierdo=insertar(nodoActual.getHijoIzquierdo(),claveAInsertar,valorAInsertar);
                nodoActual.setHijoIzquierdo(izquierdo);
                return balancear(nodoActual);
            }
            //SI SE LLEGA A ESTE PUNTO ENTOCES LA CLAVE SE ENCUENTRA EN EL ARBOL Y ES LA RAIZ
            //SE REEMPLAZA EL VALOR EN LA RAIZ
            nodoActual.setValor(valorAInsertar);
            return (nodoActual);
    }
    private NodoG<K,V>balancear(NodoG<K,V>nodoActual){
        int alturaPorIzquierda=super.altura(nodoActual.getHijoIzquierdo());
        int alturaPorDerecha=super.altura(nodoActual.getHijoDerecho());
        int diferencia=alturaPorIzquierda-alturaPorDerecha;
            if(diferencia>DIFERENCIA_MAXIMA){
                //rotacion hacia la derecha
                NodoG<K,V>izquierdo=nodoActual.getHijoIzquierdo();
                alturaPorIzquierda=super.altura(izquierdo.getHijoIzquierdo());
                alturaPorDerecha=super.altura(izquierdo.getHijoDerecho());
                if(alturaPorDerecha>alturaPorIzquierda){//LA RAMA CONTRARIA A LA ROTACION ES MAYOR ENTONCES ES ROTACION DOBLE A DERECHA
                   return rotacionDobleADerecha(nodoActual); 
                }else if(alturaPorIzquierda>alturaPorDerecha){
                   return rotacionSimpleADerecha(nodoActual);
                }
            }else if(diferencia<-DIFERENCIA_MAXIMA){
                // rotacion hacia la izquierda
                NodoG<K,V>derecho=nodoActual.getHijoDerecho();
                alturaPorIzquierda=super.altura(derecho.getHijoIzquierdo());
                alturaPorDerecha=super.altura(derecho.getHijoDerecho());
                if(alturaPorIzquierda>alturaPorDerecha){// LA RAMA CONTRARIA A LA ROTACION BAJANDO UNO ES MAYOR POR TANTO ES ROTACION DOBLE
                        return rotacionDobleAIzquierda(nodoActual);
                }else if(alturaPorIzquierda<alturaPorDerecha){
                        return rotacionSimpleAIzquierda(nodoActual);
                }
            }
           return nodoActual;
    }
    private NodoG<K,V>rotacionSimpleAIzquierda(NodoG<K,V>nodoActual){//ROTACION SIMPLES ROTA EL HIJO
        NodoG<K,V>nodoQueRota=nodoActual.getHijoDerecho();
        nodoActual.setHijoDerecho(nodoQueRota.getHijoIzquierdo());
        nodoQueRota.setHijoIzquierdo(nodoActual);
        return nodoQueRota;
        
    }
    private NodoG<K,V>rotacionSimpleADerecha(NodoG<K,V>nodoActual){//ROTACION SIMPLE ROTA SOLO EL HIJO
        NodoG<K,V>nodoQueRota=nodoActual.getHijoIzquierdo();
        nodoActual.setHijoIzquierdo(nodoQueRota.getHijoDerecho());
        nodoQueRota.setHijoDerecho(nodoActual);
        return nodoQueRota;
    }
    private NodoG<K,V>rotacionDobleAIzquierda(NodoG<K,V>nodoActual){    //ROTACION DOBLE ROTA EL NIETO DEL NODO PROBLEMATICO
       nodoActual.setHijoDerecho(this.rotacionSimpleADerecha(nodoActual.getHijoDerecho()));
       return this.rotacionSimpleAIzquierda(nodoActual);       
    }
    private NodoG<K,V>rotacionDobleADerecha(NodoG<K,V>nodoActual){//ROTACION DOBLE ROTA EL NIETO DEL NODO PROBLEMATICO
        nodoActual.setHijoIzquierdo(this.rotacionSimpleAIzquierda(nodoActual.getHijoIzquierdo()));
        return this.rotacionSimpleADerecha(nodoActual);
    }
    
    @Override
    public V eliminar(K claveAEliminar){
      V valorRetorno=super.buscar(claveAEliminar);
        if(valorRetorno==null){
          return null;
        }
        this.raiz=eliminar(raiz,claveAEliminar);
        return valorRetorno;
    }
    private NodoG<K,V>eliminar(NodoG<K,V>nodoActual,K claveAEliminar){
        if(NodoG.esNodoVacio(nodoActual)){
            return (NodoG<K,V>)NodoG.nodoVacio();
        }
            K claveActual=nodoActual.getClave();
                if(claveAEliminar.compareTo(claveActual)<0){
                    NodoG<K,V>izquierdo=eliminar(nodoActual.getHijoIzquierdo(), claveAEliminar);
                    nodoActual.setHijoIzquierdo(izquierdo);
                    return balancear(nodoActual);
                }
                if(claveAEliminar.compareTo(claveActual)>0){
                   NodoG<K,V>derecho=eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
                   nodoActual.setHijoDerecho(derecho);
                   return balancear(nodoActual);
                }
             /// SI SE LLEGA A ESTE PUNTO SE ENCONTRO LA CLAVE A ELIMINAR
             ///YA QUE LA CLAVE A ELIMINAR NO ES MENOR NI MAYOR ,SINO IGUAL
            // # caso 1 el nodo a eliminar es una hoja
            if(nodoActual.esHoja()){
                return (NodoG<K,V>)NodoG.nodoVacio();
            }
            //# CASO 2 LA CLAVE A ELIMINAR ES UN NODO INCOMPLETO
            if(nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
                return balancear(nodoActual.getHijoIzquierdo());
            }
            if(!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()){
                return balancear(nodoActual.getHijoDerecho());
            }
            // # CASO 3 LA CLAVE A ELIMINAR ES UN NODO COMPLETO 
            // HAY QUE BUSCAR SU NODO SUCESOR
            NodoG<K,V>nodoSucesor=(cambiar(nodoActual.getHijoDerecho()));
            NodoG<K,V>posibleNuevo=(eliminar(nodoActual.getHijoDerecho(),nodoSucesor.getClave()));
           
            nodoActual.setHijoDerecho((posibleNuevo));
            nodoSucesor.setHijoDerecho((nodoActual.getHijoDerecho()));
            nodoSucesor.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
            nodoActual.setHijoDerecho((NodoG<K,V>)NodoG.nodoVacio());
            nodoActual.setHijoIzquierdo((NodoG<K,V>)NodoG.nodoVacio());
        
        return ((nodoSucesor));
    }
    
    
    
    public static void main(String ar[]){
        IArbolBinario<Integer,String> arbol=new ArbolesAVL<>();
        arbol.insertar(3,"dilker");
        arbol.insertar(2,"deglis");
        arbol.insertar(1,"delgar");
       arbol.insertar(4,"dilker");
       arbol.insertar(5,"deglis");
        arbol.insertar(6,"delgar");
        arbol.insertar(7,"dilker");
       arbol.insertar(16,"deglis");
        arbol.insertar(15,"delgar");
           arbol.insertar(14,"deglis");
        arbol.insertar(13,"delgar");
        arbol.insertar(12,"delgar");
       
        
        
    }
    
}
