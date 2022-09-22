import java.util.Arrays;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {
    
    private WeightedQuickUnionUF finderunionizer;
    private int[][] array;
    private int n;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n){

        if(n <= 0){
            throw new IllegalArgumentException("Impossible to initialise an array with a negative length");
        }

        this.n = n; 
        this.array = new int[n][n]; // initialise the array
        this.finderunionizer = new WeightedQuickUnionUF(n*n + 2); //initialise the index list while adding 2 virtual boxes at the beginning and the end of the array
        this.connectVirtualBlock(); // connection the virtual boxes so as to limit the search operation to a O(n) complexity instead of an O(n^2)

        while(!this.percolates() == true){
            this.openrandomlyandjoin(); //open a random location and link it to the next open ones.
        };
    };

    // opens the site (row, col) if it is not open already
    public void open(int row, int col){
        if(this.array[row][col] == 0){
            this.array[row][col] = 1; //open the location
            join(row, col); //join the location
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){

        if (row  < this.n && row >= 0 && col < this.n && col >= 0){
            if(this.array[row][col] == 1)return true;
            else return false;
        }
        else{
            throw new IllegalArgumentException("Out of bound index");
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

        for (int[] arr : this.array)
            for(int i: arr)
                sum+=i;
    return sum ;
    }  

    // join the index
    private void join(int row, int col){

        if(col < this.n - 1 && this.isOpen(row, col + 1)){
            this.finderunionizer.union( row * this.n + col + 1, row * this.n + (col + 1) + 1);
        }
        
        if(col > 0 && this.isOpen(row, col - 1)){
            this.finderunionizer.union( row * this.n + (col - 1) + 1, row * this.n + col + 1);
        }
        
        if(row > 0 && this.isOpen(row - 1, col)){
            this.finderunionizer.union( ( row - 1 ) * this.n + col + 1, row * this.n + col + 1);
        }
        
        if(row < this.n - 1 && this.isOpen(row + 1, col)){
            this.finderunionizer.union( row * (this.n) + col + 1, (row + 1) * this.n + col + 1);
        } 
  };

    // does the system percolate?
    public boolean percolates(){

        if(this.finderunionizer.find(0) == this.finderunionizer.find(this.n * this.n + 1)){
            return true;
        }
        else return false;   
    }
    // open a random case and make unions with other cases
    private void openrandomlyandjoin(){
        int row =  StdRandom.uniformInt(0, this.n);
        int col = StdRandom.uniformInt(0, this.n);
        this.open(row, col); 
    }

    private void visualize(){

        for (int[] row : this.array){
            System.out.println(Arrays.toString(row));
    };
};
    
    // connect virtual boxes at the 'top' and the 'bottom' of the array
    private void connectVirtualBlock(){
        for (int j = 0; j < this.n; j++){
            this.finderunionizer.union( 0,  1 + j ); // top wire
            this.finderunionizer.union(this.n * this.n + 1, this.n * (this.n - 1) + j + 1); //low wire
        }
    }

    // test client (optional)
    public static void main(String[] args){
        Percolation percolate = new Percolation(10);
        percolate.visualize();
    }
        
}