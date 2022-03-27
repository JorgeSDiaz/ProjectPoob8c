package square;

import square.Square;

/**
 * solve the marathon problem
 * 
 * @author Luisa de la Hoz y Jorge Saenz. 
 * @version (1.0)
 */
public class SquareContest
{
    private Square square;
    private float solve;

    /**
     * Returns the area of ​​the desired photo
     * 
     */
    public float solve(int[] dimensions, int[][] domes, int[]desiredView) throws ExceptionSquare {
        square = new Square(dimensions, domes, desiredView);
        square.takeRequestedPhoto();
        solve = 0;
        return solve;
    }
    
    /**
     * 
     */
    public void simulate(int[] dimensions, int[][] domes, int[]desiredView) throws ExceptionSquare {
        square = new Square(dimensions, domes, desiredView);
        square.makeVisible();
    }
}
