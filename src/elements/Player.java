package elements;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player {
    
    private int x,y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private ArrayList <Tiro> tiros;
    private static int VELOCIDADE = 5;
    private boolean isVisible;
    private int vida;
    private int score;

    public Player()
    {
        this.x = 100;
        this.y = 100;
        isVisible = true;

        this.vida = 5;
        score = 0;

        tiros = new ArrayList<Tiro>();

        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/player1-spaceship-lowres-fix.png"));
        this.imagem = referencia.getImage();
        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    public void setImagemvida() {
		
        
        if (vida == 1) {
			ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/player1-spaceship-lowres-fix-middamage.png"));
			imagem = referencia.getImage();

		}
		else if (vida == 0) {
			ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/player1-spaceship-lowres-fix-highdamage.png"));
			imagem = referencia.getImage();
		}
        else{
            ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/player1-spaceship-lowres-fix.png"));
            this.imagem = referencia.getImage();
        }

	}

    public void update()
    {
        x += dx;
        y += dy;

        if (this.x < 6) {
			x = 6;
		}

		if (this.x > 938) {
			x = 938;
		}

		if (this.y < 60) {
			y = 60;
		}
		if (this.y > 600) {
			y = 600;
		}

        setImagemvida();
    }

    public void tiroSimples()
    {
        this.tiros.add(new Tiro(x+largura, y+(altura/2)));
    }

    public void keyPressed(KeyEvent tecla)
    {   
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_W)
        {
            dy = -VELOCIDADE;
        }

        if(codigo == KeyEvent.VK_S)
        {
            dy = VELOCIDADE;
        }

        if(codigo == KeyEvent.VK_A)
        {
            dx = -VELOCIDADE;
        }

        if(codigo == KeyEvent.VK_D)
        {
            dx = VELOCIDADE;
        }
        
    }

    public void mousePressed(MouseEvent evento) {
        if (evento.getButton() == MouseEvent.BUTTON1) {
            tiroSimples();
        }
    }

    public void keyReleased(KeyEvent tecla)
    {   
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_W)
        {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_S)
        {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_A)
        {
            dx = 0;
        }

        if(codigo == KeyEvent.VK_D)
        {
            dx = 0;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImagem() {
        return imagem;
    }

    public ArrayList<Tiro> getTiros() {
        return tiros;
    }

    public Rectangle getBounds() {
		return new Rectangle(x, y, largura, altura);
	}

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void somarScore()
    {
        this.score++;
    }

    

    

}
