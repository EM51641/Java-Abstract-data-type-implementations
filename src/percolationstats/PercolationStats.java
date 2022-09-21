package percolationstats;

import java.lang.Math;

import edu.princeton.cs.algs4.StdStats;
import percolationmain.Percolation;

public class PercolationStats {


    private double[] arr;
    private double std, avg, high_conf, low_conf;
    private double n;


    // perform independent trials on an n-by-n grid
    public PercolationStats(int n, int trials){

        this.arr = new double[trials];
        this.n = n;

        for(int i = 0; i < trials; i++ ){
            Percolation percolation = new Percolation(n);
            int openCase = percolation.numberOfOpenSites();
            this.arr[i] = openCase/(this.n * this.n);
        }

        this.avg = this.mean();
        this.std = this.stddev();
        this.high_conf = this.confidenceLo();
        this.low_conf = this.confidenceHi();

    }

    // sample mean of percolation threshold
    public double mean(){
        return StdStats.mean(this.arr);}
    // sample standard deviation of percolation threshold
    public double stddev(){
        return StdStats.stddev(this.arr);}

    // low endpoint of 95% confidence interval
    public double confidenceLo(){
        return mean() - 1.96 * stddev()/Math.pow(this.arr.length, .5);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi(){
        return mean() + 1.96 * stddev()/Math.pow(this.arr.length, .5);
    }

   // test client (see below)
   public static void main(String[] args){

    PercolationStats stats = new PercolationStats(5, 10000);

    System.out.println(stats.avg);
    System.out.println(stats.std);
    System.out.println('['+ String.valueOf(stats.low_conf) + ','+  String.valueOf(stats.high_conf) + ']');
   }

}