package Player;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;

    public Player() {
        this.x = 380;
        this.y = 475;
    }

    public void load() {
        ImageIcon ref = new ImageIcon("assets\\sprites\\players\\p2-spaceship.png");
        imagem = ref.getImage();
        imagem = imagem.getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP) {
            dy = -3;
        } else if (codigo == KeyEvent.VK_DOWN) {
            dy = 3;
        } else if (codigo == KeyEvent.VK_LEFT) {
            dx = -4;
        } else if (codigo == KeyEvent.VK_RIGHT) {
            dx = 4;
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP) {
            dy = 0;
        } else if (codigo == KeyEvent.VK_DOWN) {
            dy = 0;
        } else if (codigo == KeyEvent.VK_LEFT) {
            dx = 0;
        } else if (codigo == KeyEvent.VK_RIGHT) {
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
}
