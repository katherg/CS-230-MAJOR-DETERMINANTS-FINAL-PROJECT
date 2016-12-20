/** 
 *this class contains the important  methods that are used
 * to calculate the next course and percentage of completion based off of 
 * the input
 * 
 * @ authors Xochitl Say and Hannah Weissman
 * 
 */

import java.util.*;
import java.io.*;
import java.util.Scanner;
import java.util.Stack;
import javafoundations.*;
import java.lang.*;

public class MajorCourseDeterminant {
  
  // instance variables
  
  private LinkedList<String> requiredCourses; // for math and cs
  private CourseTrajectories<String> major;
  private LinkedList<String> requiredMASIntroCourses;
  private LinkedList<String> requiredMASArtCourses;
  private LinkedList<String> requiredMASCSCourses;
  
  private LinkedList<String> userCourses;
  
  private final double numCSCoursesForMajor = 11.0;
  private final double numMathCoursesForMajor = 10.0;
  private final double numMASCoursesForMajor = 12.0;
  
  /**
   * constructor for class 
   * sets up an instance of the class based off of variables that will be used through out 
   * the rest of the class
   * 
   * @ param major                   the graph of the major
   * @ param completedCoursesByMajor the completed courses in that major field
   *
   */
  
  public MajorCourseDeterminant(CourseTrajectories<String> major, LinkedList<String> completedCoursesByMajor) {
    this.major = major;
    userCourses = completedCoursesByMajor;
    requiredCourses = new LinkedList<String>();
    requiredMASIntroCourses = new LinkedList<String>();
    requiredMASArtCourses = new LinkedList<String>();
    requiredMASCSCourses = new LinkedList<String>();
  }
  
  /**
   * Populates the linked list of required courses based on req for major
   * @ param requiredCoursesFile name of course containing requirements for that major
   */
  
  public void allCSMath(String requiredCoursesFile) {
    
    // fills requiredCourses
    try {
      Scanner scan = new Scanner(new File(requiredCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
        requiredCourses.add(nameArray[0]);
      }
      selectionSort(requiredCourses); // sorts requiredCourses by number
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + requiredCoursesFile + " was not found.");
    }
  }
  
  
  /**
   * Populates the linked list based off of 3 distinct requirements
   * @ param introCoursesFile intro course requirements
   * @ param csCourses        CS course requirements
   * @ param artCourses       art course requirements
   */
  
  public void allMAS(String introCoursesFile, String csCourses, String artCourses) {
    
    // fills introMASCourses
    try {
      Scanner scan = new Scanner(new File(introCoursesFile));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
        requiredMASIntroCourses.add(nameArray[0]);
      }
      selectionSort(requiredMASIntroCourses);
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + introCoursesFile + " was not found.");
    }
    
    // fills CS courses for MAS major
    try {
      Scanner scan = new Scanner(new File(csCourses));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String [] nameArray = name.split(":");
        requiredMASCSCourses.add(nameArray[0]);
      }
      selectionSort(requiredMASCSCourses);
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + csCourses + " was not found.");
    }
    
    // fills Arts courses for MAS major
    try {
      Scanner scan = new Scanner(new File(artCourses));
      while(scan.hasNext()) {
        String name = scan.nextLine();
        String[] nameArray = name.split(":");
        requiredMASArtCourses.add(nameArray[0]);
      }
      selectionSort(requiredMASArtCourses);
    }
    catch (FileNotFoundException e) {
      System.out.println ("The file " + artCourses + " was not found.");
    }
    
  }
  
  /**
   * Method calculates percentage of cs major completed
   * 
   * @ return double based off of cs major requirements
   *
   */
  public double csMajor(){
    
    if(userCourses.isEmpty()) {
      return 0.0;
    }
    
    //allCSMath("CSRequired.txt");
    
    double count = 0;
    double twoLevel= 0;
    double threeLevel = 0;
    double math = 0;
    double reqUpLvl = 3;
    if(!userCourses.contains("CS 111") && userCourses.contains("CS 230")){
      reqUpLvl = 4;
    }
    
    for(int i = 0; i< requiredCourses.size(); i++){
      
      if(userCourses.contains(requiredCourses.get(i))){
        count++;
      }
    }
    for(int i = 0; i< userCourses.size(); i++){
      if(threeLevel + twoLevel< reqUpLvl && !requiredCourses.contains(userCourses.get(i))){
        if (userCourses.get(i).contains("CS 3")){
          threeLevel++;
          count++;
        }else if((userCourses.get(i)).contains("CS 2")&& twoLevel< reqUpLvl - 2){
          twoLevel++; 
          count++;
        }
      }else if(math<1){
        math++;
        count++;
      }
    }
    double percentage = count/numCSCoursesForMajor;
    if(percentage > 1.0) {
      percentage = 1.0;
    }
    return (int)(percentage * 100);
  }
  
  /**
   * Method calculates percentage of math major completed
   * 
   * @return double based off of math major requirements
   *
   */
  
  public double mathMajor() {
    
    double count = 0;
    double twoLevel= 0;
    double threeLevel = 0;
    double math = 0;
    double reqUpLvl = 4;
    
    if(userCourses.get(0).equals("MATH 115")){
     count++; 
    }else if (userCourses.get(0).equals("MATH 116")||userCourses.get(0).equals("MATH 120")){
     count += 2; 
    }else if (userCourses.get(0).equals("MATH 205")){
      count+=3; 
     }
    for(int i = 0; i< requiredCourses.size(); i++){

      if(userCourses.contains(requiredCourses.get(i))){
       count++;
      }
    }
    for(int i = 0; i< userCourses.size(); i++){
      if(threeLevel + twoLevel< reqUpLvl && !requiredCourses.contains(userCourses.get(i))){
        if (userCourses.get(i).contains("MATH 3")){
          threeLevel++;
          count++;
        }else if((userCourses.get(i)).contains("MATH 2")){
        twoLevel++; 
        count++;
        }
      }
      }
    double percentage = count/numMathCoursesForMajor;
    if(percentage > 1.0) {
      percentage = 1.0;
    }
    return (int)(percentage * 100);
  }
  
  /**
   * Method calculates percentage of mas major completed
   * 
   * @return double based off of mas major requirements
   *
   */
  
  public double masMajor(LinkedList<String> mas) {
    
    if(mas.isEmpty()) {
      return 0.0;
    }
    
    selectionSort(mas); // sorts the courses in ascending order
    
    
    int intro = 0; // maximum is 4
    int artCourses = 0; 
    int csCourses = 0; 
    int num300s = 0;
    int required300s = 2; // the number of required 300s the user must take
    
    for(int i = 0; i < mas.size(); i++) {
      if(requiredMASIntroCourses.contains(mas.get(i)) && intro < 4) {
        intro++;
      }
      else if(requiredMASCSCourses.contains(mas.get(i)) && csCourses + artCourses < numMASCoursesForMajor - intro - required300s) {
        if(mas.get(i).contains(" 3")) {
          num300s++;
        }
        else {
          csCourses++;
        }
      }
      else if(requiredMASArtCourses.contains(mas.get(i)) && csCourses + artCourses < numMASCoursesForMajor - intro - required300s) {
        if(mas.get(i).contains(" 3")) {
          num300s++;
        }
        else {
          artCourses++;
        }
      }
    }
    double percentage = (intro + num300s + csCourses + artCourses)/numMASCoursesForMajor;
    if(percentage > 1.0) {
      percentage = 1.0;
    }
    return (int)(percentage * 100);
  }
  
  /**
   * Helper method sorts incoming data in ascending order
   * 
   * @param linkedlist containing course names
   */
  
  private void selectionSort (LinkedList<String> data) {
    int min;
    for (int index = 0; index < data.size()-1; index++) {
      min = index;
      for (int scan = index+1; scan < data.size(); scan++) {
        if (data.get(scan).compareTo(data.get(min)) < 0) {
          min = scan;
        }
      }
      swap (data, min, index);
    }
  }
  
  /**
   * Helper method for selectionSort
   * @ param data   linkedlist of courses
   * @ param index1 index that needs to be switched
   * @ param index2 index to switch with
   * 
   */
  
  private void swap (LinkedList<String> data, int index1, int index2) {
    String index1Data = data.get(index1);
    String index2Data = data.get(index2);
    data.remove(index1);
    data.add(index1, index2Data);
    data.remove(index2);
    data.add(index2, index1Data);
  }
  
  /**
   * Method that goes through the selected/separated courses and determines what courses are left to take 
   * then it returns the courses left to take, giving priority to those that fulfill requirements.
   * 
   * @ return string subject and number of course
   */
  public String nextCourseMathCS() {
    
    CourseTrajectories<String> temp = major;
    LinkedList<String> req = requiredCourses;//both temp
    LinkedList<String> selected = userCourses;//both temp
    String course= "";
    req = adjReqMet();//updates to check if any courses selected were skipped
    
    while(!selected.isEmpty()){
      String current = selected.removeFirst();
      
      if(req.contains(current)){
        req.remove(current); 
      }
      
      temp.removeVertex(current);
    }
    
    if(!req.isEmpty()){
      LinkedList<String> canTake = major.allSources();
      for(int i = 0; i< canTake.size(); i++){
        
        if(req.contains(canTake.get(i))){
          course = canTake.get(i);
          
          break;
        }
      }
      
    }else{
      course = temp.allSources().pop();
    }

    return course;
  }
  
  /**
   * Method that return linked list which adjust requirement based off 
   * special cases where student skips courses that are prerequisites and major
   * requirements
   * 
   * @ return update linked list of requirements that are left
   */
  
  public LinkedList<String> adjReqMet(){
    LinkedList<String> adjustedReq = requiredCourses;
    for(int i = 0; i<userCourses.size(); i++){
      LinkedList<String> preReq = (major.getPredecessors(userCourses.get(i)));//gets predecessors of requirement if it's skipped(for special cases)
      if(!preReq.isEmpty()){
        for(int j =0; j<preReq.size(); j++){
          if(requiredCourses.contains(preReq.get(j)) && !userCourses.contains(preReq.get(j))){
            major.removeVertex(preReq.get(j));
            requiredCourses.remove(preReq.get(j));
          }
          
        }
      }
    }
    return adjustedReq;
  }
  
  /**
   * Method that handles mas major differently because of different course requirements
   * @ return next course as string
   */
  
  public String nextCourseMAS() {   CourseTrajectories<String> temp = major;
    
    LinkedList<String> selected = userCourses;//both temp
    String course= "";
    
    while(!selected.isEmpty()){
      String current = selected.removeFirst();
      
      temp.removeVertex(current);
    }
    
    LinkedList<String> canTake = temp.allSources();
    
    course = canTake.pop();
    
    return course;
  }
}
