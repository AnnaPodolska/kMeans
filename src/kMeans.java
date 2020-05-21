import java.util.*;

public class kMeans {
    private int k;
    private List<Point> startlistOfPoints;
    private List<Point> centroids = chooseRandomPoints(k);
    List<List<Point>> groups; // A={P1, P2, P3}, B={P4, P5}, itp.

    public kMeans(List<Point> input, int k){
        this.startlistOfPoints = input;
        this.k = k;
        executeKMeans();
    }

    public void executeKMeans() {
        //this.selectRandomCentroids(); // losujemy poczatkowe centroidy - losowo wybranych k punktow ze zbioru punktow

        for(int i = 0; i < k; k++){
            groups.add(new ArrayList<>());
            groups.get(i).add(centroids.get(i));
        }
        // losujemy k pierwszych centroidow i dodajemy je do grup - tyle ile k tyle grup, a centroidy startowe beda odniesieniem do
        // przyporzadkowania kolejnych punktow
        Map<Point, Double> map = new HashMap<>();

        for (int i = 0; i < k; i++){
            map.put(centroids.get(i), 0.0);
        }

        for(int i = 0; i < startlistOfPoints.size(); i++){
            for(int j = 0; j < centroids.size(); j++){
                if(!checkIfIsCentroid(startlistOfPoints.get(i))){
                    map.put(centroids.get(j), CountEuclideanDistancePow(startlistOfPoints.get(i), centroids.get(j)));
                }
                map = sortMap(map);
                Point closest = map.keySet().stream().findFirst().get();
                for(List l : groups){
                    if(l.contains(closest))
                        l.add(startlistOfPoints.get(i));
                }
            }
        }
        for(List l : groups){
            System.out.println(l);
        }


    }
    // losujemy k pierwszych centroidow
//    public void selectRandomCentroids(){
//        centroids = chooseRandomPoints(k);
//    }
    public boolean checkIfIsCentroid(Point p){
        for (List l : groups){
            if (l.contains(p)){
                return true;
            }
        }
        return false;
    }

    public List<Point> chooseRandomPoints(int k){
        List<Point> alreadyTaken = new ArrayList<>();
        List<Point> result = new ArrayList<>();
        while(result.size() != k){
           Point p = startlistOfPoints.get((int)Math.random()*startlistOfPoints.size());
           if(!alreadyTaken.contains(p)){
               result.add(p);
           }
        }
        return result;
    }

    public double CountEuclideanDistancePow(Point a, Point b){
       double distance = 0;
       List<Double> aVals = a.getValues();
       List<Double> bVals = b.getValues();
       // TODO dodac wyjatek jesli dwa punkty maja rozna ilosc parametrow
        for(int i =0; i < a.getValues().size(); i++){
            distance += Math.pow((aVals.get(i) - bVals.get(i)), 2);
        }
        return distance;
    }
    public Map<Point, Double> sortMap(Map<Point, Double> map) {
        List<Map.Entry<Point, Double>> list = new LinkedList<>(map.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<Point, Double>>() {
            @Override
            public int compare(Map.Entry<Point, Double> o1, Map.Entry<Point, Double> o2) {
                if (o1.getValue() < o2.getValue())
                    return -1;
                else if (o1.getValue() > o2.getValue())
                    return 1;
                else
                    return 0;
            }
        });
        Map<Point, Double> result = new LinkedHashMap<>();
        for (Map.Entry<Point, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }
}
