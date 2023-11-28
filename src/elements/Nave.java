package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.Timer;


public abstract class Nave implements ActionListener {
	protected Image imagem; // Imagem das skins dos objetos das classes filhas de Nave (InimigoComum e InimigoAtirador)
    protected int x, y; // Posição do objeto na tela
    protected int altura, largura; // Dimensões do objeto
    protected boolean isVisible; // Visibilidade do objeto na tela
    protected Timer timer;

	public Nave(int x, int y, String ref, int delay) {
		ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource(ref)); // Busca pela referência da imagem
        // Definições dos atributos
		this.imagem = referencia.getImage();
		this.x = x;
        this.y = y;
		this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
        this.isVisible = true;
		timer = new Timer(delay, this);
		timer.start();
	}

	// Criação dos métodos abstratos update() e actionPerformed() para implementação posterior nas classes filhas de Nave
	public abstract void update();
	public abstract void actionPerformed(ActionEvent e);

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

	public int getAltura() {
		return altura;
	}
	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getLargura() {
		return largura;
	}
	public void setLargura(int largura) {
		this.largura = largura;
	}

	public boolean isVisible() {
		return isVisible;
	}
	public void setVisible(boolean isVisible) {
		this.isVisible = isVisible;
	}

	public Timer getTimer() {
		return timer;
	}
	public void setTimer(Timer timer) {
		this.timer = timer;
	}

}