import java.util.List;
import java.util.Map;

public class Point {
    private List<Double> values;
    private int groupNumber = 0;
    public Point(List<Double> _val){
        this.values = _val;
    }

    public List<Double> getValues(){
        return this.values;
    }

    public void setGroupNumber(int x){
        this.groupNumber = x;
    }

    public int getGroupNumber(){
        return this.groupNumber;
    }

}
