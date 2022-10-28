/**
 * @author Rakshita Mathur
 * Student ID: 300215340
 * Course: CSI 2110 - Data Structures and Algorithms
 * Class: NearestNeighbors
 * Date: 2022/10/26
 * Description: This class implements the NearestNeighbors algorithm to find the nearest neighbors of a given point in a given dataset. 
 */

import java.util.*;

public class NearestNeighbors {
    
    /**
     * Stack of point in 3D space
     */
    private Stack<Point3D> points;
    
    /**
     * Constructor that accepts a stack of Point3D
     * @param Stack of Point3D which takes the x,y and z coordinates and make a single point.
     */
    NearestNeighbors( Stack<Point3D> points) {
        this.points = points;
    
    }

    /**
     *  a rangeQuery method that finds the nearest neighbors of a 3D point
     * @param eps that is the epsilon value
     * @param P that is the object of class point 3d
     * @return neighbors which is a stack of 3D points within the range of epsilon
     */
    
    public Stack<Point3D> rangeQuery(double eps, Point3D P) {
        Stack<Point3D> neighbors = new Stack<Point3D>();
        for (Point3D p : points) {
            if (p.distance(P) <= eps) {
                neighbors.add(p);
            }
        }
        return neighbors;
    }

}