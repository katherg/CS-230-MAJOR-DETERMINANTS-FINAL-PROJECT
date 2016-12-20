/** 
 * ResultsPanel is the class that creates the panel that displays the 
 * percentage of the major the user has completed and a suggested next
 * course in each major.
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

public class ResultsPanel extends JPanel {
  
  private JLabel percentageCompleted;
  private JLabel suggestion;
  private JButton quitButton;
  
  /**
   * Constructor that takes in the percentage of the major that is completed,
   * the sugggested next course, and the name of the major that this 
   * information applies to and creates one JLabel with the percentage of major
   * completed as its message and another JLabel with the suggested next course as 
   * its message. 
   * 
   * @ param percentage the percentage of the major the user has completed
   * @ param nextCourse the suggested next course the user should take
   * @ param major      the major that this information applies to
   *
   */
  
  public ResultsPanel(double percentage, String nextCourse, String major) {
    // set layout manager of the panel so that it will display the information
    // in a nice way
    setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    if (percentage != 100.00) {
      percentageCompleted = new JLabel("You are " + percentage + "% done with the " +
                                     major + " major!");
      suggestion = new JLabel("To continue in this major, we suggest you take "
                              + nextCourse + " next.");
    } else {
      percentageCompleted = new JLabel("You are finished with the " + major + " major! Celebrate!");
      suggestion = new JLabel("Go get your diploma!");
    }
    
    
    // Make font size of the message larger for excitement. Even if the user has 
    // only completed a little bit of the major it is exciting!
    percentageCompleted.setFont(new Font("Sans-Serif", Font.PLAIN, 20));
    // Center the JLabels on the panel 
    percentageCompleted.setAlignmentX(Component.CENTER_ALIGNMENT);
    suggestion.setAlignmentX(Component.CENTER_ALIGNMENT);
    add(percentageCompleted);
    add(suggestion);
  }
  
  /** 
   * Driver method to test this class
   */
  
  public static void main(String [] args) {
    ResultsPanel test = new ResultsPanel(100, "Math 225: Combinatorics and Graph Theory", "CS");  
    JFrame frame = new JFrame("Results");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.getContentPane().add(test);
    frame.pack();
    frame.setSize(650, 100);
    frame.setVisible(true);
  }
}