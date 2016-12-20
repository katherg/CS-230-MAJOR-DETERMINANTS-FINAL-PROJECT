import java.util.*;
import java.io.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class InformationDriver {
  
  LinkedList<String> selected;
  private double[] percentageResult;
  private String[] nextCourses;
  
  public InformationDriver(LinkedList<String> courses) {
    selected = courses;
    percentageResult = new double[3];
    nextCourses = new String[3];
  }
  
  public void setUpGraph() {
    try {
    // creates course trajectories objects for each major
    CourseTrajectories csCT = new CourseTrajectories("csCourses.tgf");
    CourseTrajectories mathCT = new CourseTrajectories("mathCourses.tgf");
    CourseTrajectories masCT = new CourseTrajectories("masCourses.tgf");
    
    // creates sorted course objects 
    SortedCourses cs = new SortedCourses(csCT, selected);
    SortedCourses math = new SortedCourses(mathCT, selected);
    SortedCourses mas = new SortedCourses(masCT, selected);
    
    // sorts course based on major
    LinkedList<String> userCSCourses = cs.majorCompletedCourses();
    LinkedList<String> userMathCourses = math.majorCompletedCourses();
    LinkedList<String> userMASCourses = mas.majorCompletedCourses();
    
    // CS
    MajorCourseDeterminant mcdCS = new MajorCourseDeterminant(csCT, userCSCourses);
    mcdCS.allCSMath("CSRequired.txt");
    percentageResult[0] = mcdCS.csMajor();
      if(percentageResult[0] != 100.0) {
        nextCourses[0] = mcdCS.nextCourseMathCS();
      }  
    
    
    // Math
    MajorCourseDeterminant mcdMath = new MajorCourseDeterminant(mathCT, userMathCourses);
    mcdMath.allCSMath("MathRequired.txt");
    percentageResult[1] = mcdMath.mathMajor();
      if(percentageResult[0] != 100.0) {
        nextCourses[1] = mcdMath.nextCourseMathCS();
      }
    
  
    
    // MAS
    MajorCourseDeterminant mcdMAS = new MajorCourseDeterminant(masCT, userMASCourses);
    mcdMAS.allMAS("MASRequired.txt", "MASCS.txt", "MASARTS.txt");
      percentageResult[2] = mcdMAS.masMajor(userMASCourses);
      if(percentageResult[0] != 100.0) {
        nextCourses[2] = mcdMAS.nextCourseMAS();
      }
    
 
    } catch (FileNotFoundException e) {
      System.out.println ("The file was not found.");
    }
  }
 
   public double [] percentages() {
     return percentageResult;
   }
   
   public String [] nextCourse() {
     return nextCourses;
   }  
  }
