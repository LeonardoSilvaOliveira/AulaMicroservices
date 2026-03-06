package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Categoria;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter

public class CategoriaDto {

    private Long id;

    @NotBlank(message = "campo nome não pode ser vazio, nulo ou em branco")
    @Size(min = 3, max = 10, message = "O nome deve ser entre 3  10 caracteries")
    private String nome;

    public CategoriaDto(Categoria categoria){
        id = categoria.getId();
        nome = categoria.getNome();

    }
}
