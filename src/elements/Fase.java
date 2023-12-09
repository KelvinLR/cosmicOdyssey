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
import java.awt.event.MouseListener;
import java.io.IOException;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Fase extends JPanel implements ActionListener {

    private Image fundo;
    private Player player1;
    private Player player2;
    private Timer timer;
    private ArrayList<InimigoComum> inimigosComuns;
    private ArrayList<InimigoAtirador> inimigosAtiradores;
    private List<Vida> vidas;
    private ArrayList <Explosao> explosoes;
    private boolean emJogo, emExplosao;
    private int powerUpVida = 0;
    private Font minecraftFont;
    private static int numeroInimigos = 20;
    // Construtor da classe Fase com a referência da tela de fundo.
    public Fase(String ref) {
        setFocusable(true);
        setDoubleBuffered(true);
        emJogo = true;

        
        ImageIcon referencia = new ImageIcon(getClass().getClassLoader().getResource(ref));
        fundo = referencia.getImage();
        //ImageIcon gamerOver = new ImageIcon(getClass().getClassLoader().getResource("res/fimdejogo.png"));
        //this.gameOver = referencia.getImage();

        player1 = new Player(1);
        player2 = new Player(2);

        powerUpVida = 0;
        vidas = new ArrayList<Vida>();

        addKeyListener(new TecladoAdapter());

        waitForSeconds();
        Timer timer = new Timer(5,this);
        timer.start();

        spawnEnemies1();
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


    public void spawnEnemies1()
    {
        inimigosComuns = new ArrayList<InimigoComum>();
        for (int i = 0; i < numeroInimigos; i++) {
			int x = (int) ((Math.random() * 8000) + 1024);
			int y = (int) ((Math.random() * 580) + 40);
            inimigosComuns.add(new InimigoComum(x, y));
		}

        spawnEnemies2();
    }

    public void spawnEnemies2(){
        inimigosAtiradores = new ArrayList<InimigoAtirador>();
        for (int i = 0; i < numeroInimigos; i++) {
            int x = (int) ((Math.random() * 12000) + 1024);
            int y = (int) ((Math.random() * 580) + 40);
            inimigosAtiradores.add(new InimigoAtirador(x, y));
        }
    }

    public void arrayShot(Graphics g){
        ArrayList<Tiro> tiros = player1.getTiros();
        ArrayList<Tiro> tiros2 = player2.getTiros();
        Graphics2D graficos = (Graphics2D) g;
        for (int i = 0; i < tiros.size(); i++) {
            Tiro t = tiros.get(i);
            graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);
        }

        for (int i = 0; i < tiros2.size(); i++) {
            Tiro t = tiros2.get(i);
            graficos.drawImage(t.getImagem(), t.getX(), t.getY(), this);
        }
    }

    public void paint(Graphics g) {
        Graphics2D graficos = (Graphics2D) g;

        if (emJogo) {
            graficos.drawImage(fundo, 0, 0, null);
            graficos.drawImage(player1.getImagem(), player1.getX(), player1.getY(), this);
            graficos.drawImage(player2.getImagem(), player2.getX(), player2.getY(), this);

            arrayShot(g);

            //Inimigos 1
            for (int i = 0; i < inimigosComuns.size(); i++) {
                InimigoComum in = inimigosComuns.get(i);
                if (in.getBounds().intersects(0, 0, 1024, 728)) {
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }
            }
            //////////////////////////////////////////////////////////////////////////////

            //Inimigos 2 e seus Tiros
            for (int i = 0; i < inimigosAtiradores.size(); i++) {
                InimigoAtirador in = inimigosAtiradores.get(i);
                if (in.getBounds().intersects(0, 0, 1024, 728)) {
                    graficos.drawImage(in.getImagem(), in.getX(), in.getY(), this);
                }
            }

            for (int i = 0; i < inimigosAtiradores.size(); i++) {
                List<TiroInimigo> tirosInimigo = inimigosAtiradores.get(i).getTirosInimigo();
                for (int o = 0; o < tirosInimigo.size(); o++) {
                    TiroInimigo m = (TiroInimigo) tirosInimigo.get(o);
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

                graficos.drawImage(on.getImagem(), on.getX(), on.getY(), null);
            }
            /////////////////////////////////////////////////////////////////////////////////

            //Status da vida
            int x1 = 10;
            for (int j = 0; j < player1.getVida(); j++) {
                ImageIcon vida = new ImageIcon(getClass().getClassLoader().getResource("res/Vida.png"));
                graficos.drawImage(vida.getImage(), x1, 10, null);
                x1 += 30;
            }

            int x2 = 10;
            for (int j = 0; j < player2.getVida(); j++) {
                ImageIcon vida = new ImageIcon(getClass().getClassLoader().getResource("res/Vida.png"));
                graficos.drawImage(vida.getImage(), x2, 40, null);
                x2 += 30;
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
        ArrayList<Tiro> tiros2 = player2.getTiros();

        player1.update();
        player2.update();
        
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

        for(int i=0 ; i < tiros2.size() ; i++)
        {
            if(tiros2.get(i).isVisible()){
                tiros2.get(i).update();
            }
            else{
                tiros2.remove(i);
                i--;
            }
        }
        

        for(int i=0 ; i < inimigosComuns.size() ; i++)
        {
            InimigoComum in = inimigosComuns.get(i);

            if(in.isVisible()){
                in.update();
            }
            else{
                inimigosComuns.remove(i);
                i--;
                explosoes.add(new Explosao(in.getX(), in.getY()));
                System.out.println(inimigosComuns.size());
                powerUpVida++;
                if (powerUpVida == 5) {
					vidas.add(new Vida(in.getX(), in.getY()));
					powerUpVida = 0;
				}
            }
        }

        if(inimigosAtiradores != null && !inimigosAtiradores.isEmpty() && inimigosComuns.isEmpty()) {
            for(int i=0 ; i < inimigosComuns.size() ; i++)
            {
                InimigoComum in = inimigosComuns.get(i);

                if(in.isVisible()){
                    in.update();
                }
                else{
                    inimigosComuns.remove(i);
                    i--;
                    explosoes.add(new Explosao(in.getX(), in.getY()));
                    System.out.println(inimigosComuns.size());
                    powerUpVida++;
                    if (powerUpVida == 5) {
                        vidas.add(new Vida(in.getX(), in.getY()));
                        powerUpVida = 0;
                    }
                }
            }
            for (int i = 0; i < inimigosAtiradores.size(); i++) {
                InimigoAtirador in = inimigosAtiradores.get(i);

                if (in.isVisible()) {
                    in.update();
                } else {
                    inimigosAtiradores.remove(i);
                    i--;
                    explosoes.add(new Explosao(in.getX(), in.getY()));
                }
            }
        }

        if(inimigosAtiradores != null && !inimigosAtiradores.isEmpty()) {
            for (int q = 0; q < inimigosAtiradores.size(); q++) {
                List<TiroInimigo> tiroInimigos = inimigosAtiradores.get(q).getTirosInimigo();
                for (int o = 0; o < tiroInimigos.size(); o++) {
                    TiroInimigo m = (TiroInimigo) tiroInimigos.get(o);
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
                if (y.isVisible()) {
                    y.update();
                } else {
                    explosoes.remove(q);
                    q--;
                }
            }
        }

        for (int p = 0; p < vidas.size(); p++) {
			Vida on = vidas.get(p);

                if(on.isVisible())
                    on.update();
                else {
                    vidas.remove(p);
                    p--;
                }
		}

        collisions();

        repaint();
    }

    public void collisions()
    {
        Rectangle formaNave = player1.getBounds();
        Rectangle formaNave2 = player2.getBounds();
        Rectangle formaInimigo1;
        Rectangle formaInimigo2;
        Rectangle formaTiroInimigo;
        Rectangle formaTiro;
        Rectangle formaTiro2;
        
        //Inimigo1, player1 e player2
        for(int i=0 ; i<inimigosComuns.size() ; i++)
        {
            formaInimigo1 = inimigosComuns.get(i).getBounds();

            if(formaNave.intersects(formaInimigo1))
            {
                player1.somarScore();

                int a = player1.getVida();

                if (a == 0) {
                    player1.setVisible(false);
                    emJogo = false;
                } else {
                    player1.setVida(a - 1);
                    inimigosComuns.get(i).setVisible(false);
                    break;
                }
            }
        }

        for(int i=0 ; i<inimigosComuns.size() ; i++)
        {
            formaInimigo1 = inimigosComuns.get(i).getBounds();

            if(formaNave2.intersects(formaInimigo1))
            {
                player2.somarScore();

                int a = player2.getVida();

                if (a == 0) {
                    player2.setVisible(false);
                    emJogo = false;
                } else {
                    player2.setVida(a - 1);
                    inimigosComuns.get(i).setVisible(false);
                    break;
                }
            }
        }
        //Inimigo2 & SeusTiros, player1 e player2
        for(int i=0 ; i<inimigosAtiradores.size() ; i++)
        {
            formaInimigo2 = inimigosAtiradores.get(i).getBounds();
            InimigoAtirador tempInimigo2 = inimigosAtiradores.get(i);
            int a = player1.getVida();

            if(formaNave.intersects(formaInimigo2))
            {
                player1.somarScore();

                if (a == 0) {
                    player1.setVisible(false);
                    emJogo = false;
                } else {
                    player1.setVida(a - 1);
                    tempInimigo2.setVisible(false);
                    break;
                }
            }

            ArrayList<TiroInimigo> tirosInimigo = tempInimigo2.getTirosInimigo();

            for(int j=0 ; j<tirosInimigo.size() ; j++)
            {
                TiroInimigo tempTiroInimigo = tirosInimigo.get(j);
                formaTiroInimigo = tempTiroInimigo.getBounds();

                if(formaNave.intersects(formaTiroInimigo))
                {
                    if (a == 0) {
                    player1.setVisible(false);
                    emJogo = false;
                    } else {
                        player1.setVida(a - 1);
                        tempTiroInimigo.setVisible(false);
                        break;
                    }
                }
            }
        }

        for(int i=0 ; i<inimigosAtiradores.size() ; i++)
        {
            formaInimigo2 = inimigosAtiradores.get(i).getBounds();
            InimigoAtirador tempInimigo2 = inimigosAtiradores.get(i);
            int a = player2.getVida();

            if(formaNave2.intersects(formaInimigo2))
            {
                player2.somarScore();

                if (a == 0) {
                    player2.setVisible(false);
                    emJogo = false;
                } else {
                    player2.setVida(a - 1);
                    tempInimigo2.setVisible(false);
                    break;
                }
            }

            ArrayList<TiroInimigo> tirosInimigo = tempInimigo2.getTirosInimigo();

            for(int j=0 ; j<tirosInimigo.size() ; j++)
            {
                TiroInimigo tempTiroInimigo = tirosInimigo.get(j);
                formaTiroInimigo = tempTiroInimigo.getBounds();

                if(formaNave2.intersects(formaTiroInimigo))
                {
                    if (a == 0) {
                    player2.setVisible(false);
                    emJogo = false;
                    } else {
                        player2.setVida(a - 1);
                        tempTiroInimigo.setVisible(false);
                        break;
                    }
                }
            }
        }

        List<Tiro> tiros = player1.getTiros();
        List<Tiro> tiros2 = player2.getTiros();

		// Tiro player e Inimigos
		for (int i = 0; i < tiros.size(); i++) {
			Tiro temptiro = tiros.get(i);
			formaTiro = temptiro.getBounds();

			for (int j = 0; j < inimigosComuns.size(); j++) {
				InimigoComum tempRedUfo = inimigosComuns.get(j);
				formaInimigo1 = tempRedUfo.getBounds();

				if (formaTiro.intersects(formaInimigo1)) {
                    player1.somarScore();
					tempRedUfo.setVisible(false);
					temptiro.setVisible(false);
                    break;
				}

			}

			for (int j = 0; j < inimigosAtiradores.size(); j++) {
				InimigoAtirador tempGreenFire = inimigosAtiradores.get(j);
				formaInimigo2 = tempGreenFire.getBounds();

				if (formaTiro.intersects(formaInimigo2)) {
                    player1.somarScore();
					tempGreenFire.setVisible(false);
					temptiro.setVisible(false);
                    break;
				}

			}

        }

        for (int i = 0; i < tiros2.size(); i++) {
			Tiro temptiro = tiros2.get(i);
			formaTiro2 = temptiro.getBounds();

			for (int j = 0; j < inimigosComuns.size(); j++) {
				InimigoComum tempRedUfo = inimigosComuns.get(j);
				formaInimigo1 = tempRedUfo.getBounds();

				if (formaTiro2.intersects(formaInimigo1)) {
                    player1.somarScore();
					tempRedUfo.setVisible(false);
					temptiro.setVisible(false);
                    break;
				}

			}

			for (int j = 0; j < inimigosAtiradores.size(); j++) {
				InimigoAtirador tempGreenFire = inimigosAtiradores.get(j);
				formaInimigo2 = tempGreenFire.getBounds();

				if (formaTiro2.intersects(formaInimigo2)) {
                    player1.somarScore();
					tempGreenFire.setVisible(false);
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

        for (int i = 0; i < vidas.size(); i++) {
            Vida tempPowerUpVida = vidas.get(i);
            formaPowerUpVida = tempPowerUpVida.getBounds();

            if (formaNave2.intersects(formaPowerUpVida)) {
                if (player2.getVida() < 5) {
                    player2.setVida(player2.getVida() + 1);
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
					explosoes.get(i).setVisible(false);
				}
			}

		});

		timer.start();
	}

    private class TecladoAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            player1.keyPressed(e);
            player2.keyPressed(e);
        }
    
        @Override
        public void keyReleased(KeyEvent e) {
            player1.keyReleased(e);
            player2.keyReleased(e);
        }
    }

}
