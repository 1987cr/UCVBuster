
package Decorator;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class ConcretePersonalizarVideo implements PersonalizarVideo{
    
    private final String sourcePath;    
    private final String targetPath;
    
    public ConcretePersonalizarVideo(String source, String destiny, String name){
        this.sourcePath = source;
        this.targetPath = destiny + "/" + name + ".png";

        baseImage();
    }
    
    public void baseImage() {
        BufferedImage baseImage = null;
        
        try {
            baseImage = ImageIO.read(new File(sourcePath));
        } catch (IOException ex) {
            Logger.getLogger(ConcretePersonalizarVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedImage resizedImage = new BufferedImage(500, 300, BufferedImage.TYPE_INT_ARGB);
        
        Graphics2D g = resizedImage.createGraphics();
	g.drawImage(baseImage, 0, 0, 500, 300, null);
	g.dispose();
        
        try {
            ImageIO.write(resizedImage, "PNG", new File(targetPath));
        } catch (IOException ex) {
            Logger.getLogger(ConcretePersonalizarVideo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public String getTargetPath(){
        return targetPath;
    }

    @Override
    public void mergeImages(String overlayName,String source, String target) {
        BufferedImage baseImg = null;
        BufferedImage overlayImg = null;
        
        try {
            baseImg = ImageIO.read(new File(source));
        } catch (IOException ex) {
            Logger.getLogger(VideoDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            overlayImg = ImageIO.read(this.getClass().getResource("/resources/"+ overlayName +".png"));
        } catch (IOException ex) {
            Logger.getLogger(VideoDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        BufferedImage combined = new BufferedImage(500, 300, BufferedImage.TYPE_INT_ARGB);

        Graphics g = combined.getGraphics();
        g.drawImage(baseImg, 0, 0, null);
        g.drawImage(overlayImg, 0, 0, null);
        g.dispose();
                
        try {
            ImageIO.write(combined, "PNG", new File(target));
        } catch (IOException ex) {
            Logger.getLogger(VideoDecorator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
