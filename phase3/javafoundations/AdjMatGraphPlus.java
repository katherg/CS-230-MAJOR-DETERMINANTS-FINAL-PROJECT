/*Xochitl Say
 * cs230
 * exam 3
 * */

import java.io.FileNotFoundException;

import java.util.LinkedList;
import java.util.Iterator;
import javafoundations.LinkedQueue;
import java.util.Stack;

/**
 * An extended Adjacency Matrix graph implementation.  Complete this template for the exam.
 */
public class AdjMatGraphPlus<T> extends AdjMatGraph<T> implements GraphPlus<T> {
 
  /** DO NOT MODIFY THE FIRST THREE METHODS **************************
    * The methods you will implement follow below.
    */
  
  /**
   * Construct an empty graph.
   */
  public AdjMatGraphPlus() {
    super();
  }
  
  /**
   * Construct a graph with the same vertices and edges as the given original.
   * @param original
   */
  public AdjMatGraphPlus(AdjMatGraph<T> original) {
    super(original);
  }
  
  /**
   * Read a TGF file and create an AdjMatGraphPlus<String> from it.
   * @param tgfFile - the TGF file to read
   * @return a graph created from the TGF file
   * @throws FileNotFoundException if TGF file is not found.
   */
  public static AdjMatGraphPlus<String> fromTGF(String tgfFile) throws FileNotFoundException {
    AdjMatGraphPlus<String> g = new AdjMatGraphPlus<String>();
    AdjMatGraph.loadTGF(tgfFile, g);
    return g;
  }
  
  
  
  /**** IMPLEMENT THE METHODS BELOW *********************************
    * Replace "throw new UnsupportedOperationException();" with
    * a working implementation.
    ******************************************************************/
  
  
  //CLONE METHOD
  public GraphPlus<T> clone() {
    //returns new graph using second constructing in AdjMatGraphPlus
    return new AdjMatGraphPlus<T>(this);
  } 

  //IS SINK METHOD
  public boolean isSink(T vertex) {
    // checks if vertex exists
    if (super.getIndex(vertex)==NOT_FOUND) {
      throw new IllegalArgumentException("Vertex is not in graph");
    }
    //if has no successors then getSuccessors will return an empty list
    //if empty= true a sink else it points to other vertices = false
    return ((super.getSuccessors(vertex)).isEmpty());
    
  }

  //ALL SINKS METHOD
  public LinkedList<T> allSinks() {
    Iterator<T> vertices = super.iterator();//calls vertices iterator so we can traverse all vertices in graph 
    LinkedList<T> sinks = new LinkedList<T>();//list that will hold all sinks
    
    while(vertices.hasNext()){//goes through iterator
      T currentVertex = vertices.next();
      
      if(isSink(currentVertex)){//checks if vertex is sink 
        sinks.add(currentVertex); //adds to list 
      }
    }
    return sinks;
    
  }

  //IS SOURCE SINK
  public boolean isSource(T vertex) {
    
    //checks if vertex exists
    if (super.getIndex(vertex)==NOT_FOUND) {
      throw new IllegalArgumentException("Vertex is not in graph");
    }
    //if vertex is source then linked list returned by getPredecessors will be empty
    //size of linked list dependent on number of predecessors
    //no predecessors means empty list returned & isSource returns true
    return ((super.getPredecessors(vertex)).isEmpty());
    
  }

  //ALL SOURCES
  public LinkedList<T> allSources() {
    Iterator<T> vertices = super.iterator();//calls parent iterator method for vertices
    LinkedList<T> sources = new LinkedList<T>();//list that will hold sources in graph 
    
    //goes through vertices and checks if vertex is source and adds to list if true
    while(vertices.hasNext()){
      T currentVertex = vertices.next();
      
      if(isSource(currentVertex)){
        sources.add(currentVertex); 
      }
    }
    return sources;
  }

 //IS ISOLATED METHOD
  public boolean isIsolated(T vertex) {
    
    if (super.getIndex(vertex)==NOT_FOUND) {
      throw new IllegalArgumentException("Vertex is not in graph");
    }
    
    return (isSink(vertex) && isSource(vertex));//RETURNS TRUE IF VERTEX IS SINK AND SOURCE OTHERWISE FALSE
    
  }

  //IS ISOLATED METHOD
  public LinkedList<T> listByPriority() throws GraphCycleException{
    LinkedList<T> list = new LinkedList<T>();//list that will hold vertices
    GraphPlus<T> temp = this.clone();//clone graph so that original graph will remain in tact when removing vertices
    
    //checks if graph is non DAG
    if((this.allSources()).size()==0){
      throw new GraphCycleException(); 
    }
    
    //loops while the list does not contain the original num of vertices
    while(list.size()!=super.n()){
      //started with first in source list and all sources is repeatedly updated every loop
      T source = temp.allSources().getFirst();//gets first source in list 
      //
      temp.removeVertex(source);//removes it from the clone graph 
      list.add(source);//adds to linked list
    }
    return list;
    
  }

  
  public LinkedList<T> dfsTraversal(T startVertex) {
    LinkedList<T> list = new LinkedList<T>();
    Stack<T> stack = new Stack<T>();
    boolean found;//keeps track of whether or not should pop stack or not
    
    if (super.getIndex(startVertex)==NOT_FOUND) {
      throw new IllegalArgumentException("Vertex is not in graph");
    }
    
    stack.push(startVertex);
    list.add(startVertex);//bc if it's in list already implies that it's visited
    
    //runs while the stack with the vertices is not empty 
    while(!stack.isEmpty()){
      T current = stack.peek(); //used as reference to where in graph it is
      found = false;//sets to false bc it has not yet reached a vertex that 
      
      //gets the successors of current to go through graph
      LinkedList<T> successors = getSuccessors(current);
      
      while(!successors.isEmpty()){
        T currentSuccessor = successors.removeFirst();
        //adds to list if vertex in not yet in list/hasn't been added to list
        if(!list.contains(currentSuccessor) && !found){
          stack.push(currentSuccessor);//adds to stack, which will update current 
          list.add(currentSuccessor);
          found = true;
        }
      }
      //if no more successors to visit then start going back to original vertex means it's found a path so being popping
      if(!found && !stack.isEmpty()){
        stack.pop(); 
      }
    }
    
    return list;
    
  }

  public LinkedList<T> bfsTraversal(T startVertex) {
    LinkedList<T> list = new LinkedList<T>();//list that will keep track of vertices
    LinkedQueue <T> q = new LinkedQueue<T>();//queue for vertices of vertex it will dequeue
    
    
    boolean[] visited = new boolean[super.n()];//keeps track of visit vertices to not add them to list or enqueue them
    //if vertex doesn't exist
    if (super.getIndex(startVertex)==NOT_FOUND) {
      throw new IllegalArgumentException("Vertex is not in graph");
    }
    //sets up all in visited to false
    for(boolean vertex: visited){
      vertex =false; 
    }
    //adds given vertex to queue and marks as visited
    q.enqueue(startVertex);
    visited[getIndex(startVertex)]= true;
    
    //while the queue is populated means there are still vertices left to add to list
    while(!q.isEmpty()){
      
      T current = q.dequeue();//keeps track of the first in queue 
      list.add(current);//adds to the list
      
      LinkedList<T> successors = getSuccessors(current);//updates according to current and gets vertices it points to
      
      //populates queue with the sucessors of what's first in the queue which allows for bfs and to get to all vertices in graph 
      while(!successors.isEmpty() ){
        T currentSuccessor = successors.removeFirst();//gets first successors
        if(!visited[getIndex(currentSuccessor)]){//checks if it's been visited, if it hasn't means it hasn't been added to queue
          q.enqueue(currentSuccessor);//adds to queue if not yet in it 
          visited[getIndex(currentSuccessor)] = true;
        }
      }
    }
  
   return list;
 }
 public static void main (String[] args){

    GraphPlus<String> G = new AdjMatGraphPlus<String>();

    G.addVertex("A"); G.addVertex("B"); G.addVertex("C");
    G.addVertex("D"); G.addVertex("E"); G.addVertex("F");
    
    G.addArc("A", "C");
    G.addArc("A", "B");
    G.addArc("B", "D");
    
    G.addArc("C", "E");
    System.out.println(G);

    System.out.println(G.clone());
    System.out.println(G.allSinks());
    System.out.println(G.allSources()); 
    try{ 
   System.out.println(G.listByPriority()); 
    }catch(GraphCycleException e){
     System.out.println("not a non dag"); 
    }
     System.out.println(G.dfsTraversal("B")); 
     System.out.println(G.bfsTraversal("B"));

 }
}