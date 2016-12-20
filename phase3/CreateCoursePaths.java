
import java.util.*;
import java.io.*;

public class CreateCoursePaths {
  
  public static void main(String [] args) throws FileNotFoundException{
    
    // create graph objects
    CourseTrajectories csCT = new CourseTrajectories("csCourses.tgf");
    CourseTrajectories mathCT = new CourseTrajectories("mathCourses.tgf");
    CourseTrajectories masCT = new CourseTrajectories("masCourses.tgf");
    
    //csCT.fromTGF("csCourses.tgf");
    System.out.println(csCT);
    System.out.println(mathCT);
    System.out.println(masCT);
    
    
    
//    // CS courses offered
//    String[] CSCourses = {"CS111", "CS112", "CS114" ,"CS115", "CS117", "CS125", "CS215", "CS220", "CS230", "CS231",
//                          "CS232", "CS235", "CS240", "CS242", "CS24901", "CS24902", "CS251", "CS301", "CS304", 
//                          "CS307", "CS310", "CS313", "CS315", "CS320", "CS332", "CS342", "CS34901", "CS34902"};
//    
//    // Math courses offered
//    String[] MathCourses = {"Math101", "Math115", "Math116", "Math120", "Math203", "Math205", "Math206", "Math210", "Math214",
//                            "Math215", "Math220", "Math221", 
//                            "Math223", "Math225", "Math249", "Math302", "Math303",  "Math305", "Math306", "Math307", "Math309",
//                            "Math310", "Math312", "Math322", "Math325", "Math326", "Math340", "Math349"};
//    
//    //MAS courses offered in Spring 2017
//    // this list can be changed every semester to reflect the courses being offered that semester
//    String[] MASCourses = {"ARTH101", "CAMS101", "ARTS108", "ARTS109", "ARTS165", "ARTS208", "ARTS221", "ARTS255", "ARTS260", 
//                           "ARTS265", "MUS275", "ARTS313", "ARTS317", "ARTS321", "ARTS322", "ARTS365", };
//    

}
}