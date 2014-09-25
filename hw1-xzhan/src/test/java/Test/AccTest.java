package Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;


public class AccTest {
  // accuracy of our output
  private double accPer;
  // recall of our output
  private double recallPer;
  
  public AccTest(){
    this.accPer = 0;
    this.recallPer = 0;
  }
  // Calculate the accuracy and recall
  public void AccuracyAndRecall(){
    
    // Read output
    File file = new File("hw1-xzhan.out");
    BufferedReader reader = null;
    
    // count how many lines match.
    int lineMatch = 0;
    
    // number of line in output
    int lineOutNum = 0;
    
    // number of line in sample
    int lineSampleNum = 0;
    // To store each line in map, easier for retrieve and compare
    Map<String,Integer> map = new HashMap();
    
    String tempString = "";
 
    try{
      reader = new BufferedReader(new FileReader(file));
      // Content of each line
      //String tempString = "";
      while ((tempString = reader.readLine()) != null) {
        lineOutNum++;
        map.put(tempString, lineOutNum);
        
        // every time cite readline() will read a line
      }
    }catch(Exception e){
      System.out.println("Not able to read file");
    }
    
    
    // Read Sample
    File sample = new File("src/main/resources/data/processed/sample.out");
    BufferedReader samReader = null;
    try{
      samReader = new BufferedReader(new FileReader(sample));
      // Content of each line
      String samString = "";
      while ((samString = samReader.readLine()) != null) {
        lineSampleNum++;
        if(map.get(samString) != null){
          lineMatch++; // number of match
        }
      }
    }catch(Exception e){
      System.out.println("Not able to read file");
    }
    
    System.out.println("Hitting number: "+lineMatch);
    System.out.println("Number of sample.out: "+lineSampleNum);
    System.out.println("Number of hw1-xzhan: "+lineOutNum);
    // The Precise
    accPer = (double) lineMatch / lineOutNum;
    // The recall
    recallPer = (double) lineMatch / lineSampleNum;
    
  }
  public double getAcc(){
    return accPer;
  }
  public double getRecall(){
    return recallPer;
  }
  public static void main(String []arg){
    AccTest acc = new AccTest();
    acc.AccuracyAndRecall();
    System.out.println("The precise of our system is " + acc.getAcc());
    System.out.println("The recall of our system is " + acc.getRecall());
  }
}
