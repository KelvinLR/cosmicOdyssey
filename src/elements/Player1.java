package elements;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.util.ArrayList;

public class Player1 {
    
    private int x,y;
    private int dx, dy;
    private Image imagem;
    private int altura, largura;
    private ArrayList <Tiro> tiros;

    public Player1()
    {
        this.x = 100;
        this.y = 100;

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

    public void tiroSimples()
    {
        this.tiros.add(new Tiro(x+largura, y+(altura/2)));
    }

    public void keyPressed(KeyEvent tecla)
    {   
        int codigo = tecla.getKeyCode();

        if(codigo == KeyEvent.VK_W)
        {
            dy = -3;
        }

        if(codigo == KeyEvent.VK_S)
        {
            dy = 3;
        }

        if(codigo == KeyEvent.VK_A)
        {
            dx = -3;
        }

        if(codigo == KeyEvent.VK_D)
        {
            dx = 3;
        }
        if(codigo == KeyEvent.VK_SPACE)
        {
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

    

}
