package elements;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player2 {
    
    private int x,y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private ArrayList <Tiro> tiros;
    private boolean isVisible;

    
    public Player2()
    {
        this.x = 100;
        this.y = 150;
        this.isVisible = true;
        tiros = new ArrayList<Tiro>();
    }

    public void load()
    {
        ImageIcon referencia = new ImageIcon("res\\Player Nave\\spaceship2.png");
        this.imagem = referencia.getImage();

        this.altura = imagem.getHeight(null);
        this.largura = imagem.getWidth(null);
    }

    public void update()
    {
        x += dx;
        y += dy;
    }

    public Rectangle getBounds(){
        return new Rectangle(x,y,largura,altura );
    }

    public void tiroSimples()
    {
        this.tiros.add(new Tiro(x+largura, y+(altura/2)));
    }

    public void keyPressed(KeyEvent tecla)
    {   
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP)
        {
            dy = -3;
        }

        if(codigo == KeyEvent.VK_DOWN)
        {
            dy = 3;
        }

        if(codigo == KeyEvent.VK_LEFT)
        {
            dx = -3;
        }

        if(codigo == KeyEvent.VK_RIGHT)
        {
            dx = 3;
        }
        
        if(codigo == KeyEvent.VK_COMMA)
        {
            tiroSimples();
        }
    }


    public void keyReleased(KeyEvent tecla)
    {   
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_UP)
        {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_DOWN)
        {
            dy = 0;
        }

        if(codigo == KeyEvent.VK_LEFT)
        {
            dx = 0;
        }

        if(codigo == KeyEvent.VK_RIGHT)
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

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean isVisible) {
        this.isVisible = isVisible;
    }    

}
