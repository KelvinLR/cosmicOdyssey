package elements;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

import songs.EfeitosSonoros;

public class Explosao {

	private Image Imagem;
	private int x, y;
	private int largura, altura;
	private boolean isVisivel;

	private static final int LARGURA = 6;
	private static int VELOCIDADE = 2;

	public Explosao(int x, int y) {
		this.x = x;
		this.y = y;
		isVisivel = true;
		SomExplosao();

		ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/explosion1.gif"));
		Imagem = referencia.getImage();
		this.altura = Imagem.getHeight(null);
		this.largura = Imagem.getWidth(null);
	}

	public void setX(int x) {
		this.x = x;
	}

	public void update() {
		this.x -= VELOCIDADE;
		if (this.x < LARGURA) {
			isVisivel = false;
		}

	}

	public void SomExplosao() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarSomExplosao();
	}

	public void setImagem(Image imagem) {
		Imagem = imagem;
	}

	public boolean isVisivel() {
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

}
