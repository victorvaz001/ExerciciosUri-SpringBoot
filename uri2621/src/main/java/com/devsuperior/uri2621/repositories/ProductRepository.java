package com.devsuperior.uri2621.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.devsuperior.uri2621.dto.ProductMinDTO;
import com.devsuperior.uri2621.entities.Product;
import com.devsuperior.uri2621.projections.ProductMinProjection;

public interface ProductRepository extends JpaRepository<Product, Long> {

	@Query(nativeQuery = true, value = "SELECT products.name "
			+ "FROM products "
			+ "INNER JOIN providers "
			+ "on products.id_providers = providers.id "
			+ "WHERE products.amount BETWEEN :min AND :max "
			+ "AND providers.name LIKE CONCAT(:beginName, '%')")
	List<ProductMinProjection> seartch1(Integer min, Integer max, String beginName);
	
	
	@Query("SELECT new com.devsuperior.uri2621.dto.ProductMinDTO(obj.name) " /*Instancia um DTO passando o nome como argumento*/
			+ "FROM Product obj "			
			+ "WHERE obj.amount BETWEEN :min AND :max " /*Quando e para um, pode navegador no objeto diretamente*/
			+ "AND obj.provider.name LIKE CONCAT(:beginName, '%')")
	List<ProductMinDTO> seartch2(Integer min, Integer max, String beginName);
	
	
}
