
/**
 *******************************************************************
 * All code contained within is released under the GNU v3 license
 *
 * Please respect and support open source software and it's creators
 *
 * Project Name - Academic Manager, AcademicManager.java
 *  
 *
 *
 * Initial Creation Date is ${date} Written by Josh Cannons
 * 
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

//  class definition
public class AcademicManager extends JFrame {

// array declarations
    String[] names = {"David Johns", "Mick Hacks", "Will North", 
                     "Linda Purr", "Amy Condor", "Bob Connor", 
                     "Josh Isman", "Tracey Gubb", "Helen Laffy", 
                     "Nick Mint", "Mark Malow", "Kate Platt"};
    int[] scoreDS = {27, 74, 84, 93, 64, 25, 23, 94, 85, 94, 56, 32};
    int[] scoreJP = {43, 23, 86, 97, 86, 68, 59, 70, 58, 58, 98, 64};
    int[] scoreSA = {97, 43, 23, 66, 45, 67, 45, 34, 54, 34, 43, 72};
// gui declarations
    private JButton sortByNameButton;
    private JButton sortByJPButton;
    private JButton statisticsButton;
    private JButton exitButton;
    private JTextField searchField;
    private final JTextArea textArea;
    
    // Constructor
    public AcademicManager() {
        
        // initialise components
        setTitle("Academic Manager");
        setLayout(new FlowLayout());
        textArea = new JTextArea(18, 50);
        sortByNameButton = new JButton("Sort by Name");
        sortByJPButton = new JButton("Sort by JP");
        statisticsButton = new JButton("Statistics");
        searchField = new JTextField ("search", 12);
        exitButton = new JButton("Exit");

        // add components to frame
        add(textArea);
        add(sortByNameButton);
        add(sortByJPButton);
        add(statisticsButton);
        add(searchField);
        add(exitButton);

        // register event handlers
        DoListener handler = new DoListener();
        sortByNameButton.addActionListener(handler);
        sortByJPButton.addActionListener(handler);
        statisticsButton.addActionListener(handler);
        exitButton.addActionListener(handler);
        searchField.addActionListener(new Listener());

        // call method displayResultData
        displayResultData();
    }

    private class Listener implements ActionListener {
            
        public void actionPerformed(ActionEvent evt) {
            
            String sName = searchField.getText();
                      
            int results = search(sName);
            
            if (results > -1) {
            String message = String.format("Student Name: %s\nJava Programming:"
                    + " %d\nDatabase Systems: %d\nSystems Analysis: %d", 
                    names[results], scoreJP[results], scoreDS[results], 
                    scoreSA[results]);
            JOptionPane.showMessageDialog(null, message);
            }     
            else if (results == -1) {   
            String errMsg = String.format("%s is not a valid student for this "
                    + "course.\nPlease re-check your spelling", sName);
            JOptionPane.showMessageDialog(null, errMsg);        
            }
        }
    }
    
    //private class for event handling
    private class DoListener implements ActionListener {

        public void actionPerformed(ActionEvent event) {
            String action = event.getActionCommand();
            Object source = event.getSource();
            if (action.equals("Sort by Name")) {
                sortByName();
            }
            else if (action.equals("Sort by JP")) {
                sortByJP();
            }
            else if (action.equals("Statistics")) {
                statistics();
            }
            else if (action.equals("Exit")) {
                System.exit(1);
            }
        }
    }

    // sort by student name; implements bubble sort
    private void sortByName () { 
        boolean run = true;  // will determine when the sort is finished
        String temporary;
        int temporary1;
        int temporary2;
        int temporary3;

       while (run) {
           run = false;
           for ( int i = 0;  i < names.length - 1;  i++ ) {
               if ( names[i].compareToIgnoreCase (names[i+1]) > 0) {
                   temporary = names[i];
                   names[i] = names[i+1];
                   names[i+1] = temporary;
                   temporary1 = scoreJP[i];
                   scoreJP[i] = scoreJP[i+1];
                   scoreJP[i+1] = temporary1;
                   temporary2 = scoreSA[i];
                   scoreSA[i] = scoreSA[i+1];
                   scoreSA[i+1] = temporary2;
                   temporary3 = scoreDS[i];
                   scoreDS[i] = scoreDS[i+1];
                   scoreDS[i+1] = temporary3;
                   run = true;
               }
           }
       }
       displayResultData();
    }                      

    // sort results by java programming scores; implements selection sort and 
    // calls swap
    private void sortByJP() {
         for (int i = 0; i < scoreJP.length - 1; i++) {
            int smallest = i;
            for (int index = i + 1; index < scoreJP.length; index++) {
                if (scoreJP[ index] < scoreJP[ smallest]) {
                    smallest = index;
                }
            }
            swap(i, smallest);
        }
        displayResultData();
    }

    // helper method to swap values in two elements
    private void swap(int first, int second) {
        // swap names
        String temporary = names[ first];
        names[ first] = names[ second];
        names[ second] = temporary;
        // swap scoreJP
        int temporary1 = scoreJP[ first];
        scoreJP[ first] = scoreJP[ second];
        scoreJP[ second] = temporary1;
        // swap scoreDS
        int temporary2 = scoreDS[ first];
        scoreDS[ first] = scoreDS[ second];
        scoreDS[ second] = temporary2;
        // swap score SA
        int temporary3 = scoreSA[ first];
        scoreSA[ first] = scoreSA[ second];
        scoreSA[ second] = temporary3;
    }

    // search a particular student result by entering name; return scores array
    private int search(String searchName) {
                
        // find location of student
        for (int i = 0; i < names.length; i++) {
            if (names[i].equalsIgnoreCase(searchName)) 
                return i;
        }    
        return -1;
                     
     }         
          
    // display statistics button implementation; shows statistics information window
    private void statistics() {
        // initiate minimum vaiables
        int mJP = findMin(scoreJP);
        int mDS = findMin(scoreDS);
        int mSA = findMin(scoreSA);
        // initiate average vaiables
        int aJP = findAverage(scoreJP);
        int aDS = findAverage(scoreDS);
        int aSA = findAverage(scoreSA);
        // initiate maximum vaiables
        int xJP = findMax(scoreJP);
        int xDS = findMax(scoreDS);
        int xSA = findMax(scoreSA);

        String message = String.format("  The statistics of all scores\n------"
                + "-----------------------------------\n%24s%8s%7s\nAverage:%8d"
                + "%8d%8d\nMaximum:%6d%8d%8d\nMinimum:%7d%8d%8d\n",
        "JP", "DB", "SA", aJP, aDS, aSA, xJP, xDS, xSA, mJP, mDS, mSA);
        
        JOptionPane.showMessageDialog(null,message);
    }

    // find the average value; return int
    private int findAverage(int[] grades) {
        int total = 0;
        for (int grade : grades) {
            total += grade;
        }
        return (int) total / grades.length;
    }

    // find the minimum value; return int
    private int findMin(int[] grades) {
        int lowGrade = grades[ 0]; // assume grades[ 0 ] is smallest
        for (int grade : grades) { // if grade lower than lowGrade, assign it
                                   // to lowGrade
            if (grade < lowGrade) {
                lowGrade = grade; // new lowest grade
            }
        }
        return lowGrade;
    }

    //  find maximum value helper method
    private int findMax(int[] grades) {
        int highGrade = grades[ 0]; // assume grades[ 0 ] is higest
        for (int grade : grades) { // if grade higher than highGrade, assign it 
                                   // to highGrade
            if (grade > highGrade) {
                highGrade = grade; // new higest grade
            }
        }
        return highGrade;
    }

    // disply the data upon the interface
    private void displayResultData() {
        String message = String.format("%5s%s%s%s\n", "    Student Name", 
                "      Java Programming   ", "Database Systems   ", 
                "Systems Analysis");
        message = message + "         -----------------------------------------"
                + "------------------------------------------------------------"
                + "-------\n";
            for (int i = 0; i < names.length;i++) {
   		message = message + String.format("     %-1s", names[i]);
                message = message + String.format("%28d%26d%26d\n", scoreJP[i],
                    scoreDS[i], scoreSA[i]);
            }
   	message = message + "         -----------------------------------------"
                + "------------------------------------------------------------"
                + "-------\n";    
        message = message + "\n   There is a total of "+ names.length +""
                + " students for this course, lets hope they enjoyed "
                + "themselves";
        textArea.setText(message);  
    }

   // main method
    public static void main(String[] args) {
        AcademicManager frame = new AcademicManager();
        frame.setSize(600, 340);
        frame.setVisible(true);
        frame.setResizable(false);
    }
}// end	of class definition