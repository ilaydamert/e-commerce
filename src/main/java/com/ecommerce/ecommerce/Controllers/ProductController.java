package com.ecommerce.ecommerce.Controllers;
import com.ecommerce.ecommerce.DTOs.Cart.CartAdd;
import com.ecommerce.ecommerce.DTOs.Product.ProductCreateDto;
import com.ecommerce.ecommerce.Exceptions.AuthenticationFailException;
import com.ecommerce.ecommerce.Exceptions.ProductExistException;
import com.ecommerce.ecommerce.JWT.JwtTokenUtil;
import com.ecommerce.ecommerce.Models.Customer;
import com.ecommerce.ecommerce.Models.Product;
import com.ecommerce.ecommerce.Response.ApiResponse;
import com.ecommerce.ecommerce.DTOs.Product.ProductDto;
import com.ecommerce.ecommerce.Response.MessageStrings;
import com.ecommerce.ecommerce.Services.CartService;
import com.ecommerce.ecommerce.Services.CustomerService;
import com.ecommerce.ecommerce.Services.JwtUserDetailsService;
import com.ecommerce.ecommerce.Services.ProductService;
import com.ecommerce.ecommerce.Utils.Helper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/read")
    public ResponseEntity<List<ProductDto>> getProducts() {
        List<ProductDto> body = productService.listProducts();
        return new ResponseEntity<List<ProductDto>>(body, HttpStatus.OK);
    }
    @GetMapping("/readById/{id}")
    public @ResponseBody ResponseEntity getProductById(@Valid @PathVariable int id) {
        return new ResponseEntity(productService.getProductById(id), HttpStatus.OK);
    }

    @PostMapping("/create")
    public @ResponseBody ResponseEntity addProduct(@Valid @RequestBody ProductCreateDto productCreateDto) {
       ProductDto product= productService.addProduct(productCreateDto);

        return new ResponseEntity(product,HttpStatus.OK);
    }
    @PostMapping("/Update")
    public @ResponseBody ResponseEntity updateProduct (@RequestBody ProductDto productDto){
        productService.updateProduct(productDto);
        int id=productDto.getId();
        return new ResponseEntity(productService.getProductById(id),HttpStatus.OK);
    }
    @DeleteMapping("/Delete/{id}")
    public @ResponseBody ResponseEntity deleteById(@PathVariable int id){
        productService.deleteById(id);
        return new ResponseEntity(id,HttpStatus.OK);
    }
}


