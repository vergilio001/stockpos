package stock.stockpos.DTO;

public class StockDTO {
    private String sku;
    private String name;
    private String stockQuantity;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    @Override
    public String toString() {
        return "StockDTO{" +
                "sku='" + sku + '\'' +
                ", name='" + name + '\'' +
                ", stockQuantity='" + stockQuantity + '\'' +
                '}';
    }
}
