package br.com.fiap.ms_produto.controller;

import br.com.fiap.ms_produto.dto.ProdutoDTO;
import br.com.fiap.ms_produto.dto.ProdutoInputDto;
import br.com.fiap.ms_produto.dto.ProdutoResponseDto;
import br.com.fiap.ms_produto.entities.Produto;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import br.com.fiap.ms_produto.service.ProdutoService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @GetMapping
    public ResponseEntity<List<ProdutoDTO>> getAllProdutos(){

        List<ProdutoDTO> list = produtoService.findAllProdutos();

        return ResponseEntity.ok(list);
    }
    @PostMapping
    public ResponseEntity<ProdutoDTO> createProduto(@RequestBody @Valid ProdutoDTO produtoDTO){

        produtoDTO = produtoService.saveProduto(produtoDTO);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoDTO.getId())
                .toUri();

        return ResponseEntity.created(uri).body(produtoDTO);
    }

    @PostMapping("/{id}")
    public ResponseEntity<ProdutoDTO> updateProduto(@PathVariable Long id,
                                                    @RequestBody @Valid ProdutoDTO produtoDTO){
        produtoDTO = produtoService.updatePruduto(id, produtoDTO);

        return ResponseEntity.ok(produtoDTO);
    }

}
