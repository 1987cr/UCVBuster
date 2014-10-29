
package Modelo;

<<<<<<< HEAD
public interface VideoDecorator {
    public abstract void mergeImages();
=======
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
    
>>>>>>> origin/master
}
