/* This program allows the user to read the data(departure city,arrival city,yearmonth and flight price respectively
from the text file FlightData.txt and save it ontho NewData.txt file,display it in the text area ,sort data by depar
ture and search data on the basis of flight price
Name: Amit Bhandari
Sid:12080721
*/


import java.awt.*;
import java.awt.event.*;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;
import javafx.scene.layout.Border;
import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.border.TitledBorder;
import java.util.concurrent.TimeUnit;

//object of JtextArea,scrollpane,Jlabel,Jpael crated
public class FlightApplication {
    private JTextArea output;
    JScrollPane scrollPane;
    ArrayList<Flight> arr = new ArrayList<>();    //array list of  Flight ,arr is created




//this method create the menubar, menu and menuitem is placed in it
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu fileMenu,saveMenu,displayMenu,sortMenu,searchMenu,helpMenu;
        JMenuItem filemenuItem,savemenuItem,displaymenuItem,sortmenuItem,searchmenuItem,helpmenuItem,exitmenuItem;

       //Create the menu bar.
        menuBar = new JMenuBar();
       //Build the first menu.
        fileMenu = new JMenu("Read File");
       menuBar.add(fileMenu);
       //a group of JMenuItems
        filemenuItem = new JMenuItem("Read");
        fileMenu.add(filemenuItem);

//event when user click Read menuitem from Read File
        filemenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                readDataFile();
                JOptionPane.showMessageDialog(null,"File Have been read successfully!");
                output.setText("File Have Been Read Successfully");
            }
        });

 //Build Save menu in the menu bar.Following response occur when user press save menuitem
        saveMenu = new JMenu("Save File");
        menuBar.add(saveMenu);
        savemenuItem = new JMenuItem("Save");
        saveMenu.add(savemenuItem);

        savemenuItem.addActionListener((ActionEvent actionEvent) -> {
             saveFile();
             JOptionPane.showMessageDialog(null,"Data saved  into NewData.txt file successfully!");
             output.setText("File Have Been Saved Successfully");
       });

 //build display menu to display data
        displayMenu = new JMenu("Display Data");
        menuBar.add(displayMenu);
        displaymenuItem = new JMenuItem("Display");
        displayMenu.add(displaymenuItem);

///action performed to display all the Flight data
       displaymenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                displayOutput();
            }
        });

        //build sort Data menu and Sort menuItem on menubar
        sortMenu = new JMenu("Sort Data");
        menuBar.add(sortMenu);
        sortmenuItem = new JMenuItem("Sort");
        sortMenu.add(sortmenuItem);

        sortmenuItem.addActionListener(new ActionListener() {
        @Override
        //action perform when user click sort menuitem.sorts all data and display on text area
        public void actionPerformed(ActionEvent actionEvent) {

			long start = System.nanoTime();
			insertionSort( arr);
			long end = System.nanoTime();
			displayOutput();
           long timeInMillis = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
           System.out.println(" Sorting Time spend in ms: " + timeInMillis);

        }
    });

        //build Search Data menu  and Search menuitem on menuBar
        searchMenu = new JMenu("Search Data");
        menuBar.add(searchMenu);
        searchmenuItem = new JMenuItem("Search");
        searchMenu.add(searchmenuItem);

        //action performed when user click of search menuitem
        searchmenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {

        String output =JOptionPane.showInputDialog(null,"Enter flight price for searching:");
        double input = returnPaymentvalue(output);  //JoptionPane takes input as stirng and as user enter input is double so user input double is converted to string


       if(output.equals(""))   //if user doesnot enter anytthing then following message appear.
               {
                 JOptionPane.showMessageDialog(null,"Enter the Flight Price to search.it should not be empty");
                }


       else if(input<1 )  //if user input negative number then following message is generated
       {
           JOptionPane.showMessageDialog(null,"Flight Price cannot be negative.Enter positive value only");

       }
       else   //sort all the data and use binary searching techinque to search data
       {

		 long start = System.nanoTime();
		  priceSort(arr);
         find(input);
         long end = System.nanoTime();
         long timeInMillis = TimeUnit.MILLISECONDS.convert(end - start, TimeUnit.NANOSECONDS);
         System.out.println(" Searching Time spend in ms: " + timeInMillis);

        }
      }


    });

        //Help menuItem  and Exit menu item is displayed in menubar.
        helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        helpmenuItem = new JMenuItem("Help");
        helpMenu.add(helpmenuItem);

        //action generated when help menu item is clicked
        helpmenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JOptionPane.showMessageDialog(null, "Please click on the menu item from the top to perform specific task \ncontact Amit Bhandari(anmeet619@gmail.com) for further assistance");
        }
    });

        //Exit menuItem is placed on menu
        exitmenuItem = new JMenuItem("Exit");
        helpMenu.add(exitmenuItem);

        //system exits if user click exit menuitem
        exitmenuItem.addActionListener((ActionEvent actionEvent) -> {
            System.exit(0);
        });
        return menuBar;
        }


    public Container createContentPane() {
        //Create the content Pane
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);

        String title = "Display Area for Flights (Departure City, Arrival City, YearMonth, Price)";
        TitledBorder border = BorderFactory.createTitledBorder(title);
        contentPane.setBorder(border);


        //Create a scrolled text area.
        output = new JTextArea(15, 30);
        output.setEditable(false);
        scrollPane = new JScrollPane(output);

        //Add the text area to the content pane.
        contentPane.add(scrollPane, BorderLayout.CENTER);


        return contentPane;
    }

     //Create the GUI and display it

    private static void createAndShowGUI() {
        //Create and set up the window.
        JFrame frame = new JFrame("Processing of Flight Data");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create and set up the content pane.
        FlightApplication demo = new FlightApplication();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());

        //Display the window.
        frame.setSize(700,600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        //creating and showing this application's GUI.

       createAndShowGUI();
       long end = System.currentTimeMillis();
       NumberFormat formatter = new DecimalFormat("#0.00000");
       System.out.print("Execution time is " + formatter.format((end - start) / 1000d) + " seconds\n\n\n");//timer is placed to display execution time of program

    }

     //method that read file from scanner
    private void readDataFile()
  {
	   try
	   {
		   Scanner in = new Scanner(new FileReader("FlightData.txt"));//open file

		   String myEntry = "" ;
                   String departure ="";
		   String arrival="";
                   String yearmonth="";
                   String price;

		   while(in.hasNextLine())
		   {

				myEntry = in.nextLine();
				StringTokenizer st = new StringTokenizer(myEntry,",");

				while(st.hasMoreTokens())
				{

				departure= st.nextToken();
				arrival = st.nextToken();
                                yearmonth= st.nextToken();
                               price = st.nextToken();
                               Double num = Double.parseDouble(price);

                    arr.add(new Flight(departure,arrival,yearmonth,num));
				}
                   }// end of while loop
			in.close();//close file


             } catch(IOException ex)//throws exception if file cannot be read
	      {
				 System.out.println("file loading failed or file doesnot exists");
	      }

  }
    //save the file by creating New file NewData.txt uding PrintWriter
    private void saveFile()
   {
	    try
	    {
			PrintWriter out = new PrintWriter(new FileWriter("NewData.txt"));

		        String departure ="";
			String arrival="";
                        double price;
			for (int i = 0; i < arr.size(); i++)
		    {
				Flight f = arr.get(i);

			    departure = f.getDeparture();
			    arrival = f.getArrival();
                            price = f.getPrice();
                               //write departure,arrival and price 3 item into new file
				out.println(departure + "," + arrival + "," + price );


		    }
                        out.println("\nNumber of total flights:"+ arr.size());
			out.close();



		  }
		  catch (IOException ex)    //throw exception if file cannot be saved
		  {
			 System.out.println("File could not be saved");
		  }
   }



    //method to display data from the file
    private void displayOutput()
    {
        String outputMsg ="Departure City\t\tArrival City\t\tYear Month\t\tFlight Price\n======================================================================================\n";

		for (int i = 0; i < arr.size();i++)
   		{
   		 outputMsg = outputMsg + arr.get(i).getDeparture()+"\t\t"+arr.get(i).getArrival()+"\t\t"+arr.get(i).getYearmonth()+"\t\t"+arr.get(i).getPrice()+"\n";
   		}

   	output.setText(outputMsg);//display file data on text area
    }
  //method that sorts the data in the file according to departure city.Insertion sort is used here
 public static void insertionSort(ArrayList<Flight> Array) {

    int i,j;

    for (i = 0; i < Array.size(); i++) {
        Flight key = new Flight("", "","",0);
        key.departure= Array.get(i).departure;
        key.arrival = Array.get(i).arrival;
        key.yearmonth = Array.get(i).yearmonth;
        key.price = Array.get(i).price;
        j = i;
        while((j > 0) && (Array.get(j - 1).departure.compareTo( key.departure))>0 ){
            Array.set(j,Array.get(j - 1));
            j--;
        }
        Array.set(j,key);
    }


}

//this method is used to take double input from user to search file as the Joption Pane take string as input it should be converted to double
public double returnPaymentvalue(String payValue) {//method that convert string into double
        try {
            return Double.parseDouble(payValue);
        } catch (NumberFormatException | NullPointerException e) {
            return -2;//if unsuccessful to convert string to double then return -2
        }

    }

//method that sort data of file according to Flightprice.This method is used before using binary search as it is compulsory in binary searching to search data.
public static void priceSort(ArrayList<Flight> Array) {  //function to sort price as binary search is used

    int i,j;

    for (i = 0; i < Array.size(); i++) {
        Flight key = new Flight("", "","",0);
        key.departure= Array.get(i).departure;
        key.arrival = Array.get(i).arrival;
        key.yearmonth = Array.get(i).yearmonth;
        key.price = Array.get(i).price;
        j = i;
        while((j > 0) && (Array.get(j - 1).price > key.price) ){
            Array.set(j,Array.get(j - 1));
            j--;
        }
        Array.set(j,key);
    }
}

public void find(double flightprice)//binary search is done to check if the user input flight price is present
    {
        int first =0;  //minimum index value of array
        int last = arr.size()-1;//maximum index value of array.
        int middle;

      while(first<=last)//check if the first index of array less than first and loop is done
        {

            middle = (first+last)/2;//divide array by half and assign to middle
            if (arr.get(middle).getPrice() == flightprice)
            {
                  output.setText("The given flight price is present");
                  break;
            }
            else if(arr.get(middle).getPrice()<flightprice)
            {

                first = middle + 1;
            }
            else
            {
                last = middle - 1;

            }

       output.setText("The given flight price is not present");
        }
}

}
