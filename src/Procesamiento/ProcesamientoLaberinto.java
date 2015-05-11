/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Kev
 */
public class ProcesamientoLaberinto {
    //Imagen actual que se ha cargado
    public BufferedImage imageActual;
    public int puntoInicialX;
    public int puntoInicialY;
    public int puntoFinalX;
    public int puntoFinalY;
    public Nodo destino_final;
    
    public int[][] tablero;
    int costo;
    
    ArrayList<Nodo> abiertos = new ArrayList();
    ArrayList<Nodo> cerrados = new ArrayList();
    
    
    
    public Nodo isInAbiertos(int x, int y)
    {
        Nodo r = null;
        for (int i = 0; i < abiertos.size(); i++) 
        {
            if(abiertos.get(i).x == x && abiertos.get(i).y == y)
            {
                r = abiertos.get(i);
                break;
            }
        }
        
        return r;
    }
    
    public Nodo isInCerrados(int x, int y)
    {
        Nodo r = null;
        for (int i = 0; i < cerrados.size(); i++) 
        {
            if(cerrados.get(i).x == x && cerrados.get(i).y == y)
            {
                r = cerrados.get(i);
                break;
            }
        }
        
        return r;
    }
    
    public void comenzarAnalisis()
    {
        Nodo inicial = new Nodo(puntoInicialX, puntoInicialY, puntoFinalX, puntoFinalY, null);
        inicial.g = 0;
        abiertos.add(inicial);
        Collections.sort(abiertos);
        
        //obtengo el que tiene un f más pequeño
        Nodo menor = abiertos.get(0);
        abiertos.remove(0);
        cerrados.add(menor);
        
        analizarNodo(menor);
    }
    
    public void analizarNodo(Nodo padre)
    {
        //System.out.println("XX: " + x);
        //System.out.println("YY: " + y);
        
        int x = puntoInicialX;
        int y = puntoInicialY;
        
        Nodo inicial = padre;
        while(true)
        {
            
            //si no se sale del mapa
            //derecha del nodo
            int valx = x + 1;
            int valy = y;
            if(valx <= tablero.length-1)
            {
                if(tablero[valx][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    //sino esta en abiertos lo agregamos de lo contrario analizamos si es un buen camino
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }
                }
            }

            //izquierda del nodo
            valx = x - 1;
            valy = y;
            if(valx >= 0)
            {
                if(tablero[valx][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY,inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }

                }
            }

            //arriba del nodo
            valx = x;
            valy = y - 1;
            if(valy >= 0)
            {
                if(tablero[valx][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }

                }
            }

            //abajo del nodo
            valx = x;
            valy = y + 1;
            if(valy <= tablero[0].length - 1)
            {
                if(tablero[valx][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }
                }
            }

            //esquina superior derecha del nodo
            valx = x + 1;
            valy = y - 1;
            if(valx <= tablero.length-1 && valy >= 0)
            {
                if(tablero[valx][valy] == 0 && tablero[valx][valy+1] == 0 && tablero[valx-1][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    //sino esta en abiertos lo agregamos de lo contrario analizamos si es un buen camino
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }

                }
            }

            //esquina inferior derecha del nodo
            valx = x + 1;
            valy = y + 1;
            if(valx <= tablero.length-1 && valy <= tablero[0].length-1)
            {
                if(tablero[valx][valy] == 0 && tablero[valx][valy-1] == 0 && tablero[valx-1][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }

                }
            }

            //esquina superior izquierda del nodo
            valx = x - 1;
            valy = y - 1;
            if(valx >= 0 && valy >= 0)
            {
                if(tablero[valx][valy] == 0 && tablero[valx][valy+1] == 0 && tablero[valx+1][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }

                }
            }

            //esquina inferior izquierda del nodo
            valx = x - 1;
            valy = y + 1;
            if(valx >= 0 && valy <= tablero[0].length-1)
            {
                if(tablero[valx][valy] == 0 && tablero[valx][valy-1] == 0 && tablero[valx+1][valy] == 0 && isInCerrados(valx, valy) == null)
                {
                    Nodo nod = isInAbiertos(valx, valy);
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                    }
                    else
                    {
                        int sumaG = nod.g + inicial.g;
                        //esto significa es un buen camino
                        if(sumaG <= inicial.g)
                        {
                            nod.padre = inicial;
                            nod.g = sumaG;
                            nod.calcularH();
                        }
                    }
                }
            }
            
            
            Collections.sort(abiertos);
        
            //obtengo el que tiene un f más pequeño
            Nodo menor = abiertos.get(0);
            inicial = menor;
            x = menor.x;
            y = menor.y;
            
            abiertos.remove(0);
            cerrados.add(menor);
            Nodo ultimo = isInAbiertos(puntoFinalX, puntoFinalY);
            if(ultimo != null)
            {
                System.out.println("ruta resuelta");
                destino_final = ultimo;
                break;
            }else
            {
            }
        }
    }
    
    public void generarNuevaImagen()
    {
        Nodo n = this.destino_final;
        while(n != null)
        {
            int colorSRGB=0 | 0 | 255;
            imageActual.setRGB(n.x, n.y,colorSRGB);
            n = n.padre;
        }
    }
    public static void mensaje(String titleBar,String infoMessage)
    {
        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
    }
    
    public void setPuntoInicial(int x, int y)
    {
        puntoInicialX = x;
        puntoInicialY = y;
    }
    
    public void setPuntoFinal(int x, int y)
    {
        puntoFinalX = x;
        puntoFinalY = y;
    }
    
    public BufferedImage CargarImagenLaberinto(){
        BufferedImage bmp=null;
        JFileChooser selector=new JFileChooser();        
        File workingDirectory = new File(System.getProperty("user.dir"));
        FileNameExtensionFilter filtroImagen = new FileNameExtensionFilter("JPG & GIF & BMP", "jpg", "gif", "bmp");
        selector.setFileFilter(filtroImagen);
        selector.setCurrentDirectory(workingDirectory);
        selector.setDialogTitle("Seleccione un laberinto");
        
        //Abrimos el cuadro de diálog
        int flag=selector.showOpenDialog(null);
        //Comprobamos que pulse en aceptar
        if(flag==JFileChooser.APPROVE_OPTION){
            try {
                //Devuelve el fichero seleccionado
                File imagenSeleccionada=selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
                 
        }
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual=bmp;
        //Retornamos el valor
        return bmp;
    }
    
    public BufferedImage InterpretarEscenario(){
        Color color;
        tablero=new int[imageActual.getWidth()][imageActual.getHeight()];
        for( int i = 0; i < imageActual.getWidth(); i++ ){
            for( int j = 0; j < imageActual.getHeight(); j++ )
            {
                color=new Color(this.imageActual.getRGB(i, j));
                if(color.getRed()>200 && color.getGreen()>200 && color.getBlue()>200){
                    tablero[i][j]=0;
                }else{
                    tablero[i][j]=1;
                }
            }
        }
        //Retornamos la imagen
        return imageActual;
    }
    
    
    
    /*
     public BufferedImage BusquedaA() {
        int origenX=this.puntoInicialX;
        int origenY=this.puntoInicialY;
        //int destinoX=tablero.length-1;
        //int destinoY=tablero[0].length-1;
        
        int destinoX=this.puntoFinalX - 1;
        int destinoY=this.puntoFinalY - 1;
        costo =0;
        tratarPunto(origenX,origenY,destinoX,destinoY,0);
        return imageActual;
    }

    private void tratarPunto(int x, int y, int destinoX, int destinoY, int padre) {
        boolean arriba=false;           
        boolean abajo=false;           
        boolean derecha=false;          
        boolean izquierda=false;        
        boolean arribaDerecha=false;    
        boolean abajoDerecha=false;    
        boolean arribaIzquierda=false;  
        boolean abajoIzquierda=false;
        int excepcion=0;
        
        if(x<destinoX && y<destinoY){
            if(x>0 && y>0){
                   if(tablero[x][y-1]==0){
                       arriba = true;
                   }
                   if(tablero[x+1][y-1]==0){
                       arribaDerecha = true;
                   }
                   if(tablero[x+1][y]==0){
                       derecha = true;
                   }
                   if(tablero[x+1][y+1]==0){
                       abajoDerecha = true;
                   }
                   if(tablero[x][y+1]==0){
                       abajo = true;
                   }
                   if(tablero[x-1][y+1]==0){
                       abajoIzquierda = true;
                   }
                   if(tablero[x-1][y]==0){
                       izquierda = true;
                   }
                   if(tablero[x-1][y-1]==0){
                       arribaIzquierda = true;
                   }
            }else if(x==0 && y>0){
                   if(tablero[x][y-1]==0){
                       arriba = true;
                   }
                   if(tablero[x+1][y-1]==0){
                       arribaDerecha = true;
                   }
                   if(tablero[x+1][y]==0){
                       derecha = true;
                   }
                   if(tablero[x+1][y+1]==0){
                       abajoDerecha = true;
                   }
                   if(tablero[x][y+1]==0){
                       abajo = true;
                   }
            }else if(x>0 && y==0){
                   if(tablero[x+1][y]==0){
                       derecha = true;
                   }
                   if(tablero[x+1][y+1]==0){
                       abajoDerecha = true;
                   }
                   if(tablero[x][y+1]==0){
                       abajo = true;
                   }
                   if(tablero[x-1][y+1]==0){
                       abajoIzquierda = true;
                   }
                   if(tablero[x-1][y]==0){
                       izquierda = true;
                   }               
            }else if(x==0 && y==0){
                   if(tablero[x+1][y]==0){
                       derecha = true;
                   }
                   if(tablero[x+1][y+1]==0){
                       abajoDerecha = true;
                   }
                   if(tablero[x][y+1]==0){
                       abajo = true;
                   }                
            }   
        }else if(x==destinoX && y<destinoY){
            if(y>0){
                   if(tablero[x][y+1]==0){
                       abajo = true;
                   }else{
                       excepcion = 1;
                   }
                   if(tablero[x-1][y+1]==0){
                       abajoIzquierda = true;
                   }
                   if(tablero[x-1][y]==0){
                       izquierda = true;
                   }
                   if(tablero[x-1][y-1]==0){
                       arribaIzquierda = true;
                   }
            }else{
                   if(tablero[x][y+1]==0){
                       abajo = true;
                   }
                   if(tablero[x-1][y+1]==0){
                       abajoIzquierda = true;
                   }
                   if(tablero[x-1][y]==0){
                       izquierda = true;
                   }
            }
        }else if(x<destinoX && y==destinoY){
            if (x>0){
                if(tablero[x][y-1]==0){
                       arriba = true;
                   }
                   if(tablero[x+1][y-1]==0){
                       arribaDerecha = true;
                   }
                   if(tablero[x+1][y]==0){
                       derecha = true;
                   }
                   if(tablero[x-1][y]==0){
                       izquierda = true;
                   }
                   if(tablero[x-1][y-1]==0){
                       arribaIzquierda = true;
                   }
            }else{
                if(tablero[x][y-1]==0){
                       arriba = true;
                   }
                   if(tablero[x+1][y-1]==0){
                       arribaDerecha = true;
                   }
                   if(tablero[x+1][y]==0){
                       derecha = true;
                   }
            }
        }else if(x==destinoX && y==destinoY){
            mensaje("Ruta","Se ha encontrado la ruta más óptima de costo "+costo+" unidades.");
            return;
        }
        int colorSRGB=0 | 0 | 255;
        imageActual.setRGB(x, y,colorSRGB);
        if(abajoDerecha && padre!=8){
            costo++;
            costo++;
            tratarPunto(x+1,y+1,destinoX,destinoY,1);
        }else if(derecha && padre!=7){
            costo++;
            tratarPunto(x+1,y,destinoX,destinoY,2);
        }else if(abajo && padre!=5){
            costo++;
            tratarPunto(x,y+1,destinoX,destinoY,3);
        }else if(arribaDerecha && padre!=6){
            costo++;
            costo++;
            tratarPunto(x+1,y-1,destinoX,destinoY,4);
        }else if(arriba && padre!=6){
            costo++;
            tratarPunto(x,y-1,destinoX,destinoY,5);
        }else if(abajoIzquierda && padre!=4){
            costo++;
            costo++;
            tratarPunto(x-1,y+1,destinoX,destinoY,6);
        }else if(arribaIzquierda && padre!=1){
            costo++;
            costo++;
            tratarPunto(x-1,y-1,destinoX,destinoY,8);
        }else if(izquierda && padre!=2){
            costo++;
            tratarPunto(x-1,y,destinoX,destinoY,7);
        }else{
            mensaje("Ruta","No se ha encontrado una ruta viable");
            return;
        }
    }
    
    */
    
}