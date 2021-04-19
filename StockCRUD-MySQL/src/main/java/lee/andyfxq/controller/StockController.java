package lee.andyfxq.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lee.andyfxq.model.Stock;
import lee.andyfxq.service.StockService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/STOCK")
public class StockController {
	
	@Autowired
	StockService stockService;
	
	@GetMapping("/list")
//	@PreAuthorize("hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Stock> requestAll() {
		return stockService.getAll();
	}
	
	@GetMapping("/id/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public Optional<Stock> requestById(@PathVariable Long id) {
		return stockService.getById(id);
	}
	
	@GetMapping("/cid/{cid}")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public List<Stock> requestByCid(@PathVariable Long cid) {
		return stockService.getByCid(cid);
	}
	
	@PostMapping("/create")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Stock> createStock(@RequestBody Stock stock) {
		try {
			Stock _stock = stockService._save(new Stock(
					stock.getCid(), stock.getSymbol(), stock.getCurrency(), stock.getVolume(), stock.getPrice()
					));
			return new ResponseEntity<>(_stock, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("edit/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<Stock> editStock(@PathVariable Long id, @RequestBody Stock stock) {
		Optional<Stock> stockData = stockService.getById(id);
		
		if (stockData.isPresent()) {
			Stock _stock = stockData.get();
			_stock.setSymbol(stock.getSymbol());
			_stock.setCurrency(stock.getCurrency());
			_stock.setPrice(stock.getPrice());
			_stock.setVolume(stock.getVolume());
			
			return new ResponseEntity<>(stockService._save(_stock), HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@DeleteMapping("edit/{id}")
//	@PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
	public ResponseEntity<HttpStatus> deleteStock(@PathVariable Long id) {
		try {
			stockService.removeById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
