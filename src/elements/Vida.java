package elements;

public class Vida extends Element {

	private static int VELOCIDADE = 2; // Velocidade do objeto

	public Vida(int x, int y) {
		// Construtor da classe abstrata Element
		super(x,y, "res/Vida.png");
	}

	// Implementação do método update() definido na classe Element
	public void update() {
		if (this.x < 0) {
			isVisible = false;
		} else {
			this.x -= VELOCIDADE;
		}
	}

}
