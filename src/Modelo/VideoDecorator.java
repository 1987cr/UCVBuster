
package Modelo;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class VideoDecorator implements PersonalizarVideo {

    protected String sourcePath;    
    protected String targetPath;
    protected PersonalizarVideo pvEntity;
    
    public VideoDecorator(PersonalizarVideo p){
        pvEntity = p;
        sourcePath = pvEntity.getTargetPath();
        targetPath = pvEntity.getTargetPath();        
    }
    
    @Override
    public void mergeImages(String overlayName, String source, String target) {
        pvEntity.mergeImages(overlayName, sourcePath, targetPath);
    }

    public String getTargetPath() {
        return pvEntity.getTargetPath();
    }
    
}
