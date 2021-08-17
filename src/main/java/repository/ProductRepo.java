package repository;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.ArrayList;

public class ProductRepo implements IProductRepo{
    @Autowired
    EntityManager entityManager;
    @Override
    public Product save(Product product) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.persist(product);
        txn.commit();
        return product;
    }

    @Override
    public ArrayList<Product> findAll() {
        String queryStr = "SELECT p FROM Product AS p";
        TypedQuery<Product> query = entityManager.createQuery(queryStr, Product.class);
        return (ArrayList<Product>) query.getResultList();
    }

    @Override
    public void delete(Product product) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.remove(product);
        txn.commit();
    }

    @Override
    public void edit(Product product) {
        EntityTransaction txn = entityManager.getTransaction();
        txn.begin();
        entityManager.merge(product);
        txn.commit();
    }
}
