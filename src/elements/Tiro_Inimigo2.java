package elements;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

public class Tiro_Inimigo2 {

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 5;

	public Tiro_Inimigo2(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;

	}

	public void load() {
		ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/TiroPoder1.png"));
		Imagem = referencia.getImage();

		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);

	}

	public void update() {
		this.x -= VELOCIDADE;
		if (this.x > LARGURA) {
			isVisivel = false;
		}

		if (this.x < 0) {
			isVisivel = false;
		}

	}

	public boolean isVisible() {
		return isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public Image getImagem() {
		return Imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setImagem(Image imagem) {
		Imagem = imagem;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

}
