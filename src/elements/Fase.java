package elements;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener, MouseListener {

    private Image fundo;
    private Player player1;
    private Timer timer;
    private ArrayList <Inimigo1> inimigos1;
    private ArrayList <Inimigo2> inimigos2;
    private List<Vida> vidas;
    private ArrayList <Explosao> explosoes;
    private boolean emJogo, emExplosao;
    private int powerUpVida = 0;
    private Font minecraftFont;

    private static int numeroInimigos = 20;

    public Fase()
    {
        setFocusable(true);
        setDoubleBuffered(true);
        emJogo = true;

        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource("res/pixel art _ Tumblr.gif"));
        fundo = referencia.getImage();
        //ImageIcon gamerOver = new ImageIcon(getClass().getClassLoader().getResource("res/fimdejogo.png"));
        //this.gameOver = referencia.getImage();

        player1 = new Player();

        powerUpVida = 0;
        vidas = new ArrayList<Vida>();

        addKeyListener(new TecladoAdapter());
        addMouseListener(new MouseAdaptado());

        waitForSeconds();
        Timer timer = new Timer(5,this);
        timer.start();

        inicializaInimigos();
        this.explosoes = new ArrayList<>();
        minecraftFont = carregarFonte();
    }

    public Font carregarFonte() {
        try {
            InputStream fontStream = getClass().getResourceAsStream("/res/Fonts/Minecraft.ttf");

            if (fontStream != null) {
                return Font.createFont(Font.TRUETYPE_FONT, fontStream).deriveFont(1, 18);
            } else {
                System.err.println("Arquivo de fonte não encontrado.");
            }
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        // Lida com exceções, por exemplo, carregando uma fonte padrão
        return new Font("Arial", Font.PLAIN, 18);
    }


    public void inicializaInimigos()
    {
        inimigos1 = new ArrayList<Inimigo1>();
        for (int i = 0; i < numeroInimigos; i++) {
			int x = (int) ((Math.random() * 8000) + 1024);
			int y = (int) ((Math.random() * 580) + 40);
            inimigos1.add(new Inimigo1(x, y));
		}

        inimigos2 = new ArrayList<Inimigo2>();
        for (int i = 0; i < numeroInimigos; i++) {
			int x = (int) ((Math.random() * 12000) + 1024);
			int y = (int) ((Math.random() * 580) + 40);
            inimigos2.add(new Inimigo2(x, y));
		}

    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);

            ArrayList<Tiro> tiros = player1.getTiros();

            //Tiros do Player
            for (int i = 0; i < tiros.size(); i++) {
                Tiro t = tiros.get(i);
                    graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);
            }
            //////////////////////////////////////////////////////////////////////////////

            //Inimigos 1
            for (int i = 0; i < inimigos1.size(); i++) {
                Inimigo1 in = inimigos1.get(i);
                if (in.getBounds().intersects(0, 0, 1024, 728)) {
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }
            }
            //////////////////////////////////////////////////////////////////////////////

            //Inimigos 2 e seus Tiros
            for (int i = 0; i < inimigos2.size(); i++) {
                Inimigo2 in = inimigos2.get(i);
                if (in.getBounds().intersects(0, 0, 1024, 728)) {
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }
            }

            for (int i = 0; i < inimigos2.size(); i++) {
                List<Tiro_Inimigo2> tirosInimigo = inimigos2.get(i).getTiroInimigo();
                for (int o = 0; o < tirosInimigo.size(); o++) {
                    Tiro_Inimigo2 m = (Tiro_Inimigo2) tirosInimigo.get(o);
                    if (m.getBounds().intersects(0, 0, 1024, 728)) {
                        graficos.drawImage(m.getImagem(), m.getX(), m.getY(), this);
                    }
                }
            }
            //////////////////////////////////////////////////////////////////////////////

            //Explosões
            for (int i = 0; i < explosoes.size(); i++) {
                Explosao ex = explosoes.get(i);
                graficos.drawImage(ex.getImagem(), ex.getX(), ex.getY(), this);
            }
            /////////////////////////////////////////////////////////////////////////////

            //PowerUp Vida
            for (int k = 0; k < vidas.size(); k++) {
                Vida on = vidas.get(k);

                graficos.drawImage(on.getVida(), on.getX(), on.getY(), null);
            }
            /////////////////////////////////////////////////////////////////////////////////

            //Status da vida
            int a = 10;
            for (int j = 0; j < player1.getVida(); j++) {
                ImageIcon vida = new ImageIcon(getClass().getClassLoader().getResource("res/Vida.png"));
                graficos.drawImage(vida.getImage(), a, 10, null);
                a += 30;
            }
            /////////////////////////////////////////////////////////////////////////////////////

            //Score
            graficos.setColor(Color.white);
            graficos.setFont(minecraftFont);

            String scoreText = "SCORE: " + player1.getScore();
            int textWidth = graficos.getFontMetrics().stringWidth(scoreText);

            int screenWidth = getWidth();
            int screenHeight = getHeight();

            int x = (screenWidth - textWidth) / 2; // Centraliza horizontalmente
            int y = 30; // Define a posição vertical (ajuste conforme necessário)

            graficos.drawString(scoreText, x, y);
        }
        else
        {
            ImageIcon gamerOver = new ImageIcon(getClass().getClassLoader().getResource("res/fimdejogo.png"));
            graficos.drawImage(gamerOver.getImage(), 0, 0, null);
        }

        g.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        ArrayList<Tiro> tiros = player1.getTiros();
        player1.update();
        
        for(int i=0 ; i < tiros.size() ; i++)
        {
            if(tiros.get(i).isVisible()){
                tiros.get(i).update();
            }
            else{
                tiros.remove(i);
                i--;
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
                i--;
                explosoes.add(new Explosao(in.getX(), in.getY()));

                powerUpVida++;
                if (powerUpVida == 5) {
					vidas.add(new Vida(in.getX(), in.getY()));
					powerUpVida = 0;
				}
            }
        }

        if(inimigos2 != null && !inimigos2.isEmpty()) {
            for (int i = 0; i < inimigos2.size(); i++) {
                Inimigo2 in = inimigos2.get(i);

                if (in.isVisible()) {
                    in.update();
                } else {
                    inimigos2.remove(i);
                    i--;
                    explosoes.add(new Explosao(in.getX(), in.getY()));
                }
            }
        }

        if(inimigos2 != null && !inimigos2.isEmpty()) {
            for (int q = 0; q < inimigos2.size(); q++) {
                List<Tiro_Inimigo2> tiroInimigos = inimigos2.get(q).getTiroInimigo();
                for (int o = 0; o < tiroInimigos.size(); o++) {
                    Tiro_Inimigo2 m = (Tiro_Inimigo2) tiroInimigos.get(o);
                    if (m.isVisible()) {
                        m.update();
                    } else {
                        tiroInimigos.remove(o);
                        o--;
                    }

                }
            }
        }

        if(explosoes != null && !explosoes.isEmpty()) {
            for (int q = 0; q < explosoes.size(); q++) {
                Explosao y = explosoes.get(q);
                if (y.isVisivel()) {
                    y.update();
                } else {
                    explosoes.remove(q);
                    q--;
                }
            }
        }

        for (int p = 0; p < vidas.size(); p++) {
			Vida on = vidas.get(p);

                if(on.isVisivel())
                    on.update();
                else {
                    vidas.remove(p);
                    p--;
                }
		}

        checarColisoes();

        repaint();
    }

    public void checarColisoes()
    {
        Rectangle formaNave = player1.getBounds();
        Rectangle formaInimigo1;
        Rectangle formaInimigo2;
        Rectangle formaTiroInimigo;
        Rectangle formaTiro;
        
        //Inimigo1 e player
        for(int i=0 ; i<inimigos1.size() ; i++)
        {
            formaInimigo1 = inimigos1.get(i).getBounds();

            if(formaNave.intersects(formaInimigo1))
            {
                player1.somarScore();

                int a = player1.getVida();

                if (a == 0) {
                    player1.setVisible(false);
                    emJogo = false;
                } else {
                    player1.setVida(a - 1);
                    inimigos1.get(i).setVisible(false);
                    break;
                }
            }
        }

        //Inimigo2 & SeusTiros e player
        for(int i=0 ; i<inimigos2.size() ; i++)
        {
            formaInimigo2 = inimigos2.get(i).getBounds();
            Inimigo2 tempInimigo2 = inimigos2.get(i);
            int a = player1.getVida();

            if(formaNave.intersects(formaInimigo2))
            {
                player1.somarScore();

                if (a == 0) {
                    player1.setVisible(false);
                    emJogo = false;
                } else {
                    player1.setVida(a - 1);
                    tempInimigo2.setVisivel(false);
                    break;
                }
            }

            ArrayList<Tiro_Inimigo2> tirosInimigo = tempInimigo2.getTiroInimigo();

            for(int j=0 ; j<tirosInimigo.size() ; j++)
            {
                Tiro_Inimigo2 tempTiroInimigo = tirosInimigo.get(j);
                formaTiroInimigo = tempTiroInimigo.getBounds();

                if(formaNave.intersects(formaTiroInimigo))
                {
                    if (a == 0) {
                    player1.setVisible(false);
                    emJogo = false;
                    } else {
                        player1.setVida(a - 1);
                        tempTiroInimigo.setVisivel(false);
                        break;
                    }
                }
            }
        }

        List<Tiro> tiros = player1.getTiros();

		// Tiro player e Inimigos
		for (int i = 0; i < tiros.size(); i++) {
			Tiro temptiro = tiros.get(i);
			formaTiro = temptiro.getBounds();

			for (int j = 0; j < inimigos1.size(); j++) {
				Inimigo1 tempRedUfo = inimigos1.get(j);
				formaInimigo1 = tempRedUfo.getBounds();

				if (formaTiro.intersects(formaInimigo1)) {
                    player1.somarScore();
					//ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					// explosion = referencia2.getImage();
					//emExplosao = true;
					tempRedUfo.setVisible(false);
					temptiro.setVisible(false);
                    break;
				}

			}

			for (int j = 0; j < inimigos2.size(); j++) {
				Inimigo2 tempGreenFire = inimigos2.get(j);
				formaInimigo2 = tempGreenFire.getBounds();

				if (formaTiro.intersects(formaInimigo2)) {
                    player1.somarScore();
					//ImageIcon referencia2 = new ImageIcon("res\\explosion1.gif");
					// explosion = referencia2.getImage();
					//emExplosao = true;
					tempGreenFire.setVisivel(false);
					temptiro.setVisible(false);
                    break;
				}

			}

        }

        // Vidas spawnadas

        Rectangle formaPowerUpVida;

        for (int i = 0; i < vidas.size(); i++) {
			Vida tempPowerUpVida = vidas.get(i);
			formaPowerUpVida = tempPowerUpVida.getBounds();

			if (formaNave.intersects(formaPowerUpVida)) {
				if (player1.getVida() < 5) {
					player1.setVida(player1.getVida() + 1);
					vidas.remove(i);
				}
                break;
			}
        }

    }

    // Temporizador
	public void waitForSeconds() {
		Timer timer;
		Timer timer2;
		Timer timerChefao;

		timer = new Timer(2000, new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < explosoes.size(); i++) {
					explosoes.get(i).setVisivel(false);
				}
			}

		});

		timer.start();
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
            if(emJogo)
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
