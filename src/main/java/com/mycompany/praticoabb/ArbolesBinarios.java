/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.praticoabb;

import java.util.ArrayList;
import java.util.*;


public class ArbolesBinarios<K extends Comparable<K>,V> implements IArbolBinario<K, V>{

    protected NodoG<K,V> raiz;

    @Override
    public void vaciar() {
      this.raiz=(NodoG<K,V>)NodoG.nodoVacio();
    }

    @Override
    public boolean esArbolVacio() {
      return NodoG.esNodoVacio(this.raiz);
    }

    @Override
    public int size() {
        Stack<NodoG<K,V>>pilaNodos=new Stack<>();
       int cantidad=0;
       if(this.esArbolVacio()){
           return cantidad;
       }
       pilaNodos.push(this.raiz);
       while(!pilaNodos.empty()){
           NodoG<K,V>nodo=pilaNodos.pop();
           cantidad++;
            if(!nodo.esVacioHijoDerecho()){
                pilaNodos.push(nodo.getHijoDerecho());
            }
            if(!nodo.esVacioHijoIzquierdo()){
                pilaNodos.push(nodo.getHijoIzquierdo());
            }
       }
       return cantidad;
    }

    @Override
    public int altura() {
        return altura(this.raiz);
    }
    protected int altura(NodoG<K,V>nodo){
        if(NodoG.esNodoVacio(nodo)){
            return 0;
        }else{
            int a=altura(nodo.getHijoDerecho());
            int b=altura(nodo.getHijoIzquierdo());
            return a>b?a+1:b+1;
        }
        
    }
    public void eliminarLasHojas(){
        
       this.raiz=eliminarLasHojas(this.raiz);
        
    }
    private NodoG<K,V>eliminarLasHojas(NodoG<K,V> nodo){
        if(NodoG.esNodoVacio(nodo)){
            return null;
        }
        NodoG<K,V>aux=eliminarLasHojas(nodo.getHijoDerecho());
        NodoG<K,V>aux2=eliminarLasHojas(nodo.getHijoIzquierdo());
            if(nodo.esHoja()){
                return null;
            }else{
              nodo.setHijoDerecho(aux);
              nodo.setHijoIzquierdo(aux2);
            }
            return nodo;
    }
        public Queue<K>sacarPorNiveles(int nivelDado){
       //se usa una lista o vector para almacenar cada clave y su hijo de izquierda a derecha 
      Queue<NodoG<K,V>>colaNodos=new LinkedList<>();
      Queue<K>colaClaves=new LinkedList<>();
       if(esArbolVacio()){
          return colaClaves;
      }
      
      int nivelActual=0;
     // Queue<NodoG<K,V>>colaNodos=new LinkedList<>();
      colaNodos.offer(this.raiz);
       nivelActual++;
        while(!colaNodos.isEmpty()&& nivelActual<=nivelDado){
            int cantidadDeNodosEnLaCola=colaNodos.size();
            int i=0;
            nivelActual++;
           while(i<cantidadDeNodosEnLaCola){    
           NodoG<K,V> actual=colaNodos.poll();
           if(!actual.esVacioHijoIzquierdo()){
               colaNodos.offer(actual.getHijoIzquierdo());
           }
           if(!actual.esVacioHijoDerecho()){
               colaNodos.offer(actual.getHijoDerecho());
           }
           i++;
          }
          
        }  
             while(!colaNodos.isEmpty()){
                colaClaves.offer(colaNodos.poll().getClave());
             }   
      return colaClaves;
        }
        
    public void eliminarLosNodosDeUnNivel(int nivelAEliminar){
        if(esArbolVacio()){
            throw new IllegalArgumentException("El arbol es vacio");
        }
    Queue<K>clavesAEliminar=new LinkedList<>();
    clavesAEliminar=sacarPorNiveles(nivelAEliminar);
        while(!clavesAEliminar.isEmpty()){
            eliminar(clavesAEliminar.poll());
        }
    
    }
    public int alturaIt(){
       //se usa una lista o vector para almacenar cada clave y su hijo de izquierda a derecha 
      if(esArbolVacio()){
          return 0;
      }
      int alturaArbol=0;
      Queue<NodoG<K,V>>colaNodos=new LinkedList<>();
      colaNodos.offer(this.raiz);  
        while(!colaNodos.isEmpty()){
            int cantidadDeNodosEnLaCola=colaNodos.size();
            int i=0;
           while(i<cantidadDeNodosEnLaCola ){    
           NodoG<K,V> actual=colaNodos.poll();
           if(!actual.esVacioHijoIzquierdo()){
               colaNodos.offer(actual.getHijoIzquierdo());
           }
           if(!actual.esVacioHijoDerecho()){
               colaNodos.offer(actual.getHijoDerecho());
           }
           i++;
          }
          alturaArbol++;
        }  
      return alturaArbol;           
    }
    public int incompletoPorIzquierdaEnUnNIvel(int nivelABuscar){
        return incompletosPorIzquierdaEnUnNivel(this.raiz,0,nivelABuscar);
    }
    private int incompletosPorIzquierdaEnUnNivel(NodoG<K,V>nodoActual,int contador,int nivelABuscar){
        if(NodoG.esNodoVacio(nodoActual)){
            return 0;
        }
        int porIzquierda=incompletosPorIzquierdaEnUnNivel(nodoActual.getHijoIzquierdo(),contador+1,nivelABuscar);
         int porDerecha=incompletosPorIzquierdaEnUnNivel(nodoActual.getHijoDerecho(),contador+1,nivelABuscar);
            if(contador==nivelABuscar){
                if(nodoActual.IncompletoPorDerecha()){
                    return  porIzquierda+porDerecha+1;
                }
            
         }
         return porIzquierda+porDerecha;
    }

    @Override
    public int nivel() {
        return this.altura()-1;
    }

    @Override
    public K minimo() {
       if(esArbolVacio()){
           return null;
       }
       NodoG<K,V>actual=this.raiz;
       NodoG<K,V>anterior=(NodoG<K,V>)NodoG.nodoVacio();
        while(!NodoG.esNodoVacio(actual)){
            anterior=actual;
            actual=actual.getHijoIzquierdo();
        }
       return anterior.getClave();
    }

    @Override
    public K maximo() {
       if(esArbolVacio()){
           return null;
       }
        NodoG<K,V>actual=this.raiz;
        NodoG<K,V>anterior=(NodoG<K,V>)NodoG.nodoVacio();
        while(!NodoG.esNodoVacio(actual)){
            anterior=actual;
            actual=actual.getHijoDerecho();
        }
        return anterior.getClave();
    }

    @Override
    public void insertar(K claveInsertar, V valorInsertar) {
        if(claveInsertar==null){
            throw new IllegalArgumentException("Clave no puede ser nula");
        }
         if(valorInsertar==null){
            throw new IllegalArgumentException("Valor no puede ser nulo");
        }
        if(this.esArbolVacio()){
            raiz=new NodoG(claveInsertar,valorInsertar);
            return;
        }
            NodoG<K,V> actual=raiz;
            NodoG<K,V> anterior=(NodoG<K,V>)NodoG.nodoVacio();
                while(!NodoG.esNodoVacio(actual)){
                    anterior=actual;
                    K claveActual=actual.getClave();
                        if(claveInsertar.compareTo(claveActual)<0){
                            actual=actual.getHijoIzquierdo();
                        }else if(claveInsertar.compareTo(claveActual)>0) {
                                actual=actual.getHijoDerecho();
                        }else{
                            actual.setValor(valorInsertar);
                            return;
                        }
                } 
                   NodoG<K,V>nuevo=new NodoG<>(claveInsertar,valorInsertar);
                   K clavePadre=anterior.getClave();
                    if(claveInsertar.compareTo(clavePadre)<0){
                       anterior.setHijoIzquierdo(nuevo); 
                    }else{
                        anterior.setHijoDerecho(nuevo); 
                    }   
                    
        }

    @Override
    public V eliminar(K claveAEliminar) {
        V valorRetorno=this.buscar(claveAEliminar);
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
                    return nodoActual;
                }
                if(claveAEliminar.compareTo(claveActual)>0){
                   NodoG<K,V>derecho=eliminar(nodoActual.getHijoDerecho(), claveAEliminar);
                   nodoActual.setHijoDerecho(derecho);
                   return nodoActual;
                }
             /// SI SE LLEGA A ESTE PUNTO SE ENCONTRO LA CLAVE A ELIMINAR
             ///YA QUE LA CLAVE A ELIMINAR NO ES MENOR NI MAYOR ,SINO IGUAL
            // # caso 1 el nodo a eliminar es una hoja
            if(nodoActual.esHoja()){
                return (NodoG<K,V>)NodoG.nodoVacio();
            }
            //# CASO 2 LA CLAVE A ELIMINAR ES UN NODO INCOMPLETO
            if(nodoActual.esVacioHijoDerecho() && !nodoActual.esVacioHijoIzquierdo()){
                return nodoActual.getHijoIzquierdo();
            }
            if(!nodoActual.esVacioHijoDerecho() && nodoActual.esVacioHijoIzquierdo()){
                return nodoActual.getHijoDerecho();
            }
            // # CASO 3 LA CLAVE A ELIMINAR ES UN NODO COMPLETO 
            // HAY QUE BUSCAR SU NODO SUCESOR
            NodoG<K,V>nodoSucesor=cambiar(nodoActual.getHijoDerecho());
            NodoG<K,V>posibleNuevo=eliminar(nodoActual.getHijoDerecho(),nodoSucesor.getClave());
            nodoActual.setHijoDerecho(posibleNuevo);
            nodoSucesor.setHijoDerecho(nodoActual.getHijoDerecho());
            nodoSucesor.setHijoIzquierdo(nodoActual.getHijoIzquierdo());
            nodoActual.setHijoDerecho((NodoG<K,V>)NodoG.nodoVacio());
            nodoActual.setHijoIzquierdo((NodoG<K,V>)NodoG.nodoVacio());
        
        return nodoSucesor;
    
    }
    public NodoG<K,V> cambiar(NodoG<K,V>nodoActual){ 
         NodoG<K,V>anterior=(NodoG<K,V>)NodoG.nodoVacio();
         if(NodoG.esNodoVacio(nodoActual)){
             return (NodoG<K,V>)NodoG.nodoVacio();
         }
         while(!NodoG.esNodoVacio(nodoActual)){
             anterior=nodoActual;
             nodoActual=nodoActual.getHijoIzquierdo();
         }
         return anterior;
    }

    @Override
    public boolean contiene(K clave) {
        return this.buscar(clave) != null;
    }

    @Override
    public V buscar(K clave) {
       if(!esArbolVacio()){
           NodoG<K,V>actual=raiz;
           while(!NodoG.esNodoVacio(actual)){
               K claveActual=actual.getClave();
               if(clave.compareTo(claveActual)==0){
                   return actual.getValor();
               }else if(clave.compareTo(claveActual)>0){
                   actual=actual.getHijoDerecho();
               }else{
                   actual=actual.getHijoIzquierdo();
               }
           }
           return null;
       }else{
           return null;
       }
    }

    @Override
    public List<K> recorridoEnInOrden() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<K> recorridoEnPreOrden() {
       Stack<NodoG<K,V>>pilaNodos=new Stack<>();
       List<K>lista=new ArrayList();
       if(this.esArbolVacio()){
           return lista;
       }
       pilaNodos.push(this.raiz);
       while(!pilaNodos.empty()){
           NodoG<K,V>nodo=pilaNodos.pop();
           lista.add(nodo.getClave());
            if(!nodo.esVacioHijoDerecho()){
                pilaNodos.push(nodo.getHijoDerecho());
            }
            if(!nodo.esVacioHijoIzquierdo()){
                pilaNodos.push(nodo.getHijoIzquierdo());
            }
       }
       return lista;
    }

    @Override
    public List<K> recorridoEnPostOrden() {
        List<K>lista=new ArrayList<>();
        if(esArbolVacio()){
            return lista;
        }
        Stack<NodoG<K,V>>pilaNodos=new Stack<>();
        NodoG<K,V>actual=this.raiz;
        //el procesos inicial antes de iterar en la pila
        meterPilaParaPostOrden(pilaNodos,actual);
        //empezamos a iterar sobre la pila
            while(!pilaNodos.isEmpty()){
                actual=pilaNodos.pop();
                lista.add(actual.getClave());
                    if(!pilaNodos.isEmpty()){
                        NodoG<K,V>tope=pilaNodos.peek();
                            if(!tope.esVacioHijoDerecho() && (tope.getHijoDerecho() != actual)){
                                meterPilaParaPostOrden(pilaNodos,tope.getHijoDerecho());
                            }
                    }
            }
            return lista;
    }
    public void meterPilaParaPostOrden(Stack<NodoG<K,V>>pila,NodoG<K,V>nodo){
        while(!NodoG.esNodoVacio(nodo)){
            pila.push(nodo);
                if(!nodo.esVacioHijoIzquierdo()){
                    nodo=nodo.getHijoIzquierdo();
                }else{
                    nodo=nodo.getHijoDerecho();
                }
        }
    }

    @Override
    public List<K> recorridoPorNiveles() {
      List<K>claves=new ArrayList<>();//se usa una lista o vector para almacenar cada clave y su hijo de izquierda a derecha
     
      if(esArbolVacio()){
          return claves;
      }
      Queue<NodoG<K,V>>colaNodos=new LinkedList<>();
      colaNodos.offer(this.raiz);  
        while(!colaNodos.isEmpty()){
           NodoG<K,V> actual=colaNodos.poll();
           claves.add(actual.getClave());
           if(!actual.esVacioHijoIzquierdo()){
               colaNodos.offer(actual.getHijoIzquierdo());
           }
           if(!actual.esVacioHijoDerecho()){
               colaNodos.offer(actual.getHijoDerecho());
           }
        }  
      return claves;         
    }
    public List<K> recorridoIn(){
       List<K>recorrido=new ArrayList<>();
       recorridoIn(recorrido,this.raiz);
       return recorrido;
    }
    private void recorridoIn(List<K> lista,NodoG<K,V>nodo){
        if(NodoG.esNodoVacio(nodo)){
        }else{
         recorridoIn(lista,nodo.getHijoIzquierdo());
         lista.add(nodo.getClave());
         recorridoIn(lista,nodo.getHijoDerecho());
        }
    }
    public String mostrar(){
        String s="";
        mostrar(s,this.raiz);
        return s;
    }
    private void mostrar(String cadena,NodoG<K,V>nodo){
        if(NodoG.esNodoVacio(nodo)){
          
        }else{
            cadena=nodo.getClave()+"\n";
            mostrar(cadena,nodo.getHijoIzquierdo());
            mostrar(cadena,nodo.getHijoDerecho());
                   
            
        }
    }
    
    public String mostrarNiveles(){
        String cadena="";
        if(esArbolVacio()){
            return cadena;
        }
        Queue<NodoG<K,V>>colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(raiz);
            while(!colaDeNodos.isEmpty()){
                int cantidadDeNodos=colaDeNodos.size();
                int i=0;
                    while(i<cantidadDeNodos){
                        NodoG<K,V>nodoActual=colaDeNodos.poll();
                        cadena=cadena+nodoActual.getClave().toString()+"    ";
                        if(!nodoActual.esVacioHijoIzquierdo()){
                            colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                        }if(!nodoActual.esVacioHijoDerecho()){
                            colaDeNodos.offer(nodoActual.getHijoDerecho());
                        }
                        i++;
                    }
                  cadena=cadena+"\n";  
            }
            return cadena;
    }
    
    public ArbolesBinarios(){
    
    }
    public ArbolesBinarios(List<K>clavesInOden,List<V> valoreInOrden,
            List<K> clavesNoInOrden,List<V> valoreNoInOrden,boolean preOrden){
    
        if(preOrden){
            this.raiz=reconstruirConPreOrdenDos(clavesInOden, valoreInOrden,
            clavesNoInOrden, valoreNoInOrden);
        }else{
            this.raiz=reconstruirConPostOrden(clavesInOden,valoreInOrden,
            clavesNoInOrden, valoreNoInOrden);
        }
        
        
    }
    public NodoG<K,V> reconstruirConPostOrden(List<K>clavesEnInOrden,List<V> valoresEnInOrden,List<K>
    clavesEnPostOrden,List<V> valoresEnPostOrden){
        
        if(clavesEnInOrden.isEmpty()){
            return (NodoG<K,V>)NodoG.nodoVacio();
        }
     int posicionPadrePostOrden=clavesEnPostOrden.size()-1;
     K clavePadrePostOrden=clavesEnPostOrden.get(posicionPadrePostOrden);
     V valorPadrePostOrden=valoresEnPostOrden.get(posicionPadrePostOrden);
     int posicionEnInOrden=this.BuscarPosicionDeLaClave(clavesEnInOrden, clavePadrePostOrden);
     //CONSTRUYENDO LA RAMA IZQUIERDA************
     List<K>clavezIzquierdaPost=clavesEnPostOrden.subList(0, posicionEnInOrden);
     List<V>valoreIzquierdaPost=valoresEnPostOrden.subList(0, posicionEnInOrden);
     List<K> clavesIzquierdaIn=clavesEnInOrden.subList(0, posicionEnInOrden);
     List<V> valorIzquierdaIN=valoresEnInOrden.subList(0, posicionEnInOrden);
     NodoG<K,V>ramaIzquieda=reconstruirConPostOrden(clavesIzquierdaIn, valorIzquierdaIN,
             clavezIzquierdaPost, valoreIzquierdaPost);
     //CONSTRUYENDO LA RAMA DERECHA***************
     List<K>clavesDerechaPost=clavesEnPostOrden.subList(posicionEnInOrden,clavesEnPostOrden.size()-1);
     List<V>valoresDerechaPost=valoresEnPostOrden.subList(posicionEnInOrden,valoresEnPostOrden.size()-1);
     List<K> clavesDerechaIn=clavesEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
     List<V> valoreDerechaIn=valoresEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
     NodoG<K,V> ramaDerecha=reconstruirConPostOrden(clavesDerechaIn, valoreDerechaIn, clavesDerechaPost, valoresDerechaPost);
     //CONSTRUYENDO LA RAIZ UNIENDO LA RAMA DERECHA E IZQUIERDA;
     NodoG<K,V> actual=new NodoG<>(clavePadrePostOrden,valorPadrePostOrden);
     actual.setHijoDerecho(ramaDerecha);
     actual.setHijoIzquierdo(ramaIzquieda); 
     
     return actual;
    }
    public NodoG<K,V> reconstruirConPostOrdenDos(List<K>clavesEnInOrden,List<V>valoresEnInOrden,List<K>clavesEnPostOrden,
            List<V>valoresEnPostOrden){
        
            if(clavesEnInOrden.isEmpty()){
                return (NodoG<K,V>)NodoG.nodoVacio();
            }
           int clavePadreEnPostOrden=clavesEnPostOrden.size()-1;
           K claveEnPostOrden=clavesEnPostOrden.get(clavePadreEnPostOrden);
           V valorEnPostOrden=valoresEnPostOrden.get(clavePadreEnPostOrden);
           int posicionEnInOrden=this.BuscarPosicionDeLaClave(clavesEnInOrden, claveEnPostOrden);
            
           //construir rama izquieda
           List<K>claveIzquiedasEnPostOrden=clavesEnPostOrden.subList(0,posicionEnInOrden);
           List<V>valoreIzquierdosEnPostOrden=valoresEnPostOrden.subList(0, posicionEnInOrden);
           List<K>clavesIzquierdasEnInOrden=clavesEnInOrden.subList(0, posicionEnInOrden);
           List<V>valoresIzquierdosEnInOrden=valoresEnInOrden.subList(0, posicionEnInOrden);
           NodoG<K,V>ramaIzquierda=reconstruirConPostOrdenDos(clavesIzquierdasEnInOrden, valoresIzquierdosEnInOrden,
                   claveIzquiedasEnPostOrden, valoreIzquierdosEnPostOrden);
          // construir rama derecha
          List<K>clavesDerechasEnPostOrden=clavesEnPostOrden.subList(posicionEnInOrden,clavesEnPostOrden.size()-1);
          List<V>valoresDerechosEnPostOrden=valoresEnPostOrden.subList(posicionEnInOrden, valoresEnPostOrden.size()-1);
          List<K>clavesDerechaEnInOrden=clavesEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
          List<V>valoresDerechosEnInOrden=valoresEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
          NodoG<K,V>ramaDerecha=reconstruirConPostOrdenDos(clavesDerechaEnInOrden, valoresDerechosEnInOrden,
                  clavesDerechasEnPostOrden, valoresDerechosEnPostOrden);
                   
                   
         // construir la raiz
         NodoG<K,V>actual=new NodoG<>(claveEnPostOrden,valorEnPostOrden);
         actual.setHijoDerecho(ramaDerecha);
         actual.setHijoIzquierdo(ramaIzquierda);
         
         
         return actual;
                   
                   
                   
            
    }
    public NodoG<K,V> reconstruirConPreOrdenDos(List<K>clavesEnInOrden,List<V>valoresEnInOrden,List<K>clavesEnPreOrden,
            List<V>valoresEnPreOrden){
        
        if(clavesEnInOrden.isEmpty()){
            return (NodoG<K,V>)NodoG.nodoVacio();
        }
        int posicionClaveEnPreOrden=0;
        K claveEnListaPreOrden=clavesEnPreOrden.get(posicionClaveEnPreOrden);
        V valorEnListaPreOrden=valoresEnPreOrden.get(posicionClaveEnPreOrden);
        int posicionEnInOrden=this.BuscarPosicionDeLaClave(clavesEnInOrden,claveEnListaPreOrden);

        //Construir la rama izquierda
        List<K>clavesIzquierdaEnPreOrden=clavesEnPreOrden.subList(1,posicionEnInOrden+1);        
        List<V>valoresIzquierdaEnPreOrden=valoresEnPreOrden.subList(1, posicionEnInOrden+1);
        List<K>clavesIzquierdaEnInOrden=clavesEnInOrden.subList(0, posicionEnInOrden);
        List<V>valoresIzquierdaEnInOrden=valoresEnInOrden.subList(0, posicionEnInOrden);
        NodoG<K,V>ramaIzquierda=reconstruirConPreOrdenDos(clavesIzquierdaEnInOrden, valoresIzquierdaEnInOrden, 
        clavesIzquierdaEnPreOrden, valoresIzquierdaEnPreOrden);
       
        //Costruir la rama derecha
       List<K>clavesDerechasEnPreOrden=clavesEnPreOrden.subList(posicionEnInOrden+1,clavesEnPreOrden.size());
       List<V>valoresDerechosEnPreOrden=valoresEnPreOrden.subList(posicionEnInOrden+1,valoresEnPreOrden.size());
       List<K>clavesDerechaEnInOrden=clavesEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
       List<V>valoreDerechosEnInOrden=valoresEnInOrden.subList(posicionEnInOrden+1,valoresEnInOrden.size());
       NodoG<K,V>ramaDerecha=reconstruirConPreOrdenDos(clavesDerechaEnInOrden, valoreDerechosEnInOrden,
               clavesDerechasEnPreOrden, valoresDerechosEnPreOrden);
        //construir la raiz
        
       NodoG<K,V>rama=new NodoG<>(claveEnListaPreOrden,valorEnListaPreOrden);
       rama.setHijoDerecho(ramaDerecha);
       rama.setHijoIzquierdo(ramaIzquierda);
        
        return rama;
    
    }
    public NodoG<K,V> reconstruirConPreOrden(List<K>clavesEnInOrden,List<V> valoresEnInOrden,List<K>
    clavesEnPreOrden,List<V> valoresEnPreOrden){
        if(clavesEnInOrden.isEmpty()){
            return(NodoG<K,V>) NodoG.nodoVacio();
        }
        int posicionPadrePreOrden=0;
        K clavePadrePreOrden=clavesEnPreOrden.get(posicionPadrePreOrden);
        V valorPadrePreOrden=valoresEnPreOrden.get(posicionPadrePreOrden);
        int posicionEnInOrden=BuscarPosicionDeLaClave(clavesEnInOrden, clavePadrePreOrden);
        //CONSTRUYENDO LA RAMA IZQUIERDA**********
        List<K>clavesIzquierdaEnInOrden=clavesEnInOrden.subList(0, posicionEnInOrden);
        List<V>valoresIzquierdaEnInOrden=valoresEnInOrden.subList(0, posicionEnInOrden);
        List<K>clavesIzquierdaEnPreOrden=clavesEnPreOrden.subList(1, posicionEnInOrden+1);
        List<V>valoresIzquierdaEnPreOrden=valoresEnPreOrden.subList(1, posicionEnInOrden+1);
        NodoG<K,V>ramaIzquierda=reconstruirConPreOrden(clavesIzquierdaEnInOrden,valoresIzquierdaEnInOrden , clavesIzquierdaEnPreOrden,
                valoresIzquierdaEnPreOrden);
        //CONSTRUYENDO LA RAMA DERECHA ***********
        List<K>clavesDerechaEnInOrden=clavesEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
        List<V>valoresDerechaEnInOrden=valoresEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
        List<K>clavesDerechaEnPreOrden=clavesEnPreOrden.subList(posicionEnInOrden+1,clavesEnPreOrden.size());
        List<V>valoresDerechaEnPreOrden=valoresEnPreOrden.subList(posicionEnInOrden+1,valoresEnPreOrden.size());
        NodoG<K,V>ramaDerecha=reconstruirConPreOrden(clavesDerechaEnInOrden, valoresDerechaEnInOrden,
                clavesDerechaEnPreOrden, valoresDerechaEnPreOrden);
        //CONSTRUYENDO LA RAIZ********************
        NodoG<K,V>actual=new NodoG<>(clavePadrePreOrden,valorPadrePreOrden);
        actual.setHijoDerecho(ramaDerecha);
        actual.setHijoIzquierdo(ramaIzquierda);
        
        return actual;
        
    }
    public NodoG<K,V> reconstruirConPostOrdenDo(List<K>clavesEnInOrden,List<V> valoresEnInOrden,List<K>
    clavesEnPostOrden,List<V> valoresEnPostOrden){
        
        if(clavesEnInOrden.isEmpty()){
            return (NodoG<K,V>)NodoG.nodoVacio();
        }
     int posicionPadrePostOrden=clavesEnPostOrden.size()-1;
     K clavePadrePostOrden=clavesEnPostOrden.get(posicionPadrePostOrden);
     V valorPadrePostOrden=valoresEnPostOrden.get(posicionPadrePostOrden);
     int posicionEnInOrden=this.BuscarPosicionDeLaClave(clavesEnInOrden, clavePadrePostOrden);
     //CONSTRUYENDO LA RAMA IZQUIERDA************
     List<K>clavezIzquierdaPost=clavesEnPostOrden.subList(0, posicionEnInOrden);
     List<V>valoreIzquierdaPost=valoresEnPostOrden.subList(0, posicionEnInOrden);
     List<K> clavesIzquierdaIn=clavesEnInOrden.subList(0, posicionEnInOrden);
     List<V> valorIzquierdaIN=valoresEnInOrden.subList(0, posicionEnInOrden);
     NodoG<K,V>ramaIzquieda=reconstruirConPostOrden(clavesIzquierdaIn, valorIzquierdaIN,
             clavezIzquierdaPost, valoreIzquierdaPost);
     //CONSTRUYENDO LA RAMA DERECHA***************
     List<K>clavesDerechaPost=clavesEnPostOrden.subList(posicionEnInOrden,clavesEnPostOrden.size()-1);
     List<V>valoresDerechaPost=valoresEnPostOrden.subList(posicionEnInOrden,valoresEnPostOrden.size()-1);
     List<K> clavesDerechaIn=clavesEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
     List<V> valoreDerechaIn=valoresEnInOrden.subList(posicionEnInOrden+1,clavesEnInOrden.size());
     NodoG<K,V> ramaDerecha=reconstruirConPostOrden(clavesDerechaIn, valoreDerechaIn, clavesDerechaPost, valoresDerechaPost);
     //CONSTRUYENDO LA RAIZ UNIENDO LA RAMA DERECHA E IZQUIERDA;
     NodoG<K,V> actual=new NodoG<>(clavePadrePostOrden,valorPadrePostOrden);
     actual.setHijoDerecho(ramaDerecha);
     actual.setHijoIzquierdo(ramaIzquieda); 
     
     return actual;
    }
    
    private int BuscarPosicionDeLaClave(List<K>listaDeClaves,K clave){
        
            for(int i=0;i<listaDeClaves.size();i++){
                K claveActual=listaDeClaves.get(i);
                    if(clave.compareTo(claveActual)==0){
                        return i;
                    }
            }
            return -1;
 
    }
    public int contarLosNodosCompletos(){
        return contarLosNodosCompletos(this.raiz);
    }
    private int contarLosNodosCompletos(NodoG<K,V>nodo){
        if(NodoG.esNodoVacio(nodo)){
            return 0;
        }
        int d=contarLosNodosCompletos(nodo.getHijoDerecho());
        int i=contarLosNodosCompletos(nodo.getHijoIzquierdo());
            if(nodo.esNodoCompleto()){
                return d+i+1;
            }
            return d+i;
        
    }
   
    public int contarLosHijosIzquierdos(){
        return contarLosHijosIzquierdos(this.raiz);
    }
    private int contarLosHijosIzquierdos(NodoG<K,V>nodo){
        if(NodoG.esNodoVacio(nodo)){
            return 0;
        }
        int cantidadPorIzquierda=contarLosHijosIzquierdos(nodo.getHijoIzquierdo());
        int cantidadPorDerecha=contarLosHijosIzquierdos(nodo.getHijoDerecho());
                if(!nodo.esVacioHijoIzquierdo()){
                    return cantidadPorIzquierda+cantidadPorDerecha+1;
                }
        return cantidadPorIzquierda+cantidadPorDerecha;
    }
    public int contarLosHijosDerechos(){
        return contarLosHijosDerechos(this.raiz);
    }
    private int contarLosHijosDerechos(NodoG<K,V> nodo){
        if(NodoG.esNodoVacio(nodo)){
            return 0;
        }
        int cantidadPorIzquierda=contarLosHijosDerechos(nodo.getHijoDerecho());
        int cantidadPorDerecha=contarLosHijosIzquierdos(nodo.getHijoIzquierdo());
            if(!nodo.esVacioHijoDerecho()){
               return cantidadPorIzquierda+cantidadPorDerecha+1; 
            }
        return cantidadPorIzquierda+cantidadPorDerecha;
    
    }
    public int cantidadDeNodosEnUnNivel(int nivel){
       Queue<K>nodosEnElNivel=this.sacarPorNiveles(nivel);
       return nodosEnElNivel.size();
    }
   
    public Queue<NodoG<K,V>> mostrarNodosEnUnNivel(int nivel){
        Queue<NodoG<K,V>>colaDeNodosEnNivel=new LinkedList<>();
            if(esArbolVacio()){
                return colaDeNodosEnNivel;
            }
            int nivelActual=0;
            colaDeNodosEnNivel.offer(raiz);
            nivelActual++;
               while(!colaDeNodosEnNivel.isEmpty() && nivelActual<=nivel){
                   nivelActual++;
                   int i=0;
                   int cantidadDeNodos=colaDeNodosEnNivel.size();
                    while(i<cantidadDeNodos){
                        
                           NodoG<K,V>nodoActual=colaDeNodosEnNivel.poll();
                            if(!nodoActual.esVacioHijoIzquierdo()){
                                colaDeNodosEnNivel.offer(nodoActual.getHijoIzquierdo());
                            }
                            if(!nodoActual.esVacioHijoDerecho()){
                                colaDeNodosEnNivel.offer(nodoActual.getHijoDerecho());
                            }
                        i++;
                    }
               }
            return colaDeNodosEnNivel;
    }
    public int cantidadDeNodosCompletosEnUnNivel(int nivel){
        if(esArbolVacio()){
            throw new IllegalArgumentException("El arbol esta vacio");
        }
        int cantidad=0;
        Queue<NodoG<K,V>>nodos=mostrarNodosEnUnNivel(nivel);
        NodoG<K,V>nodoActual;
            while(!nodos.isEmpty()){
                nodoActual=nodos.poll();
                    if(nodoActual.esNodoCompleto()){
                        cantidad++;
                    }
            }
        return cantidad;
        
    }
    public int cantidadDeNodosHojasEnUnNivel(int nivel){
        if(esArbolVacio()){
            throw new IllegalArgumentException("El arbol esta vacio");
        }
        int cantidad=0;
        Queue<NodoG<K,V>>nodos=mostrarNodosEnUnNivel(nivel);
        NodoG<K,V>nodoActual;
            while(!nodos.isEmpty()){
                nodoActual=nodos.poll();
                    if(nodoActual.esHoja()){
                        cantidad++;
                    }
            }
        return cantidad;
        
    }
    public NodoG<K,V>retornarElPadreDelNodo(K hijo){
        if(esArbolVacio() || raiz.getClave().compareTo(hijo)==0){
            return null;
        }
        NodoG<K,V>nodoAnterior=(NodoG<K,V>)NodoG.nodoVacio();
        NodoG<K,V>nodoActual=this.raiz;
            while(!nodoActual.esHoja() && nodoActual.getClave().compareTo(hijo)!=0){
               nodoAnterior=nodoActual;
                if(hijo.compareTo(nodoActual.getClave())<0){
                    nodoActual=nodoActual.getHijoIzquierdo();
                }else if(hijo.compareTo(nodoActual.getClave())>0){
                    nodoActual=nodoActual.getHijoDerecho();
                }else{
                    return nodoAnterior;
                }
            }
            if(nodoActual.getClave().compareTo(hijo)==0){
                return nodoAnterior;
            }
            
            //SI SE LLEGA A ESTE PUNTO LA CLAVE NO SE ENCUENTRA EN EL ARBOL
            return null;
    }
    public Stack<K> recorridosHaciaLaClave(K clave){
        Stack<K>caminoAlNodo=new Stack<K>();
        if(esArbolVacio() || raiz.getClave().compareTo(clave)==0){
            return caminoAlNodo;
        }
        NodoG<K,V>nodoAnterior=(NodoG<K,V>)NodoG.nodoVacio();
        NodoG<K,V>nodoActual=this.raiz;
            while(!NodoG.esNodoVacio(nodoActual)&& nodoActual.getClave().compareTo(clave)!=0){
                nodoAnterior=nodoActual;
                caminoAlNodo.push(nodoAnterior.getClave());
                    if(clave.compareTo(nodoActual.getClave())<0){
                        nodoActual=nodoActual.getHijoIzquierdo();
                    }else if(clave.compareTo(nodoActual.getClave())>0){
                        nodoActual=nodoActual.getHijoDerecho();
                    }
                    
            }
            if(NodoG.esNodoVacio(nodoActual)){
                caminoAlNodo.clear();
            }
        return caminoAlNodo;
    }
    public K padreMasCercanoEnComun(K claveUno,K claveDos){
        if(esArbolVacio()){
            throw new IllegalArgumentException("El arbol esta vacio");
        }
        Stack<K>recorridoUno=recorridosHaciaLaClave(claveUno);
        Stack<K>recorridoDos=recorridosHaciaLaClave(claveDos);
        
            while(!recorridoUno.isEmpty()){
                K primerClave=recorridoUno.pop();
                    if(recorridoDos.search(primerClave)>0){
                        return primerClave;
                    }
            }
        
        return null;
    }
    public int contarCuantasHojasHayAntesDelNivel(int nivel){
        Queue<NodoG<K,V>>colaNodos=new LinkedList<>();
            if(esArbolVacio() || nivel>this.nivel()+1){
                throw new IllegalArgumentException("el arbol no contiene ese nivel");
            }
            colaNodos.add(raiz);
            int nivelActual=0;
            int cantidadDeHojas=0;
            NodoG<K,V>nodoActual=(NodoG<K,V>)NodoG.nodoVacio();
                    while(!colaNodos.isEmpty() && nivelActual<nivel){
                        nivelActual++;
                        int cantidadDeNodos=colaNodos.size();
                        int i=0;
                        while(i<cantidadDeNodos){
                          nodoActual=colaNodos.poll();
                            if(nodoActual.esHoja()){
                                cantidadDeHojas++;
                            }
                           if(!nodoActual.esVacioHijoIzquierdo()){
                           
                               colaNodos.offer(nodoActual.getHijoIzquierdo());
                           }if(!nodoActual.esVacioHijoDerecho()){
                               colaNodos.offer(nodoActual.getHijoDerecho());
                           }
                           i++;
                        }
                    }
               return cantidadDeHojas;
    }
    public int contarCuantosNodosCompletosHayAntesDelNivel(int nivelAContar){
        if(esArbolVacio()|| nivelAContar>this.nivel()){
            throw new IllegalArgumentException("El arbol no tiene ese Nivel");
        }
        Queue<NodoG<K,V>>colaDeNodos=new LinkedList<>();
        int nivelActual=0;
        colaDeNodos.offer(raiz);
        int cantidadDeHijosCompletos=0;
            while(!colaDeNodos.isEmpty() && nivelActual<nivelAContar){
                 nivelActual++;
                 int cantidadDeNodos=colaDeNodos.size();
                 int i=0;
                 NodoG<K,V>nodoActual=(NodoG<K,V>)NodoG.nodoVacio();
                    while(i<cantidadDeNodos){
                        nodoActual=colaDeNodos.poll();
                                if(nodoActual.esNodoCompleto()){
                                    cantidadDeHijosCompletos++;
                                }
                            if(!nodoActual.esVacioHijoIzquierdo()){
                                colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                            }if(!nodoActual.esVacioHijoDerecho()){
                                colaDeNodos.offer(nodoActual.getHijoDerecho());
                            }
                       i++;
                    }
            }
        return cantidadDeHijosCompletos;
    
    }
   
    public int cantidadDeHojasEnUnNivelRe(int nivel){
        return cantidadDeHojasEnUnNivelRe(this.raiz,nivel,0);
    }
    public int cantidadDeHojasEnUnNivelRe(NodoG<K,V>nodoActual,int nivel,int contador){
            if(NodoG.esNodoVacio(nodoActual)){
                return 0;
            }
            int izquierda=cantidadDeHojasEnUnNivelRe(nodoActual.getHijoIzquierdo(),nivel,contador+1);
            int derecha=cantidadDeHojasEnUnNivelRe(nodoActual.getHijoDerecho(),nivel,contador+1);
                if(nivel==contador){
                        if(nodoActual.esHoja()){
                    return izquierda +derecha + 1;
                    }
                }
            return izquierda +derecha ;
    }
    public boolean verificarSiTodosLosNodosEstanCompletoHastaUnNivel(int nivel){
        if(esArbolVacio() || nivel>this.nivel()+1){
            throw new IllegalArgumentException("El Arbol No Tiene Ese Nivel");
        }
        boolean sw=true;
        Queue<NodoG<K,V>>colaDeNodos=new LinkedList<>();
        int nivelActual=0;
        colaDeNodos.offer(this.raiz);
                while(!colaDeNodos.isEmpty() && nivelActual<nivel && sw){
                    nivelActual++;
                    int i=0;
                    int cantidadDeNodos=colaDeNodos.size();
                    NodoG<K,V>nodoActual=(NodoG<K,V>)NodoG.nodoVacio();
                        while(i<cantidadDeNodos){
                            nodoActual=colaDeNodos.poll();
                                if(!nodoActual.esNodoCompleto()){
                                    sw=false;
                                }
                            if(!nodoActual.esVacioHijoIzquierdo()){
                             colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                             
                            }if(!nodoActual.esVacioHijoDerecho()){
                                colaDeNodos.offer(nodoActual.getHijoDerecho());
                            }
                            i++;
                        }
                    
                }
                return sw;
    }
    
    public boolean esArbolLleno(){
        if(esArbolVacio()){
            return false;
        }
        if(verificarSiTodosLosNodosEstanCompletoHastaUnNivel(this.nivel())){
            return true;
        }
        return false;
    }
    
   
     
    public boolean balanceado(){
        return balanceado(this.raiz);
    }
    private boolean balanceado(NodoG<K,V>nodoActual){
        if(NodoG.esNodoVacio(nodoActual)){
            return true;
        }
        balanceado(nodoActual.getHijoDerecho());
        balanceado(nodoActual.getHijoIzquierdo());
        int alturaIz=this.altura(nodoActual.getHijoIzquierdo());
        int alturaDe=this.altura(nodoActual.getHijoDerecho());
        if((int)Math.abs(alturaIz-alturaDe)<=1){
            return true;
        }
        return false;
    }
    /**********************************************************************************************************/
   /****************************RESOLVIENDO PRACTICO SOBRE ARBOLES BINARIOS*************-*********************/
   /**********************************************************************************************************/ 
   
    // PREGUNTA 1
    // #Implementar los métodos que no se implementaron en clases o que se implementaron a
    // medias de árboles binarios de búsqueda y AVL
    
    // TODOS LOS METODOS LOS METODOS YA SE ENCUENTRAN IMPLEMENTADOS 

    
    
    
    // PREGUNTA 2 
    // # Implemente un método recursivo que retorne la cantidad nodos que tienen solo hijo
    //   izquierdo no vacío en un árbol binario
    
    
    public int cantidadDeNodosConHijoIzquierdo(){
        return cantidadDeNodosConHijoIzquierdo(this.raiz);
    }
    private int cantidadDeNodosConHijoIzquierdo(NodoG<K,V>nodoActual){
        if(NodoG.esNodoVacio(nodoActual)){
            return 0;
        }
        int porIzquierda=cantidadDeNodosConHijoIzquierdo(nodoActual.getHijoIzquierdo());
        int porDerecha=cantidadDeNodosConHijoIzquierdo(nodoActual.getHijoDerecho());
            if(nodoActual.esIncompletoPorDerecha()){
                return porIzquierda+porDerecha+1;
            }
            return porIzquierda+porDerecha;
    }
    
    
    
    // PREGUNTA 3
    // # Implemente un método iterativo que retorne la cantidad nodos que tienen solo hijo
   // izquierdo no vacío en un árbol binario
    public int cantidadDeNodosConHijoIzquierdoIterativo(){
            if(esArbolVacio()){
                return 0;
            }
            Queue<NodoG<K,V>>colaDeNodos=new LinkedList<>();
            colaDeNodos.offer(this.raiz);
            int cantidadIncompletos=0;
            NodoG<K,V>nodoActual=(NodoG<K,V>)NodoG.nodoVacio();
                while(!colaDeNodos.isEmpty()){
                    nodoActual=colaDeNodos.poll();
                        if(nodoActual.esIncompletoPorDerecha()){
                            cantidadIncompletos++;
                        }
                        if(!nodoActual.esVacioHijoIzquierdo()){
                            colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                        }
                        if(!nodoActual.esVacioHijoDerecho()){
                            colaDeNodos.offer(nodoActual.getHijoDerecho());
                        }
                }
          return cantidadIncompletos;
    }
     
   
    // PREGUNTA 4
    // Implemente un método recursivo que retorne la cantidad nodos que tienen solo hijo
    // izquierdo no vacío en un árbol binario, pero solo en el nivel N
    
    public int cantidadDeNodosSoloConHijoIzquierdoEnUnNivel(int nivelABuscar){
     return cantidadDeNodosSoloConHijoIzquierdoEnUnNivel(this.raiz,0,nivelABuscar);
    }
    
    private int cantidadDeNodosSoloConHijoIzquierdoEnUnNivel(NodoG<K,V>nodoActual,int nivelActual,int nivelABuscar){
        if(NodoG.esNodoVacio(nodoActual)){
         return 0;
        }
        int porIzquierda=cantidadDeNodosSoloConHijoIzquierdoEnUnNivel(nodoActual.getHijoIzquierdo(),nivelActual+1,nivelABuscar);
        int porDerecha=cantidadDeNodosSoloConHijoIzquierdoEnUnNivel(nodoActual.getHijoDerecho(),nivelActual+1,nivelABuscar);
        if(nivelActual==nivelABuscar){
            if(nodoActual.IncompletoPorDerecha()){
                return porIzquierda+porDerecha+1;
            }
        }
        return porIzquierda+porDerecha;
    }
  
    
    
    //  PREGUNTA # 5
    // Implemente un método iterativo que retorne la cantidad nodos que tienen solo hijo
    // izquierdo no vacío en un árbol binario, pero solo después del nivel N
    public int cantidadDeNodosSoloConHijoIzquierdoDespuesDeUnNivel(int nivelABuscar){
        int cantidad=0;
         if(esArbolVacio()){
             return cantidad;
         }
         NodoG<K,V>nodoActual=(NodoG<K,V>)NodoG.nodoVacio();
         Queue<NodoG<K,V>>colaDeNodos=new LinkedList<>();
         int nivelActual=0;
         colaDeNodos.offer(this.raiz);
            while(!colaDeNodos.isEmpty()){
                int i=0;
                int cantidadDeNodos=colaDeNodos.size();
                    while(i<cantidadDeNodos){
                      nodoActual=colaDeNodos.poll();
                        if(nivelActual>nivelABuscar){
                                if(nodoActual.IncompletoPorDerecha()){
                                    cantidad++;
                                }
                        }
                    if(!nodoActual.esVacioHijoIzquierdo()){
                        colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                    }if(!nodoActual.esVacioHijoDerecho()){
                        colaDeNodos.offer(nodoActual.getHijoDerecho());
                    }
                    i++;
                 }
               nivelActual++;     
            }
         return cantidad;
    }
    
    

 //  PREGUNTA # 6
 //  6. Implemente un método iterativo que retorne la cantidad nodos que tienen solo hijo
 //   izquierdo no vacío en un árbol binario, pero solo antes del nivel N
 
 public int cantidadDeNodosSoloConHijoIzquierdoAntesDeUnNivel(int nivelABuscar){
    int cantidad=0;
        if(esArbolVacio()){
            return cantidad;           
        }
        Queue<NodoG<K,V>>colaDeNodos=new LinkedList<>();
        colaDeNodos.offer(this.raiz);
        int nivelActual=0;
        NodoG<K,V>nodoActual=(NodoG<K,V>)NodoG.nodoVacio();
            while(!colaDeNodos.isEmpty()){
                int i=0;
                int cantidadDeNodos=colaDeNodos.size();
                    while(i<cantidadDeNodos){
                        nodoActual=colaDeNodos.poll();
                            if(nivelActual<nivelABuscar){
                                if(nodoActual.IncompletoPorDerecha()){
                                    cantidad++;
                                }
                            }
                        if(!nodoActual.esVacioHijoIzquierdo()){
                            colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                        }
                        if(!nodoActual.esVacioHijoDerecho()){
                            colaDeNodos.offer(nodoActual.getHijoDerecho());
                        }
                        i++;
                    }
                    nivelActual++;
            }
         return cantidad;
 }
 
 
 //  PREGUNTA # 7
 //  Implemente un método recursivo que reciba como parámetro otro árbol binario de
 //búsqueda que retorne verdadero, si el árbol binario es similar al árbol binario recibido como
 //parámetro, falso en caso contrario.
 
 public boolean verificarSiSonArbolesSimilares(ArbolesBinarios<K,V> arbol){
    return verificarSiSonArbolesSimilares(this.raiz,arbol.raiz);
 }
 private boolean verificarSiSonArbolesSimilares(NodoG<K,V>nodoActual1,NodoG<K,V>nodoActual2){
     
        if(NodoG.esNodoVacio(nodoActual1) && NodoG.esNodoVacio(nodoActual2)){
            return true;
        }
        boolean respuestaPorIzquierda=verificarSiSonArbolesSimilares(nodoActual1.getHijoIzquierdo(),nodoActual2.getHijoIzquierdo());
        boolean respuestaPorDerecha=verificarSiSonArbolesSimilares(nodoActual1.getHijoDerecho(),nodoActual2.getHijoDerecho());
        if(NodoG.esNodoVacio(nodoActual1) && !NodoG.esNodoVacio(nodoActual2)){
            return false;
        }
        if(!NodoG.esNodoVacio(nodoActual1) && NodoG.esNodoVacio(nodoActual2)){
         return false;
        }
        return respuestaPorIzquierda && respuestaPorDerecha;
 }
 
 
 // PREGUNTA 8
 // Implemente el método eliminar de un árbol AVL
 // EL METODO ELIMINAR YA SE ENCUENTRA EL LA CLASE ARBOL AVL IMPLEMENTADO..
 
 
 
 // PREGUNTA 9
 //. Para un árbol binario implemente un método que retorne la cantidad de nodos que tienen
 //ambos hijos desde el nivel N.
 
 public int cantidadDeNodosCompletosDesdeUnNivel(int nivelABuscar){
    return cantidadDeNodosCompletosDesdeUnNivel(this.raiz,0,nivelABuscar);
 }
 
 private int cantidadDeNodosCompletosDesdeUnNivel(NodoG<K,V>nodoActual,int contador,int nivelABuscar){
     if(NodoG.esNodoVacio(nodoActual)){
         return 0;
     }
     int iz=cantidadDeNodosCompletosDesdeUnNivel(nodoActual.getHijoIzquierdo(),contador+1,nivelABuscar);
     int de=cantidadDeNodosCompletosDesdeUnNivel(nodoActual.getHijoDerecho(),contador+1,nivelABuscar);
     
        if(contador>nivelABuscar){
            if(nodoActual.esNodoCompleto()){
                return iz+de+1;
            }
        }   
        return iz+de;
 }
 
 
 // PREGUNTA 10 
 // Implementar un método que retorne un nuevo árbol binario de búsqueda invertido.
 public ArbolesBinarios<K,V> arbolInvertido(ArbolesBinarios<K,V>arbol){
     arbol.raiz=arbolInvertido(arbol.raiz);
     return arbol;
 }
 
 private NodoG<K,V>arbolInvertido(NodoG<K,V>arbol){
     if(NodoG.esNodoVacio(arbol)){
         return (NodoG<K,V>)NodoG.nodoVacio();
     }
     NodoG<K,V>iz=arbolInvertido(arbol.getHijoIzquierdo());
     NodoG<K,V>de=arbolInvertido(arbol.getHijoDerecho());
        
            if(arbol.esNodoCompleto()){
                arbol.setHijoDerecho(iz);
                arbol.setHijoIzquierdo(de);
                return arbol;
            }
        
     arbol.setHijoIzquierdo(iz);
     arbol.setHijoDerecho(de);
     return arbol;
     
 
 }
 
 // PREGUNTA 11
 // . Implementar un método que retorne verdadero si un árbol binario esta lleno.
 public boolean verificarSiElArbolEstaLleno(){
     if(esArbolVacio()){
         return false;
     }
     boolean sw=true;
     Queue<NodoG<K,V>>colaDeNodos=new LinkedList<>();
     colaDeNodos.offer(this.raiz);
     NodoG<K,V>nodoActual=(NodoG<K,V>)NodoG.nodoVacio();
     int nivelActual=0;
        while(!colaDeNodos.isEmpty() && sw && nivelActual<this.nivel()){
            int i=0;
            nivelActual++;
            int cantidadDeNodos=colaDeNodos.size();
                while(i<cantidadDeNodos){
                    nodoActual=colaDeNodos.poll();
                       if(!nodoActual.esNodoCompleto()){
                           sw=false;
                       }
                       if(!nodoActual.esVacioHijoIzquierdo()){
                           colaDeNodos.offer(nodoActual.getHijoIzquierdo());
                       }if(!nodoActual.esVacioHijoDerecho()){
                           colaDeNodos.offer(nodoActual.getHijoDerecho());
                       }
                i++;
                }    
                
        }
 return sw;
 
 }
 
 
 
 
    
}
