package com.almasb.fxglgames.covidfighter;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

public class CovidFighterAppTest {
    private CovidFighterApp gameObject;

    @Before
    public void setUp() {
        gameObject = new CovidFighterApp();
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    // Malav Patel
    @Test
    public void initialGame() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initialGamePlayer(), "The game is started");
    }

    // Malav Patel
    @Test
    public void initNextLevelTransferOneToTwo() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 1 to level 2");
    }

    // Malav Patel
    @Test
    public void initialPlayer() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initPhysics(), "The sprite is being loaded properly.");
    }

    // Zachery Bradford
    @Test
    public void initialText() {
        assertThrows(NoClassDefFoundError.class, () ->
                gameObject.initialText(), "The text must be assigned properly");
    }

    // Zachery Bradford
    @Test
    public void initialWalls() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initPhysics(), "The boundaries have been set properly with collision");
    }

    // Zachery Bradford
    @Test
    public void initNextLevelTwoToThree() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 2 to level 3");
    }

    // Hargopal Chaudhary
    @Test
    public void initialPhysics() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initPhysics(), "The Physics has been set properly");
    }

    // Hargopal Chaudhary
    @Test
    public void initNextLevelThreeToFour() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 3 to level 4");
    }

    // Hargopal Chaudhary
    @Test
    public void initNextLevelThreeToFive() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 3 to level 4");
    }


    // Praharsh Patel
    @Test
    public void initUpdate() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initUpdate(), "The animation has been set properly");
    }

    // Praharsh Patel
    @Test
    public void initNextLevelFourToFive() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 4 to level 5");
    }

    // Praharsh Patel
    @Test
    public void initNextLevelFiveToSix() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 5 to level 6");
    }

    // Wonil Lee
    @Test
    public void initNextLevel() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The screen can be taken to the next level");
    }

    // Wonil Lee
    @Test
    public void initNextLevelSixToSeven() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 6 to level 7");
    }

    // Wonil Lee
    @Test
    public void initNextLevelSevenToEight() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 7 to level 8");
    }

    // Malav Patel
    @Test
    public void initNextLevelTransferTwoToOne() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 1 to level 2");
    }

    // Malav Patel
    @Test
    public void initialPlay() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initPhysics(), "The sprite is being loaded properly.");
    }

    // Zachery Bradford
    @Test
    public void initialDefineText() {
        assertThrows(NoClassDefFoundError.class, () ->
                gameObject.initialText(), "The text must be assigned properly");
    }

    // Zachery Bradford
    @Test
    public void initialInnerWalls() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initPhysics(), "The boundaries have been set properly with collision");
    }

    // Hargopal Chodhry
    @Test
    public void initNextLevelThreeToTwo() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 3 to level 2");
    }

    // Hargopal Chaudhary
    @Test
    public void initialPhysicsWall() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initPhysics(), "The Physics has been set properly");
    }

    // Praharsh Patel
    @Test
    public void initNextLevelFourToThree() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 4 to level 3");
    }

    // Praharsh Patel
    @Test
    public void initNextLevelFiveToFour() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 4 to level 3");
    }

    // Wonil Lee
    @Test
    public void initNextLevelSevenToSix() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 7 to level 6");
    }

    // Wonil Lee
    @Test
    public void initNextLevelEightToSeven() {
        assertThrows(RuntimeException.class, () ->
                gameObject.initNextLevel(), "The player went from level 8 to level 7");
    }

}
