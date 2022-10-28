/*
 * @author Rakshita Mathur
 * Student ID: 300215340
 * Course: CSI 2110 - Data Structures and Algorithms
 * Class: Point3D
 * Date: 2022/10/26
 * Description: This class implements the Point3D object that is used in the DBScan algorithm. 
 */ 
 
public class Point3D{
    /**
     * x coordinate of the point
     */
    public double x;
    /**
     * y coordinate of the point
     */
    public double y;
    /**
     * z coordinate of the point
     */
    public double z;
    /**
     * Label being -1 for noise, 0 for unvisited, and a positive integer for the cluster number
     */
    public int label; 

    /**
     * Constructor that accepts three doubles
     * @param x coordinate of the point
     * @param y coordinate of the point
     * @param z coordinate of the point
     * initializing the label to 0
     */
    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
        this.label = 0;
    }

    /**
     * this function use euclidian formula and caclulated the distance 
     * @param pt is the stack of point3d
     * @return the euclidian distance using the x,y and z coordiates 
     */
    public double distance (Point3D pt){
        return Math.sqrt(Math.pow(this.x - pt.x, 2) + Math.pow(this.y - pt.y, 2) + Math.pow(this.z - pt.z, 2));
    }
    
}