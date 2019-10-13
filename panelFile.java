import java.awt.*;
import javax.swing.*;

import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
public class panelFile extends JPanel implements MouseListener, KeyListener
{
	private OthelloGame game;
	public panelFile(int panelWidth, int panelHeight)
	{
		//create the panel
		super();
		
		//sets the size of the panel
		setSize(panelWidth, panelHeight);
		
		game = new OthelloGame();
		addMouseListener(this);
		addKeyListener(this);
	}

	public void paint(Graphics g)
	{
		g.setColor(new Color(150,111,51));
		g.fillRect(0,0,800,800);
		g.setColor(new Color(39,119,20));
		g.fillRect(80,80,640,640);
		
		String turn = "";
		int turns = 0;
		if(game.getTurn() == 1)
		{
			turn = "WHITE";
			turns = 1;
			g.setColor(Color.WHITE);
		}
		if(game.getTurn() == 2)
		{
			turn = "BLACK";
			turns = 2;
			g.setColor(Color.BLACK);
		}
		
		for(int x = 1;x < 9;x++)
		{
			g.fillRect(x*80,80,5,640);
		}
		g.fillRect(715,80,5,640);
		for(int y = 1;y < 9;y++)
		{
			g.fillRect(80,y*80,640,5);
		}
		g.fillRect(80,715,640,5);
		
		Font f  = new Font("Comic Sans MS",13,18);
		g.setFont(f);
		g.setColor(Color.WHITE);
		g.drawString("Turn: ",100,40);
		g.drawString("Score: ",600,30);
		g.drawString("Black: ",670,30);
		g.drawString("White: ",670,50);
		g.drawString(""+game.whiteCount(),740,50);
		g.drawString(""+game.blackCount(),740,30);
		g.drawString("Press 'N' to restart Game",100,750);
		
		
		g.drawString(""+turn,150,40);
		//the board with pieces
		int[][] board = game.getBoard();
		for(int x = 0; x < board.length;x++)
		{
			for(int y = 0; y < board[0].length;y++)
			{
				if(game.getSpot(x,y) == game.BLACK)
				{
					g.setColor(Color.BLACK);
					g.fillOval(x*80+80+10,y*80+80+10,65,65);
				}
				if(game.getSpot(x,y) == game.WHITE)
				{
					g.setColor(Color.WHITE);
					g.fillOval(x*80+80+10,y*80+80+10,65,65);
				}
			}
		}
		if(game.canMove(turns) == true)
		{
			for(int x = 0; x < 8;x++)
			{
				for(int y = 0; y < 8;y++)
				{
					if(game.isValidMove(x,y,turns) == true)
					{
						g.setColor(new Color(255,255,255,144));
						g.fillOval(x*80+80+25,y*80+80+25,35,35);
					}
					
				}
			}
		}
		//display the possible moves for the current player
	}
	public void mousePressed(MouseEvent e)
	{
		int r = 0;	
		int c = 0;
		int x = e.getX();
		int y = e.getY();
		if((x >79 && y >79) && (x < 721 && y < 721))//calculates the pressed row and column
		{
			r = (y-80)/80;	
			c = (x-80)/80;
		}
		System.out.println(""+r+" "+c);
		game.setSpot(c,r);	//makes a move for the current player
		repaint();
		game.winner();
		
	}
	public void mouseClicked(MouseEvent e)
	{
		//not used
	}
	public void mouseReleased(MouseEvent e)
	{
		//not used
	}
	public void mouseEntered(MouseEvent e)
	{
		//not used
	}
	public void mouseExited(MouseEvent e)
	{
		//not used
	}
	public void keyPressed(KeyEvent e)
	{
		char key = e.getKeyChar();//'N' or 'n' restarts a finished game
		if((key == 'n' || key == 'N'))
		{
			System.out.println("'N' Pressed: Restarting game...");
			game.reset();//reset the board by settign all values to empty and main four pieces
			repaint();
		}
	}
	public void keyReleased(KeyEvent e)
	{
		//not used
	}
	public void keyTyped(KeyEvent e)
	{
		//not used	
	}
	public void addNotify()
	{
		super.addNotify();
		requestFocus();
	}
}