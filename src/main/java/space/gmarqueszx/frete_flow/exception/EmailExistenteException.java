package space.gmarqueszx.frete_flow.exception;

public class EmailExistenteException extends RuntimeException {
    public EmailExistenteException(String email) {
        super("Email já cadastrado: " + email);
    }
}
