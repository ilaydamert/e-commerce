package com.ecommerce.ecommerce.Repositories;

import com.ecommerce.ecommerce.Models.Cart;
import com.ecommerce.ecommerce.Models.Customer;
import com.ecommerce.ecommerce.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CartRepository extends JpaRepository<Cart,Integer> {
    List<Cart> findAllByCustomerOrderByCreatedDateDesc(Customer customer);
    List<Cart> findAllByProduct(Product product);
    Cart findById(int id);
}
