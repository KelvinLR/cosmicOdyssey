package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.ImageIcon;
import javax.swing.Timer;


public class Inimigo1 extends Nave implements ActionListener {
    private Image imagem;
    private int x, y;
    private int altura, largura;
    private boolean isVisible;
    private List<Explosao> explosoes;
    private Timer timer;

    //private static final int LARGURA = 938;
    private static int VELOCIDADE = 2;

    
    public Inimigo1(int x, int y){
        this.x = x;
        this.y = y;
        isVisible = true;

        explosoes = new ArrayList<Explosao>();

        timer = new Timer(1000, this);
		timer.start();
    }

    @Override
	public void actionPerformed(ActionEvent e) {

	}

    public void explosoes() {
		this.explosoes.add(new Explosao(x + largura, y + altura / 2));

	}

    public void load()
    {
        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/alien1.png"));
        this.imagem = referencia.getImage();

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    public void update()
    {
        if (this.x < -largura) {

			this.x = (int) (Math.random() * 8000 + 1024);
			this.y = (int) (Math.random() * 580 + 40);

		}
        else {
            this.x -= VELOCIDADE;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public static int getVELOCIDADE() {
        return VELOCIDADE;
    }

    public static void setVELOCIDADE(int vELOCIDADE) {
        VELOCIDADE = vELOCIDADE;
    }

    public Image getImagem() {
        return imagem;
    }

    public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

    public List<Explosao> getExplosoes() {
        return explosoes;
    }
}
