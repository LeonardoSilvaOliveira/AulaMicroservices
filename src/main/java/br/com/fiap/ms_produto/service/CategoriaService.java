package br.com.fiap.ms_produto.service;

import br.com.fiap.ms_produto.dto.CategoriaDto;
import br.com.fiap.ms_produto.entities.Categoria;
import br.com.fiap.ms_produto.exception.ResourceNotFoundException;
import br.com.fiap.ms_produto.repositories.CategoriaRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<CategoriaDto> findAllCategorias(){
        return categoriaRepository.findAll().stream().map(CategoriaDto::new).toList();
    }
    @Transactional(readOnly = true)
    public CategoriaDto findCategoriaById(Long id){

        Categoria categoria = categoriaRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado ID: " + id));

        return new CategoriaDto(categoria);
    }

    @Transactional
    public CategoriaDto saveCategoria( CategoriaDto inputDto){
        Categoria categoria = new Categoria();
        copyDtoCategoria(inputDto, categoria);
        categoria = categoriaRepository.save(categoria);

        return new CategoriaDto(categoria);

    }
    public void copyDtoCategoria( CategoriaDto inputDto, Categoria categoria){

        categoria.setNome(inputDto.getNome());

    }
    @Transactional
    public CategoriaDto updateCategoria(Long id, CategoriaDto inputDto){

        try {
            Categoria categoria = categoriaRepository.getReferenceById(id);
            copyDtoCategoria(inputDto, categoria);
            categoria = categoriaRepository.save(categoria);
            return new CategoriaDto(categoria);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado ID: " + id);
        }

    }
    @Transactional
    public void deleteCategoriaById(Long id){
        if(!categoriaRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: " + id);
        }
    }

}
