package Elements;

import java.awt.Image;

import javax.swing.ImageIcon;

public class Tiro {
    private Image imagem;
    private int x,y;
    private int largura, altura;
    private boolean isVisible;

    private static final int ALTURA = 40;
    private static int VELOCIDADE = 2;

    public Tiro(int x, int y) {
        this.x = x;
        this.y = y;
        isVisible = true;
    }

    public void load() {
        ImageIcon ref = new ImageIcon("assets\\sprites\\tiros\\tiro2.png");
        imagem = ref.getImage();
        //imagem = imagem.getScaledInstance(40, 33, Image.SCALE_DEFAULT);
        
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    public void update() {
        this.y -= VELOCIDADE;   //Adaptado

        if(this.y < ALTURA) 
            isVisible = false;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static int getVelocidade() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }
}
