
/**
 * This class is used to set up different major dependencies
 * Each graph represents course dependencies
 * 
 * 
 * */

import java.io.*;
import java.util.*;
import javafoundations.*;

public class CourseTrajectories<T> {
  AdjMatGraphPlus<String> graph;

  
  public CourseTrajectories(String tgfFile) throws FileNotFoundException {
    graph = new AdjMatGraphPlus<String>();
    graph = graph.fromTGF(tgfFile);

  }
    
   /*
   * just checks if the major dependency is populated
   * @return boolean true or false if graph is full or empty
   * */
  public boolean isEmpty(){
   return graph.isEmpty(); 
  }
   
     /*
   * method used to add courses to major depedency graph 
   * uses adj mat graph 
   * @param vertex takes in the name of the course
   * 
   * 
   * */
  public void addVertex (String vertex) {
    graph.addVertex(vertex);
  }

    
  /*
   * method takes a course away 
   * @param takes in the name of the image 
   * 
   * */
  public void removeVertex (String vertex) {
    graph.removeVertex(vertex);
  }

    /*
   * @param vertex takes in the name of vertex to find
   * @return the nodes that it points to in the graph
   * */
   public LinkedList<String> getSuccessors(String vertex) {
     return graph.getSuccessors(vertex);
   }
 /*
   * @param vertex takes in the name of vertex to find what comes before it 
   * @return the nodes that point to in the graph
   * */
   public LinkedList<String> getPredecessors(String vertex) {
     return graph.getPredecessors(vertex);
   }
   
  
   public AdjMatGraphPlus<String> fromTGF(String tgfFile) throws FileNotFoundException {
     return graph.fromTGF(tgfFile);
   }
/***
     * 
     * 
     * @return boolean based off of it it finds the course in the graph
     * */
   public boolean containsCourse(String vertex) {
     return graph.containsVertex(vertex);
   }
/*
    * method used to find courses without prerequisites
    * @returns linkedList of all courses
    * */
  public LinkedList<String> allSources() {
    return graph.allSources();
    }
      /** @return a string representation of graph  */
    public String toString() {

      return graph.toString();

  }
 

}
