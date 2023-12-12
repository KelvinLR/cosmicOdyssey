//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package songs;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class EfeitosSonoros extends JPanel {
	public EfeitosSonoros() {
		this.setDoubleBuffered(true);
	}

	public void tocarFase() {
		try {
			URL soundFile = this.getClass().getResource("TrilhaSonora.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception var5) {
			JOptionPane.showInputDialog(this, var5);
		}

	}

	public void tocarBoss() {
		try {
			URL soundFile = this.getClass().getResource("SomBoss.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception var5) {
			JOptionPane.showInputDialog(this, var5);
		}

	}

	public void tocarTiro() {
		try {
			URL soundFile = this.getClass().getResource("SomTiro.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception var5) {
			JOptionPane.showInputDialog(this, var5);
		}

	}

	public void tocarSomExplosao() {
		try {
			URL soundFile = this.getClass().getResource("SomExplosao.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception var5) {
			JOptionPane.showInputDialog(this, var5);
		}

	}

	public void tocarSomTurbo() {
		try {
			URL soundFile = this.getClass().getResource("SomTurbo.wav");
			AudioInputStream sound = AudioSystem.getAudioInputStream(soundFile);
			DataLine.Info info = new DataLine.Info(Clip.class, sound.getFormat());
			Clip clip = (Clip)AudioSystem.getLine(info);
			clip.open(sound);
			clip.start();
		} catch (Exception var5) {
			JOptionPane.showInputDialog(this, var5);
		}

	}
}
