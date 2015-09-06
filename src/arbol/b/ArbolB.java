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
    
     boolean hoja(Pagina p){//Metodo para determinar si una pagina es una hoja
        int j=0;
        while (p.apunt[j]== null && j< p.cont -1)
            j++;
        return (p.apunt[j]== null);
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
            if (posicion== -1)
                s=1;// La llave esta en el arbol
                else{
                        terminar=separar = 0;
                        
                        while(!pila.empty() && terminar ==0){
                            p= (Pagina)pila.pop();
                            if (p.cont == M){
                            
                            if (separar==0){
                                nuevo=romper(p, null, x, subir);
                                separar=1;
                            }
                            
                            else{
                                nuevo1=romper(p, nuevo, subir[0], subir1);
                                subir[0]= subir1[0];
                                nuevo=nuevo1;
                            }
                        }
                        else {
                                if(separar==1){
                                separar=0;
                                i=donde(p,subir[0]);
                                i= insertar (p,subir [0], i);
                              cderechaApunt(p, i+1);  //Esta debe ser la funcion buscar, asi sale en l libro pero creo ue es un error
                                p.apunt[i+1]= nuevo;
                                
                                }
                                else posicion = insertar(p,x,posicion);
                                terminar=1;
                                }
                        }
            
            if (separar ==1 && terminar ==0){
                raiz= crearPagina (subir[0]);
                raiz.apunt[0]=p;
                raiz.apunt[1]= nuevo;
            }
            }
        }return s;
    }
    int esta (Pagina p, int x, Stack pila){
        int i=0;
        boolean encontro= false;
        int posicion=-1;
        while(p!=null && !encontro){
            i=0;
            while(x>p.info[i] && i< p.cont -1)
                i++;
            if (x<p.info[i]){
                pila.push(new Componente(p, i));
               p= p.apunt[i];
            }
            else if (x> p.info[i]){
                pila.push(new Componente(p, i+1));
               p=  p.apunt[i+1];
            }
            else{
                pila.push(new Componente (p,i));
                encontro= true;
            }
        }
        if (encontro==true ){
            posicion=i;
        }
        return posicion;
    }
    
    void cderechaApunt(Pagina p, int i){
        int j;
        j=p.cont;
        while(j>i){
            p.apunt[j]=p.apunt[j-1];
            j--;
        }
    }
    
   void retirar(Pagina p, int i){
       while(i< p.cont -1){
           p.info[i]= p.info[i+1];
           i++;
       }
       p.cont= p.cont -1;
   }
   
   void cambio(Pagina p, Pagina q, Pagina r, int i, int x ){
       int k,t;
       if(x>r.info[r.cont -1]){
           t=q.info[i];
           retirar(q,i);
           k=0;
           k=insertar(p,t,k);
           t= r.info[r.cont -1];
           retirar(r, r.cont -1);
           k=i;
           if (k==-1 )
               k=0;
           k= insertar (q,t,k);
       }
       else{
           t=q.info[i];
           retirar(q,i);
           k=p.cont -1;
           if (k== -1)
               k=0;
           k= insertar (p,t,k);
           t= r.info[0];
           retirar(r, 0);
           k=i;
           if (q.cont != 0)
               if (k> q.cont -1)
                   k=q.cont -1;
           k= insertar(q,t, k);
       }
   }
   
   void cizquierda_apunt (Pagina p, int i, int j){
       while(i<j){
           p.apunt[i]=p.apunt[i+1];
           i++;
       }
       p.apunt[i]= null;
   }
   
   void unir(Pagina q, Pagina r, Pagina p, int i, Stack pila, int x, int posicion){
       int terminar= 0, j, k;
       Pagina t;
       Componente objeto =new Componente();
       retirar(p, posicion);
       if (x<r.info[0]){
           t=p;
           p=r;
           r=t;
       }
       while(terminar==0){
           if (r.cont <N && p.cont> N){
               cambio(r,q,p,i,x);
               r.apunt[r.cont]=p.apunt[0];
               cizquierda_apunt(p, 0, p.cont +1);
               terminar=1;
           }
           else if (p.cont < N && r.cont > N){
               cambio(p,q,r,i,x);
               cderechaApunt(p, 0);
               p.apunt[0]= r.apunt[r.cont +1];
               terminar =1;
           }
           
           else {
               j=r.cont;
               r.info[j++]= q.info[i];
               k=0;
               while (k<= p.cont -1){
                   r.info[j++]= p.info[k++];
               }
               r.cont= j;
               retirar(q, i);
               k=0;
               j= M - p.cont;
               while (p.apunt[k] != null){
                   r.apunt[j++]= p.apunt[k++];
               }
               p= null;
               if (q.cont == 0){
                   q.apunt[i+1]= null;
                   if (pila.empty() == true){
                       q= null;
                   }
               }
               else cizquierda_apunt(q, i+1, q.cont+1);
               
               if (q != null){
                   if (q.cont >=N){
                       terminar = 1;
                   }
                   else{
                       t=q;
                       if (!pila.empty()){
                           objeto= (Componente)pila.pop();
                           q= objeto.s;
                           i= objeto.v;
                           if (x>= q.info[0]){
                               p=t;
                               r= q.apunt[i-1];
                               i= i-1;
                           }
                           else {
                               r=t;
                               p= q.apunt [i+1];
                           }
                       }
                       else terminar=1;
                   }
               }
               else{
                   terminar =1;
                   raiz =r;
               }
           }
           
           
       }
   }
    
    int retirarkey(int x){
        int s, posicion, i, k;
        Pagina p,q,r,t;
        Stack pila= new Stack();
        Componente objeto = new Componente();
        s=1;
        posicion= esta (raiz, x, pila);
        if (posicion == -1)
            s=0;
        else{
            objeto= (Componente)pila.pop();
            p= objeto.s;
            i= objeto.v;
            if (!hoja (p)){
               t=p;
               k=i;
               pila.push(new Componente (p, i+1));
               p=p.apunt[i+1];
               while (p != null){
                   pila.push(new Componente (p,0));
                   p=p.apunt[0];
               }
               objeto = (Componente)pila.pop();
               p=objeto.s;
               i=objeto.v;
               t.info[k]= p.info[0];
               x= p.info[0];
               posicion=0;
            }
            if (p.cont >N )
                retirar(p, posicion);
            else {
                if (!pila.empty()){
                    objeto= (Componente)pila.pop();
                    q= objeto.s;
                    i= objeto.v;
                    if (i< q.cont){
                        r= q.apunt[i+1];
                        if (r.cont> N){
                            retirar(p, posicion);
                            cambio(p,q,r,i,x);
                            
                        }
                        else {
                            if ( i!= 0){
                                r= q.apunt[i-1];
                                if (r.cont >N){
                                    retirar (p, posicion);
                                    cambio(p,q,r,i-1, x);
                                    
                                }
                                else unir(q, r, p, i-1, pila, x, posicion);
                            }
                            else unir(q, r, p, i, pila, x, posicion);
                        }
                    }
                    else {
                        r=q.apunt[i-1];
                        if (r.cont> N){
                            retirar (p, posicion);
                            cambio(p,q,r,i-1,x);
                        }
                        else unir(q, r, p, i-1, pila, x, posicion);
                    }
                }
                else {
                    retirar (p, posicion);
                    if (p.cont== 0){
                        raiz= null;
                    }
                }
            }
        }
        return s;
    }
    
    public static void main(String[] args) {
        int a;
       ArbolB b=new ArbolB();
        b.insertarKey(4);
        
    }
    
}
