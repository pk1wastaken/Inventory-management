package com.Data.Controller;

import com.Data.Models.Inventory;
import com.Data.Service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class InventoryController {

	@Autowired
	private InventoryService service;

	// Dashboard: display aggregate stats and chart
	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		List<Inventory> inventoryList = service.GetInventory();
		int totalItems = inventoryList.size();
		double totalPrice = 0;
		double totalMargin = 0;
		int marginCount = 0;

		for (Inventory inv : inventoryList) {
			try {
				totalPrice += Double.parseDouble(inv.getPrice());
			} catch (NumberFormatException e) { }
			try {
				totalMargin += Double.parseDouble(inv.getMarginprice());
				marginCount++;
			} catch (NumberFormatException e) { }
		}

		double averageMargin = marginCount > 0 ? totalMargin / marginCount : 0;
		model.addAttribute("totalItems", totalItems);
		model.addAttribute("totalPrice", String.format("$%.2f", totalPrice));
		model.addAttribute("averageMargin", String.format("$%.2f", averageMargin));
		model.addAttribute("chartTotalPrice", totalPrice);
		model.addAttribute("chartTotalMargin", totalMargin);

		return "dashboard";
	}

	// Redirect root URL to page 1 of inventory list.
	@GetMapping("/")
	public String homeRedirect() {
		return "redirect:/page/1";
	}

	// Paginated inventory list with sorting
	@GetMapping("/page/{pageNo}")
	public String findPaginated(@PathVariable("pageNo") int pageNo,
								Model model,
								@RequestParam(value = "sortField", defaultValue = "productname") String sortField,
								@RequestParam(value = "sortDir", defaultValue = "asc") String sortDir) {
		int pageSize = 5;
		Page<Inventory> page = service.findPaginated(pageNo, pageSize, sortField, sortDir);
		List<Inventory> listInventory = page.getContent();

		model.addAttribute("currentPage", pageNo);
		model.addAttribute("totalPages", page.getTotalPages());
		model.addAttribute("totalItems", page.getTotalElements());
		model.addAttribute("Inventory", listInventory);
		model.addAttribute("sortField", sortField);
		model.addAttribute("sortDir", sortDir);
		model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

		return "index";
	}

	@GetMapping("/ShowNewInventoryForm")
	public String InventoryForm(Model model) {
		Inventory inventory = new Inventory();
		model.addAttribute("Inventory", inventory);
		return "new_Inventory";
	}

	@PostMapping("/saveInventory")
	public String saveInventory(@ModelAttribute("Inventory") Inventory inventory) {
		service.saveInventory(inventory);
		return "redirect:/page/1";
	}

	@GetMapping("/showFormForUpdate/{id}")
	public String ShowFormForUpdate(@PathVariable(value = "id") long id, Model model) {
		Inventory inventory = service.getInventoryById(id);
		model.addAttribute("Inventory", inventory);
		return "updateInventory";
	}

	@GetMapping("/deleteInventory/{id}")
	public String DeleteInventory(@PathVariable(value = "id") long id) {
		service.deleteInventoryById(id);
		return "redirect:/page/1";
	}

	// Search inventory by product name
	@GetMapping("/search")
	public String searchInventory(@RequestParam("keyword") String keyword, Model model) {
		List<Inventory> list = service.searchByProductName(keyword);
		model.addAttribute("Inventory", list);
		// For simplicity, set pagination parameters to defaults.
		model.addAttribute("currentPage", 1);
		model.addAttribute("totalPages", 1);
		model.addAttribute("sortField", "productname");
		model.addAttribute("sortDir", "asc");
		model.addAttribute("reverseSortDir", "desc");
		return "index";
	}

	// Export inventory data as CSV
	@GetMapping("/export")
	public void exportToCSV(HttpServletResponse response) throws IOException {
		response.setContentType("text/csv");
		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=inventory.csv";
		response.setHeader(headerKey, headerValue);

		List<Inventory> listInventory = service.GetInventory();
		PrintWriter writer = response.getWriter();
		writer.println("ID,Product Name,Price,Margin Price,Type");

		for (Inventory inv : listInventory) {
			writer.println(inv.getId() + "," + inv.getProductname() + "," + inv.getPrice() + "," + inv.getMarginprice() + "," + inv.getType());
		}
		writer.flush();
		writer.close();
	}

	// Bulk delete selected inventory items
	@PostMapping("/bulkDelete")
	public String bulkDelete(@RequestParam("ids") List<Long> ids) {
		for (Long id : ids) {
			service.deleteInventoryById(id);
		}
		return "redirect:/page/1";
	}
}
