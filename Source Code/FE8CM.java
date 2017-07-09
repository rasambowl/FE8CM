   import javax.swing.JFrame;
   public class FE8CM
   {
	   private static final int width = 240*2;
		private static final int height = 800;
	   public static void main(String[] args)
      { 
         JFrame frame = new JFrame("Fire Emblem: The Sacred Stones Conversation Maker");
         frame.setResizable(false);
         frame.setSize(width, height);
         frame.setLocation(0, 0);
         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
         frame.setContentPane(new MainPanel());
         frame.setVisible(true);
      }
   }	