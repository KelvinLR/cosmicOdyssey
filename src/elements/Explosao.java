package elements;

import songs.EfeitosSonoros;

public class Explosao extends Element {

	private static final int LARGURA = 6;
	private static int VELOCIDADE = 2;

	public Explosao(int x, int y) {
		super(x, y, "res/explosion1.gif");
		somExplosao();
	}

	// Implementação do método update() definido na classe Element
	public void update() {
		this.x -= VELOCIDADE;
		if (this.x < LARGURA) {
			isVisible = false;
		}
	}

	public void somExplosao() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarSomExplosao();
	}

}
