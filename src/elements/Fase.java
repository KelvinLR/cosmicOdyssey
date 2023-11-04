package elements;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.util.ArrayList;

public class Fase extends JPanel implements ActionListener, MouseListener {
    
    private Image fundo;
    private Player player1;
    private Timer timer;
    private ArrayList <Inimigo1> inimigos1;

    public Fase()
    {
        setFocusable(true);
        setDoubleBuffered(true);

        ImageIcon referencia = new ImageIcon("res\\Paineis\\pixel art _ Tumblr.gif");

        fundo = referencia.getImage();

        //setBackground(Color.black);

        player1 = new Player();
        player1.load();

        addKeyListener(new TecladoAdapter());
        addMouseListener(new MouseAdaptado());

        Timer timer = new Timer(5,this);
        timer.start();

        inicializaInimigos();
    }

    public void inicializaInimigos()
    {
        int cordenadas[] = new int[40];
        inimigos1 = new ArrayList<Inimigo1>();

        for (int i = 0; i < cordenadas.length; i++) {
			int x = (int) ((Math.random() * 8000) + 1024);
			int y = (int) ((Math.random() * 650) + 30);
            inimigos1.add(new Inimigo1(x, y));
		}
    }

    public void paint(Graphics g)
    {
        Graphics2D graficos = (Graphics2D) g;
        graficos.drawImage(fundo, 0, 0, null);
        graficos.drawImage(player1.getImagem(),player1.getX(),player1.getY(),this);

        ArrayList<Tiro> tiros = player1.getTiros();

        for(int i=0 ; i < tiros.size() ; i++)
        {
            Tiro t = tiros.get(i);
            t.load();
            graficos.drawImage(t.getImagem(),t.getX(),t.getY(),this);
        }

        for(int i=0 ; i < inimigos1.size() ; i++)
        {
            Inimigo1 in = inimigos1.get(i);
            in.load();
            graficos.drawImage(in.getImagem(),in.getX(),in.getY(),this);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player1.update();

        ArrayList<Tiro> tiros = player1.getTiros();

        for(int i=0 ; i < tiros.size() ; i++)
        {
            if(tiros.get(i).isVisible()){
                tiros.get(i).update();
            }
            else{
                tiros.remove(i);
            }
        }

        for(int i=0 ; i < inimigos1.size() ; i++)
        {
            Inimigo1 in = inimigos1.get(i);

            if(in.isVisible()){
                in.update();
            }
            else{
                inimigos1.remove(i);
            }
        }
        
        repaint();
    }

    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
        }
    }

     private class MouseAdaptado extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            player1.mousePressed(e);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
    }

    @Override
    public void mouseExited(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
    }
}
