package stock.stockpos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import stock.stockpos.Model.StockPos;
import stock.stockpos.Repository.StockPosJPA;
import stock.stockpos.Service.StockPosServiceImpl;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StockServiceTest {

    @Mock
    private StockPosJPA stockPosJPA;

    @InjectMocks
    private StockPosServiceImpl stockService;

    @Test
    void testFindStockBySkuOrName_NoDataFound() {
        // Arrange
        String sku = "sku0";
        String name = "product0";
        when(stockPosJPA.findBySkuOrName(sku, name)).thenReturn(Collections.emptyList());

        // Act
        Map<String, Object> result = stockService.findStockBySkuOrName(sku, name);

        // Assert
        assertEquals("No Data Found", result.get("message"));
        assertFalse(result.containsKey("data"));
    }

    @Test
    void testFindStockBySkuOrName_WithOutOfStockItems() {
        // Arrange
        String sku = "sku1";
        String name = "product1";

        StockPos inStock = new StockPos();
        inStock.setSku("sku1");
        inStock.setName("product1");
        inStock.setStockQuantity(10);

        StockPos outOfStock = new StockPos();
        outOfStock.setSku("asdasd");
        outOfStock.setName("product8");
        outOfStock.setStockQuantity(0);

        List<StockPos> mockData = Arrays.asList(inStock, outOfStock);
        when(stockPosJPA.findBySkuOrName(sku, name)).thenReturn(mockData);

        // Act
        Map<String, Object> result = stockService.findStockBySkuOrName(sku, name);

        // Assert
        assertEquals("Data Found Successfully", result.get("message"));
        assertTrue(result.containsKey("data"));
        assertEquals(mockData, result.get("data"));
        assertTrue(result.get("Note").toString().contains("product8"));
    }
}
