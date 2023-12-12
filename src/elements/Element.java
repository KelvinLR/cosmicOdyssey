//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;

public abstract class Element {
    protected Image imagem;
    protected int x;
    protected int y;
    protected int largura;
    protected int altura;
    protected boolean isVisible;

    public Element(int x, int y, String ref) {
        ImageIcon referencia = new ImageIcon(this.getClass().getClassLoader().getResource(ref));
        this.imagem = referencia.getImage();
        this.x = x;
        this.y = y;
        this.altura = this.imagem.getHeight((ImageObserver)null);
        this.largura = this.imagem.getWidth((ImageObserver)null);
        this.isVisible = true;
    }

    public abstract void update();

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.largura, this.altura);
    }

    public Image getImagem() {
        return this.imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargura() {
        return this.largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }
}
