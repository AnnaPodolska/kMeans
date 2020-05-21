import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileLoader {

    private Path path;

    public FileLoader(String path){
        this.path = Paths.get(path);
    }

    public List<Point> getFile() {

        List<String> fromFile = new ArrayList();
        try {
            fromFile = Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println("Nie mozna znalezc pliku");
        }
        List<Point> data = new ArrayList<>();
        for (String l : fromFile) {
            if (!l.isEmpty()) {

                data.add(new Point(stringToDouble(l)));
            }
        }
        return data;
    }

    private List<Double> stringToDouble(String csvFileLine){
        String[] parts = csvFileLine.split(";");
        List<Double> result = new ArrayList<>();
        for (int i = 0; i < parts.length; i++)
        {
            result.add(Double.valueOf(parts[i]));
        }
        return result;
    }
}

