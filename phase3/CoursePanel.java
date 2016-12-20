/** 
 * Course Panel is a helper class that creates a panel of a list of 
 * courses along with a text field containing names of courses selected
 * by a user from the list. 
 * 
 * @ author Katherine Gao
 * 
 * @ version 1.0 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.util.*;
import java.io.*;

public class CoursePanel extends JPanel{
  
  private DefaultListModel courseNames;
  private JList courseList;
  private JScrollPane courseListScrollPane;
  private JScrollPane takenCoursesScrollPane;
  private JTextArea takenCourses;
  private LinkedList<String> taken;

  /** Constructor which creates a panel that, given a file of courses,
   * will display course names in a list on a scroll panel. 
   * When user clicks submit button, courses selected
   * are shown in a text field at the bottom of the screen.
   * 
   * @ param major the name of the file of course names 
   */
 
  public CoursePanel(String major) {
    
    taken = new LinkedList<String>();
    
    // set layout of panel to box layout so that the course names
    // will be stacked on top of each other in a column
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

    // use file to string helper method to place all course names
    // into a linked list
    LinkedList<String> classes = fileToString(major);
    
    // add each course name to a default list model so that it can
    // be placed into a JList:
    courseNames = new DefaultListModel();
    for (int i=0; i<classes.size(); i++) {
      courseNames.addElement(classes.get(i));
    }
    courseList = new JList(courseNames); 
    
    // set selection mode to multiple selection so that the user
    // can choose more than one class he or she has taken from the list
    courseList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    
    // add an action handler to the courseList so that it will be able to
    // monitor the user's clicks
    courseList.addListSelectionListener(new SharedListSelectionHandler());
    
    // instantiate the JTextArea and make it so that the user
    // is unable to edit the text 
    takenCourses = new JTextArea(1, 50);
    takenCourses.setEditable(false);

    // put the course list and selected courses onto a scroll pane
    // in case there are more courses that show up or are selected 
    // than the panel displays
    courseListScrollPane = new JScrollPane(courseList);
    takenCoursesScrollPane = new JScrollPane(takenCourses);
    
    // put the scroll panes onto a split pane so the user can see both
    // the course options and the courses he or she has chosen at the same 
    // time and choose how much of each her or she can see (by moving the divider)
    JSplitPane splitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
                               courseListScrollPane, takenCoursesScrollPane);
    splitPane.setOneTouchExpandable(true);
    splitPane.setDividerLocation(300);
    
    //Provide minimum sizes for the two components in the split pane
    Dimension minimumSize = new Dimension(50, 50);
    courseListScrollPane.setMinimumSize(minimumSize);
    takenCoursesScrollPane.setMinimumSize(minimumSize);

    add(splitPane);
    
  }
  
  /** Internal class to define the listener attached the the JList
    */
  
  private class SharedListSelectionHandler implements ListSelectionListener {
    public void valueChanged(ListSelectionEvent e) { 
      // add the course that the user clicks on to the text area below the 
      // list of courses, but only if the course has not already been selected by
      // the user
      if (!e.getValueIsAdjusting()) {
        JList source = (JList)e.getSource();
        String selected = source.getSelectedValue().toString();
        if (inList(taken, selected) == false) {
          takenCourses.append(selected);
          taken.add(selected);
        }
      }
      takenCourses.append("\n");
      takenCourses.setCaretPosition(takenCourses.getDocument().getLength());
    }
  }
  
  /** Method that returns the course numbers of the 
    * courses the user has selected in a linked list
    * 
    * @ return a linked list of all selected courses
    */
  
  public LinkedList<String> getTakenCourses() {
    return taken;
  }
  
  /** 
   * Private helper method that determines whether or not a given object is 
   * in the given linked list. Returns true if it is and false if it is not
   * 
   * @ param list the linked list we are searching in
   * @ param item the item to search the list for
   * 
   * @ returns true if the item is in the list and false if it is not
   * 
   */
  
  private boolean inList(LinkedList<String> list, String item) {
    boolean found = false;
    for (int i=0; i<list.size(); i++) {
      if (list.get(i).equals(item)) {
        found = true;
      } 
    }
    return found;
  }
  
  /** Private helper method to read a file in and convert it to a linked list 
   * of strings. This is used in the constructor to load all courses into a JList
   * 
   * @ param fileName the string name of the file 
   * @ return         a linked list with each line of the read in file as a 
   *                  node 
   */
  
  private static LinkedList<String> fileToString(String fileName) {
    LinkedList<String> list = new LinkedList<String>(); 
    try {
      Scanner scan = new Scanner(new File(fileName));
      while (scan.hasNext()){
        String line = scan.nextLine();
        list.add(line);
      }
      return list;
    } catch (FileNotFoundException ex) {
      System.out.println(ex);
      return list;
    }
  }
  

  /**
   * Driver method to test that the panel creation works
   */
  
  public static void main(String [] args) {
    CoursePanel test = new CoursePanel("ALL100.txt");
    JFrame frame = new JFrame("Choose your Major");
    LinkedList<String> list = test.getTakenCourses();
    //System.out.println(list.get(2));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(test);
    frame.pack();
    frame.setSize(550, 500);
    frame.setVisible(true);
  }
  
  
}