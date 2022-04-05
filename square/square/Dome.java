package square;

import shapes.Circle;
/**
 * represents the dome of a construction like a Circle.
 * 
 * @author Luisa De la Hoz y Jorge Saenz.
 * @version 8/02/22.
 */
public class Dome extends Circle
{
    protected boolean canBeRemoved;
    protected int position;

    /**
     * Constructor for objects of class Dome
     */
    public Dome(int size)
    {
        super();
        this.changeSize(size  / 25);
        canBeRemoved = true;
        position = 0;
    }

    /**
     *
     * @param newPosition
     */
    public void setPosition(int newPosition) {
        position = newPosition;
    };

    /**
     *
     * @return
     */
    public int getPosition() { return position; }

    /**
     *
     * @return
     */
    public boolean isCanBeRemoved(){ return this.canBeRemoved; }

    /**
     *
     * @param xCoor
     * @param yCoor
     */
    public void setCor(int xCoor, int yCoor){
        this.setXposition(xCoor);
        this.setYposition(yCoor);
        this.makeVisible();
    }
}
