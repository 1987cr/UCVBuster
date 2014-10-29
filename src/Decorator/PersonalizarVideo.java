
package Decorator;

public interface PersonalizarVideo {
    
    public abstract void mergeImages(String overlayName, String source, String target);
    
    public abstract String getTargetPath();
}
