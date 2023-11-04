/*
package Sprites;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
    private BufferedImage sprite;
    
    private Sprites(String path){
        try{
            sprite = ImageIO.read(getClass().getResource(path));
        }catch(IOException e){
            e.printStackTrace();
        }

    }
    public BufferedImage getSprite(int x, int y, int width, int height){
        return sprite.getSubimage(x, y, width, height);
    }
}
*/