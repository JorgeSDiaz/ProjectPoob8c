package shapes;

import java.awt.*;
import java.awt.geom.*;
import java.util.List;
import java.util.Arrays;
import java.util.*;

/**
 * A circle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes
 * @version 1.0.  (15 July 2000) 
 */

public class Circle{

    public static final float PI=3.1416f;
    
    private int diameter;
    private float cArea;
    private int xPosition;
    private int yPosition;
    private String color;
    private int[] colorRGB;
    private boolean isVisible;
    

    public Circle(){
        diameter = 30;
        cArea = area();
        xPosition = 20;
        yPosition = 15;
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
    
    public void setDiameter(int newDiameter){
        diameter = newDiameter;
    }
    
    public int getDiameter(){
        return diameter;
    }
    
    /**
     * We change the size of the circle deducting the diameter from the area 
     * that the user needs
     * @param area you must enter a number, if it has a decimal part put an 
     * f at the end
     */
    public void changeArea(float newArea){
        float radio = (float)Math.sqrt(newArea / PI);
        diameter = 2 * (int)radio;
        changeSize(diameter);
    }
    
    /**
     * Having a list of strings with the colors of the rainbow,
     * with the help of a for loop, one by one we change the color
     * of the circle and draw it again.
     */
    public void rainbow(){
        List<String> rainbowColors = Arrays.asList("red", "orange", "yellow", 
        "green", "light blue", "blue", "violet");
        for (int i = 0; i < 7; i++){
            color = rainbowColors.get(i);
            draw(800);
        }
    }
    
     /**
     * We calculate the radius from the diameter and return the result 
     * of the formula for the area of a circle
     */
    public float area(){
        float radio = diameter / 2;
        return PI * (radio * radio);
    }
    
    /**
     * we use the function of changing size sending by 
     * parameter the double of the current diameter
     */
    public void duplicate(){
        changeSize(diameter * 2);
    }

       
    public void makeVisible(){
        isVisible = true;
        draw(10);
    }
    

    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    private void draw(int time){
        if(isVisible) {
            Canvas canvas = Canvas.getCanvas();
            if (colorRGB.length == 0){
                canvas.draw(this, color,
                        new Ellipse2D.Double(xPosition, yPosition,
                                diameter, diameter), xPosition, yPosition, 0, false);
            } else {
                canvas.draw(this, colorRGB,
                        new Ellipse2D.Double(xPosition, yPosition,
                                diameter, diameter), xPosition, yPosition, 0, false);
            }
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
     * Move the circle a few pixels to the right.
     */
    public void moveRight(){
        moveHorizontal(20);
    }

    /**
     * Move the circle a few pixels to the left.
     */
    public void moveLeft(){
        moveHorizontal(-20);
    }

    /**
     * Move the circle a few pixels up.
     */
    public void moveUp(){
        moveVertical(-20);
    }

    /**
     * Move the circle a few pixels down.
     */
    public void moveDown(){
        moveVertical(20);
    }

    /**
     * Move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void moveHorizontal(int distance){
        erase();
        xPosition += distance;
        draw(10);
    }

    /**
     * Move the circle vertically.
     * @param distance the desired distance in pixels
     */
    public void moveVertical(int distance){
        erase();
        yPosition += distance;
        draw(10);
    }

    /**
     * Slowly move the circle horizontally.
     * @param distance the desired distance in pixels
     */
    public void slowMoveHorizontal(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        } else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            xPosition += delta;
            draw(10);
        }
    }

    /**
     * Slowly move the circle vertically
     * @param distance the desired distance in pixels
     */
    public void slowMoveVertical(int distance){
        int delta;

        if(distance < 0) {
            delta = -1;
            distance = -distance;
        }else {
            delta = 1;
        }

        for(int i = 0; i < distance; i++){
            yPosition += delta;
            draw(10);
        }
    }

    /**
     * Change the size.
     * @param newDiameter the new size (in pixels). Size must be >=0.
     */
    public void changeSize(int newDiameter){
        erase();
        diameter = newDiameter;
        cArea = area();
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

    public void changeColor(int[] newColor){
        colorRGB = newColor;
        if (isVisible){
            draw(10);
        }
    }



}

