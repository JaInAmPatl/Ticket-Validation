import java.util.Scanner;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * This class manages anu tab delimited text file, e.g.  codes.txt
 *
 * @author Dave Slemon
 * @version v100
 */
public class Table
{ //class
    
    //instance variables
    private String tablename;
    private int numRows;
    private int numCols;
    private String[][] grid;

    
    
/** 
 *
 * Initialize the class with the name of the tab delimited text file you wish to manage.
 *
 * @param filename  the name of tab delimited text file.
 */
    public Table( String filename ) 
    { //table
        tablename = filename;
        numRows=0;
        numCols=0;
        String s;
        int r;
        String[] item;
        
        
        
        //Pass1:  Go through the text file in order to ascertain the
        //        numRows and numCols
        try {

            Scanner theFile = new Scanner(new FileInputStream(new File (tablename)));
            while ( theFile.hasNextLine() )
            {
                s = theFile.nextLine();
                item = s.split("\t", 0);
                
                
                if (item.length > numCols)
                   numCols = item.length;     

                numRows++;
      
            }
            theFile.close();
        }
        catch (FileNotFoundException  e)
        {
            System.out.println("Table class Error 1: file not found.");
        }
            
        
        
        grid = new String[numRows][numCols];
        
                
        //Pass2:  populate the grid array
        try {
            
            Scanner theFile = new Scanner(new FileInputStream(new File (tablename)));
            r=0;
            while ( theFile.hasNextLine() )
            {
                s = theFile.nextLine();
                item = s.split("\t", 0);
                
                for(int c=0; c < numCols; c++) {
                    
                   
                    
                    if ( item[c].length() == 0)
                        grid[r][c] = "";
                    else
                        grid[r][c] = item[c];
             
                       
                }
                r++;
                
  
            }
            theFile.close();
        }
        catch (Exception e)
        {
            System.out.println("Table class error 2: file not found.");
        }
    } //table

    /*
    * The method to display all the values
    * */
    public void display () {
        for (int r =0;r< grid.length;r++) {
            for (int c=0;c< grid[r].length;c++) {
                System.out.print(grid[r][c] + "\t");
            }
            System.out.println();
        }
    }

    /*
    * The method to look up the value of the r
    * @param String key
    * */
    public int lookup (String key) {
        for (int r=0;r< grid.length;r++) {
            if(key.equals(grid[r][0])) {
                return r;
            }
        }
        return -1;
    }

    /*The method to find the row of the given ticket code
    * @param String key
    *  */
    public String [] getMatches (String key) {
        int rowNum = lookup(key);
        if (rowNum < 0) return null;
        return getMatches(rowNum);
    }

    public String[] getMatches(int rowNum){
        String[] result = new String[numCols];
        if (rowNum >= 0 &&  rowNum < grid.length){
            for(int c=0;c< numCols; c++){
                result[c] = grid[rowNum][c];
            }
            return result;
        }
        return null;
    }


    /*The methods to change the values in the table*/

    public void change (int rowNum , int colNum , String newValue) {
        if (rowNum <=0 && rowNum< numRows && colNum>=0 && colNum<numCols) {
            grid[rowNum][colNum] = newValue;
        }
    }

    public void change (String key , int colNum , String newValue) {
        for (int i= 0 ; i< numRows ; i++) {
            if (grid[i][colNum].equals(key)) {
                grid[i][colNum] = newValue;
                break;
            }
        }
    }
    public void change (String key,  String newValue) {
        for(int i=0 ; i<numRows;i++) {
            if(grid[i][0].equals(key)) {
                grid[i][2] = newValue;
                break;
            }
        }
    }

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
    }

    public void save () {
        String filename = tablename; // Assuming tablename is the filename to save to
        try (FileWriter writer = new FileWriter(filename)) {
            for (int r = 0; r < numRows; r++) {
                for (int c = 0; c < numCols; c++) {
                    writer.write(grid[r][c] + "\t");
                }
                writer.write("\n");
            }
        } catch (IOException e) {
            System.err.println("Error saving data to file: " + e.getMessage());
        }
    }




    public String toString() {
        
            return ("Table: " + tablename + "  rows = " + numRows + "  cols = " + numCols);
     }
        
  } //class
    
    
    