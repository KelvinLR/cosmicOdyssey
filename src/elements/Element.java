package elements;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public abstract class Element {
    protected Image imagem; // Imagem das skins de cada objeto
    protected int x, y; // Posição do objeto na tela
	protected int largura, altura; // Dimensões do objeto
	protected boolean isVisible; // Visibilidade do objeto na tela

    // Construtor da classe Element
    public Element(int x, int y, String ref) {
        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource(ref)); // Busca pela referência da imagem
        // Definições dos atributos
		this.imagem = referencia.getImage();
        this.x = x;
		this.y = y;
		this.altura = imagem.getHeight(null);
		this.largura = imagem.getWidth(null);
        this.isVisible = true;
    }

    // Método abstrato update implementado nas classes filhas de Elements
    public abstract void update();

    // Retorna um retângulo com as dimensões e posição do objeto para interação com outros elementos
    public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

    // Métodos getters e setters
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

    
}
