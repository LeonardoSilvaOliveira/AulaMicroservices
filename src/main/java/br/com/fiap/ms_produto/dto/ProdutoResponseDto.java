package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Produto;

import java.util.List;

public class ProdutoResponseDto {
    private Long id;
    private String nome;
    private String descricao;
    private Double valor;

    public ProdutoResponseDto(Long id, String nome, String descricao, Double valor) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public static List<ProdutoResponseDto> creaMock(){
        return List.of(
                new ProdutoResponseDto(1L, "Smart TV", "Smart TV LG 20 polegadas", 22.0),
                new ProdutoResponseDto(2L, "Mouse Microsoft", "Mouse sem fio", 250.0),
                new ProdutoResponseDto(3L, "teclado Microsoft", "teclado sem fio", 278.95)
        );
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public Double getValor() {
        return valor;
    }
}
