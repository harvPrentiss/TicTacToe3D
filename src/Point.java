
public class Point {

	int x, y,z;

    public Point(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    
    public boolean equals(Point p){
    	if(this.x == p.x && this.y == p.y && this.z == p.z){
    		return true;
    	}
    	else{
    		return false;
    	}
    }

    @Override
    public String toString() {
        return "[" + x + ", " + y + ", " + z +"]";
    }

}
