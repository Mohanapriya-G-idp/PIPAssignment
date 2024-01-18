import java.io.IOException;
import java.util.ArrayList;

public class ReadData {

public static void main(String[] args) throws IOException {
    DataDriven d =new DataDriven();
    ArrayList data= d.getData("login");
    System.out.println(data.get(2));

}


}
