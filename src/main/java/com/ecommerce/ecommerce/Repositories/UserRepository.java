package com.ecommerce.ecommerce.Repositories;

import com.ecommerce.ecommerce.Models.DAOUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<DAOUser,Integer> {
    DAOUser findByUsername(String username);
    DAOUser findDAOUserById(int id);
}

