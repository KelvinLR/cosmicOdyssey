//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public class TiroChefao {
    private Image Imagem;
    private int x;
    private int y;
    private int largura;
    private int altura;
    private boolean isVisivel;
    private static final int LARGURA = 1024;
    private static int VELOCIDADE = 2;

    public TiroChefao(int x, int y) {
        this.x = x;
        this.y = y;
        this.isVisivel = true;
        this.load();
    }

    public void load() {
        ImageIcon referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/energy.gif"));
        this.Imagem = referencia.getImage();
        this.altura = this.Imagem.getHeight((ImageObserver)null);
        this.largura = this.Imagem.getWidth((ImageObserver)null);
    }

    public void update(int x, int y) {
        if (this.x > x) {
            this.x -= VELOCIDADE;
        } else {
            this.x += VELOCIDADE;
        }

        if (this.y > y) {
            this.y -= VELOCIDADE;
        } else {
            this.y += VELOCIDADE;
        }

    }

    public boolean isVisivel() {
        return this.isVisivel;
    }

    public void setVisivel(boolean isVisivel) {
        this.isVisivel = isVisivel;
    }

    public Image getImagem() {
        return this.Imagem;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public void setImagem(Image imagem) {
        this.Imagem = imagem;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.largura, this.altura);
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }
}
