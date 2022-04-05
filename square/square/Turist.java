package square;

import shapes.Triangle;

/**
 * Simulate a tourist like a Triangle.
 * 
 * @author Luisa De la Hoz y Jorge Saenz. 
 * @version 4/04/22.
 */
public class Turist extends Triangle
{
    protected int safeDistance;

    /**
     * Constructor for objects of class Turist
     */
    public Turist(int height, int width, int safeDistance)
    {
        super();
        newSize(height, width);
        this.varyAngle(-90);
        this.safeDistance = safeDistance;
    }

    /**
     * We obtain the tourist's safety distance
     * @return safety distance
     */
    public int getSafeDistance(){ return safeDistance; }

    /**
     * We assign a new size to scale
     * @param height size
     * @param width size
     */
    private void newSize(int height, int width){
        this.changeSize(height / 10, width / 10);
    }
    
    /**
     * Change the color of the tourist
     * @Param newColor Name of the color to which you want to vary, 
     * you can choose between "red", "yellow", "blue", "green",
     * "magenta", "orange", "light blue", "violet" and "black".
     */
    public void setColor(String newColor){
        this.changeColor(newColor);
    }
    
    /**
     * We change position to tourist.
     * @Param newXcor X coordinate of the tourist.
     * @Param newYcor Y coordinate of the tourist.
     */
    public void setCor(int newXcor, int newYcor){
        this.setXposition(newXcor + safeDistance);
        this.setYposition(newYcor + safeDistance);
    }

    @Override
    public int getXposition(){
        return this.xPosition;
    }

    @Override
    public int getYposition(){
        return yPosition;
    }
}
