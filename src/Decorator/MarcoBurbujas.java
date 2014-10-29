
package Modelo;

public class MarcoBurbujas extends VideoDecorator{

    public MarcoBurbujas(PersonalizarVideo p) {
        super(p);
        pvEntity.mergeImages("marco2", sourcePath, targetPath);
    }
    
}