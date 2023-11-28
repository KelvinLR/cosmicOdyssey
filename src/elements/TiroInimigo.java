package elements;

public class TiroInimigo extends Element {

	private static final int LARGURA = 1024; // Largura do objeto
	private static int VELOCIDADE = 5; // Velocidade do objeto

	// Construtor da classe TiroInimigo
	public TiroInimigo(int x, int y) {
		super(x,y, "res/TiroInimigo.png"); // Chamada do construtor da classe abstrata Element
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
