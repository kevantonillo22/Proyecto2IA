/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Procesamiento;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    public BufferedImage imageOriginal;
    
    public File imagenSeleccionada;
            
    public int puntoInicialX;
    public int puntoInicialY;
    public int puntoFinalX;
    public int puntoFinalY;
    public Nodo destino_final;
    
    public int[][] tablero;
    public Nodo[][] tablero_nodos_cerrados;
    public Nodo[][] tablero_nodos_abiertos;
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
        tablero_nodos_cerrados[puntoInicialX][puntoInicialY] = inicial;
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
        int largoy = tablero[0].length-1;
        int largox = tablero.length - 1;
        
        Nodo inicial = padre;
        int num = 1;
        while(true)
        {
            
            //si no se sale del mapa
            //derecha del nodo
            int valx = x + num;
            int valy = y;
            if(valx <= largox)
            {
                if(tablero[valx][valy] == 0 && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    //sino esta en abiertos lo agregamos de lo contrario analizamos si es un buen camino
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
            valx = x - num;
            valy = y;
            if(valx >= 0)
            {
                if(tablero[valx][valy] == 0 && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY,inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
            valy = y - num;
            if(valy >= 0)
            {
                if(tablero[valx][valy] == 0 && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
            valy = y + num;
            if(valy <= largoy)
            {
                if(tablero[valx][valy] == 0 && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 10 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
            valx = x + num;
            valy = y - num;
            if(valx <= largox && valy >= 0)
            {
                if(tablero[valx][valy] == 0 /*&& tablero[valx][valy+num] == 0 && tablero[valx-num][valy] == 0*/ && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    //sino esta en abiertos lo agregamos de lo contrario analizamos si es un buen camino
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
            valx = x + num;
            valy = y + num;
            if(valx <= largox && valy <= largoy)
            {
                if(tablero[valx][valy] == 0 /*&& tablero[valx][valy-num] == 0 && tablero[valx-num][valy] == 0*/ && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
            valx = x - num;
            valy = y - num;
            if(valx >= 0 && valy >= 0)
            {
                if(tablero[valx][valy] == 0 /*&& tablero[valx][valy+num] == 0 && tablero[valx+num][valy] == 0*/ && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
            valx = x - num;
            valy = y + num;
            if(valx >= 0 && valy <= largoy)
            {
                if(tablero[valx][valy] == 0 /*&& tablero[valx][valy-num] == 0 && tablero[valx+num][valy] == 0*/ && tablero_nodos_cerrados[valx][valy] == null)
                {
                    Nodo nod = tablero_nodos_abiertos[valx][valy];
                    if(nod == null)
                    {
                        Nodo adyacente = new Nodo(valx, valy, puntoFinalX, puntoFinalY, inicial);
                        adyacente.g = 14 + inicial.g;
                        adyacente.calcularH();
                        adyacente.calcularF();
                        abiertos.add(adyacente);
                        tablero_nodos_abiertos[valx][valy] = adyacente;
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
        
            Nodo menor;
            try
            {
                abiertos.remove(0);
                //obtengo el que tiene un f más pequeño
                menor = abiertos.get(0);
                inicial = menor;
                x = menor.x;
                y = menor.y;
            }
            catch(Exception e)
            {
                mensaje("Error","No existe ruta para poder llegar");
                break;
            }
            
            cerrados.add(menor);
            tablero_nodos_abiertos[x][y] = null;
            tablero_nodos_cerrados[x][y] = menor;
            
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
        BufferedImage bmp2=null;
        
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
                imagenSeleccionada=selector.getSelectedFile();
                //Asignamos a la variable bmp la imagen leida
                bmp = ImageIO.read(imagenSeleccionada);
                bmp2 = ImageIO.read(imagenSeleccionada);
            } catch (Exception e) {
            }
                 
        }
        //Asignamos la imagen cargada a la propiedad imageActual
        imageActual=bmp;
        imageOriginal=bmp2;
        
        //Retornamos el valor
        return bmp;
    }
    
    public BufferedImage InterpretarEscenario(){
        Color color;
        tablero=new int[imageActual.getWidth()][imageActual.getHeight()];
        tablero_nodos_cerrados = new Nodo[imageActual.getWidth()][imageActual.getHeight()];
        tablero_nodos_abiertos = new Nodo[imageActual.getWidth()][imageActual.getHeight()];
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
    
    public void reiniciar()
    {
        try {
            imageActual = ImageIO.read(imagenSeleccionada);
            imageOriginal = ImageIO.read(imagenSeleccionada);
        } catch (IOException ex) {
            Logger.getLogger(ProcesamientoLaberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void asignarImagen(URL url)
    {
        try {
            try {
                imagenSeleccionada = new File(url.toURI());
              } catch(Exception e) {
                imagenSeleccionada = new File(url.getPath());
              }
            imageActual = ImageIO.read(url);
            imageOriginal = ImageIO.read(url);
        } catch (IOException ex) {
            Logger.getLogger(ProcesamientoLaberinto.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
