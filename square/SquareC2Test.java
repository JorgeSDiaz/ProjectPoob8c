

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class SquareC2Test.
 *
 * @author  Valentina de la Hoz y Jorge Saenz.
 * @version 4.0
 */
public class SquareC2Test
{
    private Square zoneA;
    private Square zoneB;
    /**
     * Default constructor for test class SquareC2Test
     */
    public SquareC2Test() throws ExceptionSquare {
        zoneA = new Square(1000, 1000, 5);
        int[] dimensions = {300, 230, 3};
        int[][] dome = {{100, 120}, {70, 90}, {150, 10}};
        int[] desiredView = {3, 1, 2};
        zoneB = new Square(dimensions, dome, desiredView);
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }
    
    @Test
    /**
     * A zone is created correctly
     */
    public void shouldCreateZoneDimensionsSafeDis(){
        Square zone = new Square(100, 100, 5);
        int[] dimensions = {100, 100};
        assertArrayEquals(dimensions, zone.getDimensions());
        assertEquals(5, zone.getSafetyDistance());
    }
    
    @Test
    /**
     * A zone is correctly created based on its dimensions, its domes and its desired view.
     */
    public void shouldCreateZoneMultiDoms(){
        int[] dimensions = {200, 200, 3};
        int[][] dome = {{100, 120}, {70, 90}, {150, 10}};
        int[] desiredView = {3, 1, 2};
        Square zone = new Square(dimensions, dome, desiredView);
        int[] dimensionsXY = {200, 200};
        assertArrayEquals(dimensionsXY, zone.getDimensions());
        assertEquals(3, zone.domes().length);
    }
    
    @Test
    /**
     * A new desired view is defined
     */
    public void shouldDefineRequestedPhoto(){
        String[] dome = {"red", "blue", "white"};
        zoneA.defineRequestedPhoto(dome);
        assertArrayEquals(dome, zoneA.getRequestedView());
    }
    
    @Test
    /**
     * a dome is added correctly
     */
    public void shouldAddDome() throws ExceptionSquare {
        zoneA.addDome("red", 50, 50);
        String[] dome = {"red"};
        assertArrayEquals(dome, zoneA.domes());
    }
    
    @Test
    /**
     * a dome is deleted correctly
     */
    public void shouldDelDome() throws ExceptionSquare {
        zoneA.addDome("pink", 50, 50);
        zoneA.addDome("light blue", 25, 25);
        zoneA.delDome("pink");
        assertEquals(1, zoneA.domes().length);
    }
    
    @Test
    /**
     * a tourist is added correctly
     */
    public void shouldTouristArrive() throws ExceptionSquare {
        zoneA.touristArrive("pink", 25, 50);
        zoneA.touristArrive("white", 30, 30);
        zoneA.touristArrive("black", 50, 60);
        assertEquals(3, zoneA.tourists().length);
    }
    
    @Test
    /**
     * a tourist moves in the right way
     */
    public void shoulTouristMove() throws ExceptionSquare {
        zoneA.touristArrive("pink", 25, 50);
        zoneA.touristArrive("white", 30, 30);
        zoneA.touristMove("pink", 50, 50, 75);
        zoneA.touristMove("white", 20, 10, 180);
        int[] pinkData = {50, 50, 75};
        int[] whiteData = {20, 10, 180};
        assertArrayEquals(pinkData, zoneA.tourist("pink"));
        assertArrayEquals(whiteData, zoneA.tourist("white"));
    }
    
    @Test
    /**
     * Photo is taken
     */
    public void shouldTouristTakePhoto() throws ExceptionSquare {
        zoneA.touristArrive("pink", 100, 100);
        zoneA.touristArrive("white", 200, 200);
        zoneA.touristMove("pink", 50, 50, 270);
        zoneA.touristMove("white", 20, 10, 90);
        zoneA.addDome("pink", 50, 50);
        zoneA.addDome("blue", 110, 25);
        zoneA.addDome("violet", 10, 90);
        zoneA.addDome("light blue", 200, 200);
        zoneA.addDome("orange", 150, 200);
        String[] pinkView = {"orange", "blue", "light blue"};
        String[] whiteView = {"violet"};
        assertArrayEquals(pinkView, zoneA.touristTakePhoto("pink"));
        assertArrayEquals(whiteView, zoneA.touristTakePhoto("white"));
    }
    
    @Test
    /**
     * It gives us who can take the defined forum
     */
    public void shouldWhoRequestedPhoto() throws ExceptionSquare {
        int[] dimensions = {300, 250, 3};
        int[][] dome = {{110, 25}, {200, 202}, {155, 201}};
        int[] desiredView = {1, 2, 3};
        Square zoneC = new Square(dimensions, dome, desiredView);
        zoneC.touristArrive("pink", 50, 50);
        zoneC.touristMove("pink", 50, 50, 270);
        String[] res = {"pink"};
        assertArrayEquals(res, zoneC.whoRequestedPhoto());
    }

    @Test
    /**
     * A complete list of domes in the area is obtained.
     */
    public void shouldGetDomes() throws ExceptionSquare {
        zoneA.addDome("red", 10, 10);
        zoneA.addDome("orange", 40, 40);
        String[] domes = {"red", "orange"};
        assertArrayEquals(domes, zoneA.domes());
    }

    @Test
    /**
     * A complete list of tourists in the area is obtained.
     */
    public void shouldGetTourist() throws ExceptionSquare {
        zoneA.touristArrive("red", 10, 10);
        zoneA.touristArrive("orange", 40, 40);
        String[] tourist = {"red", "orange"};
        assertArrayEquals(tourist, zoneA.tourists());
    }

    @Test
    /**
     * A list with complete information about a specific dome in the area is obtained.
     */
    public void shouldGetDomeInfo() throws ExceptionSquare {
        zoneA.addDome("red", 10, 10);
        int[] domeInfo = {10, 10};
        assertArrayEquals(domeInfo, zoneA.dome("red"));
    }

    @Test
    /**
     * A list with internally modified information to fit the area is obtained from a tourist.
     */
    public void shouldGetTouristInfo() throws ExceptionSquare {
        zoneA.touristArrive("orange", 40, 40);
        int[] touristInfo = {45, 45, 90};
        assertArrayEquals(touristInfo, zoneA.tourist("orange"));
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }
}
