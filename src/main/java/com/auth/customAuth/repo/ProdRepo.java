package com.auth.customAuth.repo;

import com.auth.customAuth.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdRepo extends JpaRepository<Users,Integer> {


}
