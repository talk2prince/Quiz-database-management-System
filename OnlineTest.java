package javaapplication3;

import java.awt. *;
import java.awt. event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;


import javax.swing.*;


class QuestionSeries{
static String info ="Java Online Test Week  \n \nINSTRUCTIONS:\nyou have got 10 minutes to complete them all.\nThe finish button is highlighted in green when you reach the end of the test. \nClicking the finish button will display the results with the correct answers marked in light red.  \n \nThe timecounter  begins when you click on the 'start' button \n \nBest of luck!\n";
    connection con=null;
    static String question[] = new String[25];
    static String answers[][] = new String[25][4];
    static String option[]=new String[25];
    static String choice[]=new String[9];
    static int n[] = new int[50];
    int count2=0;
  
void createconnection() throws SQLException
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb","root","Prince@123");
            Statement stmt = con.createStatement();
            ResultSet rs =   stmt.executeQuery("SELECT * FROM QUESTION");
   
            int i=0,j=0;

         
            while(rs.next())
            {
                count2++;
                System.out.println(count2);
                int count=0;
                int k;
                
                String quescont =  rs.getString("quescontent");
                
                 option[0]=  (rs.getString("option1"))+"\n";
                 option[1]=  (rs.getString("option2"))+"\n";
                 option[2]=  (rs.getString("option3"))+"\n";
                 option[3]=  (rs.getString("option4"))+"\n";
                 for(k=0;k<4;k++)
                 {
                     answers[i][k]=option[k];
             
                 }
                int cb1 = rs.getInt("checkbox1");
                int cb2 = rs.getInt("checkbox2");
                int cb3 = rs.getInt("checkbox3");
                int cb4 = rs.getInt("checkbox4"); 

                question[i]=quescont;
                if(cb1==1)
                {
                    count++;

                    choice[i]="1";
                    n[i]=count;
                }
   
                 if(cb2==1)
                {
                    count++;

                    String temp = choice[i];
                     if(temp==null)
                    {
                        temp="";
                    }
                    choice[i]=temp+"2";
                    n[i]=count;
                    
                } 
                if(cb3==1)
                {
                    count++;
           
                    String temp = choice[i];
                     if(temp==null)
                    {
                        temp="";
                    }
                    choice[i]=temp+"3";
                    n[i]=count;
                }
                 
                if(cb4==1)
                {
                    count++;
       
                    String temp = choice[i];
                    if(temp==null)
                    {
                        temp="";
                    }
                        
                    choice[i]=temp+"4";
                    n[i]=count;
                }
                System.out.println(question[i]);
                count=0;
                i++;   
         
            }
            
            
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JavaApplication3.class.getName()).log(Level.SEVERE, null, ex);
        }
         
    }


static int tally = choice.length;

static String testtitle="ONLINE QUIZ";
static int timeLimit =10;
static int passMark = 33;
   
    
}


public class OnlineTest extends JFrame{
    static String studentname ="";
    static int TOTAL=0;

	static {
	try{
	TOTAL = QuestionSeries.tally;		
	
	studentname = JOptionPane.showInputDialog("Enter your name: ");
	if(studentname.length() < 1) studentname = "Anonymous   ";
	else studentname = studentname.trim() + " ";
	}catch(NullPointerException e){ System.exit(0); }
	}	

    	int seconds, minutes;
    	int quesnum, itemCheck, mark; 
	final String TESTTITLE = QuestionSeries.testtitle;
    	final int TIMELIMIT = QuestionSeries.timeLimit;
    	final int PASS = QuestionSeries.passMark;
    	String []answers = new String[TOTAL];
	JButton []choice_button = new JButton[6];
	JTextArea answerboxes[] = new JTextArea[4];
	JCheckBox []boxes = new JCheckBox[4];
    	JTextPane pane = new JTextPane();
	JLabel student, choose, message, timecounter, testresult;
    	boolean start_test, check_answer, allowRestart, finishtest;
	Northwindow panelNorth = new Northwindow();
	Southwindow panelSouth = new Southwindow();
	Centerwindow panelCenter = new Centerwindow();


	protected OnlineTest(){
        for (int i=0; i<TOTAL; i++) answers[i] ="";
		getContentPane().setLayout(new BorderLayout() );
		getContentPane().add("North", panelNorth);
		getContentPane().add("South", panelSouth);
		getContentPane().add("Center", panelCenter);
		int width = 0, height=0; 
	        if(java.awt.Toolkit.getDefaultToolkit().getScreenSize().getWidth()<799){width=640;height=460; }
	        else {width=720; height=540; }
		setSize(width,height);
		Dimension dim = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		setLocation((dim.width-width)/2, (dim.height-height)/2);
	}

/**
**  Northwindow class
**/

class Northwindow extends JPanel{

/*
  Northwindow constructor 
*/
    public Northwindow(){
		setLayout(new GridLayout(2,2));
		setBackground(new Color(230, 230, 255));
		student = new JLabel("\t Welcome : "+studentname+" to the Online Java Test");
		student.setFont(new Font("",Font.BOLD,16) );
		message = new JLabel();
		message.setForeground(Color.blue);
		add(student);
		add(message);
		add(new JLabel("               ") );
		add(new JLabel("               ") );
        setBorder(BorderFactory.createEtchedBorder() );
	}
}

/*
  Southwindow class
*/
class Southwindow extends JPanel{
	public Southwindow(){
		String []key = {"","start:","next:","finish:","check next:","check previous:"};
			for(int i=0; i<choice_button.length; i++)
                   {
				choice_button[i] = new JButton(key[i]);
				choice_button[i].addActionListener(new ActionHandler() );
				if(i !=0)add(choice_button[i]);
			  }
        setBorder(BorderFactory.createEtchedBorder() );
	}
}

/*
  Centerwindow class
*/
class Centerwindow extends JPanel{

	public Centerwindow(){
		setLayout(new GridLayout(1,2) );
		JScrollPane west = new JScrollPane(pane);       
		pane.setForeground(Color.blue);	       
   		pane.setFont(new Font ("Times New Roman",0,16) );
		pane.setText(QuestionSeries.info);
		pane.setEditable(false);
		JPanel east = new JPanel();
		east.setLayout(new BorderLayout() );	
		JPanel northEast = new JPanel();
		northEast.setBackground(new Color(230, 230, 255) ); 
		east.add("North", northEast);
		JPanel westEast = new JPanel();
		westEast.setLayout(new GridLayout(6,1) );
		east.add("West", westEast);
		JPanel centerEast = new JPanel();
		centerEast.setLayout(new GridLayout(6,1) );
		centerEast.setBackground(new Color(255,255,200));
		east.add("Center", centerEast);       
			timecounter = new JLabel("     There are "+TOTAL+" questions in total");
			timecounter.setFont(new Font ("Arial",Font.BOLD,16) );
			timecounter.setForeground(new Color(0,90,20) );
			northEast.add(timecounter);
			westEast.add(new JLabel(" "));
        String []boxs = {" A ", " B ", " C ", " D "};
			for(int i=0; i<boxes.length; i++) { 
				boxes[i] = new JCheckBox(boxs[i]);
				boxes[i].addItemListener(new ItemHandler() );
				westEast.add(boxes[i]);
			}
        westEast.add(new JLabel() );
		choose = new JLabel("    CHOOSE CORRECT ANSWERS");
		choose.setBorder(BorderFactory.createEtchedBorder() );
		centerEast.add(choose);
        JScrollPane panes[] = new JScrollPane[4];
			for(int i=0; i<answerboxes.length; i++){
				answerboxes[i] = new JTextArea();
			    answerboxes[i].setBorder(BorderFactory.createEtchedBorder() );
				answerboxes[i].setEditable(false);
				answerboxes[i].setBackground(Color.white);
				answerboxes[i].setFont(new Font("",0,12) );
	            answerboxes[i].setLineWrap(true);      
                answerboxes[i].setWrapStyleWord(true);
                panes[i] = new JScrollPane(answerboxes[i]);
			    centerEast.add(panes[i]);
			}
		if(TIMELIMIT >0)testresult = new JLabel(studentname+",   You have only : "+TIMELIMIT+" minutes to complete.");   
		else testresult = new JLabel("     There is no time limit for this test");
		testresult.setBorder(BorderFactory.createEtchedBorder() );
		centerEast.add(testresult);
	add(west);
	add(east);
	}
}

/**
**  ActionHandler class to handle all the action events from the buttons. 
**/

class ActionHandler implements ActionListener{

/* actionPerformed method */
	public void actionPerformed(ActionEvent evt){
	  String source = evt.getActionCommand();
		if(source.equals("start:")){
			choice_button[1].setVisible(false);
			start_test=true;
			allowRestart=true;
            if(TIMELIMIT >0)new Timer(); // inner Timer class 
			panelSouth.remove(choice_button[1]); //start
            displayquestion();            
        }
	if(start_test){
		if(source.equals("previous:"))  {            			
			recordanswer();
			quesnum--;
    		if(quesnum ==  -1) quesnum=TOTAL-1;
			checkteststatus();
            displayquestion(); 
		}
		if(source.equals("next:")) {
			recordanswer();
			quesnum++;            
			if(quesnum ==  TOTAL-1) finishtest=true;
			if(quesnum ==  TOTAL) quesnum=0;
            checkteststatus();
            displayquestion(); 
		}
		if(source.equals("finish:")) {
			if (finishtest){
				recordanswer();
				quesnum = 0;

				choice_button[4].setBackground(Color.lightGray);
				timecounter.setForeground(Color.blue);
				timecounter.setFont(new Font ("Arial",0,14) );
				start_test=false; 
				check_answer=true;
				panelSouth.add(choice_button[0]);
				mark_ques();
				displayquestion();
				checkteststatus();
				calculateResult();
			}
			else  JOptionPane.showMessageDialog(null,"Cycle through all questions before pressing finish",
																 "User Message",JOptionPane.INFORMATION_MESSAGE);
		}
	} 

	if (check_answer){
		if(source.equals("check next:")) {
			quesnum++;
			if(quesnum ==  TOTAL) quesnum=0;
			mark_ques();
			displayquestion();
			checkteststatus();
		}
		if(source.equals("check previous:")) {
			quesnum--;
			if(quesnum ==  -1) quesnum=TOTAL-1;
			mark_ques();
			displayquestion();
			checkteststatus();
		}
	}
	validate();        
	}

/* Timer class */

class Timer extends Thread implements Runnable{
	public Timer(){
		new Thread(this).start();
	}

    public void run() {
		while(start_test){
            try {
               Thread.sleep(1000);
               seconds++;
		if(seconds % 60 == 0 && seconds != 0){
                    seconds -= 60;
                    minutes++;
				}
				timecounter.setText("    Time Counter:  "+minutes+" mins : "+seconds+" secs ");
				if(minutes==TIMELIMIT){
					start_test=false;
					endTest();
                }
		    }
            catch(InterruptedException ex)  { System.out.print(ex); }
		}
    }
}

/* checkteststatus method */

		public void checkteststatus(){
		if((quesnum ==  TOTAL-1)&&(start_test))choice_button[3].setBackground(Color.green);
		else choice_button[4].setBackground(Color.lightGray);
      	  if(answers[quesnum].length() >0){ 
			for(int i=0; i<answers[quesnum].length(); i++)
			boxes[Integer.parseInt(answers[quesnum].substring(i,i+1) )-1].setSelected			(true);
		}
		else for(int i=0; i<boxes.length; i++)boxes[i].setSelected(false);
		}

/* displayquestion method */

	public void displayquestion(){
        int j = quesnum+1;
        System.out.println(QuestionSeries.question[quesnum]);
		pane.setText(QuestionSeries.question[quesnum]);
		if(start_test)message.setText("Question "+j+" out of "+TOTAL);
        for (int i=0; i<4; i++)answerboxes[i].setText(QuestionSeries.answers[quesnum][i]);
		if(start_test){
            String temp="";
			if(QuestionSeries.n[quesnum]==1) temp="<html>&nbsp;&nbsp;&nbsp;Choose only 		<b>ONE</b>  Option</html>";
			else if(QuestionSeries.n[quesnum]==2) temp="<html>&nbsp;&nbsp;Choose <b>TWO		</b> Options</html>";
		else if(QuestionSeries.n[quesnum]==3) temp="<html>&nbsp;&nbsp;Choose <b>THREE</b> 		Options</html>";
            else temp="<html>&nbsp;&nbsp;<b>ALL are true</b> true</html>";
		choose.setText(temp);
		}
		else {
		timecounter.setText("    Your choices are shown in the boxes");
            choose.setText("    Correct answers are marked in light red.");
		}
	}

/* record answer method */

	public void recordanswer(){
		String tmp = "";
		for(int i=0; i<boxes.length; i++) if(boxes[i].isSelected() ) tmp +=i+1;
        answers[quesnum] = tmp;
    }

/* endTest method */ 

	public void endTest(){
		message.setText("TIME OVER: please press 'finish'");
        choice_button[2].setEnabled(false); 
        choice_button[3].setEnabled(false); 
        choice_button[4].setEnabled(true); 
	}

/* mark_ques() method to highlight correct answers */

	public void mark_ques(){
		for(int i=0; i<answerboxes.length; i++) answerboxes[i].setBackground(Color.white);
		for(int i=0; i<QuestionSeries.choice[quesnum].length(); i++)
			answerboxes[Integer.parseInt(QuestionSeries.choice[quesnum].substring(i,i+1))-1].setBackground(Color.cyan);
		if(QuestionSeries.choice[quesnum].equals(answers[quesnum])) message.setText("Answer correct, well done!");
		else message.setText("Sorry, you got this one wrong.");
	}


	public void calculateResult(){
		mark=0;
		double temp=0.0;
        java.text.DecimalFormat df = new java.text.DecimalFormat("#0.#");
		for(int i=0; i<TOTAL; i++)if(QuestionSeries.choice[i].equals(answers[i]))mark++;
		temp=(double)mark;
		if(temp/TOTAL*100 >=PASS) testresult.setText("  Well done "+studentname.substring(0,studentname.indexOf(' ') )+", you passed");
		else testresult.setText("  Better luck next time "+studentname.substring(0,studentname.indexOf(' ') ) );
		student.setText(" Final score for "+studentname+":  "+mark+" out of "+TOTAL+":  "+df.format(temp/TOTAL*100)+"%");
		new Resultwindow().show();
	}
}

/* Resultwindow class */

class Resultwindow extends JFrame{
		Resultwindow() {
      	super( studentname+" results: " +(mark*100/TOTAL >=PASS?"PASS":"FAIL") );
  		Container cont = getContentPane();
		cont.setLayout(new GridLayout(TOTAL/2+3,5,2,5) );
		cont.setBackground(new Color(255,220,255) );
		cont.add(new JLabel("  "+"Marks:    "+mark+"/"+TOTAL+": "+"Percentage:  "+(mark*100/TOTAL)+"%") );
           for(int i=0; i<3; i++)cont.add(new JLabel() );
      	  String temp[] = new String[TOTAL];
			for(int i=0; i<TOTAL; i++){
				if(QuestionSeries.choice[i].equals(answers[i])) temp[i]="correct";
				else temp[i]="wrong";
			}
			for(int i=0; i<TOTAL; i++) cont.add(new JLabel("  Question "+(i+1)+":  "+temp[i]) );
		pack();
		setLocation(200,200);
	}
}

/* ItemHandler class */

class ItemHandler implements ItemListener{
	public void itemStateChanged(ItemEvent evt){
	  if(start_test){
		for(int i=0; i<boxes.length; i++) if(boxes[i].isSelected() ) itemCheck++; 
		if(itemCheck > QuestionSeries.n[quesnum]){
			java.awt.Toolkit.getDefaultToolkit().beep();
            if(QuestionSeries.n[quesnum]==1)	JOptionPane.showMessageDialog(null,"<html><font 		size='4' color='00308a'><center>"+
      	"There is only "+QuestionSeries.n[quesnum]+" possible<br> answer to question "+(quesnum+1)+
                    "<html>","User Information Message",JOptionPane.INFORMATION_MESSAGE);
			else JOptionPane.showMessageDialog(null,"<html><font size='4' color='00308a'><center>"+
                    "There are only "+QuestionSeries.n[quesnum]+" possible<br> answers to question "+(quesnum+1)+
                    "<html>","User Information Message",JOptionPane.INFORMATION_MESSAGE);
		}
		itemCheck=0;
	  }	  
	}
}

/*  main method */

	public static void main(String [] args) throws SQLException{

                QuestionSeries pro = new QuestionSeries();
                pro.createconnection();
                OnlineTest frame = new OnlineTest();
		frame.setTitle("    "+QuestionSeries.testtitle);
		frame.setDefaultCloseOperation( EXIT_ON_CLOSE );
		frame.setVisible(true);
    }
}