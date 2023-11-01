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

import Player.Player;

public class Fase extends JPanel implements ActionListener {
    private Image background;
    private Player player1;
    private Timer timer;
    //private Player player2;

    public Fase(String path) {
        setFocusable(true);
        setDoubleBuffered(true);
        
        ImageIcon ref = new ImageIcon(path);
        background = ref.getImage();

        player1 = new Player();
        player1.load();

        addKeyListener(new TecladoAdapter());

        timer = new Timer(5, this);
        timer.start();
    }

    public void paint(Graphics g) {
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
        graphics.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);
        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        player1.update();
        repaint();
    }

    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent tecla) {
            player1.keyPressed(tecla);
        }

        @Override
        public void keyReleased(KeyEvent tecla) {
            player1.keyRelease(tecla);
        }
    }
}
