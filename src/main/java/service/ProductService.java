package service;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import repository.IProductRepo;

import java.util.ArrayList;

public class ProductService {
    @Autowired
    IProductRepo iProductRepo;

    public ArrayList<Product> list = new ArrayList<>();

    public void save(Product product) {
        iProductRepo.save(product);
    }
    public void findAll() {
        list = iProductRepo.findAll();
    }
    public void delete(int index) {
        iProductRepo.delete(list.get(index));
    }
    public void edit(Product product) {
        iProductRepo.edit(product);
    }
}
