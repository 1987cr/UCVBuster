
package Modelo;

import com.xuggle.mediatool.IMediaWriter;
import com.xuggle.mediatool.ToolFactory;
import com.xuggle.xuggler.ICodec;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;

public class ProcesarVideo {
    
    private static final double FRAME_RATE = 0.2;
    
    private static final int SECONDS_TO_RUN_FOR = 15;

    private final String targetPath;
    
    private final String targetImgPath;

    private static Dimension screenBounds;

    static int i = 1;
    
    public ProcesarVideo(String target, String name) throws IOException{
        this.targetPath = target + "/" + name + ".mp4";
        this.targetImgPath = target + "/" + name + ".png";
                
        procesar();
    }
    
    public void procesar() throws IOException {
        // let's make a IMediaWriter to write the file.
        final IMediaWriter writer = ToolFactory.makeWriter(targetPath);
        
        screenBounds = Toolkit.getDefaultToolkit().getScreenSize();

        // We tell it we're going to add one video stream, with id 0,
        // at position 0, and that it will have a fixed frame rate of FRAME_RATE.
        writer.addVideoStream(0, 0, ICodec.ID.CODEC_ID_MPEG4, 
                   screenBounds.width/2, screenBounds.height/2);

        long startTime = System.nanoTime();
        
        for (int index = 0; index < SECONDS_TO_RUN_FOR * FRAME_RATE; index++) {
            
            // take the screen shot
            BufferedImage screen = getDesktopScreenshot();

            // convert to the right image type
            BufferedImage bgrScreen = convertToType(screen, BufferedImage.TYPE_3BYTE_BGR);

            // encode the image to stream #0
            writer.encodeVideo(0, bgrScreen, System.nanoTime() - startTime, TimeUnit.NANOSECONDS);

            // sleep for frame rate milliseconds
            try {
                Thread.sleep((long) (1000 / FRAME_RATE));
            } 
            catch (InterruptedException e) {
                // ignore
            }
            
        }
        
        // tell the writer to close and write the trailer if  needed
        writer.close();
    }
    
    public BufferedImage convertToType(BufferedImage sourceImage, int targetType) {
        
        BufferedImage image;

        // if the source image is already the target type, return the source image
        if (sourceImage.getType() == targetType) {
            image = sourceImage;
        }
        // otherwise create a new image of the target type and draw the new image
        else {
            image = new BufferedImage(sourceImage.getWidth(), 
                 sourceImage.getHeight(), targetType);
            image.getGraphics().drawImage(sourceImage, 0, 0, null);
        }

        return image;
        
    }
    
    private BufferedImage getDesktopScreenshot() throws IOException {
        BufferedImage img;
        if(i == 1)
            img = ImageIO.read(this.getClass().getResource("/resources/intro.png"));
        else
            img = ImageIO.read(new File(targetImgPath));
        
        i++;
        return img;
    }
}
