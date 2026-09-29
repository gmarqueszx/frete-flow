package space.gmarqueszx.frete_flow.exception;

public class DocumentoInvalidoException extends RuntimeException {
    public DocumentoInvalidoException(String documento) {
        super("Documento inválido para o tipo de pessoa: " + documento);
    }
}
