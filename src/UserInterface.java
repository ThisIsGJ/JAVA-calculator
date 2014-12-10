
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/* 
 *make the graphical user interface for the calculator.The calculator have four basic operation "+,-.*,/"
 *and "c" for clean function,"Ans" used for remember previous result. This class is just used for show the 
 *frame on the scren.And the ba is related to the basic operation class.
 *@author Jun Guan
 *@version 1.0
 */

public class UserInterface implements ActionListener
{
    private JFrame frame;
    private JTextField userInput;
    private JTextField text;
    private BasicOperation ba;
    // it is used to show the user input to TextField 
    private String show;
    // put the user input to a ArrayList(inputString) which is used to calculator result
    private ArrayList<String> inputString;
    //mid is used to store number(string) temporary. if the next input is sign, the mid will store to inputString.*/ 
    private String mid;
    private String result;// answer
       
    public UserInterface()
    {	
        makeFrame();
        ba = new BasicOperation();
        inputString = new ArrayList<String>();
        show = "";
        userInput.setEditable(false);
        result = "";
        mid = "";
    }
    
    private void quit()
    {
    	System.exit(0);
    	
    }
    
    /*
     * add button to the panel method.
     * @param panel The panel to show the buttons
     * @param button the button we want to added for the panel.
     * */
    private void addButton(Container panel, String button)
    {
    	JButton btn = new JButton(button);
    	btn.addActionListener(this);
    	btn.setForeground(Color.red);//word's color
    	btn.setMargin(new Insets(1,1,1,1));// the distance between the word and the white side.
        panel.add(btn);   	
    }
    
    //This is the frame for the calculator.
    private void makeFrame()
    {
        frame = new JFrame("Calculator");
        setMenuBar(frame);
        JPanel contentPane = (JPanel)frame.getContentPane();
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(Color.white);
        
        contentPane.setLayout(new BorderLayout(10,10));
        contentPane.setBorder(new EmptyBorder(10,10,10,10));
        
        //userInput 
        userInput = new JTextField("0", 30);
        contentPane.add(userInput, BorderLayout.NORTH);
        
        //button 
        JPanel panel = new JPanel(new GridLayout(4,5));
        addButton(panel, "7");
        addButton(panel, "8");
        addButton(panel, "9");
        addButton(panel, "/");
        addButton(panel, "C");
        
        addButton(panel, "4");
        addButton(panel, "5");
        addButton(panel, "6");
        addButton(panel, "*");
        addButton(panel, "Ans");
        
        addButton(panel, "1");
        addButton(panel, "2");
        addButton(panel, "3");
        addButton(panel, "+");
        addButton(panel, "-");
        
        addButton(panel, "0");
        addButton(panel, ".");
        addButton(panel, "=");
        addButton(panel, "(");
        addButton(panel, ")");
        
        contentPane.add(panel, BorderLayout.CENTER);
       
        frame.pack(); 
        frame.setVisible(true);
    }
    
    public void setMenuBar(JFrame frame)
    {
        //build the main frame's menu bar
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        
        JMenu fileMenu = new JMenu("Calculator");
        menuBar.add(fileMenu);
            
        JMenu helpMenu = new JMenu("Help");
        menuBar.add(helpMenu);
        
        JMenuItem aboutItem = new JMenuItem("about");
        aboutItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
            	System.out.println(" Button Ans is used to use the previous answer which you got" );
            	System.out.println(	" XD you could also use the quit to quit the system");
            	System.out.println(	" If you need any more help or you want to change anything about the calculator,please tell me,my username is jg495");}
        });
        helpMenu.add(aboutItem);
        
        JMenuItem quitItem = new JMenuItem("Qiut");
        quitItem.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) { quit(); }
        });
        fileMenu.add(quitItem);
    }
    
    /**
     * Connect the UnserInterface class to the BasicOperation class
     * Find out what should we handle the UserInput.
     * @param e The event occur.
     * */
	
	public void actionPerformed(ActionEvent event) {
		        
		String command = event.getActionCommand();
		
		if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/") || command.equals("(") || 
				command.equals(")"))
		{	
			show += command;
			if(command == "-")
			{	
				try
				{	
					//test "-" is minus sign or a negative number 
					//if the String before "-" is a number, the "-" is a minus sign, vice versa.
					if(mid != "")
					{
						inputString.add(mid);
						
					}
					mid = "";
					//char c = inputString.get(inputString.size()-1).charAt(0);
					if(ba.isNumeric(inputString.get(inputString.size()-1)))
					{	
						mid += command;
					}
					else
					{	
						inputString.add(command);
					}
				}
				catch (Exception e)
				{	
					 mid += command;
				}
			
			}
			else
			{
				if(mid != "")
				{	
					inputString.add(mid);
					mid = "";
				}

			inputString.add(command);
			}		
		}
		
			
		else if (command.equals("="))
		{
			if(mid != "")
			{
				inputString.add(mid);
				mid = "";
			}
			try//in case the input is  invalid 
			{	
				result = ba.calAnswer(inputString);// use the BasicOperation class to calculate answer
				if(result.equals("(") || result.equals(")"))//in case the input is such lick "(((((" or ")))"
				{
					show = "Error!";
					result = "";
				}
				
			}
			catch(Exception e)
			{	
				show = "Error!";
				result = "";
			}
			
		
			try
			{	
				show = ba.makeInt(result);
				result = show;
				if(show.equals("Infinity"))// in case the dividend is "0"
				{
					show = "Error!!";
				}
			}
			catch (Exception e)
			{
				 mid += command;
			}
							
		}
		
		else if(command.equals("C"))
		{
			show = "";
			inputString = new ArrayList<String>();
			mid = "";
			
		}
		
		else if(command.equals("Ans"))
		{
			show += result;
			inputString.add(result);
		}
	
		else
		{	
			show += command;
			mid += command;
		}
		userInput.setText(show);		
	}
}



