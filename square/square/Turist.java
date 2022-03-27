package square;

import shapes.Triangle;

/**
 * Simulate a tourist like a Triangle.
 * 
 * @author Luisa De la Hoz y Jorge Saenz. 
 * @version 8/02/22.
 */
public class Turist
{
    private boolean visible;
    private Triangle turis = new Triangle();
    protected int safeDistance;

    /**
     * Constructor for objects of class Turist
     */
    public Turist(int height, int width, int safeDistance)
    {
        newSize(height, width);
        turis.changeColor("magenta");
        turis.varyAngle(-90);
        this.safeDistance = safeDistance;
    }

    public int getSafeDistance(){ return safeDistance; }

    private void newSize(int height, int width){
        turis.changeSize(height / 10, width / 100);
    }
    
    /**
     * Change the color of the tourist
     * @Param newColor Name of the color to which you want to vary, 
     * you can choose between "red", "yellow", "blue", "green",
     * "magenta", "orange", "light blue", "violet" and "black".
     */
    public void setColor(String newColor){
        turis.changeColor(newColor);
    }
    
    /**
     * Gives the color that the tourist has
     */
    public String getColor(){
        return turis.getColor();
    }
    
    public int getHeight(){
        return turis.getHeight();
    }

    public int getWidth(){
        return turis.getWidth();
    }
    
    /**
     * Sets the angle the tourist faces.
     * @Param newAngle is the new angle.
     */
    public void setAngle(int newAngle){
        turis.varyAngle(newAngle);
    }
    
    public int getAngle(){
        return turis.getAngle();
    }
    
    /**
     * We change position to tourist.
     * @Param newXcor X coordinate of the tourist.
     * @Param newYcor Y coordinate of the tourist.
     */
    public void setCor(int newXcor, int newYcor){
        turis.setXposition(newXcor + safeDistance);
        turis.setYposition(newYcor + safeDistance);
    }

    public void setXposition(int newXcor){
        turis.setXposition(newXcor);
    }

    public void setYposition(int newYcor){
        turis.setYposition(newYcor);
    }

    /**
     * we get the coordinate x where the tourist is located.
     */
    public int getXcor(){
        return turis.getXposition();
    }
    
    /**
     * we get the coordinate y where the tourist is located.
     */
    public int getYcor(){
        return turis.getYposition();
    }
    
    /**
     * Makes the tourist visible.
     */
    public void makeVisible(){
        visible = true;
        turis.makeVisible();
    }
    
    /**
     * Makes the tourist invisible.
     */
    public void makeInvisible(){
        turis.makeInvisible();
        visible = false;
    }
}
