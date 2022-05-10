package com.ecommerce.ecommerce.Controllers;
import com.ecommerce.ecommerce.DTOs.Cart.*;
import com.ecommerce.ecommerce.JWT.JwtTokenUtil;
import com.ecommerce.ecommerce.Models.Cart;
import com.ecommerce.ecommerce.Models.Customer;
import com.ecommerce.ecommerce.Models.Product;
import com.ecommerce.ecommerce.Services.CartService;
import com.ecommerce.ecommerce.Services.CustomerService;
import com.ecommerce.ecommerce.Services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/cart")
public class CartsController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private JwtTokenUtil tokenUtil;
    @PostMapping("/add")
    public @ResponseBody ResponseEntity<CartDto> addToCart(@RequestBody CartAdd cartAdd)  {
        String userName= tokenUtil.getUsernameFromToken(cartAdd.getToken());
        Customer customer=customerService.findByUserName(userName);
        Product product = productService.getProductById(cartAdd.getProductId());
        cartService.addToCart(cartAdd, product, customer);
        productService.stolen(product,cartAdd.getQuantity());
        CartDto cartDto = cartService.listCartItems(customer);
        return new ResponseEntity<CartDto>(cartDto, HttpStatus.OK);
    }
    @PostMapping("/read")
    public @ResponseBody ResponseEntity<CartDto> getCartItems(@RequestBody CartReadDto cartReadDto)  {
        String userName=tokenUtil.getUsernameFromToken(cartReadDto.getToken());
        Customer customer=customerService.findByUserName(userName);
        CartDto cartDto = cartService.listCartItems(customer);
        return new ResponseEntity<CartDto>(cartDto,HttpStatus.OK);
    }
    @PutMapping("/update")
    public @ResponseBody ResponseEntity updateCartItem(@RequestBody @Valid CartUpdateDto cartUpdateDto)  {
        Customer customer=customerService.findByUserName(tokenUtil.getUsernameFromToken(cartUpdateDto.getToken()));
        Cart cart=cartService.findById(cartUpdateDto.getId());
        Product oldProduct=cart.getProduct();
        int oldQuantity=cart.getQuantity();
        Product product = productService.getProductById(cartUpdateDto.getProductId());
        cartService.checkToken(cart,customer);
        productService.updateStock(oldProduct,product,oldQuantity,cartUpdateDto.getQuantity());
        cartService.updateCartItem(cartUpdateDto.getId(),cartUpdateDto, product);
        String body="Updated:"+cartUpdateDto.getId();
        return new ResponseEntity(body,HttpStatus.OK);
    }
    @PostMapping("/Delete")
    public @ResponseBody ResponseEntity delete(@RequestBody @Valid CartDeleteDto cartDeleteDto){
        String userName=tokenUtil.getUsernameFromToken(cartDeleteDto.getToken());
        Customer customer=customerService.findByUserName(userName);
        Cart cart=cartService.findById(cartDeleteDto.getId());
        cartService.checkToken(cart,customer);
        cartService.delete(cartDeleteDto);
        int body=cartDeleteDto.getId();
        return new ResponseEntity(body,HttpStatus.OK);
    }
}