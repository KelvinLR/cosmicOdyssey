//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.Timer;

public class Chefao {
	private Image Imagem;
	private int x;
	private int y;
	private int a;
	private int largura;
	private int altura;
	private boolean isVisivel;
	private boolean movimento;
	private boolean tiro;
	private boolean hit;
	private boolean tiroBossTemp;
	private List<TiroChefao> tiroBoss;
	private int vida;
	private int yPlayer;
	private Timer timer;
	private Timer timer2;
	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 1;
	private boolean explodido;

	public Chefao(int x, int y) {
		this.x = x;
		this.y = y;
		this.isVisivel = true;
		this.vida = 0;
		this.a = 0;
		this.tiro = true;
		this.tiroBoss = new ArrayList();
		this.hit = false;
		this.explodido = false;
	}

	public void load() {
		ImageIcon referencia;
		if (!this.hit) {
			referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/Chefao.gif"));
			this.Imagem = referencia.getImage();
		}

		if (this.hit) {
			referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/ChefaoHit.gif"));
			this.Imagem = referencia.getImage();
		}

		this.altura = this.Imagem.getHeight((ImageObserver)null);
		this.largura = this.Imagem.getWidth((ImageObserver)null);
	}

	public void update() {
		if (this.x < -this.largura) {
			this.x = 1024;
		} else {
			this.x -= VELOCIDADE;
		}

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

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public void setyPlayer(int yPlayer) {
		this.yPlayer = yPlayer;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void tiroBoss() {
		this.tiroBoss.add(new TiroChefao(this.x, this.y + this.altura / 2));
	}

	public List<TiroChefao> getTiroBoss() {
		return this.tiroBoss;
	}

	public boolean isHit() {
		return this.hit;
	}

	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public int getLargura() {
		return this.largura;
	}

	public int getAltura() {
		return this.altura;
	}

	public int getVida() {
		return this.vida;
	}

	public void setVida(int vida) {
		this.vida = vida;
	}

	public boolean isVisivel() {
		return this.isVisivel;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisivel = isVisivel;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
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

	public Rectangle getBounds() {
		return new Rectangle(this.x, this.y, this.largura, this.altura);
	}

	public void setImagem(Image imagem) {
		this.Imagem = imagem;
	}

	public boolean getExplodido() {
		return this.explodido;
	}

	public void setExplodido(boolean explodido) {
		this.explodido = explodido;
	}
}
