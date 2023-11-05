package elements;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;


public class Inimigo1 {
    private Image imagem;
    private int x, y;
    private int altura, largura;
    private boolean isVisible;

    //private static final int LARGURA = 938;
    private static int VELOCIDADE = 2;

    
    public Inimigo1(int x, int y){
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load()
    {
        ImageIcon referencia = new ImageIcon("res\\Inimigos\\enemy1.png");
        this.imagem = referencia.getImage();

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    public void update()
    {
        this.x -= VELOCIDADE;
          //if(this.x > LARGURA)
            //isVisible = false;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura);
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }

    

    

    






}
