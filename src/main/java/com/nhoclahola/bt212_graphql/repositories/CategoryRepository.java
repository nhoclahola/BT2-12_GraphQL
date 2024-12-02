package com.nhoclahola.bt212_graphql.repositories;

import com.nhoclahola.bt212_graphql.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>
{
    List<Category> findByUserId(Integer userId);
}
