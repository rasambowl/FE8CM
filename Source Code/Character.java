import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.image.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

 public class Character 
{
   private ImageIcon chara;
   
   private static final int wholeWidth = 128;
   private static final int wholeHeight = 112;
   
   private static final int pWidth = 96;
   private static final int pHeight = 80;
   
   private static final int mWidth = 32;
   private static final int mHeight = 16;
   
   private static final int eWidth = 32;
   private static final int eHeight = 16;
   
   private static final int mxlocation = 16;
   private static final int mylocation = 48;
   
   private static final int exlocation = 24;
   private static final int eylocation = 32;
   
   private int eye;
   private int mouth;
   
   private Scanner scanner;
   private String charaname;
   
   boolean leftornot;
   
   ImageIcon Sprite;
   
   int righteyecalc;
   int rightmouthcalc;
   
   public Character(String filename)
   {
	   chara = new ImageIcon(filename);
	   
	   eye = 1;
	   mouth = 1;
	   int charalength = filename.length();
	   charaname = filename.substring(7, charalength-4);
   }
    
   public void drawChar(int mouth, int eye, boolean leftornot, String name, Graphics2D myBuffer)
   {
	   ///////////////////////////////////////////
	   //COORDINATE CALCULATIONS
	   
	   boolean done = false;
	   int eyex =0;
	   int eyey =0;
	   int mouthx =0;
	   int mouthy =0;
	   int loop =0;
	   int countlimit = new File("Data").list().length;
	   String tempname;
	   int count = 1;
	   
	   while(!done && count<countlimit)
	   {
		try {scanner = new Scanner(new File("Data/data"+count+".txt"));}
	    catch (FileNotFoundException e){
	    	myBuffer.drawString("ERROR.", 10, 25);
	    	myBuffer.drawString("POSITION DATA COULD NOT BE RETRIVED.", 10, 50);
	    	myBuffer.drawString("GO TWEET ME AT @RASAMBOWL", 10, 125);
	    	myBuffer.drawString("I'LL TRY TO FIX IT FOR YOU.", 10, 150);
	    }
		
		//System.out.println("Character data retrieved from file: data" + count + ".txt");
		eyex = scanner.nextInt()*2;
		eyey = scanner.nextInt()*2;
		mouthx = scanner.nextInt()*2;
		mouthy = scanner.nextInt()*2;
		loop = scanner.nextInt();
		
		for(int x=0; x<loop; x++)
		{
			tempname = scanner.next();
			if(charaname.equals(tempname))
			{
				done = true;
			}
		}
		count++;
	   }
	   
	   ///////////////////////////////////////////
	   
	   BufferedImage charaimg = new BufferedImage(
			    chara.getIconWidth(),
			    chara.getIconHeight(),
			    BufferedImage.TYPE_INT_ARGB);
			Graphics g = charaimg.createGraphics();
			// paint the Icon to the BufferedImage.
			chara.paintIcon(null, g, 0,0);
			g.dispose();
	   if(leftornot == true)
	   { 
		   AffineTransform tx = AffineTransform.getScaleInstance(-1, 1);
		   tx.translate(-charaimg.getWidth(null), 0);
		   AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_NEAREST_NEIGHBOR);
		   charaimg = op.filter(charaimg, null);

		   myBuffer.drawImage(charaimg, -64, 160, wholeWidth*2, wholeHeight*2, null);
		   
		   switch(eye)
		   {
		   case 1:
			   break;
		   case 2:
			   //myBuffer.drawImage(charaimg, 80, 208, (eWidth*2)+80, (eHeight*2)+208, 0, 48, 32, 64, null);
			   myBuffer.drawImage(charaimg, eyex, eyey, (eWidth*2)+eyex, (eHeight*2)+eyey, 0, 48, 32, 64, null);
			   break;
		   case 3:
			   //myBuffer.drawImage(charaimg, 80, 208, (eWidth*2)+80, (eHeight*2)+208, 0, 64, 32, 80, null);
			   myBuffer.drawImage(charaimg, eyex, eyey, (eWidth*2)+eyex, (eHeight*2)+eyey, 0, 64, 32, 80, null);
			   break;
		   }
		   switch(mouth)
		   {
		   case 1:
			   break;
		   case 2:
			   //myBuffer.drawImage(charaimg, 96, 240, (mWidth*2)+96, (mHeight*2)+240, 96, 80, 128, 96, null);
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 96, 80, 128, 96, null);
			   break;
		   case 3:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 96, 96, 128, 112, null);
			   break;
		   case 4:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 64, 80, 96, 96, null);
			   break;
		   case 5:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 64, 96, 96, 112, null);
			   break;
		   case 6:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 32, 80, 64, 96, null);
			   break;
		   case 7:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 32, 96, 64, 112, null);
			   break;
		   case 8:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 0, 80, 32, 96, null);
			   break;
		   }
	   }
	   else if(leftornot == false)
	   {
		   myBuffer.drawImage(charaimg, 288, 160, wholeWidth*2, wholeHeight*2, null);
		   
		   eyex = 480 - (eyex + 64);
		   mouthx = 480 - (mouthx + 64);
		   
		   switch(eye)
		   {
		   case 1:
			   break;
		   case 2:
			   //myBuffer.drawImage(charaimg, 336, 208, (eWidth*2)+336, (eHeight*2)+208, 96, 48, 128, 64, null);
			   myBuffer.drawImage(charaimg, eyex, eyey, (eWidth*2)+eyex, (eHeight*2)+eyey, 96, 48, 128, 64, null);
			   break;
		   case 3:
			   myBuffer.drawImage(charaimg, eyex, eyey, (eWidth*2)+eyex, (eHeight*2)+eyey, 96, 64, 128, 80, null);
			   break;
		   }
		   switch(mouth)
		   {
		   case 1:
			   break;
		   case 2:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 0, 80, 32, 96, null);
			   break;
		   case 3:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 0, 96, 32, 112, null);
			   break;
		   case 4:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 32, 80, 64, 96, null);
			   break;
		   case 5:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 32, 96, 64, 112, null);
			   break;
		   case 6:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 64, 80, 96, 96, null);
			   break;
		   case 7:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 64, 96, 96, 112, null);
			   break;
		   case 8:
			   myBuffer.drawImage(charaimg, mouthx, mouthy, (mWidth*2)+mouthx, (mHeight*2)+mouthy, 96, 80, 128, 96, null);
			   break;
		   }
	   }
   }
}
