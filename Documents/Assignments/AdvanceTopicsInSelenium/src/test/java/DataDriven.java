import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.*;

public class DataDriven {
    ArrayList<String> a = new ArrayList<>();

    public ArrayList<String> getData(String testcaseName) throws IOException {


        FileInputStream fis = new FileInputStream("src/test/resources/datas.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        Iterator<Row> rows =sheet.iterator();
        Row first =rows.next();
        Iterator<Cell> cells = first.cellIterator();
        int k=0;
        int column = 0;
        while (cells.hasNext()){
            Cell value= cells.next();
            if(value.getStringCellValue().equals("")){
column=k;
            }
        }
k++;
        while (rows.hasNext()){
            Row r= rows.next();
            if(r.getCell(column).getStringCellValue().equals(testcaseName)){
                Iterator<Cell> cv =r.cellIterator();
                while (cv.hasNext()){
                    Cell c = cv.next();
                    if(c.getCellType()== CellType.STRING){
                    a.add(c.getStringCellValue());
                }
                    else{
                        a.add(String.valueOf(c.getNumericCellValue()));
                    }

                    }
            }
        }
        fis.close();
return a;

    }
}
