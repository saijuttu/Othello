import java.awt.*;
import javax.swing.*;

public class panelFile extends JPanel
{
	public panelFile(int panelWidth, int panelHeight)
	{
		//create the panel
		super();
		
		//sets the size of the panel
		setSize(panelWidth, panelHeight);
	}

	public void paint(Graphics g)
	{
		// draws a black background and a red X
		g.setColor(new Color(0,188,255));
		g.fillRect(0,0,getWidth(),getHeight());
		g.setColor(new Color(22,188,10));
		g.fillRect(0,400,1000,400);
		g.setColor(new Color(255,204,0));
		g.fillOval(65,105,150,150);
		g.setColor(Color.WHITE);
		
		for(int i = 0;i<6;i++)
		{
			int x = (int)(Math.random()*1000);
			int y = (int)(Math.random()*200);
			g.fillOval(x,y,200,100);
		}
		g.setColor(new Color(175,171,170));
		g.fillRect(300,400,400,400);
		g.setColor(Color.WHITE);
		g.fillRect(490,420,30,50);
		g.fillRect(490,500,30,50);
		g.fillRect(490,580,30,50);
		g.fillRect(490,660,30,50);
		g.fillRect(490,740,30,50);
		g.setColor(new Color(113,47,47));
		g.fillRect(800,500,50,200);
		g.setColor(new Color(8,153,53));
		
		for(int i = 0;i<70;i++)
		{
			int x = (int)(Math.random()*600+650);
			int y = (int)(Math.random()*200+250);
			g.fillOval(x,y,150,100);
		}
		
		g.setColor(new Color(117,143,122));
		g.fillRoundRect(350,550,40,10,5,5);
		g.fillRoundRect(400,550,40,10,5,5);
		
		g.setColor(new Color(230,24,231));
		g.fillRect(325,500,140,50);
		g.fillRect(350,450,90,50);
		
		g.setColor(new Color(223,48,48));
		g.fillRect(330,505,20,20);
		g.fillRect(440,505,20,20);
		
		g.setColor(new Color(117,143,122));
		g.fillRect(355,455,80,40);
		
	}
}