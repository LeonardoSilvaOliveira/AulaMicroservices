package br.com.fiap.ms_produto.dto;

//Este é o objeto que o usuário envia para a sua API.
public class ProdutoInputDto {
    private String nome;
    private String descricao;
    private double valor;


    public ProdutoInputDto(){
    }
    public ProdutoInputDto(String nome, String descricao, double valor) {
        this.nome = nome;
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public double getValor() {
        return valor;
    }
}
