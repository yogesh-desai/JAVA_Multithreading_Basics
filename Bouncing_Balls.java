import java.applet.*;
import java.awt.*;
import java.awt.event.*;
/**
 *
 * @author YogeshD
 */
/*<applet code="bouncingball/BouncingBall.class" width="500" height="500">
 </applet>*/
public class BouncingBall extends Applet implements ActionListener{

    /**
     * @param args the command line arguments
     */
    Button btn;
    Ball b1,b2;
    int cntr=0;
    
    public void init()
    {
        btn =new Button("Start");
        add(btn);
        btn.addActionListener(this);
    }
    public void paint(Graphics g)
    {
        try
        {
          g.setColor(Color.RED);  
          g.fillOval(b1.x, b1.y,b1.w,b1.h);
          g.setColor(Color.GREEN);
          g.fillOval(b2.x,b2.y,b2.w,b2.h);
          
        }catch(NullPointerException e){
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        if(cntr==0)
        {
            b1=new Ball(0,0,50,50);
            cntr++;
        }
        else if(cntr==1)
        {
            b2=new Ball(75,0,50,50);
            cntr++;
        }
        else
        {
            btn.setEnabled(false);
        }
    }
    
    class Ball implements Runnable
    {
        Thread t=null;
        int x,y,w,h,incr_y,incr_x;
        Ball(int x,int y,int w,int h)
        {
            this.x=x;
            this.y=y;
            this.w=w;
            this.h=h;
            incr_y=1;
            incr_x=1;
            t=new Thread(this);
            t.start();
        }
        public void run()
        {
           while(true) 
           {
               y=y+incr_y;
               x=x+incr_x;
                             
                if((y==getSize().height-h)||(y==0))
                    incr_y=(-1)*incr_y;
                if(x==getSize().width-w||x==0)    
                    incr_x=(-1)*incr_x;
               
               try
               {
                   t.sleep(10);
               }
               catch(InterruptedException e)
               {
                   
               }
               repaint();
           }
        }
    }
}
