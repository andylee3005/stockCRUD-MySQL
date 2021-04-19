package lee.andyfxq.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import lee.andyfxq.model.Stock;

public interface StockRepository extends JpaRepository<Stock, Long> {

	List<Stock> findByCid(Long cid);
	
	@Modifying
	@Transactional
	@Query(value="DELETE from portfolio_stock WHERE stock_id = :sid", nativeQuery=true)
	int deleteRelationsStock(@Param("sid") Long sid);
	
}
