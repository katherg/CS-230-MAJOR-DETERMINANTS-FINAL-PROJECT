/** 
 * AllResultsDialog is the class that creates the final dialog box that will 
 * pop up notifying the user the percentage of the CS, Math, and MAS majors
 * he or she has completed along with a suggested next course in each major.
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

public class AllResultsDialog extends JDialog {
  
  private ResultsPanel csResults;
  private ResultsPanel mathResults;
  private ResultsPanel masResults;
  private JPanel container;
  private JPanel buttonPanel;
  private JButton quitButton;
  
  
  /**
   * Constructor that creates a dialog box that will pop up at the 
   * end after the user has selected the courses he or she has taken
   * in each major. Creates three ResultsPanel objects, one for CS, Math,
   * and MAS. It places these onto a JPanel to be displayed in the dialog box.
   * Creates a quit button that is placed at the bottom of the dialog box. 
   * When the quit button is clicked by the user, the dialog box closes.
   * 
   * @ param parent      the frame that the dialog box is attached to
   * @ param percentages an array of the percentages completed of each major
   * @ param suggestions an array of the suggested next course in each major
   * 
   */
  
  public AllResultsDialog(double[] percentages, String[] suggestions) {
    Point p = new Point(200,200);
    setLocation(p.x,p.y);
    
    // Create the label and message that will show up on the dialog box:
    container = new JPanel();
    container.setLayout(new FlowLayout());
    csResults = new ResultsPanel(percentages[0], suggestions[0], "CS");
    mathResults = new ResultsPanel(percentages[1], suggestions[1], "Math");
    masResults = new ResultsPanel(percentages[2], suggestions[2], "MAS");
    // Add the messages to the label
    container.add(csResults);
    container.add(mathResults);
    container.add(masResults);
    // Add label to dialog box
    getContentPane().add(container);
    
    // Create the quit button
    buttonPanel = new JPanel();
    JButton quitButton = new JButton("Close");
    buttonPanel.add(quitButton);
    
    // Set action listener on the button
    quitButton.addActionListener(new MyActionListener());
    getContentPane().add(buttonPanel, BorderLayout.PAGE_END);
    setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    pack();
    setVisible(true);

  }
  
  
  /**
   * An action listener to be used when the button is pressed
   * 
   */
  
  private class MyActionListener implements ActionListener {
    
    /**
     * Close and dispose of the window.
     */
    
    public void actionPerformed(ActionEvent e) {
      setVisible(false);
      dispose();
    } 
  }
  
  /** 
   * Driver method to test this class
   */
  
  public static void main(String [] args) {
    double[] percentages = {25,100,75};
    String[] suggestions = {"CS 240", "Math 225", "ARTS 301"};
    JFrame frame = new JFrame("All Results");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    AllResultsDialog test = new AllResultsDialog(percentages, suggestions);
    frame.pack();
    frame.setSize(600, 180);
    frame.setVisible(true);
  }
}