/** 
 * SelectCoursesGUI is the class that creates the GUI the user interacts with.
 * It creates three CoursePanel objects each displaying either 100, 200, or 300 level courses
 * within CS, Math, and MAS majors. The three objects are placed next to each 
 * other in a border layout format so that the user is able to see his or her options
 * at once. After the user selects a course, the name of the course he or she has 
 * selected is displayed at the bottom of the screen in a text area. Both the 
 * list of courses and the text area are scrollable in case the user has 
 * taken many classes. Once the user is finished selecting the courses he or she
 * has taken, he or she will click the Next button, which will then prompt a dialog
 * box to pop up informing the user of the percentage of each major he or she has completed
 * and a suggested next course for each major.
 * 
 * We assume that the user is an average Wellesley College student, i.e. they will not 
 * have skipped a majority of major requirements. For example, we take into account the situation
 * in which they have taken up to multivariable calculus prior to coming to Wellesley, however,
 * we do not take into account the situation in which they have taken anything beyond this 
 * (i.e. Linear Algebra) because this would be a relatively rare situation. We also assume
 * that the user would not select courses they have not taken. 
 * 
 * @ author Katherine Gao
 * 
 * @ version 1.0 
 * 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class SelectCoursesGUI extends JPanel {
  
  private JButton nextButton;
  private JPanel questionPanel;
  private JPanel buttonPanel;
  private JLabel whichOneCourses;
  private JLabel whichTwoCourses;
  private JLabel whichThreeCourses;
  private LinkedList<String> completedCourses;
  private CoursePanel oneHundred;
  private CoursePanel twoHundred;
  private CoursePanel threeHundred;
  private double [] percentages;
  private String [] suggestions;
  
  
  /** 
   * Sets up the GUI that the user will interact with. Creates three CoursePanel
   * objects from text files containing the 100, 200, and 300 level courses
   * that count for the CS, Math, and MAS majors. Uses a border layout format 
   * manager. Creates three JLabels to instruct the user to choose all 100, 200,
   * and 300 level courses he or she has taken. Creates the submit button that 
   * the user will click on after he or she has selected all of the courses he or
   * she has taken. 
   * 
   * @ param one name of the text file containing 100 level classes
   * @ param two name of the text file containing 200 level classes
   * @ param three name of the text file containing 300 level classes
   * 
   */
  
  public SelectCoursesGUI(String one, String two, String three) {
    // set layout of the panel:
    setLayout(new BorderLayout());
    
    // 
    oneHundred = new CoursePanel(one);
    oneHundred.setPreferredSize(new Dimension(400,200));
    twoHundred = new CoursePanel(two);
    twoHundred.setPreferredSize(new Dimension(400,200));
    threeHundred = new CoursePanel(three);
    threeHundred.setPreferredSize(new Dimension(400,200));
    
    questionPanel = new JPanel();
    questionPanel.setPreferredSize(new Dimension(50, 50));
    questionPanel.setLayout(new GridLayout(1,3));
    whichOneCourses = new JLabel("Select all 100 level classes you have taken", whichOneCourses.CENTER);
    whichTwoCourses = new JLabel("Select all 200 level classes you have taken", whichTwoCourses.CENTER);
    whichThreeCourses = new JLabel("Select all 300 level classes you have taken", whichThreeCourses.CENTER);
    questionPanel.add(whichOneCourses);
    questionPanel.add(whichTwoCourses);
    questionPanel.add(whichThreeCourses);
    
    buttonPanel = new JPanel();
    buttonPanel.setPreferredSize(new Dimension(25,40));
    nextButton = new JButton("Next");
    nextButton.addActionListener(new ButtonListener());
    buttonPanel.add(nextButton);
    
    add(oneHundred, BorderLayout.WEST);
    add(twoHundred, BorderLayout.CENTER);
    add(threeHundred, BorderLayout.EAST);
    add(questionPanel, BorderLayout.NORTH);
    add(buttonPanel, BorderLayout.SOUTH);
  }
  
  /**
   * Declares an event handler class that specfies that the class 
   * implements an ActionListener interface 
   */
  
  private class ButtonListener implements ActionListener {
    
    /**
     * Implements the methods in the listener interface. Uses the 
     * getTakenCourses() method from CoursePanel class to get a 
     * linked list of the 100 level classes, 200 level classes, and 
     * 300 level classes. Compiles these lists into one linked list
     * and splits the names of the classes so that only the course 
     * number is stored in the list. Uses the linked list to call methods
     * from the InformationDriver class which will calculate the percentage
     * completed of each major and suggest a next course in each major.
     */
    
    public void actionPerformed(ActionEvent event) {
      completedCourses = new LinkedList<String>();
      if (event.getSource() == nextButton) {
        LinkedList<String> first = oneHundred.getTakenCourses();
        LinkedList<String> second = twoHundred.getTakenCourses();
        LinkedList<String> third = threeHundred.getTakenCourses();
        for (int i=0; i<first.size(); i++){
          String class1 = first.get(i);
          String [] class1Array = class1.split(":");
          completedCourses.add(class1Array[0]);
        }
        for (int j=0; j<second.size(); j++) {
          String class2 = second.get(j);
          String [] class2Array = class2.split(":");
          completedCourses.add(class2Array[0]);
        }
        for (int k=0; k<third.size(); k++) {
          String class3 = third.get(k);
          String [] class3Array = class3.split(":");
          completedCourses.add(class3Array[0]);
        }
        InformationDriver information = new InformationDriver(completedCourses);
        information.setUpGraph();
        percentages = information.percentages();
        suggestions = information.nextCourse();
        AllResultsDialog results = new AllResultsDialog(percentages, suggestions);
      }
    }
  }

  
  /** 
   * Driver method to run the program
   */
  
  public static void main(String [] args) {
    SelectCoursesGUI allCourses = new SelectCoursesGUI("ALL100.txt", "ALL200.txt", "ALL300.txt"); 
    JFrame frame = new JFrame("Choose your Major");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(allCourses);
    frame.pack();
    frame.setSize(1200, 600);
    frame.setVisible(true);
  }
}