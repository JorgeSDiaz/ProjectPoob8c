import java.awt.geom.*;
/**
 * represents the dome of a construction like a Circle.
 * 
 * @author Luisa De la Hoz y Jorge Saenz.
 * @version 8/02/22.
 */
public class Dome
{
    private Circle dome = new Circle();
    private boolean visible;

    /**
     * Constructor for objects of class Dome
     */
    public Dome(int size)
    {
        dome.changeColor("light blue");
        dome.changeSize(size  / 10);
        dome.setXposition(50);
        dome.setYposition(50);
        visible = false;
    }
    
    public int getDiameter(){
        return dome.getDiameter();
    }
    
    /**
     * Change the color of the dome.
     * @Param newColor Name of the color to which you want to vary, 
     * you can choose between "red", "yellow", "blue", "green",
     * "magenta", "orange", "light blue", "violet" and "black".
     */
        public void setColor(String newColor){
        dome.changeColor(newColor);
    }
    
    /**
     * Gives the color that the dome has.
     */
    public String getColor(){
        return dome.getColor();
    }
    
    /**
     * We change position to dome.
     * @Param newXcor X coordinate of the dome.
     * @Param newYcor Y coordinate of the dome.
     */
    public void setCor(int newXcor, int newYcor){
        dome.setXposition(newXcor);
        dome.setYposition(newYcor);
        draw(10);
    }
    
    /**
     * we get the coordinate x where the dome is located.
     */
    public int getXcor(){
        return dome.getXposition();
    }
    
    /**
     * we get the coordinate y where the dome is located.
     */
    public int getYcor(){
        return dome.getYposition();
    }
    
    /**
     * Makes the dome visible.
     */
    public void makeVisible(){
        visible = true;
        draw(300);
    }
    
    /**
     * Makes the dome invisible.
     */
    public void makeInvisible(){
        visible = true;
        dome.makeInvisible();
    }
    
    /**
     * We draw the dome as a circle.
     * @Param time canvas refresh time.
     */
    private void draw(int time){
        if(visible) {
            Canvas canvas = Canvas.getCanvas();
            canvas.draw(this, dome.getColor(), 
                new Ellipse2D.Double(dome.getXposition(), dome.getYposition(), 
                dome.getDiameter(), dome.getDiameter()), dome.getXposition(),
                dome.getYposition(), 0, false);
            canvas.wait(time);
        }
    }
}
