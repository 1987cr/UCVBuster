
package Modelo;

public class LetrasPequenas extends VideoDecorator{
     public LetrasPequenas(PersonalizarVideo p) {
        super(p);
        pvEntity.mergeImages("letras1", sourcePath, targetPath);
    }
}
