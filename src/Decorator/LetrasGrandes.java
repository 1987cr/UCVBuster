
package Decorator;

public class LetrasGrandes extends VideoDecorator{
     public LetrasGrandes(PersonalizarVideo p) {
        super(p);
        pvEntity.mergeImages("letras2", sourcePath, targetPath);
    }
}
