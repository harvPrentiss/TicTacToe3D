import java.util.ArrayList;
import java.util.List;

// Class to handle functions for the computer


public class Computer {
	public String name;
	public int piece;
	public GameBoard gBoard;
	public int upToDepth = 6;
	
	List<PointsAndScores> rootsChildrenScore = new ArrayList<>();
	
	public Computer(){
		name = "Computer";
		piece = 1;
	}
	
	public Computer(GameBoard board){
		this.gBoard = board;
		name = "Computer";
		piece = 1;
	}
	
	public void setBoard(GameBoard board){
		this.gBoard = board;
	}
	
	public void takeTurn(){
		minimaxAB(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1);
		//for (PointsAndScores pas : rootsChildrenScore) 
        //    System.out.println("Point: " + pas.point + " Score: " + pas.score);
		gBoard.placeAMove(returnBestMove(), 1, true);
	}
	
	public Point returnBestMove() {
        int MAX = -100000;
        int best = -1;

        for (int i = 0; i < rootsChildrenScore.size(); ++i) {
            if (MAX < rootsChildrenScore.get(i).score) {
                MAX = rootsChildrenScore.get(i).score;
                best = i;
            }
        }
        return rootsChildrenScore.get(best).point;
    }
	
	// Minimax function with AB pruning
    public int minimaxAB(int alpha, int beta, int depth, int turn){
    	//System.out.println("Alpha:" + alpha + " Beta:" + beta + " Depth:" + depth + " Turn:"+ turn);
        if(beta<=alpha){
        	if(turn == 1) return Integer.MAX_VALUE; 
        	else return Integer.MIN_VALUE; 
        	}
        
        if(gBoard.isGameOver(false) || depth == upToDepth) return gBoard.evaluateBoard();
        
        List<Point> pointsAvailable = gBoard.getAvailableStates();
        
        if(pointsAvailable.isEmpty()) return 0;
        
        if(depth==0) rootsChildrenScore.clear(); 
        
        int maxValue = Integer.MIN_VALUE, minValue = Integer.MAX_VALUE;
        for(int i=0;i<pointsAvailable.size(); i++){
            Point point = pointsAvailable.get(i);
            
            int currentScore = 0;
            
            if(turn == 1){
                gBoard.placeAMove(point, 1, false); 
                currentScore = minimaxAB(alpha, beta, depth+1, 2);
                maxValue = Math.max(maxValue, currentScore); 
                
                //Set alpha
                alpha = Math.max(currentScore, alpha);
                
                if(depth == 0)
                    rootsChildrenScore.add(new PointsAndScores(currentScore, point));
            }else if(turn == 2){
                gBoard.placeAMove(point, 2, false);
                currentScore = minimaxAB(alpha, beta, depth+1, 1); 
                minValue = Math.min(minValue, currentScore);
                
                //Set beta
                beta = Math.min(currentScore, beta);
            }
            //reset board
            gBoard.board[point.x][point.y][point.z] = 0; 
            
            //If a pruning has been done, don't evaluate the rest of the sibling states
            if(currentScore == Integer.MAX_VALUE || currentScore == Integer.MIN_VALUE) break;
        }
        return turn == 1 ? maxValue : minValue;
    } 
	
}
