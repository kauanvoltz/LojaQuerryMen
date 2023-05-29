package empresa.quarrymen.projetoquerrymen.Controller;

import empresa.quarrymen.projetoquerrymen.Model.Produto;
import empresa.quarrymen.projetoquerrymen.Service.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public List<Produto> listarProdutos() {
        return produtoService.listarProdutos();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criarProduto(@RequestBody Produto produto) {
        return produtoService.salvarProduto(produto);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Produto buscarProdutoPorId(@PathVariable("id") Long id) {
        return produtoService.buscarProdutoPorId(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + id + " não encontrado"));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removerProduto(@PathVariable("id") Long id) {
        produtoService.buscarProdutoPorId(id)
                .ifPresentOrElse(produto -> produtoService.removerProduto(produto.getId()),
                        () -> {
                            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + id + " não encontrado");
                        });
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void atualizarProduto(@PathVariable("id") Long id, @RequestBody Produto produto) {
        produtoService.buscarProdutoPorId(id)
                .ifPresentOrElse(produtoBase -> {
                    modelMapper.map(produto, produtoBase);
                    produtoService.salvarProduto(produtoBase);
                }, () -> {
                    throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto com ID " + id + " não encontrado");
                });
    }
}
