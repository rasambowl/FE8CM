 import javax.swing.*;
 import java.io.*; 
 import java.io.InputStream;
 import java.awt.*;
 import java.awt.event.*;
 import java.awt.geom.AffineTransform;
 import java.awt.geom.Rectangle2D;
 import java.awt.image.*;

 public class Screen extends JPanel
{
	private static final int width = 240*2;
	private static final int height = 160*2;
	
	
	private BufferedImage myImage;
	public Graphics2D myBuffer;
	private Timer t;
	private ImageIcon bg;
	private ImageIcon bg1, bg2, bg3, bg4, bg5, bg6, bg7, bg8, bg9, bg10, bg11, bg12, bg13, bg14, bg15, bg16, bg17, bg18;
	private ImageIcon textbox;
	
	private boolean oneleft;
	private boolean twoleft;
	
	
	private Character charone, chartwo;
	
	private String screenText;
	private boolean screenDir;
	private Font FEdialogue;
	private Color black, grey, red;
	private String text1, text2;
	private boolean single;
	
	private String onechar, twochar;
	
	private int eye1, eye2, mouth1, mouth2;
	
	FontMetrics fm;
	Rectangle2D bounds;
	
	public Screen()
	{
		screenDir = true;
		screenText = "This is an example conversation, press Enter to set text.";
		try {
			String fName = "Font/GBA FE Dialogue.ttf";
			//InputStream is = Screen.class.getResourceAsStream(fName);
			//Font FEdialogue = Font.createFont(Font.TRUETYPE_FONT, is);
			InputStream myStream = new BufferedInputStream(new FileInputStream(fName));
            Font ttfBase = Font.createFont(Font.TRUETYPE_FONT, myStream);
            FEdialogue = ttfBase.deriveFont(Font.PLAIN, 25);   
		}
	    catch(IOException e) {
	    	FEdialogue = new Font("Comic Sans MS", Font.PLAIN, 25);
	    	System.out.println(e);
	    }
		catch(FontFormatException e) {
			FEdialogue = new Font("Comic Sans MS", Font.PLAIN, 25);
			System.out.println(e);
	    }
		black = new Color(40, 40, 40);
		grey = new Color(184, 184, 184);
		red = Color.red;
		
		myImage =  new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	    myBuffer = myImage.createGraphics();
	    myBuffer.setFont(FEdialogue);
	    myBuffer.setColor(black);
		
	    eye1 = 1;
	    eye2 = 1;
	    mouth1 = 1;
	    mouth2 = 1;
	    
	    onechar = "Eirika";
	    twochar = "Ephraim";
	    
	    bg = new ImageIcon("Backgrounds/Rock.png");
	    
	    textbox = new ImageIcon("Assets/textbox.png");
	    
	    charone = new Character("Assets/Eirika.png");
	    chartwo = new Character("Assets/Ephraim.png");
	    
	    oneleft = true;
	    twoleft = false;
		
		t = new Timer(5, new Listener());
		setFocusable(true);
	    t.start();
	}
	
	public void paintComponent(Graphics g)
	{
	    g.drawImage(myImage, 0, 0, getWidth(), getHeight(), null);
	}
	
	public BufferedImage getImage()
	{
		return myImage;
	}
	
	public void setBackground(String bgname)
	{
		bg = new ImageIcon("Backgrounds/"+bgname+".png");
	}
	public void setCharone(String chara)
	{
		charone = new Character("Assets/" + chara + ".png");
		onechar = chara;
	}
	public void setChartwo(String chara)
    {
		chartwo = new Character("Assets/" + chara + ".png");
		twochar = chara;
    }
	public void setScreenText(String temp)
	{
		screenText = temp;
	}
	public void setScreenDir(boolean temp)
	{
		screenDir = temp;
	}
	public void seteye1(int temp)
	{
		eye1 = temp;
	}
	public void seteye2(int temp)
	{
		eye2 = temp;
	}
	public void setmouth1(int temp)
	{
		mouth1 = temp;
	}
	public void setmouth2(int temp)
	{
		mouth2 = temp;
	}
	public void drawTextBox(boolean left, String text, Graphics2D myBuffer)
	{
		myBuffer.setColor(grey);
		BufferedImage textimg = new BufferedImage(
		textbox.getIconWidth(),
		textbox.getIconHeight(),
		BufferedImage.TYPE_INT_ARGB);
		Graphics g = textimg.createGraphics();
		// paint the Icon to the BufferedImage.
		textbox.paintIcon(null, g, 0,0);
		g.dispose();
		
		single = true;
		
		fm = myBuffer.getFontMetrics();
		bounds = fm.getStringBounds(text, myBuffer);
		int length = (int)bounds.getWidth();
		
		if(length > 330)
		{
			int text1length = (330*text.length())/length;
			
			text1 = text.substring(0, text1length);
			text2 = text.substring(text1length, text.length());
			single = false; 
		}
		
		if(left == true){
			if(single){
			myBuffer.drawImage(textimg, 16,48, 184*2, 55*2, null);
			myBuffer.drawString(text, 34, 92);
			myBuffer.setColor(black);
			//myBuffer.setColor(red);
			myBuffer.drawString(text, 32, 92);}
			else{
				myBuffer.drawImage(textimg, 16,48, 184*2, 55*2, null);
				myBuffer.drawString(text1, 34, 92);
				myBuffer.drawString(text2, 34, 124);
				myBuffer.setColor(black);
				//myBuffer.setColor(red);
				myBuffer.drawString(text1, 32, 92);
				myBuffer.drawString(text2, 32, 124);}
		}
		else{
			AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
			tx.translate(-textimg.getWidth(null), 0);
			AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
			textimg = op.filter(textimg, null);
			
			if(single){
			myBuffer.drawImage(textimg, 96,48, 184*2, 55*2, null);
			myBuffer.drawString(text, 116, 92);
			myBuffer.setColor(black);
			//myBuffer.setColor(red);
			myBuffer.drawString(text, 114, 92);}
			else{
				myBuffer.drawImage(textimg, 96,48, 184*2, 55*2, null);
				myBuffer.drawString(text1, 116, 92);
				myBuffer.drawString(text2, 116, 124);
				myBuffer.setColor(black);
				//myBuffer.setColor(red);
				myBuffer.drawString(text1, 114, 92);
				myBuffer.drawString(text2, 114, 124);}
		}
	}
	
	private class Listener implements ActionListener
	   {
	      public void actionPerformed(ActionEvent e)
	      {
	    	 myBuffer.drawImage(bg.getImage(), 0, 0, width, height, null);
	    	 
	    	 
	    	 charone.drawChar(mouth1, eye1, oneleft, onechar, myBuffer);
	    	 chartwo.drawChar(mouth2, eye2, twoleft, twochar, myBuffer);
	    	 
	    	 drawTextBox(screenDir, screenText, myBuffer);
	    	 
	         repaint();
	      }
	   } 
}