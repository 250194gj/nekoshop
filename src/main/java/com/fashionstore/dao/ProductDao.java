
        package com.fashionstore.dao;

import com.fashionstore.db.DBContext;
import com.fashionstore.model.Product;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDao {

    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();

        String sql = """
                SELECT id, name, price, description, image
                FROM products
                ORDER BY id DESC
                """;

        try (Connection connection = DBContext.getConnection();
             PreparedStatement statement =
                     connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Product product = new Product();

                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setDescription(
                        resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));

                products.add(product);
            }

        } catch (SQLException e) {
            throw new RuntimeException(
                    "Lỗi khi lấy danh sách sản phẩm", e);
        }

        return products;
    }

    public Product findById(int id) {
        String sql ="SELECT * FROM products where id = ?";
        Product product = new Product();
        try {
            Connection connection = DBContext.getConnection();
            PreparedStatement preparedStatement= connection.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                product.setId(resultSet.getInt("id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getBigDecimal("price"));
                product.setDescription(resultSet.getString("description"));
                product.setImage(resultSet.getString("image"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return product;
    }

    public void add(Product product) {

    }

    public void delete(int id) {
    }
    public static void main(String[] args) {
        ProductDao productDao = new ProductDao();
        Product product = productDao.findById(2);
        System.out.println(product.toString());

    }
}