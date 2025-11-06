package stock.stockpos.Service;

import stock.stockpos.DTO.StockDTO;
import stock.stockpos.Model.StockPos;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

public interface StockPosService {

    List<StockPos> getAllStockPos();
    Map<String, Object> findStockBySkuOrName(String sku , String name);
    public String saveAllStocks(List<StockDTO> stockDto);
}
