//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package elements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;
import meujogo.Container;

public class Menu extends JPanel implements ActionListener {
	private int currentOption = 0;
	private int maxOption = 2;
	private boolean up;
	private boolean down;
	private boolean enter;
	private Image player;
	private Image background;
	private Container container;

	public Menu(Container container) {
		this.setFocusable(true);
		this.setDoubleBuffered(true);
		ImageIcon ref = new ImageIcon(this.getClass().getClassLoader().getResource("res/space.gif"));
		this.background = ref.getImage();
		ref = new ImageIcon(this.getClass().getClassLoader().getResource("res/spaceship2_small.png"));
		this.player = ref.getImage();
		this.addKeyListener(new TecladoAdapter());
		Timer timer = new Timer(16, this);
		timer.start();
		this.container = container;
	}

	public void tick() {
		if (this.up) {
			this.up = false;
			--this.currentOption;
			if (this.currentOption < 0) {
				this.currentOption = this.maxOption;
			}
		}

		if (this.down) {
			this.down = false;
			++this.currentOption;
			if (this.currentOption > this.maxOption) {
				this.currentOption = 0;
			}
		}

		if (this.enter) {
			this.enter = false;
			if (this.currentOption == 0) {
				Container.gameState = "JOGO";
				new Container(2);
				this.container.dispose();
			} else if (this.currentOption == 1) {
				new Container(3);
				this.container.dispose();
			} else if (this.currentOption == 2) {
				System.exit(0);
			}
		}

	}

	public void render(Graphics g) {
		Font minecraftFont = this.carregarFonte2();

		minecraftFont = minecraftFont.deriveFont(Font.PLAIN, 70);
		g.setFont(minecraftFont);
		g.setColor(Color.WHITE);

		int screenWidth = this.getWidth();
		int screenHeight = this.getHeight();

		String texto1 = "COSMIC";
		int larguraTexto1 = g.getFontMetrics().stringWidth(texto1);
		int xTexto1 = 137;
		g.drawString(texto1, xTexto1, 100);

		String texto2 = "ODYSSEY";
		int larguraTexto2 = g.getFontMetrics().stringWidth(texto2);
		int xTexto2 = 120;
		g.drawString(texto2, xTexto2, 145);

		minecraftFont = this.carregarFonte();

		minecraftFont = minecraftFont.deriveFont(Font.PLAIN, 18.0F);
		g.setFont(minecraftFont);
		g.setColor(Color.WHITE);

		g.drawString("SINGLEPLAYER", 178, 240);
		g.drawString("MULTIPLAYER", 186, 270);
		g.drawString("QUIT", 228, 300);
		g.setColor(Color.YELLOW);
		if (this.currentOption == 0) {
			g.drawImage(this.player, 148, 226, (ImageObserver)null);
			g.drawString("SINGLEPLAYER", 178, 240);
		}

		if (this.currentOption == 1) {
			g.drawImage(this.player, 156, 256, (ImageObserver)null);
			g.drawString("MULTIPLAYER", 186, 270);
		}

		if (this.currentOption == 2) {
			g.drawImage(this.player, 199, 286, (ImageObserver)null);
			g.drawString("QUIT", 228, 300);
		}

	}

	public Font carregarFonte() {
		try {
			InputStream fontStream = this.getClass().getResourceAsStream("/res/Fonts/Minecraft.ttf");
			if (fontStream != null) {
				return Font.createFont(0, fontStream).deriveFont(Font.BOLD, 18.0F);
			}

			System.err.println("Arquivo de fonte não encontrado.");
		} catch (FontFormatException | IOException var2) {
			var2.printStackTrace();
		}

		return new Font("Arial", 0, 18);
	}

	public Font carregarFonte2() {
		try {
			InputStream fontStream = this.getClass().getResourceAsStream("/res/Fonts/ARCADECLASSIC.TTF");
			if (fontStream != null) {
				return Font.createFont(0, fontStream).deriveFont(Font.BOLD, 18.0F);
			}

			System.err.println("Arquivo de fonte não encontrado.");
		} catch (FontFormatException | IOException var2) {
			var2.printStackTrace();
		}

		return new Font("Arial", 0, 18);
	}

	public void paint(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		g2.drawImage(this.background, 0, 0, (ImageObserver)null);
		g2.scale(2.0, 2.0);
		if (Container.gameState.equals("MENU")) {
			this.render(g2);
		}

		g.dispose();
	}

	public void actionPerformed(ActionEvent e) {
		if (Container.gameState == "MENU") {
			this.tick();
		}

		this.repaint();
	}

	private class TecladoAdapter extends KeyAdapter {
		private TecladoAdapter() {
		}

		public void keyPressed(KeyEvent e) {
			if ((e.getKeyCode() == 38 || e.getKeyCode() == 87) && Container.gameState == "MENU") {
				Menu.this.up = true;
			}

			if ((e.getKeyCode() == 40 || e.getKeyCode() == 83) && Container.gameState == "MENU") {
				Menu.this.down = true;
			}

			if (e.getKeyCode() == 10 && Container.gameState == "MENU") {
				Menu.this.enter = true;
			}

		}

		public void keyReleased(KeyEvent e) {
			if ((e.getKeyCode() == 38 || e.getKeyCode() == 87) && Container.gameState == "MENU") {
				Menu.this.up = false;
			}

			if ((e.getKeyCode() == 40 || e.getKeyCode() == 83) && Container.gameState == "MENU") {
				Menu.this.down = false;
			}

			if (e.getKeyCode() == 10 && Container.gameState == "MENU") {
				Menu.this.enter = false;
			}

		}
	}
}
