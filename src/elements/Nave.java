//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.ImageObserver;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public abstract class Nave implements ActionListener {
    protected Image imagem;
    protected int x;
    protected int y;
    protected int altura;
    protected int largura;
    protected boolean isVisible;
    protected Timer timer;

    public Nave(int x, int y, String ref, int delay) {
        ImageIcon referencia = new ImageIcon(this.getClass().getClassLoader().getResource(ref));
        this.imagem = referencia.getImage();
        this.x = x;
        this.y = y;
        this.altura = this.imagem.getHeight((ImageObserver)null);
        this.largura = this.imagem.getWidth((ImageObserver)null);
        this.isVisible = true;
        this.timer = new Timer(delay, this);
        this.timer.start();
    }

    public abstract void update();

    public abstract void actionPerformed(ActionEvent var1);

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

    public int getAltura() {
        return this.altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public int getLargura() {
        return this.largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Timer getTimer() {
        return this.timer;
    }

    public void setTimer(Timer timer) {
        this.timer = timer;
    }
}
