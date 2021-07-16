package service;

import model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();

    Product findOne(int id);

    boolean save(Product product);

    boolean update(int id, Product product);

    boolean remove(int id);


}