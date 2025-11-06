package stock.stockpos.Controller;

import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import stock.stockpos.Model.StockPos;
import stock.stockpos.Service.StockPosService;
import stock.stockpos.Utils.CSVUtils;

import java.io.FileNotFoundException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/stockpos")
public class StockPosController {
    @Autowired
    private StockPosService stockPosService;


    @PostMapping("/v1/importStocks")
    public ResponseEntity<?> importStocks() {
        try {
            stockPosService.saveAllStocks(CSVUtils.parseCSV());
            return ResponseEntity.ok("Stocks imported successfully");
        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("CSV file not found! ");
        } catch (CsvValidationException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("CSV validation failed! ");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Unexpected error: " + e.getMessage());
        }
    }

    @GetMapping("/v1/getAllStocks")
    public ResponseEntity<Map<String, Object>> getAllStocks() {
        Map<String, Object> response = new HashMap<>();
        response.put("status", "success");
        response.put("data", stockPosService.getAllStockPos());
        return ResponseEntity.ok(response);
    }
    @GetMapping("/v1/getStocks")
    public ResponseEntity<Map<String, Object>> getStocks(@RequestParam(defaultValue = "") String sku,
                                                         @RequestParam (defaultValue = "") String name) {
        return ResponseEntity.ok(stockPosService.findStockBySkuOrName(sku,name));
    }

}
