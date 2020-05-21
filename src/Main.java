import java.util.List;

public class Main {

    int k = 3;
    FileLoader fl = new FileLoader("data.csv");
    kMeans km = new kMeans(fl.getFile(), k);


}
