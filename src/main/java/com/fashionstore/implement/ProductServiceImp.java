package com.fashionstore.implement;

import com.fashionstore.dao.ProductDao;
import com.fashionstore.model.Product;
import com.fashionstore.service.productService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImp implements productService {
    private final ProductDao productDao;
    public ProductServiceImp(ProductDao productDao) {
        this.productDao = productDao;
    }
    public List<Product> findAll() {
        return productDao.findAll();
    }

    @Override
    public Product findById(int id) {
        return productDao.findById(id);
    }

    @Override
    public void save(Product product) {
        productDao.add(product);

    }

    @Override
    public void deleteById(int id) {
productDao.delete(id);
    }
}
