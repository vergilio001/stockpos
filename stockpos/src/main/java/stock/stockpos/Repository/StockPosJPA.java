package stock.stockpos.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import stock.stockpos.Model.StockPos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockPosJPA extends JpaRepository<StockPos, Long> {
    @Query("SELECT sp FROM StockPos sp WHERE sp.sku = :sku OR sp.name = :name ")
    List<StockPos> findBySkuOrName(@Param("sku") String sku, @Param("name") String name);

}
