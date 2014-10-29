
package Modelo;

public class MarcoGrama extends VideoDecorator{

    public MarcoGrama(PersonalizarVideo p) {
        super(p);
        pvEntity.mergeImages("marco1", sourcePath, targetPath);
    }
    
}
