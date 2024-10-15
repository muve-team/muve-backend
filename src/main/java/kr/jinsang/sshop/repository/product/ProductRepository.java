package kr.jinsang.sshop.repository.product;

import jakarta.persistence.EntityManager;
import kr.jinsang.sshop.domain.product.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProductRepository {

    private final EntityManager em;

    public void save(Product product) {
        if (product == null) {
            em.persist(product);
            return;
        }
        em.merge(product);
    }

    public List<Product> findAll() {
        return em.createQuery("select p from Product p", Product.class)
                .getResultList();
    }

    public Product findOne(Long id) {
        return em.find(Product.class, id);
    }

}
