package space.gmarqueszx.frete_flow.exception;

public class DocumentoExistenteException extends RuntimeException {
    public DocumentoExistenteException(String documento) {
        super("Documento já cadastrado: " + documento);
    }
}
