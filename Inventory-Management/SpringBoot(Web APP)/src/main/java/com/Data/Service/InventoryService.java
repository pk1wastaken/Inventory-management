package com.Data.Service;

import java.util.List;

import com.Data.Models.Inventory;
import org.springframework.data.domain.Page;
public interface InventoryService {
	
		 void saveInventory(Inventory inventory);
		 Inventory getInventoryById(long id);
		 void deleteInventoryById(long id);
		 List<Inventory> GetInventory();
		 List<Inventory> searchByProductName(String keyword);
		 List<Inventory> getSortedInventory(String sortField, String sortDir);
		 Page<Inventory> findPaginated(int pageNo, int pageSize, String sortField, String sortDir);
}
