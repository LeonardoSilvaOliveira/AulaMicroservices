package service;

import br.com.fiap.ms_produto.dto.ProdutoDto;
import br.com.fiap.ms_produto.entities.Produto;
import br.com.fiap.ms_produto.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    @Transactional(readOnly = true)
    public List<ProdutoDto> findAllProdutos(){

        List<Produto> produtos =produtoRepository.findAll();

        return produtos.stream().map(ProdutoDto::new).toList();
    }

    @Transactional(readOnly = true)
    public ProdutoDto findProdutoById(long id){
        Produto produto = produtoRepository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Recurso não encontrado. ID:" + id)
        );
        return new ProdutoDto(produto);
    }
    @Transactional
    public ProdutoDto saveProduto(ProdutoDto produtoDto){

        Produto produto = new Produto();

        copyDtoProduto(produtoDto, produto);
        produto =produtoRepository.save(produto);
        return new ProdutoDto(produto);
    }

    private void copyDtoProduto(ProdutoDto produtoDto, Produto produto){
        produto.setNome(produtoDto.getNome());
        produto.setDescricao(produtoDto.getDescricao());
        produto.setValor(produtoDto.getValor());
    }
}
