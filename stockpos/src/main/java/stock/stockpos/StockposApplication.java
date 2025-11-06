package stock.stockpos;

import stock.stockpos.Service.StockPosService;
import stock.stockpos.Utils.CSVUtils;
import com.opencsv.exceptions.CsvValidationException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.FileNotFoundException;

@SpringBootApplication
public class StockposApplication {

	public static void main(String[] args) throws CsvValidationException, FileNotFoundException {
		SpringApplication.run(StockposApplication.class, args);

	}



}
