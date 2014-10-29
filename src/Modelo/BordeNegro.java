
package Modelo;

public class BordeNegro extends VideoDecorator{
     public BordeNegro(PersonalizarVideo p) {
        super(p);
        pvEntity.mergeImages("borde1", sourcePath, targetPath);
    }
}
