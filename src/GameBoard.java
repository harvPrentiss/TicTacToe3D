import java.util.ArrayList;
import java.util.List;


public class GameBoard {
	
	public int[][][] board;
	List<Point> availablePoints;
	public Point[] victoryIndex;
	public List<String> moves = new ArrayList<String>();
	
	public GameBoard(){
		board = new int[3][3][3];
		victoryIndex = new Point[3];
		victoryIndex[0] = new Point(-1,-1,-1);
		victoryIndex[1] = new Point(-1,-1,-1);
		victoryIndex[2] = new Point(-1,-1,-1);
		//victoryIndex[3] = new Point(-1,-1,-1);
		for(int i = 0; i < board.length; i++){
			for(int j = 0; j < board.length; j++){
				for(int k = 0; k < board.length; k++){
					board[i][j][k] = 0;
				}
			}
		}
	}
	
	public int evaluateBoard() {
        int score = 0;

        //Check rows z changing
        for (int i = 0; i < board.length; ++i) {           
            for (int j = 0; j < board.length; ++j) {
                 int X = 0;
                 int O = 0;
            	for(int k = 0; k < board.length; k++){
	                if (board[i][j][k] == 2) {
	                    X++;
	                } else if (board[i][j][k] == 1) {
	                    O++;
	                } 
            	}
            	score+=changeInScore(X, O);
            }              
        }

        //Check rows y changing
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                 int X = 0;
                 int O = 0;
	            for(int k = 0; k < board.length; k++){
	                if (board[i][k][j] == 2) {
	                    X++;
	                } else if (board[i][k][j] == 1) {
	                    O++;
	                } 
	            }
	            score+=changeInScore(X, O);
            }
            
        }
        
      //Check cols x changing
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
                 int X = 0;
                 int O = 0;
	            for(int k = 0; k < board.length; k++){
	                if (board[k][i][j] == 2) {
	                    X++;
	                } else if (board[k][i][j] == 1) {
	                    O++;
	                } 
	            }
	            score+=changeInScore(X, O);
            }
            
        }

        //Check diagonal (top left to bottom right)
        for(int k = 0; k < board.length; k++){
             int X = 0;
             int O = 0;
	        for (int i = 0, j = 0; i < board.length; ++i, ++j) {
	            if (board[i][j][k] == 2) {
	                X++;
	            } else if (board[i][j][k] == 1) {
	                O++;
	            } 
	        }
	        score+=changeInScore(X, O);
        }

        

      //Check diagonal (top right to bottom left)
        for(int k = 0; k < board.length; k++){
             int X = 0;
             int O = 0;
	        for (int i = 0, j = board.length-1; i > board.length; ++i, --j) {
	            if (board[i][j][k] == 2) {
	                X++;
	            } else if (board[i][j][k] == 1) {
	                O++;
	            } 
	        }
	        score+=changeInScore(X, O);
        }
        
      //Check diagonal (top front left to back bottom left)
        for(int k = 0; k < board.length; k++){
            int X = 0;
            int O = 0;
	        for (int i = 0, j = 0; i < board.length; ++i, ++j) {
	            if (board[i][k][j] == 2) {
	                X++;
	            } else if (board[i][k][j] == 1) {
	                O++;
	            } 
	        }
	        score+=changeInScore(X, O);
       }
        
      //Check diagonal (bottom front left to back top left)
        for(int k = 0; k < board.length; k++){
             int X = 0;
             int O = 0;
	        for (int i = 0, j = board.length-1; i < board.length; i++, j--) {
	            if (board[j][k][i] == 2) {
	                X++;
	            } else if (board[j][k][i] == 1) {
	                O++;
	            } 
	        }
	        score+=changeInScore(X, O);
        }
        
      //Check diagonal (top front left to back top right)
        for(int k = 0; k < board.length; k++){
             int X = 0;
             int O = 0;
	        for (int i = 0, j = 0; i < board.length; ++i, ++j) {
	            if (board[k][j][i] == 2) {
	                X++;
	            } else if (board[k][j][i] == 1) {
	                O++;
	            } 
	        }
	        score+=changeInScore(X, O);
        }
        
      //Check diagonal (top front right to back top left)
        for(int k = 0; k < board.length; k++){
             int X = 0;
             int O = 0;
	        for (int i = 0, j = board.length-1; i < board.length; ++i, --j) {
	            if (board[k][j][i] == 2) {
	                X++;
	            } else if (board[k][j][i] == 1) {
	                O++;
	            } 
	        }
	        
	        score+=changeInScore(X, O);
        }
        
        // Check cross cube diagonals
      
        int X = 0;
        int O = 0;
        for(int k = 0; k < board.length; k++){
        	if (board[k][k][k] == 2) {
                X++;
            } else if (board[k][k][k] == 1) {
                O++;
            } 
        }        
        score+=changeInScore(X, O);
        
        
       	
        X = 0;
        O = 0;
	    for (int i = 0, j = 0, k = board.length-1; i > board.length; ++i, ++j, --k) {
	            if (board[k][i][j] == 2) {
	                X++;
	            } else if (board[k][i][j] == 1) {
	                O++;
	            } 
	    }       
		score+=changeInScore(X, O);
		
		
        X = 0;
        O = 0;
	    for (int i = 0, j = 0, k = board.length-1; i > board.length; ++i, ++j, --k) {
	            if (board[j][i][k] == 2) {
	                X++;
	            } else if (board[j][i][k] == 1) {
	                O++;
	            } 
	    }       
		score+=changeInScore(X, O);
		
        X = 0;
        O = 0;
	    for (int i = 0, j = 0, k = board.length-1; i > board.length; ++i, ++j, --k) {
	            if (board[i][k][j] == 2) {
	                X++;
	            } else if (board[i][k][j] == 1) {
	                O++;
	            } 
	    }       
		score+=changeInScore(X, O);

        return score;
    }
	
    private int changeInScore(int X, int O){
        int change;
        if (X == 3) {
            change = -5000;
        } else if (X == 2 && O == 0) {
            change = -200;
        } else if (X == 1 && O == 0) {
            change = -1;
        }
       //else if(X == 1 && O == 0){
       //	change = -1;
        //}
        else if (O == 3) {
            change = 5000;
        } else if (O == 2 && X == 0) {
            change = 200;
        } else if (O == 1 && X == 0) {
            change = 1;
        }
        //else if(O == 1 && X == 0){
        //	change = 1;
        //}
        else {
            change = 0;
        } 
        return change;
    } 

    public boolean isGameOver(boolean saveVictory) {
        //Game is over is someone has won, or board is full (draw)
        return (hasWon(saveVictory,1) || hasWon(saveVictory,2) || getAvailableStates().isEmpty());
    }

    public boolean hasWon(boolean saveVictory, int check) {
    	// Checks for victory conditions by plane ( x then y then z)
    	for(int i = 0; i < board.length; i++){    		
		    if (board[i][0][0] == board[i][0][1] && board[i][0][0] == board[i][0][2]  && board[i][0][0] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,0,0);
			        victoryIndex[1] = new Point(i,0,1);
			        victoryIndex[2] = new Point(i,0,2);
			        //victoryIndex[3] = new Point(i,0,3);
		        }
		        return true;
    		}	
		    if (board[i][1][0] == board[i][1][1] && board[i][1][0] == board[i][1][2]  && board[i][1][0] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,1,0);
			        victoryIndex[1] = new Point(i,1,1);
			        victoryIndex[2] = new Point(i,1,2);
			        //victoryIndex[3] = new Point(i,1,3);
		        }
		        return true;
    		}	
		    if (board[i][2][0] == board[i][2][1] && board[i][2][0] == board[i][2][2]  && board[i][2][0] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,2,0);
			        victoryIndex[1] = new Point(i,2,1);
			        victoryIndex[2] = new Point(i,2,2);
			        //victoryIndex[3] = new Point(i,2,3);
		        }
		        return true;
    		}	
		    /*if (board[i][3][0] == board[i][3][1] && board[i][3][0] == board[i][3][2]  && board[i][3][0] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,3,0);
			        victoryIndex[1] = new Point(i,3,1);
			        victoryIndex[2] = new Point(i,3,2);
			        victoryIndex[3] = new Point(i,3,3);
		        }
		        return true;
    		}*/
		    if (board[i][0][0] == board[i][1][0] && board[i][0][0] == board[i][2][0]  && board[i][0][0] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,0,0);
			        victoryIndex[1] = new Point(i,1,0);
			        victoryIndex[2] = new Point(i,2,0);
			       // victoryIndex[3] = new Point(i,3,0);
		        }
		        return true;
    		}	
		    if (board[i][0][1] == board[i][1][1] && board[i][0][1] == board[i][2][1]  && board[i][0][1] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,0,1);
			        victoryIndex[1] = new Point(i,1,1);
			        victoryIndex[2] = new Point(i,2,1);
			       // victoryIndex[3] = new Point(i,3,1);
		        }
		        return true;
    		}	
		    if (board[i][0][2] == board[i][1][2] && board[i][0][2] == board[i][2][2]  && board[i][0][2] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,0,2);
			        victoryIndex[1] = new Point(i,1,2);
			        victoryIndex[2] = new Point(i,2,2);
			        //victoryIndex[3] = new Point(i,3,2);
		        }
		        return true;
    		}	
		    /*if (board[i][0][3] == board[i][1][3] && board[i][0][3] == board[i][2][3] && board[i][0][3] == board[i][3][3] && board[i][0][3] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,0,2);
			        victoryIndex[1] = new Point(i,1,2);
			        victoryIndex[2] = new Point(i,2,2);
			        victoryIndex[3] = new Point(i,3,2);
		        }
		        return true;
    		}	*/
		    if (board[i][0][0] == board[i][1][1] && board[i][0][0] == board[i][2][2]  && board[i][0][0] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,0,0);
			        victoryIndex[1] = new Point(i,1,1);
			        victoryIndex[2] = new Point(i,2,2);
			        //victoryIndex[3] = new Point(i,3,3);
		        }
		        return true;
    		}
		    if (board[i][0][2] == board[i][1][1] && board[i][0][2] == board[i][2][0] && board[i][0][2] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(i,0,2);
			        victoryIndex[1] = new Point(i,1,1);
			        victoryIndex[2] = new Point(i,2,0);
			        //victoryIndex[3] = new Point(i,3,0);
		        }
		        return true;
    		}	
    	 		
		   if (board[0][i][0] == board[0][i][1] && board[0][i][0] == board[0][i][2]  && board[0][i][0] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(0,i,0);
			        victoryIndex[1] = new Point(0,i,1);
			        victoryIndex[2] = new Point(0,i,2);
			        //victoryIndex[3] = new Point(0,i,3);
		       }
		       return true;
		   }	
		   if (board[1][i][0] == board[1][i][1] && board[1][i][0] == board[1][i][2]  && board[1][i][0] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(1,i,0);
			        victoryIndex[1] = new Point(1,i,1);
			        victoryIndex[2] = new Point(1,i,2);
			       // victoryIndex[3] = new Point(1,i,3);
		       }
		       return true;
		   }	
		   if (board[2][i][0] == board[2][i][1] && board[2][i][0] == board[2][i][2]  && board[2][i][0] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(2,i,0);
			        victoryIndex[1] = new Point(2,i,1);
			        victoryIndex[2] = new Point(2,i,2);
			       // victoryIndex[3] = new Point(2,i,3);
		       }
		       return true;
		   }	
		   /*if (board[3][i][0] == board[3][i][1] && board[3][i][0] == board[3][i][2] && board[3][i][0] == board[3][i][3] && board[3][i][0] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(3,i,0);
			        victoryIndex[1] = new Point(3,i,1);
			        victoryIndex[2] = new Point(3,i,2);
			        victoryIndex[3] = new Point(3,i,3);
		       }
		       return true;
		   }*/	
		   if (board[0][i][0] == board[1][i][0] && board[0][i][0] == board[2][i][0]  && board[0][i][0] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(0,i,0);
			        victoryIndex[1] = new Point(1,i,0);
			        victoryIndex[2] = new Point(2,i,0);
			        //victoryIndex[3] = new Point(3,i,0);
		       }
		       return true;
		   }	
		   if (board[0][i][1] == board[1][i][1] && board[0][i][1] == board[2][i][1]  && board[0][i][1] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(0,i,1);
		      		victoryIndex[1] = new Point(1,i,1);
		      		victoryIndex[2] = new Point(2,i,1);
		      		//victoryIndex[3] = new Point(3,i,1);
		       }
		       return true;
		   }	
		   if (board[0][i][2] == board[1][i][2] && board[0][i][2] == board[2][i][2]  && board[0][i][2] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(0,i,2);
		      		victoryIndex[1] = new Point(1,i,2);
		      		victoryIndex[2] = new Point(2,i,2);
		      		//victoryIndex[3] = new Point(3,i,2);
		       }
		       return true;
		   }	
		   /*if (board[0][i][3] == board[1][i][3] && board[0][i][3] == board[2][i][3] && board[0][i][3] == board[3][i][3] && board[0][i][3] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(0,i,3);
		      		victoryIndex[1] = new Point(1,i,3);
		      		victoryIndex[2] = new Point(2,i,3);
		      		victoryIndex[3] = new Point(3,i,3);
		       }
		       return true;
		   }*/	
		   if (board[0][i][0] == board[1][i][1] && board[0][i][0] == board[2][i][2]  && board[0][i][0] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(0,i,0);
		      		victoryIndex[1] = new Point(1,i,1);
		      		victoryIndex[2] = new Point(2,i,2);
		      		//victoryIndex[3] = new Point(3,i,3);
		       }
		       return true;
		   }
		   if (board[0][i][2] == board[1][i][1] && board[0][i][2] == board[2][i][0] && board[0][i][2] == check){
		      	if(saveVictory){
		      		victoryIndex[0] = new Point(0,i,2);
		      		victoryIndex[1] = new Point(1,i,1);
		      		victoryIndex[2] = new Point(2,i,0);
		      		//victoryIndex[3] = new Point(3,i,0);
		       }
		       return true;
		   }
    	  		
		   if (board[0][0][i] == board[1][0][i] && board[0][0][i] == board[2][0][i]  && board[0][0][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(0,0,i);
			        victoryIndex[1] = new Point(1,0,i);
			        victoryIndex[2] = new Point(2,0,i);
			        //victoryIndex[3] = new Point(3,0,i);
		        }
		        return true;
    		}	
		    if (board[0][1][i] == board[1][1][i] && board[0][1][i] == board[2][1][i]  && board[0][1][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(0,1,i);
			        victoryIndex[1] = new Point(1,1,i);
			        victoryIndex[2] = new Point(2,1,i);
			        //victoryIndex[3] = new Point(3,1,i);
		        }
		        return true;
    		}	
		    if (board[0][2][i] == board[1][2][i] && board[0][2][i] == board[2][2][i]  && board[0][2][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(0,2,i);
			        victoryIndex[1] = new Point(1,2,i);
			        victoryIndex[2] = new Point(2,2,i);
			       // victoryIndex[3] = new Point(3,2,i);
		        }
		        return true;
    		}	
		    /*if (board[0][3][i] == board[1][3][i] && board[0][3][i] == board[2][3][i] && board[0][3][i] == board[3][3][i] && board[0][3][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(0,3,i);
			        victoryIndex[1] = new Point(1,3,i);
			        victoryIndex[2] = new Point(2,3,i);
			        victoryIndex[3] = new Point(3,3,i);
		        }
		        return true;
    		}	*/
		    if (board[0][0][i] == board[0][1][i] && board[0][0][i] == board[0][2][i]  && board[0][0][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(0,0,i);
			        victoryIndex[1] = new Point(0,1,i);
			        victoryIndex[2] = new Point(0,2,i);
			        //victoryIndex[3] = new Point(0,3,i);
		        }
		        return true;
    		}	
		    if (board[1][0][i] == board[1][1][i] && board[1][0][i] == board[1][2][i]  && board[1][0][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(1,0,i);
			        victoryIndex[1] = new Point(1,1,i);
			        victoryIndex[2] = new Point(1,2,i);
			        //victoryIndex[3] = new Point(1,3,i);
		        }
		        return true;
    		}	
		    if (board[2][0][i] == board[2][1][i] && board[2][0][i] == board[2][2][i]  && board[2][0][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(2,0,i);
			        victoryIndex[1] = new Point(2,1,i);
			        victoryIndex[2] = new Point(2,2,i);
			        //victoryIndex[3] = new Point(2,3,i);
		        }
		        return true;
    		}	
		    /*if (board[3][0][i] == board[3][1][i] && board[3][0][i] == board[3][2][i] && board[3][0][i] == board[3][3][i] && board[3][0][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(3,0,i);
			        victoryIndex[1] = new Point(3,1,i);
			        victoryIndex[2] = new Point(3,2,i);
			        victoryIndex[3] = new Point(3,3,i);
		        }
		        return true;
    		}	*/
		    if (board[0][0][i] == board[1][1][i] && board[0][0][i] == board[2][2][i]  && board[0][0][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(0,0,i);
			        victoryIndex[1] = new Point(1,1,i);
			        victoryIndex[2] = new Point(2,2,i);
			        //victoryIndex[3] = new Point(3,3,i);
		        }
		        return true;
    		}
		    if (board[2][0][i] == board[1][1][i] && board[2][0][i] == board[0][2][i] && board[2][0][i] == check){
		       	if(saveVictory){
		       		victoryIndex[0] = new Point(2,0,i);
			        victoryIndex[1] = new Point(1,1,i);
			        victoryIndex[2] = new Point(0,2,i);
			        //victoryIndex[3] = new Point(0,3,i);
		        }
		        return true;
    		}
    	}
    	
    	// Check diagonals
    	if (board[0][0][0] == board[1][1][1] && board[0][0][0] == board[2][2][2]  && board[0][0][0] == check){
	       	if(saveVictory){
	       		victoryIndex[0] = new Point(0,0,0);
		        victoryIndex[1] = new Point(1,1,1);
		        victoryIndex[2] = new Point(2,2,2);
		        //victoryIndex[3] = new Point(3,3,3);
	        }
	        return true;
		}	
    	if (board[0][0][2] == board[1][1][1] && board[0][0][2] == board[2][2][0]  && board[0][0][2] == check){
	       	if(saveVictory){
	       		victoryIndex[0] = new Point(0,0,3);
		        victoryIndex[1] = new Point(1,1,2);
		        victoryIndex[2] = new Point(2,2,1);
		        //victoryIndex[3] = new Point(3,3,0);
	        }
	        return true;
		}	
    	if (board[0][2][2] == board[1][1][1] && board[0][2][2] == board[2][0][0]  && board[0][2][2] == check){
	       	if(saveVictory){
	       		victoryIndex[0] = new Point(0,3,3);
		        victoryIndex[1] = new Point(1,2,2);
		        victoryIndex[2] = new Point(2,1,1);
		       // victoryIndex[3] = new Point(3,0,0);
	        }
	        return true;
		}	
    	if (board[0][2][0] == board[1][1][1] && board[0][2][0] == board[2][0][2]  && board[0][2][0] == check){
	       	if(saveVictory){
	       		victoryIndex[0] = new Point(0,3,0);
		        victoryIndex[1] = new Point(1,2,1);
		        victoryIndex[2] = new Point(2,1,2);
		        //victoryIndex[3] = new Point(3,0,3);
	        }
	        return true;
		}	
        
        return false;
    }

    public List<Point> getAvailableStates() {
        availablePoints = new ArrayList<>();
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
            	for(int k = 0; k < board.length; k++){
	                if (board[i][j][k] == 0) {
	                    availablePoints.add(new Point(i, j, k));
	                }
            	}
            }
        }
        return availablePoints;
    }

    public boolean placeAMove(Point point, int player, boolean record) {
    	if(board[point.x][point.y][point.z] != 1 && board[point.x][point.y][point.z] != 2  && validMove(point)){
    		board[point.x][point.y][point.z] = player;   //player = 1 for X, 2 for O
    		if(record){
	    		if(player == 2){
	    			moves.add("X: " + point.toString());
	    		}
	    		else{
	    			moves.add("O: " + point.toString());
	    		}
    		}
    		return true;
    	}
    	return false;
    }
    
    public boolean validMove(Point point){
    	if(point.x >= 0 && point.x < board.length && point.y >= 0 && point.y < board.length){
    		return true;
    	}
    	return false;
    }
    
    public void resetBoard() {
        for (int i = 0; i < board.length; ++i) {
            for (int j = 0; j < board.length; ++j) {
            	for(int z = 0; z < board.length; z++)
                board[i][j][z] = 0;
            }
        }
        victoryIndex[0] = new Point(-1,-1,-1);
		victoryIndex[1] = new Point(-1,-1,-1);
		victoryIndex[2] = new Point(-1,-1,-1);
		//victoryIndex[3] = new Point(-1,-1,-1);
		moves.clear();
    } 
}