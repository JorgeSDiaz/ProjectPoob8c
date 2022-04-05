package shapes;

/**
 * A rectangle that can be manipulated and that draws itself on a canvas.
 * 
 * @author  Michael Kolling and David J. Barnes (Modified by Valentina de la Hoz y Jorge Saenz)
 * @version 1.5  (4 April 2022)
 */
public class Rectangle extends Shapes{

    public static int EDGES = 4;
    
    private int height;
    private int width;

    /**
     * Create a new rectangle at default position with default color.
     */
    public Rectangle(){
        height = 30;
        width = 40;
        xPosition = 70;
        yPosition = 15;
        color = "magenta";
        isVisible = false;
    }

    /**
     * Change the X and Y coordinates of the rectangle
     * @param x X coordinate
     * @param y Y coordinate
     */
    public void changePosition(int x, int y){
        xPosition = x;
        yPosition = y;
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

    /**
     * We obtain the width of the rectangle
     * @return width of the rectangle
     */
    public int getWidth(){
        return width;
    }

    /**
     * We obtain the height of the rectangle
     * @return height of the rectangle
     */
    public int getHeight(){
        return height;
    }

    /**
     * Make this rectangle visible. If it was already visible, do nothing.
     */
    public void makeVisible(){
        isVisible = true;
        draw();
    }
    
    /**
     * Make this rectangle invisible. If it was already invisible, do nothing.
     */
    public void makeInvisible(){
        erase();
        isVisible = false;
    }

    /**
     * Change the size to the new size
     * @param newHeight the new height in pixels. newHeight must be >=0.
     * @param newWidth the new width in pixels. newWidth must be >=0.
     */
    public void changeSize(int newHeight, int newWidth) {
        erase();
        height = newHeight;
        width = newWidth;
        draw();
    }
    
    /**
     * Change the color. 
     * @param newColor the new color. Valid colors are "red", "yellow", "blue", "green",
     * "magenta" and "black".
     */
    public void changeColor(String newColor){
        color = newColor;
        draw();
    }

    /**
     * Draw the rectangle with current specifications on screen.
     */
    private void draw() {
        if(isVisible) {
            shapes.Canvas canvas = shapes.Canvas.getCanvas();
            canvas.draw(this, color,
                new java.awt.Rectangle(xPosition, yPosition, 
                                       width, height), xPosition, yPosition, 180, false);
            canvas.wait(70);
        }
    }

    /**
     * Erase the rectangle on screen.
     */
    private void erase(){
        if(isVisible) {
            shapes.Canvas canvas = Canvas.getCanvas();
            canvas.erase(this);
        }
    }
}
