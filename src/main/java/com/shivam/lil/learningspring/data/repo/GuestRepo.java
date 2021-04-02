package com.shivam.lil.learningspring.data.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.shivam.lil.learningspring.data.entity.Guest;

@Repository
public interface GuestRepo extends CrudRepository<Guest, Long>{

}
