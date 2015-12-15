import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.sql.Time;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main implements Runnable {

	final static int HEIGHT=320 ;
	final static int WIDTH =320 ;
	JFrame frame;
	public static void main(String[] main){
		new Main();
	}
	public Main(){
		
		
		EventQueue.invokeLater(new Runnable(){
			public void run() {
				frame = new JFrame();
			frame.setVisible(true);;
			frame.setLocationRelativeTo(null);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setPreferredSize(new Dimension(WIDTH,HEIGHT));
			frame.setMaximumSize(new Dimension(WIDTH,HEIGHT));
			frame.setMinimumSize(new Dimension(WIDTH,HEIGHT));
			frame.pack();
			frame.setContentPane(new Panel());
			frame.setVisible(true);	
			}		
		});
	}
	
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
	
}

class Panel extends JPanel implements Runnable{

	Thread t;
	BufferedImage screen;
	Graphics g;
	Ball atom = new Ball();
	
	Orbit[] orbits = { new Orbit(1*Main.WIDTH/3 ), new Orbit(9*Main.WIDTH/16), new Orbit(3*Main.WIDTH/4), new Orbit(15*Main.WIDTH/16) };
	
	public Panel(){
		
		screen = new BufferedImage(Main.WIDTH, Main.HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = screen.getGraphics();
		if (t==null)
			t = new Thread(this);
		t.start();
		
	}
	public void run(){
		try{Thread.sleep(2000);}catch(Exception e){e.printStackTrace();}
		
		while(true){
			
			update();
			render();
			drawToScreen();
			
			try{Thread.sleep(10);}catch(Exception e){e.printStackTrace();}
		}
		
	}
	
	public void update(){
		
		
		
	}
	double m=0;
	Calendar calendar = new GregorianCalendar();
	public void render(){
		g.setColor(Color.RED);
		m = (new GregorianCalendar().get(Calendar.SECOND));
		int yval = (int) ( -(orbits[orbits.length-1].r  )  *  Math.cos( (2*Math.PI*(m/60)) ) ) ;
		int xval = (int)( (orbits[orbits.length-1].r  )  *  Math.sin( (2*Math.PI*(m/60)) ) );
		
		g.fillOval( (xval>=0) ? Main.WIDTH/2 + xval - atom.r/2 : Main.WIDTH/2 + xval - atom.r/2  ,   (yval >=0) ? Main.HEIGHT/2+yval - atom.r/2 : Main.HEIGHT/2 +yval-atom.r/2   , atom.r, atom.r);
		
		
		System.out.println(new GregorianCalendar().get(Calendar.SECOND));
		
		for(Orbit o :  orbits){
			g.drawOval( Main.WIDTH/2 - o.d/2 , Main.WIDTH/2 - o.d/2, o.d, o.d);
		}
		
	}
	public void drawToScreen(){
		Graphics2D g2 = (Graphics2D) this.getGraphics();
			if(g2==null){System.out.println("Empty@");}
		g2.drawImage(screen, 0, 0, Main.WIDTH,Main.HEIGHT, null);
		g.clearRect(0, 0, screen.getWidth(), screen.getHeight());
		g2.clearRect(0, 0, Main.WIDTH, Main.HEIGHT);
		g2.dispose();
		
	}
	
}

class Orbit{
	
	int d;
	int r;
	public Orbit(int d){
		this.d=d;
		this.r = d/2;
	}
	
}

class Ball{

	int r= (int)(Main.WIDTH * 0.05);
	int x=Main.WIDTH/2-r;
	int y=Main.WIDTH/2-r;
	int progress=1;
	
	
	public Ball(){
		
	}
	
	
}









