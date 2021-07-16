package service;

import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService implements IProductService {
    @Autowired
    private Map<Integer, Product> productMap;

    @Override
    public List<Product> findAll() {
        return new LinkedList<>(productMap.values());
    }

    @Override
    public Product findOne(int id) {
        return productMap.get(id);
    }

    @Override
    public boolean save(Product product) {
        int id = productMap.size() + 1;
        product.setId(id);
        return productMap.put(id, product) == product;
    }

    @Override
    public boolean update(int id, Product product) {
        return productMap.put(id, product) == product;
    }

    @Override
    public boolean remove(int id) {
        Product product = productMap.get(id);
        return productMap.remove(id) == product;
    }


}