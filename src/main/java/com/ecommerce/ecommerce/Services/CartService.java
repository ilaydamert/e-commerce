package com.ecommerce.ecommerce.Services;
import com.ecommerce.ecommerce.DTOs.Cart.*;
import com.ecommerce.ecommerce.Exceptions.CartIdWrongException;
import com.ecommerce.ecommerce.Exceptions.NotEnoughProduct;
import com.ecommerce.ecommerce.Models.Cart;
import com.ecommerce.ecommerce.Models.Customer;
import com.ecommerce.ecommerce.Models.Product;
import com.ecommerce.ecommerce.Repositories.CartRepository;
import com.ecommerce.ecommerce.Response.MessageStrings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
@Transactional
public class CartService  {
    @Autowired
    private CartRepository cartRepository;
    @Autowired
    private ProductService productService;
    public CartService(){}

    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void addToCart(CartAdd cartAdd, Product product, Customer customer){
        if(product.getStock()>=cartAdd.getQuantity()){
            Cart cart = new Cart(product, cartAdd.getQuantity(), customer);
            cartRepository.save(cart);
        }
        else{
            throw new NotEnoughProduct(MessageStrings.NOT_ENOUGH_PRODUCT);
        }
    }

    public CartDto listCartItems(Customer customer) {
        List<Cart> cartList = cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
        List<CartItemDto> cartItems = new ArrayList<>();
        for (Cart cart:cartList){
            CartItemDto cartItemDto = getDtoFromCart(cart);
            cartItems.add(cartItemDto);
        }
        double totalCost = 0;
        for (CartItemDto cartItemDto :cartItems){
            totalCost += (cartItemDto.getProductPrice()* cartItemDto.getQuantity());
        }
        return new CartDto(cartItems,totalCost);
    }

    public static CartItemDto getDtoFromCart(Cart cart) {
        return new CartItemDto(cart);
    }


    public void updateCartItem(int id, CartUpdateDto cartUpdateDto, Product product){
        Cart cart =cartRepository.findById(id);
        cart.setProduct(product);
        cart.setQuantity(cartUpdateDto.getQuantity());
        cart.setCreatedDate(new Date());
        cartRepository.save(cart);
    }
    public void deleteByProduct(Product product){
        List<Cart> productList=cartRepository.findAllByProduct(product);
        for (Cart cart:productList) {
            cartRepository.delete(cart);
        }
    }
    public void deleteByCustomer(Customer customer){
        List<Cart> cartList=cartRepository.findAllByCustomerOrderByCreatedDateDesc(customer);
        for (Cart cart:cartList) {
            cartRepository.delete(cart);
        }
    }
    public void delete(CartDeleteDto cartDeleteDto){
        int id=cartDeleteDto.getId();
        Cart cart=cartRepository.findById(id);
        productService.deletedCart(cart.getProduct(),cart.getQuantity());
        cartRepository.delete(cart);
    }
    public void checkToken(Cart cart, Customer customer){
        if(cart.getCustomer().getId()!= customer.getId()){
            throw new CartIdWrongException(MessageStrings.CART_ID_WRONG);
        }
    }
    public Cart findById(int id){
        Cart cart=cartRepository.findById(id);
        return cart;
    }
}
