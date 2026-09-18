package space.gmarqueszx.frete_flow.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import org.springframework.util.StringUtils;
import space.gmarqueszx.frete_flow.exception.DocumentoInvalidoException;
import space.gmarqueszx.frete_flow.model.enums.TipoPessoa;
import space.gmarqueszx.frete_flow.util.CNPJValidator;
import space.gmarqueszx.frete_flow.util.CPFValidator;

@Entity
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    //Define se o cliente é pessoa jurídica ou pessoa física.
    @Enumerated(value = EnumType.STRING)
    @Column(name = "tipo_pessoa", nullable = false)
    private TipoPessoa tipoPessoa;

    //Caso pessoa física deve ser inserido o CPF, caso pessoa jurídica deve inserido o CNPJ.
    @Column(nullable = false, unique = true)
    private String documento;

    /*Caso pessoa física deve ser inserido o nome completo conforme a receita federal, caso pessoa
    jurídica deve inserido a razão social.*/
    @NotBlank
    @Column(nullable = false)
    private String nome;

    //Deve ser preenchido apenas se for pessoa jurídica
    @Column(name = "nome_fantasia")
    private String nomeFantasia;

    @Email
    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 20)
    private String telefone;

    @PrePersist
    @PreUpdate
    private void validar() {
        normalizarDocumento();
        validarDocumento();
        validarNomeFantasia();
    }

    private void validarNomeFantasia() {
        if (tipoPessoa == TipoPessoa.FISICA && StringUtils.hasText(nomeFantasia)) {
            throw new IllegalStateException("Nome fantasia não é permitido para pessoa física.");
        }
    }

    private void normalizarDocumento() {
        if (documento != null) {
            documento = documento.replaceAll("\\D", "");
        }
    }

    private void validarDocumento() {
        boolean valido = tipoPessoa == TipoPessoa.FISICA
                ? CPFValidator.isValid(documento)
                : CNPJValidator.isValid(documento);
        if (!valido) {
            throw new DocumentoInvalidoException(documento);
        }
    }
}
