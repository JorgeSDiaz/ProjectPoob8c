package shapes;

/**
 * Abstract class of figures
 *
 * @author Valentina de la Hoz y Jorge Saenz
 * @version 1.0
 */
public abstract class Shapes {
    protected int xPosition;
    protected int yPosition;
    protected String color;
    protected boolean isVisible;

    /**
     * We obtain the color of the figure
     * @return Color
     */
    public abstract String getColor();

    /**
     * We obtain the position in X of the figure
     * @return position in X
     */
    public abstract int getXposition();

    /**
     * We obtain the position in Y of the figure
     * @return position in Y
     */
    public abstract int getYposition();


}
