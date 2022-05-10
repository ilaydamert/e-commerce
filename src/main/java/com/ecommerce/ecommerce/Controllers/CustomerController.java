package com.ecommerce.ecommerce.Controllers;
import com.ecommerce.ecommerce.DTOs.ResponseDto;
import com.ecommerce.ecommerce.DTOs.Customer.*;
import com.ecommerce.ecommerce.DTOs.User.UserDto;
import com.ecommerce.ecommerce.Exceptions.CustomException;
import com.ecommerce.ecommerce.JWT.JwtRequestFilter;
import com.ecommerce.ecommerce.JWT.JwtTokenUtil;
import com.ecommerce.ecommerce.Services.CustomerService;
import com.ecommerce.ecommerce.Services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import javax.persistence.Access;
import javax.validation.Valid;
import java.util.List;
@CrossOrigin(origins = "http://localhost:8081")
@RequestMapping("/customers")
@RestController
public class CustomerController {
    @Autowired
    private JwtUserDetailsService userDetailsService;
    @Autowired
    CustomerService customerService;
    @GetMapping("/read")
    public  ResponseEntity<List<CustomerDto>> getCustomers() {
        List<CustomerDto> body = customerService.listCustomers();
        return new ResponseEntity<List<CustomerDto>>(body, HttpStatus.OK);
    }
    @GetMapping("/readById/{id}")
    public ResponseEntity getCustomersById(@Valid @PathVariable int id) {
        return new ResponseEntity(customerService.getCustomerById(id), HttpStatus.OK);
    }
    public ResponseEntity saveUser( UserDto user) throws Exception {
        return new ResponseEntity(userDetailsService.save(user),HttpStatus.OK);
    }
    @PostMapping("/create")
    public @ResponseBody ResponseEntity CreateCustomer( @RequestBody CustomerCreateDto customerCreateDto) throws Exception {
        customerService.signUp(customerCreateDto);
        String username=customerCreateDto.getUsername();
        return new ResponseEntity(customerService.findByUserName(username),HttpStatus.OK);
    }
    @DeleteMapping("/Delete/{id}")
    public @ResponseBody ResponseEntity deleteCustomer (@PathVariable("id") int id){
        customerService.deleteById(id);
        return new ResponseEntity(id,HttpStatus.OK);
    }
    @PostMapping("/Update")
    public @ResponseBody ResponseEntity updateCustomer (@RequestBody CustomerDto customerDto){
        customerService.updateCustomer(customerDto);
        int id=customerDto.getId();

        return new ResponseEntity(customerService.getCustomerById(id),HttpStatus.OK);
    }
}