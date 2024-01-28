package com.devsuperior.uri2609.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2609.entities.Category;
import com.devsuperior.uri2609.projections.CategorySumProjection;
import com.fasterxml.jackson.annotation.JacksonInject.Value;

public interface CategoryRepository extends JpaRepository<Category, Long> {
	
	@Query(nativeQuery = true, value = "SELECT categories.name, SUM(products.amount) "
			+ "FROM categories "
			+ "INNER JOIN products ON products.id_categories = categories.id "
			+ "GROUP BY categories.name")
	List<CategorySumProjection> search1();
}
