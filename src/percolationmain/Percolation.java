package percolationmain;
//import java.io.Console;
//import java.lang.reflect.Array;
//import java.util.Arrays;

import edu.princeton.cs.algs4.StdRandom;
//import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    
    private WeightedQuickUnionUF finderunionizer;
    public int[][] array;
    public int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){
        this.n = n;
        this.array = new int[n][n]; // initialise the arra
        this.finderunionizer = new WeightedQuickUnionUF(n*n);
        boolean percolated = false;
        int m;
        
        while(percolated == false){
            
            for (int i = 0; i < this.n; i++){ 
                m = StdRandom.uniformInt(0, this.n);
                this.open(i,m); 
                };

            for (int i = 0; i < this.n; i++){ 
                for(int j = 0; j < this.n; j++){

                    if(this.array[i][j] == 0){
                        continue; 
                    }

                    if(this.array[i][j] == 1){
                        
                        if(this.isOpen(i, j + 1)){
                            this.finderunionizer.union( i * this.n + j, i * this.n + (j + 1));
                        }

                        if(this.isOpen(i, j - 1)){
                            this.finderunionizer.union( i * this.n + (j - 1), i * this.n + j);
                        }
    
                        if(this.isOpen(i - 1, j)){
                            this.finderunionizer.union( ( i - 1 ) * this.n + j, i * this.n + j);
                        }
    
                        if(this.isOpen(i + 1, j)){
                            this.finderunionizer.union( i * (this.n) + j, (i + 1) * this.n + j );
                        } 
              };
            };
        };


        //System.out.println("-----------------------------------------");
        //System.out.println(Arrays.toString(this.array[0]));
        //System.out.println(Arrays.toString(this.array[1]));
        //System.out.println(Arrays.toString(this.array[2]));
        //System.out.println(Arrays.toString(this.array[3]));
        //System.out.println(Arrays.toString(this.array[4]));
        //System.out.println(Arrays.toString(this.array[5]));
        //System.out.println(Arrays.toString(this.array[6]));
        //System.out.println(Arrays.toString(this.array[7]));
        //System.out.println(Arrays.toString(this.array[8]));
        //System.out.println(Arrays.toString(this.array[9]));
        //System.out.println("-----------------------------------------");

            for (int i = 0; i < this.n; i++){ 
                //System.out.println("-----------------------------------------");
                //System.out.println(i);
                //System.out.println("-----------------------------------------");

                for(int j = this.n * this.n - this.n; j < this.n * this.n; j++){ 
                    //System.out.println(j);
                    if(this.finderunionizer.find(i) == this.finderunionizer.find(j)){
                        percolated = this.percolates();
                        break;
                    }
                //System.out.println("-----------------------------------------");    

                };
            };
            
            //if (1 == 1){
            //    break;}  

        };
    };

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        this.array[row][col] = 1;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        try{
            if(this.array[row][col] == 1)return true;
            else return false;}
        catch(Exception e){
           return false;
        }  
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if(this.array[row][col] == 0)return true;
        else return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites(){
    
        int sum = 0;

        for (int[] arr : array)
            for(int i: arr)
                sum+=i;
    
    return sum;
    }  


    // does the system percolate?
    public boolean percolates(){
        return true;
    }


    // test client (optional)
    public static void main(String[] args){
        Percolation percolate = new Percolation(10);
    }
        
}