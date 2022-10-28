/**
 * @author Rakshita Mathur
 * Student ID: 300215340
 * Course: CSI 2110 - Data Structures and Algorithms
 * Class: DBScan
 * Date: 2022/10/26
 * Description: This class implements the DBScan algorithm to find clusters in a given dataset. 
 */

import java.io.*;
import java.util.*;

public class DBScan{

/**
 * Stack of point in 3D space
 */
    private Stack<Point3D> points;

/**
 * epsilon representing the distace between the two points 
 */
    private double eps;

/**
 * number of minimum points in a single cluster 
 */
    private double minPts;

/**
 * Group of points or Numbe rof cluster formed using the Dataset 
 */
    int clusters = 0; 


/**
 * Constructor
 * @param Stack of Point3D which takes the x,y and z coordinates and make a single point.
 */
    public DBScan(Stack<Point3D> pt) {
    this.points = pt;
    } 


/**
 * Setter and getter meathod for Epsilon 
 */
    public double setEps(double eps) {
    this.eps = eps;
    return eps;
    }


/**
 * Setter and getter meathod for Minimum Points 
 */
    public double setMinPts(double minPts) {
    this.minPts = minPts;
    return minPts;
    }


/**
 * Function findcluster which executes the DBScan algortithm 
 */
    public void findClusters(){
        clusters = 0;
        for (Point3D p : points) {
            if (p.label != 0) {
                continue;
            }
            NearestNeighbors N = new NearestNeighbors(points);

            Stack<Point3D> neighbors = N.rangeQuery(eps, p);

            if (neighbors.size() < minPts) {
                p.label = -1;
                continue;
            } 
            clusters++;
            p.label = clusters;
            Stack <Point3D> S = new Stack<Point3D>();
            S.addAll(neighbors);
            while ( !S.isEmpty() ) {
                Point3D Q = S.pop();
                if( Q.label == -1 ) {
                    Q.label = clusters;
                }
                if( Q.label == 0 ) {
                    Q.label = clusters;
                    NearestNeighbors N2 = new NearestNeighbors(points);
                    Stack<Point3D> neighbors2 = N2.rangeQuery(eps, Q);
                    if (neighbors2.size() >= minPts) {
                        S.addAll(neighbors2);
                    }
                }
                
            }
        }
        //System.out.println("Number of clusters: " + clusters);
    } 


/**
 * int getNumberOfCluster() method which gives us the number of cluster generated 
 */
    public int getNumberOfClusters(){ 
        return this.clusters;
    }


/**
 * A getPoints function that return the Stack of Point3D
 */
    public Stack<Point3D> getPoints(){
    return this.points;
    }


/**
 * the read static method that accept a filename and returns a list of Point3D 
 */
    public static Stack<Point3D> read( String filename)  {
    
        Stack<Point3D> stack = new Stack<Point3D>();

        try (Scanner s = new Scanner(new BufferedReader(new FileReader(filename)))) {
            String line = s.nextLine();
            while (s.hasNext()) {
                line = s.nextLine();
                String[] parts = line.split(",");
                double x = Double.parseDouble(parts[0]);
                double y = Double.parseDouble(parts[1]);
                double z = Double.parseDouble(parts[2]);
                Point3D pt = new Point3D(x, y, z);
                stack.push(pt);
            }
        } catch (FileNotFoundException e){
            System.out.println(e);

        }
        return stack;  
    }


/**
    * the save method that saves all the points with their cluster label and associated RGB color
    * RGB are the random values that are generated using the corrdinates and number of clusters ]
    * 
 */
    public void save(String filename) throws FileNotFoundException {
        
        File csvOutputFile = new File(filename);
        try (PrintWriter out = new PrintWriter(csvOutputFile)) {
            out.println("x,y,z,label,R,G,B");
            for (Point3D p : points) {
                double r = p.x / this.getNumberOfClusters();
                double g = p.y / this.getNumberOfClusters();
                double b = p.z / this.getNumberOfClusters();
                out.println(p.x + "," + p.y + "," + p.z + "," + p.label+ "," + r + "," + g + "," + b);
            }
        }
    }


/** 
    * the main method that executes the DBScan algorithm and saves the results
    * @param args[0] is the file name
    * @param args[1] is the epsilon value 
    * @param args[2] is the minimun points 
    * this method runs the save() which creates a new csv file named in the form filename_clusters_eps_minPts_nclusters.csv 
*/
    public static void main(String[] args) throws FileNotFoundException {
    
        Stack<Point3D> myStack = new Stack<Point3D>();
        myStack = DBScan.read(args[0]);
        DBScan myScan = new DBScan(myStack);
        myScan.setEps(Double.parseDouble(args[1]));
        myScan.setMinPts(Double.parseDouble(args[2]));
        myScan.findClusters();
        String[] data = args[0].split("\\.");
        String filename = data[0] +"_clusters_"+args[1]+"_"+args[2]+"_"+ myScan.getNumberOfClusters()+".csv";
        myScan.save(filename);
    }
}
