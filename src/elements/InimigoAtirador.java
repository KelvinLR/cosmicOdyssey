//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class InimigoAtirador extends Nave {
	private boolean tiro = true;
	private ArrayList<TiroInimigo> tirosInimigo = new ArrayList();
	private static final int LARGURA = 1024;
	private static int VELOCIDADE = 3;

	public InimigoAtirador(int x, int y) {
		super(x, y, "res/enemy2.png", 1500);
	}

	public void actionPerformed(ActionEvent e) {
		if (this.x < 1024 && this.tiro) {
			this.atirarDisparoInimigo();
		}

	}

	public void update() {
		if (this.x < -this.largura) {
			this.x = (int)(Math.random() * 8000.0 + 1024.0);
			this.y = (int)(Math.random() * 580.0 + 40.0);
		} else {
			this.x -= VELOCIDADE;
		}

	}

	public void atirarDisparoInimigo() {
		this.tirosInimigo.add(new TiroInimigo(this.x - this.largura / 2, this.y + this.altura / 2));
	}

	public boolean getTiro() {
		return this.tiro;
	}

	public void setTiro(boolean tiro) {
		this.tiro = tiro;
	}

	public ArrayList<TiroInimigo> getTirosInimigo() {
		return this.tirosInimigo;
	}

	public void setTiroInimigo(ArrayList<TiroInimigo> tirosInimigo) {
		this.tirosInimigo = tirosInimigo;
	}

	public int getLargura() {
		return 1024;
	}

	public static int getVELOCIDADE() {
		return VELOCIDADE;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}
}
