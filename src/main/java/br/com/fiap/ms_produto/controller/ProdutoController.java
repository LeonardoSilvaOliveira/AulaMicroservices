package br.com.fiap.ms_produto.controller;

import br.com.fiap.ms_produto.dto.ProdutoDto;
import br.com.fiap.ms_produto.dto.ProdutoInputDto;
import br.com.fiap.ms_produto.dto.ProdutoResponseDto;
import br.com.fiap.ms_produto.entities.Produto;
import br.com.fiap.ms_produto.service.ProdutoService;
import jakarta.servlet.Servlet;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    //forçando erro 500 para test
    //@Profile("test")
    //@GetMapping("/--demo/500")
    //public String force500(){
    //    throw new RuntimeException("Erro 500 forçado para teste");
    //}
    @GetMapping
    public ResponseEntity<List<ProdutoDto>> getAllProdutos(){

        List<ProdutoDto> list = produtoService.findAllProdutos();
        return ResponseEntity.ok(list);
    }
    @GetMapping("/{id}")
    public ResponseEntity <ProdutoDto>getProdutoById(@PathVariable Long id){
        ProdutoDto produtoDto = produtoService.findProdutoById(id);
        return  ResponseEntity.ok(produtoDto);
    }
    @PostMapping
    public ResponseEntity<ProdutoDto>creatProduto(@RequestBody @Valid ProdutoDto produtoDto){

        produtoDto = produtoService.saveProduto(produtoDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(produtoDto.getId())
                .toUri();

        return  ResponseEntity.created(uri).body(produtoDto);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProdutoDto>updateProduto(@PathVariable Long id, @RequestBody @Valid ProdutoDto produtoDto){

        produtoDto = produtoService.updateProduto(id, produtoDto);
        return ResponseEntity.ok(produtoDto);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDto>deleteProduto(@PathVariable Long id){
        produtoService.deleteProduto(id);
        return ResponseEntity.noContent().build();
    }
}