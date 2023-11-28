package elements;

import songs.EfeitosSonoros;

public class Explosao extends Element {

	private static final int LARGURA = 6; // Largura do objeto
	private static int VELOCIDADE = 2; // Velocidade do objeto

	// Construtor da classe Explosao
	public Explosao(int x, int y) {
		super(x, y, "res/explosion1.gif"); // Chamada do construtor da classe abstrata Element
		somExplosao();
	}

	// Implementação do método update() definido na classe Element
	public void update() {
		this.x -= VELOCIDADE;
		if (this.x < LARGURA) {
			isVisible = false;
		}
	}

	// Responsável pelo efeito sonoro de Explosao
	public void somExplosao() {
		EfeitosSonoros a = new EfeitosSonoros();
		a.tocarSomExplosao();
	}

}
