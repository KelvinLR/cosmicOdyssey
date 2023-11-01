package Container;

import javax.swing.JFrame;

import Fases.Fase;

public class Container extends JFrame {
    public Container(){
        add(new Fase());
        setTitle("Cosmic Odyssey: Luzes do Infinito");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        this.setResizable(false);
        setVisible(true);
    }
}
