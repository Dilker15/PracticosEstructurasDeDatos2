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
public class NodoG<K,V> {
    private K clave;
    private V valor;
    private NodoG<K,V> hijoDerecho;
    private NodoG<K,V> hijoIzquierdo;
    
    
    
    public NodoG(){
        
    }
    public NodoG(K c,V v){
        this.clave=c;
        this.valor=v;
        this.hijoDerecho=null;
        this.hijoIzquierdo=null;
        
    }
    public void setValor(V valor){
        this.valor=valor;
    }
    public void setClave(K clave){
        this.clave=clave;
    }
    public K getClave(){
        return this.clave;
    }
    public V getValor(){
        return this.valor;
        
    }
    public NodoG<K,V> getHijoDerecho(){
        return this.hijoDerecho;
    }
    public NodoG<K,V> getHijoIzquierdo(){
        return this.hijoIzquierdo;
    }
    public boolean IncompletoPorDerecha(){
        return this.esVacioHijoDerecho()&&!this.esVacioHijoIzquierdo();
    }
    public void setHijoDerecho(NodoG<K,V> hi){
        this.hijoDerecho=hi;
    }
    public void setHijoIzquierdo(NodoG<K,V> hd){
        this.hijoIzquierdo=hd;
    }
    public boolean esVacioHijoIzquierdo(){
        return NodoG.esNodoVacio(this.getHijoIzquierdo());
    }
    public boolean esVacioHijoDerecho(){
        return NodoG.esNodoVacio(this.getHijoDerecho());
    }
    public static boolean esNodoVacio(NodoG nodo){
            return nodo==NodoG.nodoVacio();
    }
    public static NodoG<?,?> nodoVacio(){
        return null;
    }
    public boolean esNodoCompleto(){
        return !this.esVacioHijoDerecho()&& 
                !this.esVacioHijoIzquierdo();
    }
    public boolean esHoja(){
        return this.esVacioHijoDerecho()&& 
                this.esVacioHijoIzquierdo();
    }
    public boolean esIncompletoPorDerecha(){
        return esVacioHijoDerecho()&&!esVacioHijoIzquierdo();
    }
    
}
