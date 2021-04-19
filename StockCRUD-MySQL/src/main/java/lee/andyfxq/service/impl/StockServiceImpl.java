package lee.andyfxq.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lee.andyfxq.model.Stock;
import lee.andyfxq.repository.StockRepository;
import lee.andyfxq.service.StockService;

@Service("stockService")
public class StockServiceImpl implements StockService {

	@Autowired
	StockRepository stockRepo;

	@Override
	public List<Stock> getAll() {
		return stockRepo.findAll();
	}

	@Override
	public List<Stock> getByCid(Long cid) {
		return stockRepo.findByCid(cid);
	}
	
	@Override
	public Optional<Stock> getById(Long id) {
		return stockRepo.findById(id);
	}

	@Override
	public Stock _save(Stock stock) {
		return stockRepo.save(stock);
	}

	@Override
	public void removeById(Long id) {
		stockRepo.deleteRelationsStock(id);
		stockRepo.deleteById(id);
	}

}
