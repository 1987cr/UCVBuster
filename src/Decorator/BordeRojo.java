
package Decorator;

public class BordeRojo extends VideoDecorator{

    public BordeRojo(PersonalizarVideo p) {
        super(p);
        pvEntity.mergeImages("borde2", sourcePath, targetPath);
    }
    
}
