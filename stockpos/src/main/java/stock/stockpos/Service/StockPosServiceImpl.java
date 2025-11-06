package stock.stockpos.Service;

import org.springframework.stereotype.Service;
import stock.stockpos.DTO.StockDTO;
import stock.stockpos.Model.StockPos;
import stock.stockpos.Repository.StockPosJPA;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
@Service
public class StockPosServiceImpl implements StockPosService {

    @Autowired
    private StockPosJPA stockPosJPA;

    @Override
    public List<StockPos> getAllStockPos() {
        return stockPosJPA.findAll().isEmpty() ? List.of() : stockPosJPA.findAll();
    }

    @Override
    public   Map<String, Object> findStockBySkuOrName(String sku, String name) {
        Map<String, Object> response = new HashMap<>();
        if(stockPosJPA.findBySkuOrName(sku,name).isEmpty()){
            response.put("message", "No Data Found");
            return response;
        }
        List<String> outOfStockItems = stockPosJPA.findBySkuOrName(sku, name).stream()
                .filter(sp -> sp.getStockQuantity() <= 0)
                .map(StockPos::getName)
                .collect(Collectors.toList());
        response.put("data", stockPosJPA.findBySkuOrName(sku,name));
        if(!outOfStockItems.isEmpty()) {
            response.put("Note", outOfStockItems + " are out of stock");
        }
        response.put("message" , "Data Found Successfully");
        return response;
    }

    @Override
    public String saveAllStocks(List<StockDTO> stockDto) {
        List<StockPos> stockPos = stockDto.stream()
                .map(dto -> {
                    StockPos stock = new StockPos();
                    stock.setSku(dto.getSku());
                    stock.setName(dto.getName());
                    stock.setStockQuantity(Integer.parseInt(dto.getStockQuantity()));
                    return stock;
                })
                .collect(Collectors.toList());

        if (stockPos.isEmpty()) {
            return "No Data to Save";
        }
        stockPosJPA.saveAll(stockPos);
        return "Saved Successfully";
    }
}
