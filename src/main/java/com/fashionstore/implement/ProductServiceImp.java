package com.fashionstore.implement;

import com.fashionstore.repository.ProducRepository;
import com.fashionstore.model.Product;
import com.fashionstore.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductServiceImp implements ProductService {
    private final ProducRepository producRepository;
    public ProductServiceImp(ProducRepository productDao) {
        this.producRepository = productDao;
    }
    @Override
    public List<Product> findAll() {
        return producRepository.findAll();
    }
    @Override
    public Product findById(int id) {

        return producRepository
                .findById(id)
                .orElse(null);
    }
    @Override
    public void save(Product product) {

    }

    @Override
    public void deleteById(int id) {

    }

}
