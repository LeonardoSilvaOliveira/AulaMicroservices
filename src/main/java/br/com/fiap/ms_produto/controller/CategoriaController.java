package br.com.fiap.ms_produto.controller;

import br.com.fiap.ms_produto.dto.CategoriaDto;
import br.com.fiap.ms_produto.service.CategoriaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public ResponseEntity<List<CategoriaDto>> getAllCategorias(){
        List<CategoriaDto> categorias = categoriaService.findAllCategorias();

        return ResponseEntity.ok(categorias);

    }
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDto> getcategoriaById(@PathVariable Long id){
        CategoriaDto categoriaDto = categoriaService.findCategoriaById(id);
        return ResponseEntity.ok(categoriaDto);
    }
    @PostMapping
    public ResponseEntity<CategoriaDto> creatCategoriaDto(
            @Valid @RequestBody CategoriaDto categoriaDto ){

        categoriaDto = categoriaService.saveCategoria(categoriaDto);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(categoriaDto.getId())
                .toUri();

        return ResponseEntity.created(uri).body(categoriaDto);
    }
    @PostMapping("/{id}")
    public ResponseEntity<CategoriaDto> updateDto(@PathVariable Long id, @RequestBody @Valid CategoriaDto categoriaDto){

        categoriaDto = categoriaService.updateCategoria(id, categoriaDto);

        return ResponseEntity.noContent().build();
    }
    @DeleteMapping("/{id}")
    public  ResponseEntity<Valid> deleteCategoriaById(@PathVariable Long id){
        categoriaService.deleteCategoriaById(id);
        return ResponseEntity.noContent().build();
    }
}
