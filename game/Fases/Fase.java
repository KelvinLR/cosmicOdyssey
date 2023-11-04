package Fases;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

import Player.Player1;

public class Fase extends JPanel implements ActionListener {
    private Image background;
    private Player1 player1;
    private Player1 player2;
    private Timer timer;
    
    public Fase(String path) {
        setFocusable(true);
        setDoubleBuffered(true);
        
        ImageIcon ref = new ImageIcon(path);
        background = ref.getImage();

        player1 = new Player1(190, KeyEvent.VK_UP, KeyEvent.VK_DOWN, KeyEvent.VK_LEFT, KeyEvent.VK_RIGHT);
        player1.load("assets\\sprites\\players\\p1-spaceship.png");

        player2 = new Player1(570, KeyEvent.VK_W, KeyEvent.VK_S, KeyEvent.VK_A, KeyEvent.VK_D);
        player2.load("assets\\sprites\\players\\p2-spaceship.png");

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        graphics.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);
        graphics.drawImage(player2.getImagem(), player2.getX(), player2.getY(), this);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player1.update();
        player2.update();
        repaint();
    }

    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent tecla) {
            player1.keyPressed(tecla);
            player2.keyPressed(tecla);
        }

        @Override
        public void keyReleased(KeyEvent tecla) {
            player1.keyRelease(tecla);
            player2.keyRelease(tecla);
        }
    }
}
