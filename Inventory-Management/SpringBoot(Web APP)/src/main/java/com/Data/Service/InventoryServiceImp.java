package com.Data.Service;

import com.Data.Models.Inventory;
import com.Data.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InventoryServiceImp implements InventoryService {

	@Autowired
	private InventoryRepository repo;

	@Override
	public List<Inventory> GetInventory() {
		return repo.findAll();
	}

	@Override
	public void saveInventory(Inventory inventory) {
		repo.save(inventory);
	}

	@Override
	public Inventory getInventoryById(long id) {
		Optional<Inventory> optional = repo.findById(id);
		if(optional.isPresent()){
			return optional.get();
		} else {
			throw new RuntimeException("Inventory not found for id :: " + id);
		}
	}

	@Override
	public void deleteInventoryById(long id) {
		repo.deleteById(id);
	}

	@Override
	public List<Inventory> searchByProductName(String keyword) {
		return repo.findByProductnameContainingIgnoreCase(keyword);
	}

	@Override
	public List<Inventory> getSortedInventory(String sortField, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		return repo.findAll(sort);
	}

	@Override
	public Page<Inventory> findPaginated(int pageNo, int pageSize, String sortField, String sortDir) {
		Sort sort = sortDir.equalsIgnoreCase("asc") ? Sort.by(sortField).ascending() : Sort.by(sortField).descending();
		Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
		return repo.findAll(pageable);
	}
}
