package square;

public class ExceptionSquare extends Exception{
    public final static String ALREADY_ADDED = "The element has already been added";
    public final static String BAD_LOCATION = "Object moves out of the zone";
    public final static String NOT_ADDED = "Item has not been added";
    public final static String NO_PHOTO = "Photo cannot be taken";
    public final static String FIXE_NODELETE = "dome fixed cannot be removed";
    public final static String NO_IS_A_TYPE = "Nonexistent type";


    public ExceptionSquare(String message){ super(message); }
}
