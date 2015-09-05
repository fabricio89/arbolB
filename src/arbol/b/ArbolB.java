/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package arbol.b;

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
    
    int donde(Pagina p, int x){  //
        int i=0;
        while(x>p.info[i] && i<p.cont -1)
            i++;
                    return i;
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
    }
    
}
