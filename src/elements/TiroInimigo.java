package elements;

public class TiroInimigo extends Element {

	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 5;

	public TiroInimigo(int x, int y) {
		super(x,y, "res/TiroInimigo.png");
	}

	// Implementação do método update() definido na classe Element
	public void update() {
		this.x -= VELOCIDADE;
		if (this.x > LARGURA) {
			isVisible = false;
		}

		if (this.x < 0) {
			isVisible = false;
		}
	}

}
