import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import org.testng.annotations.Test;

import java.io.*;
import java.util.Scanner;

public class HandleCSV {

    @Test(priority = 1)
    public void writeData() throws IOException {
        String csvFile = "src/test/resources/datas.csv";
        FileWriter write = new FileWriter(csvFile);
write.append("Name,Place \n");
write.append("Vijay,Chennai \n");
write.append("Rani,madurai \n");
write.append("Priya,salem \n");
write.close();}
    @Test(priority = 2)
    public void readData() throws IOException, CsvValidationException {
        CSVReader read = new CSVReader(new FileReader("src/test/resources/datas.csv"));
        String[] str;
        while ((str=read.readNext())!=null){
            for (String s :str){
                System.out.print(s+" ");
            }
            System.out.println();
        }

    }}
//@Test
//public void useScanner() throws FileNotFoundException {
//    File f = new File("src/test/resources/datas.csv");
//    Scanner s = new Scanner(f);
//s.useDelimiter(",");
//while (s.hasNext()){
//    System.out.print(s+" ");
//}
//s.close();
//}}