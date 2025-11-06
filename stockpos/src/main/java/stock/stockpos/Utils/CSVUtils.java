package stock.stockpos.Utils;

import stock.stockpos.DTO.StockDTO;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVUtils {

    public static CSVReader getCSVFiles() throws FileNotFoundException {
        String csvFilePath = System.getProperty("user.dir") + File.separator + StringConstants.CSV_DIR;
        System.out.println(csvFilePath);
        return new CSVReader(new FileReader(csvFilePath));
    }

    public static List<StockDTO> parseCSV() throws FileNotFoundException, CsvValidationException {
        List<StockDTO> stockList = new ArrayList<>();

        try (CSVReader csvReader = getCSVFiles()) {
            String[] nextLine;

            int ctr = 0;

            while ((nextLine = csvReader.readNext()) != null) {
                if (ctr == 0) {
                    ctr++;
                    continue;
                }// to skip the header
                StockDTO stockDTO = new StockDTO();
                stockDTO.setSku(sanitizeString(nextLine[0]));
                stockDTO.setName(sanitizeString(nextLine[1]));
                stockDTO.setStockQuantity(StringUtils.isNullOrEmpty(sanitizeString(nextLine[2])) ? "0"
                        : sanitizeString(nextLine[2]));
                System.out.println("CSV parsed successfully");
                stockList.add(stockDTO);

            }
            return stockList;
        }catch (FileNotFoundException e) {
            throw e;
        }catch (CsvValidationException e){
            throw e;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public static String sanitizeString(String input) {
        return input.replaceAll("[^\\x00-\\x7F]", ""); // Remove non-ASCII characters
    }


}
