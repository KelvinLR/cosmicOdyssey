package Player;

import Tiros.Tiro;

import java.util.List;
import java.util.ArrayList;

import java.awt.Image;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

public class Player1 {
    private int x, y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private List<Tiro> tiros;

    private int upKey;
    private int downKey;
    private int leftKey;
    private int rightKey;
    private int shotKey;

    public Player1(int x, int upKey, int downKey, int leftKey, int rightKey) {
        this.x = x;
        this.y = 490;

        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;
        this.shotKey = shotKey;

        this.tiros = new ArrayList<Tiro>();
    }

    public void load(String path) {
        ImageIcon ref = new ImageIcon(path);
        imagem = ref.getImage();
        imagem = imagem.getScaledInstance(120, 120, Image.SCALE_DEFAULT);
        
        altura = imagem.getHeight(null);
        largura = imagem.getWidth(null);
    }

    public void update() {
        x += dx;
        y += dy;
    }

    public void tiroSimples() {
        //this.tiros.add(new Tiro(x + largura, y + (altura/2)));  //Sai do meio, adaptar pra sair dos lados
        this.tiros.add(new Tiro(x + 24, y));
        this.tiros.add(new Tiro(x + 71, y));
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();

        if(codigo == shotKey) {
            tiroSimples();
        } 
        
        if(codigo == upKey) {
            dy = -2;
        } else if (codigo == downKey) {
            dy = 2;
        } else if (codigo == leftKey) {
            dx = -3;
        } else if (codigo == rightKey) {
            dx = 3;
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

    public List<Tiro> getTiros() {
        return tiros;
    }
}
