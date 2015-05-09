import java.util.Scanner;


public class Player {
	public String name;
	public int piece;
	
	public Player(String name, int piece){
		this.name = name;
		this.piece = piece;
	}
	
	public Player(){
		name = "Unknown";
		piece= 2;
				
	}
	
	public boolean playerMove(GameBoard board, Point move){		
		if(!board.placeAMove(move, piece, true)){
			return false;
		}
		else{
			return true;
		}
	}
	
}
