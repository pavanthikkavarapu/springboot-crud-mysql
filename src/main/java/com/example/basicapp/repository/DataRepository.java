package com.example.basicapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.basicapp.model.Data;


@Repository
public interface DataRepository extends JpaRepository<Data, Long> {

}
