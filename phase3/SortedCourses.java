import java.util.*;

public class SortedCourses{
  
  /**
   * Sorts the user's course by identifying which major(s) each course 
   * counts toward
   * to determine this we compare each course with the 3 major graphs
   * if a course is found to be in a major graph it gets added to a linked list
   * this linked listed contains all the course a user has taken that count towards a certain major 
   */
  
  CourseTrajectories<String> graph;
  
  // instance variables 
  LinkedList<String> selected;
  
  public SortedCourses(CourseTrajectories<String> g1, LinkedList<String> selected){
    graph = g1;
    this.selected = selected;
  }
  
  /**
  * populates linked list with courses user has taken
  * @return the linkedlist of courses for a particular major
  */
  public LinkedList<String> majorCompletedCourses(){
    LinkedList<String> result = new LinkedList<String>();
    for(int i = 0; i<selected.size(); i++){
     if(graph.containsCourse(selected.get(i))){
       result.add(selected.get(i));
     }
    }
    return result; 
  }
  
}
