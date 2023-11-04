package Player;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player1 {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;

    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;

    public Player1(int x, int upKey, int downKey, int leftKey, int rightKey) {
        this.x = x;
        this.y = 475;

        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
    }

    public void load(String path) {
        ImageIcon ref = new ImageIcon(path);
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

        if(codigo == upKey) {
            dy = -3;
        } else if (codigo == downKey) {
            dy = 3;
        } else if (codigo == leftKey) {
            dx = -4;
        } else if (codigo == rightKey) {
            dx = 4;
        }
    }

    public void keyRelease(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if(codigo == upKey) {
            dy = 0;
        } else if (codigo == downKey) {
            dy = 0;
        } else if (codigo == leftKey) {
            dx = 0;
        } else if (codigo == rightKey) {
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
