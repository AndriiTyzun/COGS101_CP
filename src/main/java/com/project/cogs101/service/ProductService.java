package com.project.cogs101.service;

import com.project.cogs101.domain.Product;
import java.util.List;

public interface ProductService {
    void save(Product product);
    void edit(long id, Product newProduct);
    void delete(long id);

    void clear();
    Product findById(long id);
    List<Product> findAllByOrderByIdAsc();

    long count();
}
