import java.lang.Math;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {


    private double[] arr;
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

    int N = Integer.parseInt(args[0]);
    int Trials = Integer.parseInt(args[1]);

    PercolationStats stats = new PercolationStats(N, Trials);

    double mean         = stats.mean();
    double stddev       = stats.stddev();
    double confidenceLo = stats.confidenceLo();
    double confidenceHi = stats.confidenceHi();

    System.out.printf("mean:                    %f\n", mean);
    System.out.printf("stddev:                  %f\n", stddev);
    System.out.printf("95%% confidence interval: %s, %s", confidenceLo, confidenceHi);
    System.out.print("\n");
   }

}