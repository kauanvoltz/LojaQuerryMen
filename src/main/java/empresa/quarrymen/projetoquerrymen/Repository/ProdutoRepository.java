package empresa.quarrymen.projetoquerrymen.Repository;

import empresa.quarrymen.projetoquerrymen.Model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
