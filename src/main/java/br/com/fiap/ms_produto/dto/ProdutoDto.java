package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Produto;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
//serve para transformar um dado que veio do banco de dados em um DTO de forma automática.
public class ProdutoDto {

    private Long id;

    @NotBlank(message = "O campo do nome é obrigatorio")
    @Size(min = 3, max = 100, message = "O campo da descrição deve ter entre 3 e 100 caracteres")
    private String nome;

    @NotBlank(message = "O campo da descrição é obrigatorio")
    @Size(min = 3, max = 100, message = "O campo do nome deve ter entre 3 e 100 caracteres")
    private String descricao;

    @NotNull(message = "O campo de valor é obrigatorio")
    @Positive(message = "O campo valor deve ser um número maior que 0")
    private Double valor;

    @NotNull(message = "O campo categoria é obrigatorio")
    private CategoriaDto categoria;

    public ProdutoDto (Produto produto){
        id = produto.getId();
        nome = produto.getNome();
        descricao = produto.getDescricao();
        valor = produto.getValor();
        categoria = new CategoriaDto(produto.getCategoria());

    }
}
