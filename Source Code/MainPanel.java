import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

 public class MainPanel extends JPanel
{
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int screenwidth = 240*2;
	private static final int screenheight = 160*2;
	 
	 private Screen screen;
	 private JPanel controlpanel;
	 private JPanel textpanel;
	 
	 private JComboBox ch1select;
	 private JComboBox ch2select;
	 
	 private JButton eye1button;
	 private JButton eye2button;
	 
	 private JButton mouth1button;
	 private JButton mouth2button;
	 
	 private JButton bgbutton;
	 private JComboBox bgselect;
	 private String[] bgtitles;
	 
	 private JButton expbutton;
	 
	 private JButton charswitch;
	 private JTextField textbox;
	 
	 private String[] charnames;
	 private ArrayList<String> charanames;
	 
	 //private String[] charnamesone;
	 //private String[] charnamestwo;
	 private boolean cs;
	 private int bgint;
	 private String tempCharone;
	 private String tempChartwo;
	 private String mainText;
	 
	 private int eye1;
	 private int eye2;
	 
	 private int mouth1;
	 private int mouth2;
	 
	 private Scanner startup;
	 private String start;
	 
	 public MainPanel()
	 {
		 setLayout(new BorderLayout());
		 
		 try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		 } catch (Exception e) {}
		 
		 screen = new Screen();
		 screen.setPreferredSize(new Dimension(screenwidth, screenheight));
		 add(screen, BorderLayout.NORTH);
		 
		 controlpanel = new JPanel();
		 controlpanel.setPreferredSize(new Dimension(screenwidth, 100));
		 controlpanel.setLayout(new GridLayout(4,2));
		 add(controlpanel, BorderLayout.CENTER);
		 
		 textpanel = new JPanel();
		 textpanel.setLayout(new GridLayout(2,1));
		 add(textpanel, BorderLayout.SOUTH);
		 
         //charnamesone = new String[]{"Eirika", "Ephraim", "Seth", "Gilliam", "Franz", "Moulder", "Vanessa", "Ross", "Neimi", "Colm", "Garcia", "Innes", "Lute", "Natasha", "Cormag", "Forde", "Kyle", "Amelia", "Artur", "Gerik", "Tethys", "Marisa", "Saleh", "Ewan", "L'Arachel", "Dozla", "Rennae", "Duessel", "Myrrh", "DragonMyrrh", "Knoll", "Joshua", "Syrene", "Tana", "Anna", "Caellach", "Glen","None"};
		  //charnamestwo = new String[]{"Ephraim", "Eirika", "Seth", "Gilliam", "Franz", "Moulder", "Vanessa", "Ross", "Neimi", "Colm", "Garcia", "Innes", "Lute", "Natasha", "Cormag", "Forde", "Kyle", "Amelia", "Artur", "Gerik", "Tethys", "Marisa", "Saleh", "Ewan", "L'Arachel", "Dozla", "Rennae", "Duessel", "Myrrh", "DragonMyrrh", "Knoll", "Joshua", "Syrene", "Tana", "Anna", "Caellach", "Glen","None"};
		 
		 //charnames = new String[]{"None", "Amelia", "Anna", "Artur", "Caellach", "Colm", "Cormag", "Dozla", "Duessel", "Eirika", "Ephraim", "Ewan", "Forde", "Franz", "Garcia", "Gerik", "Gilliam", "Glen", "Innes", "Joshua", "Knoll", "Kyle", "L'Arachel", "Lute", "Lyon", "Marisa", "Moulder", "Myrrh", "MyrrhDragon", "Natasha", "Neimi", "Orson", "Rennae", "Riev", "Ross", "Saleh", "Selena", "Seth", "Syrene", "Tana", "Tethys", "Valter", "Vanessa", "Vigarde"};
		 
		 charanames = new ArrayList<String>();
		 try {
			startup = new Scanner(new File("Data/charas.txt"));
			 
			while(startup.hasNext())
			{
				String name = startup.next();
				charanames.add(name);
			}
			charnames = charanames.toArray(new String[0]);
			 }
		 catch (FileNotFoundException e) {
		    screen.myBuffer.drawString("ERROR.", 10, 25);
		    screen.myBuffer.drawString("CHARACTER DATA COULD NOT BE RETRIVED.", 10, 75);
		    screen.myBuffer.drawString("GO TWEET ME AT @RASAMBOWL", 10, 125);
		   	screen.myBuffer.drawString("I'LL TRY TO FIX IT FOR YOU.", 10, 150);
		 }
		 
		 
		 ch1select = new JComboBox<String>(charnames);
		 ch1select.addActionListener(new ch1Listener());
		 ch1select.setSelectedIndex(9);
		 ((JLabel)ch1select.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		 controlpanel.add(ch1select);
		 
		 ch2select = new JComboBox<String>(charnames);
		 ch2select.setSelectedIndex(10);
		 ch2select.addActionListener(new ch2Listener());
		 ((JLabel)ch2select.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		 controlpanel.add(ch2select);
		 
		 eye1 = 1;
		 eye1button = new JButton("Change Left Eye: " + eye1);
		 eye1button.addActionListener(new eye1Listener());
		 controlpanel.add(eye1button);
		 
		 eye2 = 1;
		 eye2button = new JButton("Change Right Eye: " + eye2);
		 eye2button.addActionListener(new eye2Listener());
		 controlpanel.add(eye2button);
		 
		 mouth1 = 1;
		 mouth1button = new JButton("Change Left Mouth: " + mouth1);
		 mouth1button.addActionListener(new mouth1Listener());
		 controlpanel.add(mouth1button);
		 
		 mouth2 = 1;
		 mouth2button = new JButton("Change Right Mouth: " + mouth2);
		 mouth2button.addActionListener(new mouth2Listener());
		 controlpanel.add(mouth2button);
		 
		 /*bgint = 1;
		 bgbutton = new JButton("Change Background: " + bgint);
		 bgbutton.addActionListener(new bgListener());
		 controlpanel.add(bgbutton);*/
		 
		 bgtitles = new String[]{"None", "Rock", "Caer Pelyn", "House", "Normal Village", "Village Clear", "Village Sunset", "Serafew Village", "Serafew Flashback", "Port", "Ship", "Fireplace", "Castle Interior", "Castle Night", "Grado Chamber", "Throne Normal", "Throne Flashback", "Castle Bright", "Castle Dark", "Gate", "Garden", "Garden Flashback", "Manse Back", "Manse Flashback", "Cell", "Plain 1", "Plain 1 Fog", "Plain 1 Sunset", "Plain 2", "Plain 2 Fog", "Plain 2 Sunset", "Plain 2 Night", "Grass Plains", "Grass Plains 2", "Stream", "Trees", "Forest", "Town", "Castle Back", "Interior Black", "Interior Brown", "Fort Sunset", "Passage", "Burning Castle", "Stone Chamber", "Stone Flashback", "Renais Chamber", "White Temple", "Desert", "Darkling Woods", "Volcano", "Black Temple Outside", "Black Temple Inside"};
		 bgselect = new JComboBox<String>(bgtitles);
		 ((JLabel)bgselect.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		 bgselect.addActionListener(new bgListener());
		 bgselect.setSelectedIndex(1);
		 controlpanel.add(bgselect);
		 
		 expbutton = new JButton("Export");
		 expbutton.addActionListener(new expListener());
		 controlpanel.add(expbutton);
		 
		 charswitch = new JButton("Left is Talking");
		 cs = true;
		 charswitch.addActionListener(new CharSwitchListener());
		 //charswitch.setPreferredSize(new Dimension(15,160));
		 textpanel.add(charswitch);
		 
		 mainText = "This is an example conversation, press Enter to set text.";
		 textbox = new JTextField("" + mainText);
		 textbox.setFont(new Font("default", Font. PLAIN, 12));
		 textbox.setHorizontalAlignment(JTextField.CENTER);
		 textbox.setPreferredSize(new Dimension(160,50));
		 textbox.addActionListener(new textboxListener());
		 textpanel.add(textbox);
		 
		 tempCharone = "None";
				 
		 tempChartwo = "None";
		 
		 /*try {
			startup = new Scanner(new File("Data/startup.txt"));
			start = startup.nextLine();
		} catch (FileNotFoundException e) {
		}
		 if(start.equals(false))
		 {
			 JOptionPane.showMessageDialog(new JFrame(), "This is your first time using the Sacred Stones Conversation Maker! Have you read the README file included in the .zip? It contains the font necessary for proper use of this program. Please download that, thanks! -@rasambowl");
			 try{
				    PrintWriter writer = new PrintWriter("Data/startup.txt", "UTF-8");
				    writer.println("true");
				    writer.close();
				} catch (IOException e) {
				   // do something
				}
		 }*/
		 
	 }
	  class CharSwitchListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
          {
          
          	if(cs == true){
          		charswitch.setText("Right is Talking");
          		cs = false;}
          	else if(cs == false){
          		charswitch.setText("Left is Talking");
          		cs = true;}
          	
          	screen.setScreenDir(cs);
          }
	  }
	  class bgListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
          {
			  /*if(bgint < 18)
					bgint++;
			  else
					bgint = 1;
			  
			  screen.setBackground(bgint);
			  
			  bgbutton.setText("Change Background: " + bgint);*/
			  
			  screen.setBackground(bgselect.getSelectedItem().toString());
          }
	  }
	  class ch1Listener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
          {
			  screen.setCharone(ch1select.getSelectedItem().toString());
          }
	  }
	  class ch2Listener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
          {
			  screen.setChartwo(ch2select.getSelectedItem().toString());
          }
	  }
	  class textboxListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			  mainText = textbox.getText();
			  screen.setScreenText(mainText);
		  }
	  }
	  class eye1Listener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			  if(eye1 < 3)
					eye1++;
			  else
					eye1 = 1;
			  
			  screen.seteye1(eye1);
			  eye1button.setText("Change Left Eye: " + eye1);
		  }
	  }
	  class eye2Listener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			  if(eye2 < 3)
					eye2++;
			  else
					eye2 = 1;
			  
			  screen.seteye2(eye2);
			  eye2button.setText("Change Right Eye: " + eye2);
		  }
	  }
	  class mouth1Listener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			  if(mouth1 < 8)
					mouth1++;
			  else
					mouth1 = 1;
			  
			  screen.setmouth1(mouth1);
			  mouth1button.setText("Change Left Mouth: " + mouth1);
		  }
	  }
	  class mouth2Listener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			  if(mouth2 < 8)
					mouth2++;
			  else
					mouth2 = 1;
			  
			  screen.setmouth2(mouth2);
			  mouth2button.setText("Change Right Mouth: " + mouth2);
		  }
	  }
	  class expListener implements ActionListener
	  {
		  public void actionPerformed(ActionEvent e)
		  {
			    
			  
			    String suggesteddir = ".";
	            String EXTENSION = ".png";
	            JFileChooser fileChooser = new JFileChooser(suggesteddir);
	            JFrame choose = new JFrame();
	            choose.setTitle("Save To ...");
	             int status = fileChooser.showSaveDialog(choose);
	            if (status == JFileChooser.APPROVE_OPTION) 
	            {

	                try 
	                {
	                    File selectedFile = fileChooser.getSelectedFile();
	                    String newfile = selectedFile.getCanonicalPath();
	                    if (!newfile.endsWith(EXTENSION)) {
	                        newfile=newfile + EXTENSION;
	                    }

	                    ImageIO.write(screen.getImage(), "png", new File(newfile)); //write img to file

	                } catch (IOException ex) {
	                    ex.printStackTrace();

	                }
	            }
		  }
	  }
}