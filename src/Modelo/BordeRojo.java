
package Modelo;

import Modelo.PersonalizarVideo;
import Modelo.VideoDecorator;

public class BordeRojo extends VideoDecorator{

    public BordeRojo(PersonalizarVideo p) {
        super(p);
        pvEntity.mergeImages("borde2", sourcePath, targetPath);
    }
    
}
