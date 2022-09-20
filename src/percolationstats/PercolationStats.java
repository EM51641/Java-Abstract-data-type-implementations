package percolationstats;

import java.lang.Math;
import edu.princeton.cs.algs4.StdStats;
import percolationmain.Percolation;

public class PercolationStats {


    public double[] arr;
    public double std, avg, high_conf, low_conf;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){

        this.arr = new double[trials];

        for(int i = 0; i < trials; i++ ){
            Percolation percolation = new Percolation(n);
            int openCase = percolation.numberOfOpenSites();
            this.arr[i] = openCase;
        }

        this.avg = this.mean(this.arr);
        this.std = this.stddev(this.arr);;
        this.high_conf = this.confidenceLo(this.arr);;
        this.low_conf = this.confidenceHi(this.arr);;

    }

    // sample mean of percolation threshold
    public double mean(double[] a){
        return StdStats.mean(a);}

    // sample standard deviation of percolation threshold
    public double stddev(double[] a){
        return StdStats.stddev(a);}

    // low endpoint of 95% confidence interval
    public double confidenceLo(double[] a){
        return StdStats.mean(a) - 1.96 * stddev(a) * 1/(Math.pow(a.length, .5));
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(double[] a){
        return StdStats.mean(a) + 1.96 * stddev(a) * 1/(Math.pow(a.length, .5));
    }

   // test client (see below)
   public static void main(String[] args){

    PercolationStats stats = new PercolationStats(150, 1000);

    System.out.println(stats.avg);
    System.out.println(stats.std);
    System.out.println(stats.low_conf);
    System.out.println(stats.high_conf);
    System.out.println("Probability " + stats.avg/(150 * 150));
   }

}