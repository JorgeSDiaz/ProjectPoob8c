package square;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
    public SquareC2Test(){
        zoneA = new Square(1000, 1000, 5);
        int[] dimensions = {300, 230, 3};
        int[][] dome = {{100, 120}, {70, 90}, {150, 10}};
        int[] desiredView = {2, 1, 3};
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
    public void shouldAddDomeNormal() throws ExceptionSquare {
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

    @Test
    /**
     * The fixed dome is tested to ensure that it cannot be deleted.
     */
    public void ShouldFixeNoDelete(){
        try {
            zoneA.addDome("green", 200, 200, "fixed");
            zoneA.delDome("green");
        } catch (ExceptionSquare t){
            assertEquals(ExceptionSquare.FIXE_NODELETE, t.getMessage());
        }
    }

    @Test
    /**
     * Exception tested when registering a non-existent special type
     */
    public void ShouldntAddNoSpecialDome(){
        try {
            zoneA.addDome("green", 200, 200, "normal");
        } catch (ExceptionSquare t){
            assertEquals(ExceptionSquare.NO_IS_A_TYPE, t.getMessage());
        }
    }

    @Test
    /**
     * We check that the shy dome changes its position when a tourist takes a picture of it.
     */
    public void ShouldShyMove(){
        try {
            int[] coors = {150, 200};
            zoneA.addDome("pink", 150, 200, "shy");
            zoneA.touristArrive("white", 50, 50);
            zoneA.touristMove("white", 50, 50, 270);
            assertNotEquals(coors, zoneA.dome("pink"));
        } catch (ExceptionSquare t){
            fail("Exception : "+t.getMessage());
        }
    }

    @Test
    /**
     * We test that the perfectionist only takes the desired photo.
     */
    public void ShouldPerfectionistNoTakePhoto(){
        try {
            String[] desired = {"red", "blue", "white"};
            zoneA.defineRequestedPhoto(desired);
            zoneA.touristArrive("pink", 100, 100, "perfectionist");
            zoneA.touristMove("pink", 50, 50, 270);
            zoneA.addDome("blue", 110, 25);
            zoneA.addDome("light blue", 200, 200);
            zoneA.addDome("orange", 150, 200);
            String[] pinkView = {"orange", "blue", "light blue"};
            assertNotEquals(pinkView, zoneA.touristTakePhoto("pink"));
        } catch (ExceptionSquare t){
            fail("Exception : "+t.getMessage());
        }
    }

    @Test
    /**
     * We test that the prudent tourist maintains double the safety distance.
     */
    public void ShouldPrudentHaveDoubleSafeDistance(){
        try {
            zoneA.touristArrive("violet", 10, 200, "prudent");
            int[] tourist = zoneA.tourist("violet");
            assertEquals(210, tourist[1]);
        } catch (ExceptionSquare t){
            fail("Exception : "+t.getMessage());
        }
    }

    @Test
    /**
     * The fact that the 360° tourist takes pictures of all the domes regardless of their angle of view is tested.
     */
    public void ShouldTourist360SeeEverything(){
        try {
            zoneA.addDome("blue", 110, 25);
            zoneA.addDome("light blue", 20, 200);
            zoneA.addDome("orange", 900, 40);
            zoneA.touristArrive("violet", 10, 200, "turis360");
            ArrayList<String> photo = new ArrayList<>(Arrays.asList(zoneA.touristTakePhoto("violet")));
            assertTrue(photo.contains("blue"));
            assertTrue(photo.contains("orange"));
            assertTrue(photo.contains("light blue"));
        } catch (ExceptionSquare t){
            fail("Exception : "+t.getMessage());
        }
    }

    @Test
    /**
     * Exception tested when registering a non-existent special type
     */
    public void ShouldntAddNoSpecialTourist(){
        try {
            zoneA.touristArrive("green", 500, 10, "normal");
        } catch (ExceptionSquare t){
            assertEquals(ExceptionSquare.NO_IS_A_TYPE, t.getMessage());
        }
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
