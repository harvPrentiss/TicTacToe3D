import java.util.Scanner;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


public class Game extends Frame implements MouseListener{
	
	GameBoard board = new GameBoard();
	Rectangle drawingBoard[] = new Rectangle[27];
	Rectangle newGameBtn = new Rectangle();
	Player player1 = new Player();
	Computer comp = new Computer(board);
	boolean gameOver = false;
	boolean playerTurn = true;
	boolean playerWin = false;
	boolean compWin = false;
	int spaceWidth = 40;
	int spaceHeight = 40;
	String playerMessage = "Choose your move";
	Point mousePoint = new Point(0,0,0);
	Image compWinImage;
	Image playerWinImage;

	public static void main(String[] args) {
		
		Frame frame = new Game();
		frame.setResizable(false);
		frame.setSize(900,600);
		frame.setVisible(true);
		
	}
	
	public Game(){		
		
		
		compWinImage = new ImageIcon("compWin.jpg").getImage();
		playerWinImage = new ImageIcon("playerWin.jpg").getImage();
		
		setDrawingBoard();	
		
		WindowListener l = new WindowAdapter()
		{			
			public void windowClosing(WindowEvent ev)
			{
				System.exit(0);
			}
			public void windowActivated(WindowEvent ev)
			{
				repaint();
			}
			public void windowStateChanged(WindowEvent ev)
			{
				repaint();
			}
		};
		
		ComponentListener k = new ComponentAdapter()
		{
			public void componentResized(ComponentEvent e) 
			{
        		repaint();           
    		}
		};
		

		// register listeners
			
		this.addWindowListener(l);
		this.addComponentListener(k);
		addMouseListener(this);
		
	}
	
	public void paint(Graphics g)
	{
		int ww = this.getWidth();
		int wh = this.getHeight();
		this.setBackground(Color.DARK_GRAY);
		int startHeight = 25;
		
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		g.setColor(Color.GREEN);
		g.drawString("3D Tic-Tac-Toe", ww/2 - 50, 25 + startHeight);
		
		g.setColor(Color.orange);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 25));
		g.drawString("Moves", ww/2 + 200, 30 + startHeight);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		Point movesDrawPoint = new Point(ww/2 + 200, 60 + startHeight,0);
		for (int i = 0; i < board.moves.size(); i ++){
			if((i+2)%2 == 0)
			{
				g.setColor(Color.cyan);
			}
			else{
				g.setColor(Color.red);
			}
	        g.drawString(board.moves.get(i), movesDrawPoint.x, movesDrawPoint.y);
	        movesDrawPoint.y += 22;
		}
		g.setColor(Color.MAGENTA);
		if(board.isGameOver(true)){
			if(board.hasWon(false, player1.piece)){
				playerWin = true;
				playerMessage = "Congratulations you won!";
				g.drawImage(playerWinImage, ww/2 -50 , 100, playerWinImage.getWidth(this), playerWinImage.getWidth(this), null);
			}
			else if(board.hasWon(false, comp.piece)){
				compWin = true;
				playerMessage = "You have been defeated by the superior computer!";
				g.drawImage(compWinImage, ww/2 -50 , 100, compWinImage.getWidth(this), compWinImage.getWidth(this), null);
			}
			else{
				playerMessage = "It's a draw!";
			}			
		}	
		if(playerWin){
			g.setColor(Color.cyan);
		}
		else{
			g.setColor(Color.red);
		}
		
		
		Point checkPoint;
		for(int d = 0; d < drawingBoard.length; d++){
			
			if(d<9){
				if(d<3){
					checkPoint = new Point(0,d%3, 2);
				}
				else if (d<6){
					checkPoint = new Point(0,d%3, 1);
				}
				/*else if(d<12){
					checkPoint = new Point(0,d%3, 1);
				}*/
				else{
					checkPoint = new Point(0,d%3, 0);
				}
			}
			else if(d<18){
				if(d<12){
					checkPoint = new Point(1,d%3, 2);
				}
				else if (d<15){
					checkPoint = new Point(1,d%3, 1);
				}
				/*else if(d<28){
					checkPoint = new Point(1,d%3, 1);
				}*/
				else{
					checkPoint = new Point(1,d%3, 0);
				}
			}
			else {
				if(d<21){
					checkPoint = new Point(2,d%3, 2);
				}
				else if (d<24){
					checkPoint = new Point(2,d%3, 1);
				}
				/*else if(d<44){
					checkPoint = new Point(2,d%3, 1);
				}*/
				else{
					checkPoint = new Point(2,d%3, 0);
				}
			}
			/*else{
				if(d<52){
					checkPoint = new Point(3,d%3, 3);
				}
				else if (d<56){
					checkPoint = new Point(3,d%3, 2);
				}
				/*else if(d<60){
					checkPoint = new Point(3,d%3, 1);
				}*/
				/*else{
					checkPoint = new Point(3,d%3, 0);
				}
			}*/
			
			if(checkPoint.equals(board.victoryIndex[0]) || checkPoint.equals(board.victoryIndex[1])  || checkPoint.equals(board.victoryIndex[2]) ){
				g.setColor(Color.LIGHT_GRAY);
				g.fillRect(drawingBoard[d].x+1, drawingBoard[d].y+1, drawingBoard[d].width-1, drawingBoard[d].height-1);
			}
			g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
			g.setColor(Color.MAGENTA);
			g.drawString(playerMessage, 100, 520);
			g.setColor(Color.white);
			g.drawRect(drawingBoard[d].x, drawingBoard[d].y, drawingBoard[d].width, drawingBoard[d].height);
			
		}
		int startY = 160;
		int startX = 183;
		Point drawingPoint = new Point(startX, startY, 0); 
		g.setFont(new Font("TimesRoman", Font.PLAIN, 30));
		for(int i = 0; i < board.board.length; i++){
			for(int j = 0; j < board.board.length; j++){
				for(int k = 0; k < board.board.length; k++){
					if(board.board[i][k][j] == 1){
						g.setColor(Color.red);
						g.drawString("O", drawingPoint.x, drawingPoint.y);
					}
					else if(board.board[i][k][j] == 2){
						g.setColor(Color.cyan);
						g.drawString("X", drawingPoint.x, drawingPoint.y);
					}
					
					drawingPoint.x += spaceWidth;
				}
				
				drawingPoint.x = startX;
				drawingPoint.y -= spaceHeight;
			}
			
			drawingPoint.y = startY + (160*(i+1));
			startX -= spaceWidth;
			drawingPoint.x = startX;
		}
		g.setFont(new Font("TimesRoman", Font.PLAIN, 20));
		g.setColor(Color.gray);
		//g.drawLine(175, 125, 125, 325);
		g.drawLine(175, 50, 95, 370);
		g.drawLine(295, 170, 215, 490);
		
		g.drawRoundRect(newGameBtn.x, newGameBtn.y, newGameBtn.width, newGameBtn.height, 15, 15);
		g.drawString("New Game", newGameBtn.x+5, newGameBtn.y+20);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePoint.x = e.getX();
		mousePoint.y = e.getY();
		if(playerTurn){
			if(!board.isGameOver(false)){
				Point move = new Point(-1,-1, -1);
				for(int d = 0; d < drawingBoard.length; d++){
					if(drawingBoard[d].contains(mousePoint.x, mousePoint.y)){
						if(d<9){
							if(d<3){
								move = new Point(0,d%3, 2);
							}
							else if (d<6){
								move = new Point(0,d%3, 1);
							}
							/*else if(d<12){
								move = new Point(0,d%3, 1);
							}*/
							else{
								move = new Point(0,d%3, 0);
							}
						}
						else if(d<18){
							if(d<12){
								move = new Point(1,d%3, 2);
							}
							else if (d<15){
								move = new Point(1,d%3, 1);
							}
							/*else if(d<28){
								move = new Point(1,d%3, 1);
							}*/
							else{
								move = new Point(1,d%3, 0);
							}
						}
						else {
							if(d<21){
								move = new Point(2,d%3, 2);
							}
							else if (d<24){
								move = new Point(2,d%3, 1);
							}
							/*else if(d<44){
								move = new Point(2,d%3, 1);
							}*/
							else{
								move = new Point(2,d%3, 0);
							}
						}
	
						if(player1.playerMove(board, move)){
							comp.takeTurn();
							repaint();
						}
						else{
							playerMessage = "That is not a valid move";
							repaint();
						}
					}
				}
			}
			else{
				repaint();
			}
		}
		if(newGameBtn.contains(mousePoint.x, mousePoint.y)){
			board.resetBoard();
			playerWin = false;
			compWin = false;
			gameOver = false;
			playerMessage = "Choose your move";
			repaint();
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void setDrawingBoard(){
		int xPoint = 175;
		Point boardPoint = new Point(xPoint, 50, 0);
		for(int i = 0; i < drawingBoard.length; i++){
			drawingBoard[i] = new Rectangle(boardPoint.x, boardPoint.y, spaceWidth, spaceHeight);
			boardPoint.x += spaceWidth;
			if((i+1)%3 == 0){
				boardPoint.y += spaceHeight;
				boardPoint.x = xPoint;
			}
			if((i+1)%9 == 0){
				boardPoint.y += spaceHeight;
				xPoint -=spaceWidth;
				boardPoint.x = xPoint;
			}
		}
		newGameBtn = new Rectangle(25, 50, 100, 30);
	}

	
}
