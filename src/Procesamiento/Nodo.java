/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

/**
 *
 * @author Kev
 */
public class Nodo implements Comparable {
    int x;
    int y;
    int destinox;
    int destinoy;
    
    public int f;
    public int g;
    public int h;
    public Nodo padre;
    
    public Nodo(int x, int y, int destinox, int destinoy, Nodo padre)
    {
        this.x = x;
        this.y = y;
        this.destinox = destinox;
        this.destinoy = destinoy;
        
        this.padre = padre;
    }
    
    public int calcularG_total()
    {
        
        return 10;
    }
    
    public int calcularH()
    {
        h = Math.abs(x-destinox) + Math.abs(y-destinoy);
        return h;
    }
    
    public int calcularF()
    {
        f = this.g + this.h;
        return f;
    }
    
    @Override
    public int compareTo(Object t) 
    {
        int c = ((Nodo)t).f;
        return this.f - c;
    }
}
