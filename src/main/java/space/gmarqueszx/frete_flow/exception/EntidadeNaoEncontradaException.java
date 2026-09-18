package space.gmarqueszx.frete_flow.exception;

public class EntidadeNaoEncontradaException extends RuntimeException {
    public EntidadeNaoEncontradaException(Long id) {
        super("Não foi encontrado nenhum registro com id: " + id + " em nossa base de dados.");
    }
}
