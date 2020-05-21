import java.util.List;

public class Group {
    public List<Point> points;
    public Point centroid;

    public void setCentroid(Point p){
        this.centroid = p;
    }
    public Point getCentroid(){
        return  centroid;
    }
}
