 public class OthelloGame
{
	private int[][] board;
	private int turn;
	private boolean playing;
	public static final int EMPTY = 0;
	public static final int WHITE = 1;
	public static final int BLACK = 2;
	public static final int TIE = 3;
	public static final int PLAYING = 4;
	
	public OthelloGame()
	{
		reset();
	}
	public int[][] getBoard()
	{
		return board;
	}
	public void reset()
	{
		board = new int[8][8];
		playing = true;
		turn = WHITE;
		
		for(int x = 0;x< board.length;x++)
		{
			for(int y = 0;y < board[0].length;y++)
			{
				board[x][y] = EMPTY;
			}
		}
		board[3][3]= WHITE;
		board[3][4]= BLACK;
		board[4][4]= WHITE;
		board[4][3]= BLACK;
	}
	public int getTurn()
	{
		return turn;
	}
	public int getSpot(int c,int r)
	{
		if(c>=board.length || r>=board[0].length || c < 0 || r < 0)
			return -1;
		else
			return board[r][c];
	}
	public boolean setSpot(int c,int r)
	{
		if(isValidMove(c,r,turn) == true)
		{
			board[r][c] = turn;//place piece
			flip(c,r,turn);// flip as needed
			if(turn == WHITE)// change turns(if needed)
			{
				if(canMove(BLACK) == true)
				{
					turn = BLACK;
				}
			}
			else if(turn == BLACK)
			{
				if(canMove(WHITE) == true)
				{
					turn = WHITE;
				}
			}
			boolean empty = false;
			for(int x = 0; x < 8; x++)
			{
				for(int y = 0; y < 8; y++)
				{
					if(board[x][y] != EMPTY)
						empty = false;
					else
					{
						empty = true;
					}
				}
			}
			if(empty = true)
				playing = false;
			if(isWinner() == 1 || isWinner() == 2 || isWinner() == 3)//check for game over
			{
				if(isWinner() == 1)
					System.out.println("White Wins!");
				else if(isWinner() == 2)
					System.out.println("Black Wins!");
				else if(isWinner() == 3)
					System.out.println("It's a Tie!");
			}
			return true;
		}
		else
			return false;
	}
	public boolean isValidMove(int c,int r,int player)
	{
		boolean valid = false;
		if((c > 8 || r > 8)||(c < 0||r <0 ) || getSpot(c,r) != EMPTY)//if(/*players turn and move is valid return true*/
		{
			return false;
		}
		else if((c < 8 && r < 8)&&(c > -1 && r > -1 ) && getSpot(c,r) == EMPTY)
		{
			
			//up
			if(r-1 > -1 && board[r-1][c] == opponent(player))
			{
				for(int rr = r-2; rr > -1 ;rr--)
				{
					if(board[rr][c] == player)
						valid = true;
					else if(board[rr][c] == EMPTY)
						break;
				}
				
			}
			
			//down
			if(r+1 < 8 && board[r+1][c] == opponent(player))
			{
				for(int rr = r+2; rr < 8;rr++)
				{
					if(board[rr][c] == player)
						valid = true;
					else if(board[rr][c] == EMPTY)
						break;
				}
				
			}
			
			//left
			if(c-1 > -1 && board[r][c-1] == opponent(player))
			{
				for(int cc = c-2; cc > -1;cc--)
				{
					if(board[r][cc] == player)
						valid = true;
					else if(board[r][cc] == EMPTY)
						break;
				}
				
			}
			
			//right
			if(c+1 < 8 && board[r][c+1] == opponent(player))
			{
				for(int cc = c+2; cc < 8;cc++)
				{
					if(board[r][cc] == player)
						valid = true;
					else if(board[r][cc] == EMPTY)
						break;
				}
				
			}
			
			//upleft
			if((r-1 > -1 && c-1 > -1) && board[r-1][c-1] == opponent(player))
			{
				for(int rr = 2; c-rr > -1 && r-rr > -1;rr++)
				{
					if(board[r-rr][c-rr] == player)
						valid = true;
					else if(board[r-rr][c-rr] == EMPTY)
						break;
				}
				
			}
			
			//downleft
			if((r+1 < 8 && c-1 > -1) && board[r+1][c-1] == opponent(player))
			{
				for(int rr = 2; c-rr > -1 && r+rr < 8;rr++)
				{
					if(board[r+rr][c-rr] == player)
						valid = true;
					else if(board[r+rr][c-rr] == EMPTY)
						break;
				}
				
			}
			
			//upright
			if((r-1 > -1 && c+1 < 8) && board[r-1][c+1] == opponent(player))
			{
				for(int rr = 2; c+rr < 8 && r-rr > -1;rr++)
				{

					if(board[r-rr][c+rr] == player)
						valid = true;
					else if(board[r-rr][c+rr] == EMPTY)
						break;
				}
				
			}
			
			//downright
			if((r+1 < 8 && c+1 < 8) && board[r+1][c+1] == opponent(player))
			{
				for(int rr = 2; c+rr < 8 && r+rr < 8;rr++)
				{
					if(board[r+rr][c+rr] == player)
						valid = true;
					else if(board[r+rr][c+rr] == EMPTY)
						break;
				}
				
			}
					
		}	
		return valid;
	}
	public boolean canMove(int player)
	{
		for(int r = 0; r < board.length;r++)
		{
			for(int c = 0; c < board[0].length;c++)
			{
				if(isValidMove(c,r,player) == true && player == WHITE)
				{
					return true;
				}
				if(isValidMove(c,r,player) == true && player == BLACK)
				{
					return true;
				}
			}
		}
			return false;
	}
	public int isWinner()
	{
		
		if(playing = false)
		{
			int black = blackCount();
			int white = whiteCount();
			if(black>white)
				return BLACK;
			if(black<white)
				return WHITE;
			if(black==white)
				return TIE;
		}
		return PLAYING;
	}
	public int blackCount()
	{
		int blackCount = 0;
		for(int r = 0;r < board.length;r++)
		{
			for(int c = 0;c < board[0].length;c++)
			{
				if(getSpot(r,c) == BLACK)
					blackCount++;
			}
		}
		return blackCount++;
	}
	public int whiteCount()
	{
		int whiteCount = 0;
		for(int r = 0;r < board.length;r++)
		{
			for(int c = 0;c < board[0].length;c++)
			{
				if(getSpot(r,c) == WHITE)
					whiteCount++;
			}
		}
		return whiteCount++;
	}
	public void flip(int c, int r, int player)
	{	
			//up
			if(r-1 > -1 && board[r-1][c] == opponent(player))
			{
				int rr = 0;
				for(rr = r-2; rr > -1 ;rr--)
				{	
					if(board[rr][c] == EMPTY)
					{	
						break;
					}
					else if(board[rr][c] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < r - rr;x++)
						{
							board[rr+x][c] = player;
						}
					}
				}
				
			}
			
			//down
			if(r+1 < 8 && board[r+1][c] == opponent(player))
			{
				for(int rr = r+2; rr < 8;rr++)
				{	
					if(board[rr][c] == EMPTY)
						break;
					else if(board[rr][c] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < rr-r;x++)
						{
							board[rr-x][c] = player;
						}
					}
					
				}
				
			}
			
			//left
			if(c-1 > -1 && board[r][c-1] == opponent(player))
			{
				for(int cc = c-2; cc > -1;cc--)
				{	
					if(board[r][cc] == EMPTY)
						break;
					else if(board[r][cc] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < c - cc;x++)
						{
							board[r][cc+x] = player;
						}
					}
					
				}
				
			}
			
			//right
			if(c+1 < 8 && board[r][c+1] == opponent(player))
			{
				for(int cc = c+2; cc < 8;cc++)
				{	
					if(board[r][cc] == EMPTY)
						break;
					else if(board[r][cc] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < cc-c;x++)
						{
							board[r][cc-x] = player;
						}
					}
					
				}
				
			}
			
			//upleft
			if((r-1 > -1 && c-1 > -1) && board[r-1][c-1] == opponent(player))
			{
				for(int rr = 2; c-rr > -1 && r-rr > -1;rr++)
				{	
					if(board[r-rr][c-rr] == EMPTY)
						break;
					else if(board[r-rr][c-rr] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < (r+rr)-r;x++)
						{
							board[r-x][c-x] = player;
						}
					}
				}
				
			}
			
			//downleft
			if((r+1 < 8 && c-1 > -1) && board[r+1][c-1] == opponent(player))
			{
				for(int rr = 2; c-rr > -1 && r+rr < 8;rr++)
				{	
					if(board[r+rr][c-rr] == EMPTY)
						break;
					else if(board[r+rr][c-rr] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < (rr+r)-r;x++)
						{
							board[r+x][c-x] = player;
						}
					}
				}
				
			}
			
			//upright
			if((r-1 > -1 && c+1 < 8) && board[r-1][c+1] == opponent(player))
			{
				for(int rr = 2; c+rr < 8 && r-rr > -1;rr++)
				{
					if(board[r-rr][c+rr] == EMPTY)
						break;
					else if(board[r-rr][c+rr] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < (rr+r)-r;x++)
						{
							board[r-x][c+x] = player;
						}
					}
				}
				
			}
			
			//downright
			if((r+1 < 8 && c+1 < 8) && board[r+1][c+1] == opponent(player))
			{
				for(int rr = 2; c+rr < 8 && r+rr < 8;rr++)
				{	
					if(board[r+rr][c+rr] == EMPTY)
						break;
					else if(board[r+rr][c+rr] == player)
					{
						System.out.println("Sai da Cool¯\\_(._.)_/¯");
						for(int x = 1;x < (rr+r)-r;x++)
						{
							board[r+x][c+x] = player;
						}
					}
				}
				
			}
	}
	public int opponent(int player)
	{
		int turns = 0;
		if(player == WHITE)
		{
			turns = BLACK;
		}
		if(player == BLACK)
		{
			turns = WHITE;
		}
		return turns;
	}
	public void winner()
	{
		boolean empty = false;
			for(int x = 0; x < 8; x++)
			{
				for(int y = 0; y < 8; y++)
				{
					if(board[x][y] == EMPTY)
						empty = true;
				}
			}
			if(empty = false)
				playing = false;
			if(isWinner() == 1 || isWinner() == 2 || isWinner() == 3)//check for game over
			{
				if(isWinner() == 1)
					System.out.println("White Wins!");
				else if(isWinner() == 2)
					System.out.println("Black Wins!");
				else if(isWinner() == 3)
					System.out.println("It's a Tie!");
			}
	}
}