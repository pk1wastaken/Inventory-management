package com.Data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import com.Data.Models.Inventory;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, Long>{

    List<Inventory> findByProductnameContainingIgnoreCase(String keyword);
	
}
