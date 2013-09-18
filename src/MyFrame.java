    import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;


import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
     
     
    public class MyFrame extends JFrame implements KeyListener {
     
        /**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		private GraphicsDevice device;
        private String font = "Serif";
        private JLabel left_po;
        private JLabel right_po;
        private int title_size=0;
       
        public MyFrame(String title, String left, String right)  {

            this.setLayout(null);
            this.setLocationRelativeTo(null);
            this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            this.addKeyListener(this); 
            
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            device = ge.getDefaultScreenDevice();

            this.setUndecorated(true);
            this.setVisible(true);
            this.setExtendedState(Frame.MAXIMIZED_BOTH);  
            
            if(device.isFullScreenSupported()){	
                device.setFullScreenWindow(this);
            }
           
            try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            if(!title.equals(""))
            {
            JLabel TITLE = new JLabel();
            this.add(TITLE);
            TITLE.setOpaque(true);
            TITLE.setText(title);
            
            int font_size=2;

            while(font_size!=(int)(this.getHeight()*0.15)){
            
            Font theFont = new Font(font, Font.PLAIN,font_size);
            FontMetrics fm = TITLE.getFontMetrics(theFont);
            int width = fm.stringWidth(TITLE.getText());
            
            if(this.getWidth()<width)
            {
            font_size--;
            break;
            }
            font_size++;
            }
            
            TITLE.setFont(new Font(font, Font.PLAIN, font_size));
            FontMetrics fm = TITLE.getFontMetrics(TITLE.getFont());
            int width = fm.stringWidth(TITLE.getText());
            
            TITLE.setSize(width, (int)(font_size*1.3));
            TITLE.setLocation((int)((this.getWidth()-width)/2), 0);
            title_size=font_size;
            }
            
            
            if(!left.equals(""))
            {
            JLabel LEFT_L = new JLabel();
            this.add(LEFT_L);
            LEFT_L.setOpaque(true);
            LEFT_L.setText(left);
            
            int font_size=2;

            while(font_size!=(int)(this.getHeight()*0.1)){
            
            Font theFont = new Font(font, Font.PLAIN,font_size);
            FontMetrics fm = LEFT_L.getFontMetrics(theFont);
            int width = fm.stringWidth(LEFT_L.getText());
            
            if(this.getWidth()*0.40<width)
            {
            font_size--;
            break;
            }
            font_size++;
            }
            
            LEFT_L.setFont(new Font(font, Font.PLAIN, font_size));
            FontMetrics fm = LEFT_L.getFontMetrics(LEFT_L.getFont());
            int width = fm.stringWidth(LEFT_L.getText());
            
            LEFT_L.setSize(width, (int)(font_size*1.3));
            LEFT_L.setLocation((int)(this.getWidth()/4-width/2), (int) (this.getHeight()*0.85)+ (int)(this.getHeight()*0.15/2-font_size/2));
            }
            
            
            if(!right.equals(""))
            {
            JLabel RIGHT_L = new JLabel();
            this.add(RIGHT_L);
            RIGHT_L.setOpaque(true);
            RIGHT_L.setText(right);
            
            int font_size=2;

            while(font_size!=(int)(this.getHeight()*0.1)){
            
            Font theFont = new Font(font, Font.PLAIN,font_size);
            FontMetrics fm = RIGHT_L.getFontMetrics(theFont);
            int width = fm.stringWidth(RIGHT_L.getText());
            
            if(this.getWidth()*0.40<width)
            {
            font_size--;
            break;
            }
            font_size++;
            }
            
            RIGHT_L.setFont(new Font(font, Font.PLAIN, font_size));
            FontMetrics fm = RIGHT_L.getFontMetrics(RIGHT_L.getFont());
            int width = fm.stringWidth(RIGHT_L.getText());
            
            RIGHT_L.setSize(width, (int)(font_size*1.3));
            RIGHT_L.setLocation((int)((this.getWidth()/4-width/2)+this.getWidth()/2), (int) (this.getHeight()*0.85)+ (int)(this.getHeight()*0.15/2-font_size/2));
            }
            
            
            left_po = new JLabel();
            this.add(left_po);
            
            right_po = new JLabel();
            this.add(right_po);
            
            change_left();
            change_right();
            
            this.repaint();

        }
       
        
        public void paint(Graphics g){
            super.paintComponents( g );
            int left = (int)(this.getWidth()/2-this.getWidth()*0.01);
            int right = (int)(this.getWidth()/2+this.getWidth()*0.01);
            int size = right-left;
            g.fillRect(left,(int)(title_size*1.2),size,(int)(this.getHeight()-title_size*1.2));
        } 
        
        public void keyTyped(KeyEvent e) {}

        public void keyPressed(KeyEvent e) {}

        public void keyReleased(KeyEvent e) {
            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                System.out.println("Escape released, close window!");
                this.dispose();
                ChessCounter.writeFile();
            }  else if(e.getKeyCode() == KeyEvent.VK_7 || e.getKeyCode() == KeyEvent.VK_NUMPAD7){
            	ChessCounter.leftPlus();
            	change_left();
                this.repaint();
            }   else if(e.getKeyCode() == KeyEvent.VK_1 || e.getKeyCode() == KeyEvent.VK_NUMPAD1){
            	ChessCounter.leftMinus();
            	change_left();
                this.repaint();
            }   else if(e.getKeyCode() == KeyEvent.VK_9 || e.getKeyCode() == KeyEvent.VK_NUMPAD9){
            	ChessCounter.rightPlus();
            	change_right();
                this.repaint();
            }   else if(e.getKeyCode() == KeyEvent.VK_3 || e.getKeyCode() == KeyEvent.VK_NUMPAD3){
            	ChessCounter.rightMinus();
            	change_right();
                this.repaint();
            } 
            

        
        } 

       
        
        public void change_left()
        {
            left_po.setOpaque(true);
        	left_po.setText(ChessCounter.getString_left());
            
            int font_size=2;

            while(font_size!=(int)(this.getHeight()*0.75)){
            
            Font theFont = new Font(font, Font.PLAIN,font_size);
            FontMetrics fm = left_po.getFontMetrics(theFont);
            int width = fm.stringWidth(left_po.getText());
            
            if(this.getWidth()*0.40<width)
            {
            font_size--;
            break;
            }
            font_size++;
            }
            
            left_po.setFont(new Font(font, Font.PLAIN, font_size));
            FontMetrics fm = left_po.getFontMetrics(left_po.getFont());
            int width = fm.stringWidth(left_po.getText());
            
            left_po.setSize(width, (int)(font_size*1.3));
            left_po.setLocation((int)(this.getWidth()/4-width/2), (int) (this.getHeight()/2-font_size/1.6));
         }
        
        
        public void change_right()
        {
        	right_po.setOpaque(true);
        	right_po.setText(ChessCounter.getString_right());
            
            int font_size=2;

            while(font_size!=(int)(this.getHeight()*0.75)){
            
            Font theFont = new Font(font, Font.PLAIN,font_size);
            FontMetrics fm = right_po.getFontMetrics(theFont);
            int width = fm.stringWidth(right_po.getText());
            
            if(this.getWidth()*0.40<width)
            {
            font_size--;
            break;
            }
            font_size++;
            }
            
            right_po.setFont(new Font(font, Font.PLAIN, font_size));
            FontMetrics fm = right_po.getFontMetrics(right_po.getFont());
            int width = fm.stringWidth(right_po.getText());
            
            right_po.setSize(width, (int)(font_size*1.3));
            right_po.setLocation((int)((this.getWidth()/4-width/2)+this.getWidth()/2), (int) (this.getHeight()/2-font_size/1.6));
            }

    }