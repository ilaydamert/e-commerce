package com.ecommerce.ecommerce.Services;
import com.ecommerce.ecommerce.DTOs.User.UserDto;
import com.ecommerce.ecommerce.Exceptions.ProductNotExistException;
import com.ecommerce.ecommerce.Models.DAOUser;
import com.ecommerce.ecommerce.Repositories.CustomerRepository;
import com.ecommerce.ecommerce.Repositories.UserRepository;
import com.ecommerce.ecommerce.DTOs.Customer.*;
import com.ecommerce.ecommerce.DTOs.ResponseDto;
import com.ecommerce.ecommerce.Exceptions.CustomException;
import com.ecommerce.ecommerce.Models.Customer;
import com.ecommerce.ecommerce.Utils.Helper;
import com.ecommerce.ecommerce.Response.ResponseStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import static com.ecommerce.ecommerce.Response.MessageStrings.USER_CREATED;
@Service
public class CustomerService {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private PasswordEncoder bcryptEncoder;
    @Autowired
    CartService cartService;
    //Logger logger = LoggerFactory.getLogger(CustomerService.class);
    @Autowired
    private JwtUserDetailsService userDetailsService;
    public DAOUser saveUser(String username,String password) {
        DAOUser newUser = new DAOUser();
        newUser.setUsername(username);
        newUser.setPassword(bcryptEncoder.encode(password));
        return userRepository.save(newUser);
    }

    public ResponseDto signUp(CustomerCreateDto customerDto)  throws CustomException {
        if (Helper.notNull(customerRepository.findByUsername(customerDto.getUsername()))) {
            throw new CustomException("Customer already exists");
        }

        Customer customer = new Customer(customerDto);

        Customer createdCustomer;
        DAOUser createdUser;
        customer.setPassword(bcryptEncoder.encode(customerDto.getPassword()));
        createdCustomer = customerRepository.save(customer);
        createdUser=saveUser(customerDto.getUsername(),customerDto.getPassword());
        return new ResponseDto(ResponseStatus.success.toString(), USER_CREATED);
    }

    public Customer findByUserName(String username){
        return customerRepository.findByUsername(username);

    }

    public static CustomerDto getDtoFromCustomer(Customer customer) {
        CustomerDto customerDto = new CustomerDto(customer);
        return customerDto;
    }

    public static Customer getCustomerFromDto(CustomerDto customerDto) {
        Customer customer = new Customer(customerDto);
        return customer;
    }
    public static DAOUser getUserFromDto(UserDto userDto) {
        DAOUser daoUser = new DAOUser(userDto);
        return daoUser;
    }
    public List<CustomerDto> listCustomers() {
        List<Customer> customers = customerRepository.findAll();
        List<CustomerDto> customerDtos = new ArrayList<>();
        for(Customer customer : customers) {
            CustomerDto customerDto = getDtoFromCustomer(customer);
            customerDtos.add(customerDto);
        }
        return customerDtos;
    }

    public String deleteById(int id){
         Customer customer= customerRepository.findCustomerById(id);
         cartService.deleteByCustomer(customer);
         userDetailsService.delete(customer.getUsername());
         customerRepository.deleteById(id);
         return "Deleted";
    }
    public Customer getCustomerById(Integer customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (!optionalCustomer.isPresent())
            throw new ProductNotExistException("id " + customerId);
        return optionalCustomer.get();
    }
    public void updateCustomer(CustomerDto customerDto) {
        Customer customer=customerRepository.findCustomerById(customerDto.getId());
        userDetailsService.update(customer.getUsername(),customerDto);
        customer.setUsername(customerDto.getUsername());
        customer.setPassword(bcryptEncoder.encode(customerDto.getPassword()));
        customer.setEmail(customerDto.getEmail());
        customer.setFirstName(customerDto.getFirstName());
        customer.setLastName(customerDto.getLastName());
        customerRepository.save(customer);
    }

}