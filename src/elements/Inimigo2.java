package elements;

import java.awt.Image;
import javax.swing.ImageIcon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.Timer;


public class Inimigo2 extends Nave implements ActionListener {
    private Image imagem;
    private int x, y;
    private int altura, largura;
    private boolean isVisible, tiro;
    private ArrayList<Tiro_Inimigo2> tiroInimigo;
    private Timer timer;

    private static final int LARGURA = 1024;
    private static int VELOCIDADE = 2;

    
    public Inimigo2(int x, int y){
        this.x = x;
        this.y = y;
        isVisible = true;
        tiro = true;
        
        tiroInimigo = new ArrayList<Tiro_Inimigo2>();

        timer = new Timer(1500, this);
		timer.start();

		ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/enemy2.png"));
        this.imagem = referencia.getImage();
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    @Override
	public void actionPerformed(ActionEvent e) {
		if (x < LARGURA) {
			if (tiro == true) {
				tiroInimigo();
			}
		}

	}

    public void update()
    {
        if (this.x < -largura) {
			this.x = (int) (Math.random() * 8000 + 1024);
			this.y = (int) (Math.random() * 580 + 40);
		} else {
			this.x -= VELOCIDADE;
		}
    }

    public void tiroInimigo() {
		this.tiroInimigo.add(new Tiro_Inimigo2(x - largura/2 , y + altura/2));

	}

    public ArrayList<Tiro_Inimigo2> getTiroInimigo() {
		return tiroInimigo;
	}

	public boolean isVisible() {
		return isVisible;
	}

	public void setVisivel(boolean isVisivel) {
		this.isVisible = isVisivel;
	}

	public static void setVELOCIDADE(int vELOCIDADE) {
		VELOCIDADE = vELOCIDADE;
	}

	public Image getImagem() {
		return this.imagem;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

	public void setImagem(Image imagem) {
		this.imagem = imagem;
	}

}
