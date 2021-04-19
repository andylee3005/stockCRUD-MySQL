package lee.andyfxq.service;

import java.util.List;
import java.util.Optional;

import lee.andyfxq.model.Stock;

public interface StockService {
	
	List<Stock> getAll();
	List<Stock> getByCid(Long cid);
	Optional<Stock> getById(Long id);
	Stock _save(Stock stock);
	void removeById(Long id);
	
}
