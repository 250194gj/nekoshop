
package com.fashionstore.repository;

import com.fashionstore.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProducRepository extends JpaRepository<Product,Integer> {
public List<Product> findAllBy();

}