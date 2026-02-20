package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Produto;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;

import javax.management.ConstructorParameters;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class ProdutoDto {
    private Long id;
    private String nome;
    private String descricao;
    private Double valor;

    public  ProdutoDto(Produto produto){
        id = produto.getId();
        nome = produto.getNome();
        descricao = produto.getDescricao();
        valor = produto.getValor();
    }
}
