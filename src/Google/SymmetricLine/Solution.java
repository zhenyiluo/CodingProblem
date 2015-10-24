import java.util.*;
class Point{
    double x;
    double y;
    public Point(double x, double y){
        this.x = x;
        this.y = y;
    }

    @Override
    public int hashCode(){
        int result = ((Double)x).hashCode();
        result = result * 31 + ((Double)y).hashCode();
        return result;
    }
    @Override
    public boolean equals(Object obj){
        if(obj instanceof Point){
            Point p = (Point) obj;
            return Double.compare(this.x, p.x) == 0 && Double.compare(this.y, p.y) == 0;
        }else{
            return false;
        }
    }
}

public class Solution{
    public static final double DELTA = 1e-6;
    public static void main(String[] args){
        List<Point> points1 = new ArrayList<>();
        List<Point> points2 = null;
        List<Point> points3 = new ArrayList<>();
        points3.add(new Point(1.1, 2));
        points3.add(new Point(2, 2));
        List<Point> points4 = new ArrayList<>();
        points4.add(new Point(1, 2));
        points4.add(new Point(2, 2));
        points4.add(new Point(2, 2));

        List<Point> points5 = new ArrayList<>();
        points5.add(new Point(1, 2));
        points5.add(new Point(2, 2));
        points5.add(new Point(2, 2));
        points5.add(new Point(2, 3));

        System.out.println("Testcase#1:");
        System.out.println(new Solution().isSymmetricLineExist(points1));
        System.out.println("Test case #2:");
        System.out.println(new Solution().isSymmetricLineExist(points2));
        System.out.println("Test case #3:");
        System.out.println(new Solution().isSymmetricLineExist(points3));
        System.out.println("Test case #4:");
        System.out.println(new Solution().isSymmetricLineExist(points4));
        System.out.println("Test case #5:");
        System.out.println(new Solution().isSymmetricLineExist(points5));
    }

    public boolean isSymmetricLineExist(List<Point> points){
        if(points == null || points.size() == 0){
            return false;
        }
        double sum = 0;
        HashSet<Point> tmpSet = new HashSet<>(points);
        points = new ArrayList<>(tmpSet);
        for(Point point : points){
            sum += point.x;
        }
        double average = sum / points.size();
        HashSet<Point> hs = new HashSet<>(points);
        for(Point point : points){
            if(!hs.contains(point)){
                continue;
            }
            if(Math.abs(point.x - average) < DELTA){
                hs.remove(point);
                continue;
            }else if(point.x < average){
                Point p = new Point(average + average - point.x, point.y);
                if(!hs.contains(p)){
                    return false;
                }
                hs.remove(point);
                hs.remove(p);
            }else{
                Point p = new Point(average - (point.x - average), point.y);
                if(!hs.contains(p)){
                    return false;
                }
                hs.remove(point);
                hs.remove(p);
            }
        }
        return hs.isEmpty();
    }

}
