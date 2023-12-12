//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import javax.swing.ImageIcon;

public class Player {
    private int x;
    private int y;
    private int dx;
    private int dy;
    private Image imagem;
    private int altura;
    private int largura;
    private ArrayList<Tiro> tiros;
    private static int VELOCIDADE = 5;
    private boolean isVisible;
    private boolean explodido;
    private int vida;
    private int score;
    private int p;

    public Player(int p) {
        this.p = p;
        switch (p) {
            case 1:
                this.x = 100;
                this.y = 100;
                ImageIcon referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/player1.png"));
                this.imagem = referencia.getImage();
                break;
            case 2:
                this.x = 100;
                this.y = 300;
                ImageIcon referencia2 = new ImageIcon(this.getClass().getClassLoader().getResource("res/player2.png"));
                this.imagem = referencia2.getImage();
        }

        this.isVisible = true;
        this.explodido = false;
        this.vida = 5;
        this.score = 0;
        this.tiros = new ArrayList();
        this.altura = this.imagem.getHeight((ImageObserver)null);
        this.largura = this.imagem.getWidth((ImageObserver)null);
    }

    public void setImagemvida() {
        ImageIcon referencia;
        if (this.p == 1) {
            if (this.vida == 1) {
                referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/player1midDamage.png"));
                this.imagem = referencia.getImage();
            } else if (this.vida == 0) {
                referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/player1lowDamage.png"));
                this.imagem = referencia.getImage();
            } else {
                referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/player1.png"));
                this.imagem = referencia.getImage();
            }
        } else if (this.vida == 1) {
            referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/player2midDamage.png"));
            this.imagem = referencia.getImage();
        } else if (this.vida == 0) {
            referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/player2lowDamage.png"));
            this.imagem = referencia.getImage();
        } else {
            referencia = new ImageIcon(this.getClass().getClassLoader().getResource("res/player2.png"));
            this.imagem = referencia.getImage();
        }

    }

    public void update() {
        if (this.isVisible) {
            this.x += this.dx;
            this.y += this.dy;
            if (this.x < 6) {
                this.x = 6;
            }

            if (this.x > 938) {
                this.x = 938;
            }

            if (this.y < 60) {
                this.y = 60;
            }

            if (this.y > 600) {
                this.y = 600;
            }

            this.setImagemvida();
        }

    }

    public void tiroSimples() {
        this.tiros.add(new Tiro(this.x + this.largura, this.y + this.altura / 2));
    }

    public void keyPressed(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (this.p == 1) {
            if (codigo == 87) {
                this.dy = -VELOCIDADE;
            }

            if (codigo == 83) {
                this.dy = VELOCIDADE;
            }

            if (codigo == 65) {
                this.dx = -VELOCIDADE;
            }

            if (codigo == 68) {
                this.dx = VELOCIDADE;
            }

            if (codigo == 32 && this.tiros.size() < 10) {
                this.tiroSimples();
            }
        } else if (this.p == 2) {
            if (codigo == 38) {
                this.dy = -VELOCIDADE;
            }

            if (codigo == 40) {
                this.dy = VELOCIDADE;
            }

            if (codigo == 37) {
                this.dx = -VELOCIDADE;
            }

            if (codigo == 39) {
                this.dx = VELOCIDADE;
            }

            if (codigo == 44 && this.tiros.size() < 20) {
                this.tiroSimples();
            }
        }

    }

    public void keyReleased(KeyEvent tecla) {
        int codigo = tecla.getKeyCode();
        if (this.p == 1) {
            if (codigo == 87) {
                this.dy = 0;
            }

            if (codigo == 83) {
                this.dy = 0;
            }

            if (codigo == 65) {
                this.dx = 0;
            }

            if (codigo == 68) {
                this.dx = 0;
            }
        } else if (this.p == 2) {
            if (codigo == 38) {
                this.dy = 0;
            }

            if (codigo == 40) {
                this.dy = 0;
            }

            if (codigo == 37) {
                this.dx = 0;
            }

            if (codigo == 39) {
                this.dx = 0;
            }
        }

    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public Image getImagem() {
        return this.imagem;
    }

    public ArrayList<Tiro> getTiros() {
        return this.tiros;
    }

    public Rectangle getBounds() {
        return new Rectangle(this.x, this.y, this.largura, this.altura);
    }

    public boolean isVisible() {
        return this.isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }

    public int getVida() {
        return this.vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public int getScore() {
        return this.score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void somarScore() {
        ++this.score;
    }

    public boolean getExplodido() {
        return this.explodido;
    }

    public void setExplodido(boolean explodido) {
        this.explodido = explodido;
    }
}
