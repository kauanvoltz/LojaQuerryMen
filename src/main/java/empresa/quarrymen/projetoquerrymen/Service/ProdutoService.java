package empresa.quarrymen.projetoquerrymen.Service;

import empresa.quarrymen.projetoquerrymen.Model.Produto;
import empresa.quarrymen.projetoquerrymen.Repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository produtoRepository;

    public List<Produto> listarProdutos(){
        return produtoRepository.findAll();
    }

    public Produto salvarProduto(Produto produto){
        return  produtoRepository.save(produto);
    }

    public Optional<Produto> buscarProdutoPorId(Long id){
        return produtoRepository.findById(id);
    }

    public void removerProduto(Long id){
        produtoRepository.deleteById(id);
    }

}
