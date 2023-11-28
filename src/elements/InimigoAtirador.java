package elements;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InimigoAtirador extends Nave {   
    private boolean tiro;
    private ArrayList<TiroInimigo> tirosInimigo;

    private static final int LARGURA = 1024; // Largura do InimigoAtirador
    private static int VELOCIDADE = 2; // Velocidade do InimigoAtirador

	// Constutor da classe InimgoAtirador
    public InimigoAtirador(int x, int y){
		super(x, y, "res/enemy2.png", 1500); // Chamada do construtor da classe abstrata Nave
        this.tiro = true;
        this.tirosInimigo = new ArrayList<TiroInimigo>();
    }

	// Implementação do método abstrato actionPerformed()
    @Override
	public void actionPerformed(ActionEvent e) {
		if (x < LARGURA) {
			if (tiro == true) {
				atirarDisparoInimigo();
			}
		}
	}

	// Implementação do método abstrato update()
    public void update() {
        if (this.x < -largura) {
			this.x = (int) (Math.random() * 8000 + 1024);
			this.y = (int) (Math.random() * 580 + 40);
		} else {
			this.x -= VELOCIDADE;
		}
    }

	// Esse método faz com que InimigoAtirador realize os disparos
    public void atirarDisparoInimigo() {
		this.tirosInimigo.add(new TiroInimigo(x - largura/2 , y + altura/2)); // adiciona um objeto da classe TiroInimigo no ArrayList tirosInimigo
	}

	// Métodos getters e setters
	public boolean getTiro() {
		return tiro;
	}

	public void setTiro(boolean tiro) {
		this.tiro = tiro;
	}

	public ArrayList<TiroInimigo> getTirosInimigo() {
		return tirosInimigo;
	}

	public void setTiroInimigo(ArrayList<TiroInimigo> tirosInimigo) {
		this.tirosInimigo = tirosInimigo;
	}

	public int getLargura() {
		return LARGURA;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

}
