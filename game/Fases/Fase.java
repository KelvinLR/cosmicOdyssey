package Fases;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.nio.file.Path;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class Fase extends JPanel{
    private Image background;

    public Fase(String path) {
        ImageIcon ref = new ImageIcon(path);
        background = ref.getImage();
    }

    public void paint(Graphics g){
        Graphics2D graphics = (Graphics2D) g;
        graphics.drawImage(background, 0, 0, null);
    }

}
