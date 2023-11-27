package elements;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Element {
    protected Image imagem;
    protected int x, y;
	protected int largura, altura;
	protected boolean isVisible;

    public Element(int x, int y, String ref) {
        this.x = x;
		this.y = y;
		isVisible = true;

        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource(ref));
		imagem = referencia.getImage();
		this.altura = imagem.getHeight(null);
		this.largura = imagem.getWidth(null);
    }

    public abstract void update();

    public Image getImagem() {
        return imagem;
    }

    public void setImagem(Image imagem) {
        this.imagem = imagem;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getLargura() {
        return largura;
    }

    public void setLargura(int largura) {
        this.largura = largura;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}
}
