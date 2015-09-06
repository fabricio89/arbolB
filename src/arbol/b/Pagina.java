/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arbol.b;

import java.util.Stack;

/**
 *
 * @author FabianP
 */
public class Pagina {
    
    public int N=2;
    public int M=4;
    final int M1=5;
    public int cont;
    int[]info;
    Pagina apunt[];
    
    public Pagina() {
        info= new int[M];
        apunt=new Pagina[M1];
        
    }
    
    

 class Componente{
    Pagina s;
    int v;

    public Componente(Pagina s, int v) {
        this.s=s;
        this.v=v;
    }
    Componente(){
        
    }
  
    
 }}