package br.com.fiap.ms_produto.service;

import br.com.fiap.ms_produto.dto.CategoriaDto;
import br.com.fiap.ms_produto.dto.ProdutoDto;
import br.com.fiap.ms_produto.entities.Categoria;
import br.com.fiap.ms_produto.entities.Produto;
import br.com.fiap.ms_produto.exception.DatabaseException;
import br.com.fiap.ms_produto.exception.ResourceNotFoundException;
import br.com.fiap.ms_produto.repositories.CategoriaRepository;
import br.com.fiap.ms_produto.repositories.ProdutoRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {


    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDto> findAllProdutos(){
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream().map(ProdutoDto::new).toList();

    }

    @Transactional(readOnly = true)
    public ProdutoDto findProdutoById(Long id){

        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Recurso não encontrado. ID: " + id)
        );

        return new ProdutoDto(produto);
    }

    @Transactional
    public ProdutoDto saveProduto( ProdutoDto produtoDto){

        try {
            Produto produto = new Produto();

            copyDtoToProduto(produtoDto, produto);
            produto = produtoRepository.save(produto);
            return new ProdutoDto(produto);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Não foi possovel salvar o produto. Categoria Inexistente"+ "(ID: " + produtoDto.getCategoria().getId() + ")");
        }

    }

    private void copyDtoToProduto(ProdutoDto produtoDto, Produto produto){
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setValor(produtoDto.getValor());

        Categoria categoria = categoriaRepository
                .getReferenceById(produtoDto.getCategoria().getId());
        produto.setCategoria(categoria);
    }

    @Transactional
    public ProdutoDto updateProduto(Long id, ProdutoDto produtoDto){
        try{
            Produto produto = produtoRepository.getReferenceById(id);
            copyDtoToProduto(produtoDto, produto);
            produto = produtoRepository.save(produto);
            return new ProdutoDto(produto);
        }catch (EntityNotFoundException e){
            throw new ResourceNotFoundException("Recurso não encontrado. ID: "+ id);
        }
    }

    @Transactional
    public void deleteProduto(Long id){

        if(!produtoRepository.existsById(id)){
            throw new ResourceNotFoundException("Recurso não encontrdao. ID: "+id);

        }
        produtoRepository.deleteById(id);
    }
}
