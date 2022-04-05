package shapes;

import shapes.Canvas;

import java.awt.*;

/**
 * A triangle that can be manipulated and that draws itself on a canvas.
 *
 * @author  Michael Kolling and David J. Barnes (Modified by Valentina de la Hoz y Jorge Saenz)
 * @version 1.5  (4 April 2022)
 */

public class Triangle extends Shapes{
    
    public static int VERTICES=3;
    
    protected int height;
    protected int width;
    protected int angle;
    private boolean isRotate;

    /**
     * Create a new triangle at default position with default color.
     */
    public Triangle(){
        height = 30;
        width = 20;
        xPosition = 140;
        yPosition = 15;
        color = "green";
        isVisible = false;
        angle = 0;
        isRotate = false;
    }
    /**
     * We obtain the height of the Triangle
     * @return height of the Triangle
     */
    public int getHeight(){
        return height;
    }

    /**
     * We obtain the width of the Triangle
     * @return width of the Triangle
     */
    public int getWidth() {return width;}
    
    /**
     * Sets the angle the triangle faces.
     * @Param newAngle is the new angle.
     */
    public void varyAngle(int newAngle){
        erase();
        if (newAngle >= 0 && newAngle < 360){
            angle = -newAngle;
        } else {
            if (newAngle > 0){
                int var = newAngle / 360;
                angle = -newAngle + (360 * var);
            } else{
                int var = newAngle / 360;
                angle = newAngle - (360 * var);
            }
        }
        isRotate = true;
        if (isVisible){
            draw();
        }
        isRotate = false;
    }

    /**
     * We obtain the angle to which the triangle is rotated.
     * @return angle
     */
    public int getAngle(){
        if (angle < 0){
            return -angle;
        } else {
            return angle;
        }
    }

    /**
     * The new X coordinate of the triangle is assigned.
     * @param newXposition new X coordinate
     */
    public void setXposition(int newXposition){
        erase();
        xPosition = newXposition;
        draw();
    }
    
    public int getXposition(){
        return xPosition;
    }

    /**
     * The new Y coordinate of the triangle is assigned.
     * @param newYposition new Y coordinate
     */
    public void setYposition(int newYposition){
        erase();
        yPosition = newYposition;
        if (isVisible){
            draw();
        }
    }
    
    public int getYposition(){
        return yPosition;
    }
    
    public String getColor(){
        return color;
    }

    /**
     * Make this triangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        isRotate = true;
        draw();
    }
    
    /**
     * Make this triangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isRotate = false;
        isVisible = false;
    }

    /**
     * Move the triangle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw();
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidth the new width in pixels. newWidht must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        if (isVisible){
            draw();
        }
    }
    
    /**
     * Change the color. 
     * @param newColor the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        if (isVisible){
            draw();
        }
    }

    /**
     * Draw the triangle with current specifications on screen.
     */
    private void draw(){
        if(isVisible) {
            shapes.Canvas canvas = shapes.Canvas.getCanvas();
            int[] xPoints = {xPosition, xPosition + (width/2), xPosition - (width/2)};
            int[] yPoints = {yPosition, yPosition + height , yPosition + height};
            canvas.draw(this, color, new Polygon(xPoints, yPoints, 3), xPosition,(3*yPosition+2*height)/3,angle,isRotate);
            canvas.wait(10);
        }
    }

    /**
     * Erase the triangle on screen.
     */
    private void erase(){
        if(isVisible) {
            shapes.Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}