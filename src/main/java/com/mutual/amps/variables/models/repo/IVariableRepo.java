package com.mutual.amps.variables.models.repo;


import com.mutual.amps.variables.models.Variable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IVariableRepo extends JpaRepository<Variable, Integer> {
    
}
