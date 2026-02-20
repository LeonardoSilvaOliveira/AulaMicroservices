package br.com.fiap.ms_produto.controller;

import br.com.fiap.ms_produto.dto.ProdutoDto;
import br.com.fiap.ms_produto.dto.ProdutoInputDto;
import br.com.fiap.ms_produto.dto.ProdutoResponseDto;
import br.com.fiap.ms_produto.entities.Produto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.ProdutoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getAllProdutos(){

        List<ProdutoDto> list = produtoService.findAllProdutos();

        return ResponseEntity.ok(list);
    }

}
