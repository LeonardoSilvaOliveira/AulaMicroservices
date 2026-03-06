package br.com.fiap.ms_produto.dto;

import br.com.fiap.ms_produto.entities.Produto;

import java.util.List;

//Este é o objeto que a sua API devolve para o usuário após uma consulta ou cadastro. Controla o que o usuario vê.
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
    //static factoy method para criar instâncias mokadas
    //somente para testes
    public static List<ProdutoResponseDto> createMock(){
        return List.of(new ProdutoResponseDto(1L, "Notebook", "Notebook Dell Inspiron 15", 3500.00),
        new ProdutoResponseDto(2L, "Smartphone", "Smartphone Samsung Galaxy S", 2500.00),
        new ProdutoResponseDto(3L, "Tablet", "Tablet Apple iPad", 3000.00),
        new ProdutoResponseDto(4L, "Monitor", "Monitor LG 27 polegadas", 1200.00)

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
