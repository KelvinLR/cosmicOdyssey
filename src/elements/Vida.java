package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.util.Random;

import javax.swing.ImageIcon;

public class Vida {

	private Image vida;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 2;

	public Vida(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
	}

	public void update() {
		if (this.x < 0) {
			isVisivel = false;
		} else {
			this.x -= VELOCIDADE;
		}
	}

	public void load_Vida() {
		ImageIcon referencia = new ImageIcon("C:\\Users\\world\\Desktop\\Arquivos Acadêmicos\\Programação\\Programas\\Java\\Trabalhos\\Cosmic Odyssey - Jogo 2D (My)\\res\\Paineis\\Vida.png");
		vida = referencia.getImage();
		this.altura = vida.getHeight(null);
		this.largura = vida.getWidth(null);
	}

	
	public Image getVida() {
		return vida;
	}

	public boolean isVisivel() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

}
