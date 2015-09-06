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
public class ArbolB {

       public int N=2;
    public int M=4;
    final int M1=5;
    Pagina raiz;
   
    void inicializar(Pagina p){
        int i=0;
        p.cont=0;
        while(i<M1)
            p.apunt[i++]=null;
    }
    
    Pagina crearPagina (int x){
        Pagina p= new Pagina();
        p.cont=1;
        p.info[0]=x;
        return p;
    }
    
    int insertar(Pagina p, int x, int i){
        int j;
        if (p.cont!=0){
            if(x> p.info[i])
                i++;
            else{
                j=p.cont-1;
                while(j>=i){
                    p.info[j+1]=p.info[j];
                    j=j-1;
                }
            }
        }p.cont++;
        p.info[i]=x;
        return i;
    }
    
    int donde(Pagina p, int x){  //inserta un dato en la pagina dada
        int i=0;
        while(x>p.info[i] && i<p.cont -1)
            i++;
                    return i;
    }
    
    //Inserta x en la pagina p
   // i=donde(p,x);
    //i=insertar(p,x,i);
    
    //INsertar el numero almacenado
    
    int Buscar (Pagina p, int x, Stack pila){
        int i=-1, posicion=-1;
        boolean encontro= false;
        
        while (p !=null && !encontro){
            pila.push(p);
            i=0;
            
            while(x> p.info [i] && i< p.cont -1)
                i++;
            if(x<p.info[i])
                p=p.apunt[i];
            else if (x>p.info[i])
                p=p.apunt[i+1];
            else encontro=true;
        }
    if (!encontro){
    posicion =i;
}
    return posicion;
}
    
    Pagina romper (Pagina p, Pagina t, int x, int[]subir){
        int []a;
        int i=0;
        a= new int[M1];
        boolean s = false;
        Pagina []b= new Pagina[M1+1];
        while(i<M && !s){
            if(p.info[i]< x){
                a[i]=p.info[i];
                b[i]=p.apunt[i];
                p.apunt[i++]=null;
            }else s =true;
        }
        a[i]= x;
        b[i]=p.apunt[i];
        p.apunt[i]=null;
        b[++i]=t;
        while(i<= M){
            a[i]=p.info[i-1];
            b[i+1]=p.apunt[i];
            p.apunt[i++]= null;
            
        }
        Pagina q= new Pagina();
        inicializar(q);
        p.cont=q.cont= N;
        i=0;
        while (i<N){
            p.info[i]=a[i];
            p.apunt[i]= b[i];
            q.info[i]= a [i+N+1];
            i++;
        }
        p.apunt[N]=b[N];
        q.apunt[N]= b[M1];
        subir[0]= a[N];
        return q;
    }
    
    int insertarKey(int x){
        Stack pila= new Stack();
        int[]subir= new int[1];
        int[]subir1= new int[1];
        int posicion, i, terminar, separar;
        Pagina p= null, nuevo= null, nuevo1;
        int s=0;
        
        if (raiz==null){
            raiz= crearPagina(x);
        }
        else{
            posicion= Buscar(raiz, x, pila);
            if (posicion== -1){
                s=1;
            }
        }
    }
    
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
