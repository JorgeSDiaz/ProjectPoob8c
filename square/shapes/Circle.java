package shapes;

import java.awt.geom.Ellipse2D;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle extends Shapes{

    public static final float PI=3.1416f;
    
    protected int diameter;
    

    public Circle(){
        diameter = 30;
        xPosition = 50;
        yPosition = 50;
        color = "blue";
        isVisible = false;
    }
    
    public String getColor(){
        return color;
    }
    
    public int getXposition(){
        return xPosition;
    }
    
    public int getYposition(){
        return yPosition;
    }
    
    public void setXposition(int newXposition){
        xPosition = newXposition;
    }
    
    public void setYposition(int newYposition){
        yPosition = newYposition;
    }
    
    public int getDiameter(){
        return diameter;
    }

       
    public void makeVisible(){
        isVisible = true;
        draw(10);
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    protected void draw(int time){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
                canvas.draw(this, color,
                        new Ellipse2D.Double(xPosition, yPosition,
                                diameter, diameter), xPosition, yPosition, 0, false);
            canvas.wait(time);
        }
    }

    private void erase(){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        draw(10);
    }

    /**
     * Change the color. 
     * @param newColor the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta", "orange", "light blue", "violet" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        if (isVisible){
            draw(10);
        }
    }
}

