package CISC3130;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;
import java.util.Arrays;
import java.util.Comparator;
import java.io.File;
import java.io.PrintStream;
/*Creates a Artist Node*/
class Artist {
    String name;
    Artist next;
    public Artist(String name) {
      this.name = name;
      }
}
/*Linked list*/
class TopStreamingArtists {
    private Artist first;
    public TopStreamingArtists(){
      first = new Artist(null);
    }
  
    
 public void add(String name) {
  if(first == null) {
   first = new Artist(name);
  }
  Artist current = first;
  while(current.next != null) {
   current = current.next;
  }
  current.next = new Artist(name);
 }
 public void toFile() throws FileNotFoundException {
  Artist current = first;    
  PrintStream ps = new PrintStream(new File("SortedArtist.txt"));
        if(first == null) {    
            System.out.println("List is empty");    
            return;    
        }    
        ps.println("Artists: ");    
        while(current != null) {    
             
            ps.println(current.name + " ");    
            current = current.next;    
        }    
        System.out.println();    
    }    
  
 }

public class Lab3 {
  public static void main(String [ ] args) throws FileNotFoundException {
    TopStreamingArtists artistNames = new TopStreamingArtists();
   
   
    int cols = 100;  
    int rows = 202;
    
    String[][] myList = new String[rows][cols];

    
    
    Scanner sc = new Scanner(new BufferedReader(new FileReader("src/regional-global-daily-latest.csv")));
    while(sc.hasNextLine()) {
         for (int i=0; i < myList.length; i++) {
            String[] line = sc.nextLine().trim().split(",");
            for (int j=0; j<line.length; j++) {
               myList[i][j] = line[j];
         }
       }
    }
    

    Arrays.sort(myList, new Comparator<String[]>() {
    @Override
    public int compare(final String[] entry1, final String[] entry2) {
        final String time1 = entry1[2];
        final String time2 = entry2[2];
        return time1.compareTo(time2);
    }
});
 
    String[] name = new String[rows];
    for(int i = 0; i < myList.length; i++){
      name[i] = myList[i][2];
      artistNames.add(name[i]);
      
    }
    
   
    
    artistNames.toFile();
    
 }
}