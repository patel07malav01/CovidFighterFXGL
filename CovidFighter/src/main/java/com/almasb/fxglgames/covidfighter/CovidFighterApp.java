package com.almasb.fxglgames.covidfighter;

import com.almasb.fxgl.animation.AnimatedValue;
import com.almasb.fxgl.animation.Animation;
import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.ApplicationMode;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.core.util.LazyValue;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.level.Level;
import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.ui.Position;
import com.almasb.fxgl.ui.ProgressBar;
import com.almasb.fxgl.ui.UI;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.*;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Arrow.DragonDownArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Arrow.DragonLeftArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Arrow.DragonRightArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Arrow.DragonUpArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Bullet.DragonDownBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Bullet.DragonLeftBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Bullet.DragonRightBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Bullet.DragonUpBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.FireBall.DragonDownFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.FireBall.DragonLeftFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.FireBall.DragonRightFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.FireBall.DragonUpFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Vaccine.DragonDownVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Vaccine.DragonLeftVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Vaccine.DragonRightVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.dragon.Vaccine.DragonUpVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Arrow.PurpleEnemyDownArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Arrow.PurpleEnemyLeftArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Arrow.PurpleEnemyRightArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Arrow.PurpleEnemyUpArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Bullet.PurpleEnemyDownBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Bullet.PurpleEnemyLeftBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Bullet.PurpleEnemyRightBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Bullet.PurpleEnemyUpBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.FireBall.PurpleEnemyDownFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.FireBall.PurpleEnemyLeftFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.FireBall.PurpleEnemyRightFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.FireBall.PurpleEnemyUpFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Vaccine.PurpleEnemyLeftVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Vaccine.PurpleEnemyRightVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Vaccine.PurpleEnemyUpVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Arrow.RedEnemyDownArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Arrow.RedEnemyLeftArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Arrow.RedEnemyRightArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Arrow.RedEnemyUpArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Bullet.*;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.FireBall.RedEnemyDownFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.FireBall.RedEnemyLeftFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.FireBall.RedEnemyRightFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.FireBall.RedEnemyUpFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Vaccine.RedEnemyDownVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Vaccine.RedEnemyLeftVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Vaccine.RedEnemyRightVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redEnemy.Vaccine.RedEnemyUpVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Arrow.RedPurpleDownArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Arrow.RedPurpleLeftArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Arrow.RedPurpleRightArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Arrow.RedPurpleUpArrowHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Bullet.RedPurpleDownBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Bullet.RedPurpleLeftBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Bullet.RedPurpleRightBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Bullet.RedPurpleUpBulletHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.FireBall.RedPurpleDownFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.FireBall.RedPurpleLeftFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.FireBall.RedPurpleRightFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.FireBall.RedPurpleUpFireballHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Vaccine.RedPurpleDownVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Vaccine.RedPurpleLeftVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Vaccine.RedPurpleRightVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Vaccine.RedPurpleUpVaccineHandler;
import com.almasb.fxglgames.covidfighter.collisions.weaponHandler.Arrow.*;
import com.almasb.fxglgames.covidfighter.collisions.weaponHandler.Bullet.*;
import com.almasb.fxglgames.covidfighter.collisions.weaponHandler.Fireball.*;
import com.almasb.fxglgames.covidfighter.components.PlayerComponent;
import com.almasb.fxglgames.covidfighter.event.GameEvent;
import com.almasb.fxglgames.covidfighter.ui.MyMenu;
import com.almasb.fxglgames.covidfighter.ui.NewLevelSubScene;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import javax.swing.text.View;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.stream.IntStream;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxglgames.covidfighter.Config.Asset;
import static com.almasb.fxglgames.covidfighter.Config.START_LIVES;
import static com.almasb.fxglgames.covidfighter.EntityType.*;

//import javafx.scene.text.Font;
//import javafx.scene.text.FontPosture;
//import javafx.scene.text.FontWeight;
//import javafx.scene.text.Text;

/**
 * CS 2340 PROJECT SUMMER 2021
 * COVID FIGHTER
 *
 * This runs on the FXGL game engine.
 * I do not own the engine. Credit for engine goes to:
 * Almas Baimagambetov (https://github.com/AlmasB)
 *
 * @author Zachery Bradford (zbradford3@gatech.edu), Malav Patel (malavp@gatech.edu),
 * @version 0.0.5
 */

public class CovidFighterApp extends GameApplication {

    private static final int STARTING_LEVEL = 0;
    private static final int MAX_LEVEL = 8;
    private static DecimalFormat df2 = new DecimalFormat("#.##");
    private int currentLevelNumber = 0;

    private int highScore;

    private PlayerComponent playerComp;
    private LazyValue<NewLevelSubScene> levelSubScene
            = new LazyValue<>(() -> new NewLevelSubScene());

    private String highScoreName;

    private CovidFighterController uiController;

    private PlayerComponent playerComponent;
//    private Text textUserTime = getUIFactoryService().newText("", Color.WHITE, 24.0);

    private Entity player;
    private Entity purpleEnemy;
    private Entity redEnemy;
    private Entity dragonEnemy;
    private Entity redPurpleEnemy;

    private String userName;

    //General Settings
    @Override
    protected void initSettings(GameSettings gameSettings) {
        gameSettings.setWidth(480);
        gameSettings.setHeight(480);
        gameSettings.setApplicationMode(ApplicationMode.RELEASE);
        gameSettings.setTitle("Covid Fighter");
        gameSettings.setVersion("0.0.5");
        gameSettings.setGameMenuEnabled(true);
        gameSettings.setMainMenuEnabled(true);
        gameSettings.getEnabledMenuItems();
        gameSettings.setSceneFactory(new MySceneFactory());
    }

    //First process of game
    protected void initGame() {
        CovidFighterFactory cv = new CovidFighterFactory();

        getGameScene().setBackgroundColor(Color.BLACK);
        getGameWorld().addEntityFactory(cv);
        player = null;

        nextLevel();

        if (currentLevelNumber == 1) {
            player = spawn("player", 2230, 738);
            playerComponent = player.getComponent(PlayerComponent.class);

            purpleEnemy = spawn("purple_enemy", 2212, 909);
            purpleEnemy = spawn("purple_enemy", 2317, 821);
            purpleEnemy = spawn("purple_enemy", 2324, 766);
            purpleEnemy = spawn("purple_enemy", 2272, 685);
            purpleEnemy = spawn("purple_enemy", 2282, 902);
            purpleEnemy = spawn("purple_enemy", 2152, 1743);
            purpleEnemy = spawn("purple_enemy", 2162, 1805);
            purpleEnemy = spawn("purple_enemy", 2028, 2108);
            purpleEnemy = spawn("purple_enemy", 2125, 1993);
        }
        set("player", player);
        set("levelTime", 0.0);
        getGameScene().getViewport().bindToEntity(player, getAppWidth() / 2, getAppHeight() / 2);
        getGameScene().getViewport().setBounds(0, 0, 2400, 2400);
    }

    public boolean initialGamePlayer() {
        try {
            initGame();
            initGame();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed");
        }
    }

    @Override
    protected void initUI() {
        uiController = new CovidFighterController(getGameScene());

        UI ui = getAssetLoader().loadUI(Asset.FXML_MAIN_UI, uiController);

        IntStream.range(0, geti("lives"))
                .forEach(i -> uiController.addLife());

        getGameScene().addUI(ui);

        ProgressBar hpBarPurpleEnemy = new ProgressBar();
        hpBarPurpleEnemy.setMinValue(0);
        hpBarPurpleEnemy.setMaxValue(2);
        hpBarPurpleEnemy.setCurrentValue(100);
        hpBarPurpleEnemy.setWidth(12.5);
        hpBarPurpleEnemy.setTranslateX(323);
        hpBarPurpleEnemy.setTranslateY(5);
        hpBarPurpleEnemy.setLabelVisible(true);
        hpBarPurpleEnemy.setLabelPosition(Position.RIGHT);
        hpBarPurpleEnemy.setFill(Color.PURPLE);

        ProgressBar hpBarRedEnemy = new ProgressBar();
        hpBarRedEnemy.setMinValue(0);
        hpBarRedEnemy.setMaxValue(4);
        hpBarRedEnemy.setCurrentValue(100);
        hpBarRedEnemy.setWidth(25);
        hpBarRedEnemy.setTranslateX(323);
        hpBarRedEnemy.setTranslateY(25);
        hpBarRedEnemy.setLabelVisible(true);
        hpBarRedEnemy.setLabelPosition(Position.RIGHT);
        hpBarRedEnemy.setFill(Color.RED);

        ProgressBar hpBarRedPurpleEnemy = new ProgressBar();
        hpBarRedPurpleEnemy.setMinValue(0);
        hpBarRedPurpleEnemy.setMaxValue(10);
        hpBarRedPurpleEnemy.setCurrentValue(10);
        hpBarRedPurpleEnemy.setWidth(62.5);
        hpBarRedPurpleEnemy.setTranslateX(323);
        hpBarRedPurpleEnemy.setTranslateY(45);
        hpBarRedPurpleEnemy.setLabelVisible(true);
        hpBarRedPurpleEnemy.setLabelPosition(Position.RIGHT);
        hpBarRedPurpleEnemy.setFill(Color.MAGENTA);

        ProgressBar hpBarDragonEnemy = new ProgressBar();
        hpBarDragonEnemy.setMinValue(0);
        hpBarDragonEnemy.setMaxValue(16);
        hpBarDragonEnemy.setCurrentValue(16);
        hpBarDragonEnemy.setWidth(100);
        hpBarDragonEnemy.setTranslateX(323);
        hpBarDragonEnemy.setTranslateY(65);
        hpBarDragonEnemy.setLabelVisible(true);
        hpBarDragonEnemy.setLabelPosition(Position.RIGHT);
        hpBarDragonEnemy.setFill(Color.GREEN);

        getGameScene().addUINode(hpBarRedEnemy);
        getGameScene().addUINode(hpBarPurpleEnemy);
        getGameScene().addUINode(hpBarRedPurpleEnemy);
        getGameScene().addUINode(hpBarDragonEnemy);


    }

    public boolean initialText() {
        try {
            initUI();
            return true;
        } catch (Exception e) {
            throw new NoClassDefFoundError("Failed");
        }
    }

    private void setLevel(int levelNum) {

        Level level = setLevelFromMap("tmx/level" + levelNum  + ".tmx");
        if (levelNum == 1) {
            animateCamera(() -> {
                getSceneService().pushSubScene(new NewLevelSubScene());
            });
        }

        if (player != null && levelNum == 2) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(480, 480));
            player.setZIndex(Integer.MAX_VALUE);
            purpleEnemy = spawn("purple_enemy", 516, 118);
            purpleEnemy = spawn("purple_enemy", 642, 178);
            purpleEnemy = spawn("purple_enemy", 705, 285);
            purpleEnemy = spawn("purple_enemy", 663, 434);
            purpleEnemy = spawn("purple_enemy", 643, 549);
            purpleEnemy = spawn("purple_enemy", 658, 708);
            redEnemy = spawn("red_enemy", 728, 389);

        } else if (player != null && levelNum == 3) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(896, 512));
            player.setZIndex(Integer.MAX_VALUE);
            purpleEnemy = spawn("purple_enemy", 633, 202);
            redEnemy = spawn("red_enemy", 681 , 276);
            purpleEnemy = spawn("purple_enemy", 743, 347);
            redEnemy = spawn("red_enemy", 779, 409);
            purpleEnemy = spawn("purple_enemy", 770, 499);
            purpleEnemy = spawn("purple_enemy", 720, 591);
            redEnemy = spawn("red_enemy", 690, 688);
            purpleEnemy = spawn("purple_enemy", 553, 767);

        } else if (player != null && levelNum == 4) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(480, 896));
            player.setZIndex(Integer.MAX_VALUE);
            purpleEnemy = spawn("purple_enemy", 707, 223);
            redEnemy = spawn("red_enemy", 752, 283);
            redEnemy = spawn("red_enemy", 774, 359);
            redEnemy = spawn("red_enemy", 824, 402);
            redEnemy = spawn("red_enemy", 819, 476);
            redEnemy = spawn("red_enemy", 816, 550);
            purpleEnemy = spawn("purple_enemy", 755, 612);
            redEnemy = spawn("red_enemy", 654, 708);

        } else if (player != null && levelNum == 5) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(416, 896));
            player.setZIndex(Integer.MAX_VALUE);
            purpleEnemy = spawn("purple_enemy", 637, 155);
            purpleEnemy = spawn("purple_enemy", 698, 237);
            redEnemy = spawn("red_enemy", 594, 628);
            redEnemy = spawn("red_enemy", 549, 704);
            redEnemy = spawn("red_enemy", 701, 355);
            redEnemy = spawn("red_enemy", 723, 450);
            redPurpleEnemy = spawn("redPurple_enemy", 752, 527);

        } else if (player != null && levelNum == 6) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(480, 896));
            player.setZIndex(Integer.MAX_VALUE);
            purpleEnemy = spawn("purple_enemy", 686, 134);
            purpleEnemy = spawn("purple_enemy", 710, 199);
            redEnemy = spawn("red_enemy", 794, 308);
            redEnemy = spawn("red_enemy", 760, 404);
            redEnemy = spawn("red_enemy", 779, 499);
            redPurpleEnemy = spawn("redPurple_enemy", 657, 631);
            redPurpleEnemy = spawn("redPurple_enemy", 766, 682);
            redPurpleEnemy = spawn("redPurple_enemy", 613, 365);

        } else if (player != null && levelNum == 7) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(894, 416));
            player.setZIndex(Integer.MAX_VALUE);
            redEnemy = spawn("red_enemy", 615, 225);
            redEnemy = spawn("red_enemy", 798, 380);
            redEnemy = spawn("red_enemy", 702, 424);
            redEnemy = spawn("red_enemy", 664, 524);
            redPurpleEnemy = spawn("redPurple_enemy", 604, 659);
            redPurpleEnemy = spawn("redPurple_enemy", 546, 328);
            redPurpleEnemy = spawn("redPurple_enemy", 589, 458);
            redPurpleEnemy = spawn("redPurple_enemy", 582, 792);

        } else if (player != null && levelNum == 8) {
            player.getComponent(PhysicsComponent.class).overwritePosition(new Point2D(512, 896));
            player.setZIndex(Integer.MAX_VALUE);
            dragonEnemy = spawn("dragon", 300, 300);
            redEnemy = spawn("red_enemy", 535, 733);
            redEnemy = spawn("red_enemy", 584, 676);
            redEnemy = spawn("red_enemy", 623, 608);
            redEnemy = spawn("red_enemy", 708, 543);
            purpleEnemy = spawn("purple_enemy", 756, 473);
            purpleEnemy = spawn("purple_enemy", 736, 344);
            purpleEnemy = spawn("purple_enemy", 776, 247);
            purpleEnemy = spawn("purple_enemy", 569, 574);
            redPurpleEnemy = spawn("redPurple_enemy", 676, 385);
            redPurpleEnemy = spawn("redPurple_enemy", 648, 455);
            redPurpleEnemy = spawn("redPurple_enemy", 659, 229);
            redPurpleEnemy = spawn("redPurple_enemy", 639, 137);
            redPurpleEnemy = spawn("redPurple_enemy", 458, 685);
            redPurpleEnemy = spawn("redPurple_enemy", 663, 126);
        }
    }

    //PlayerInput
    @Override
    protected void initInput() {

        getInput().addAction(new UserAction("Move Left") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).left();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
            }, KeyCode.A);

        getInput().addAction(new UserAction("Move Right") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).right();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.D);

        getInput().addAction(new UserAction("Move Up") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).up();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.W);

        getInput().addAction(new UserAction("Move Down") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).down();
            }

            @Override
            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }
        }, KeyCode.S);

        getInput().addAction(new UserAction("rightShoot") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).rightShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.RIGHT);

        getInput().addAction(new UserAction("leftShoot") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).leftShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.LEFT);

        getInput().addAction(new UserAction("downShoot") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).downShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.DOWN);

        getInput().addAction(new UserAction("upShoot") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).upShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.UP);


        getInput().addAction(new UserAction("rightFire") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).rightFireShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.DIGIT1);

        getInput().addAction(new UserAction("leftFire") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).leftFireShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.DIGIT2);

        getInput().addAction(new UserAction("downFire") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).downFireShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.DIGIT3);

        getInput().addAction(new UserAction("upFire") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).upFireShoot();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.DIGIT4);

        getInput().addAction(new UserAction("upVaccine") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).upVaccine();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.I);

        getInput().addAction(new UserAction("rightVaccine") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).rightVaccine();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.L);

        getInput().addAction(new UserAction("leftVaccine") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).leftVaccine();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.J);

        getInput().addAction(new UserAction("downVaccine") {
            @Override
            protected void onAction() {
                player.getComponent(PlayerComponent.class).downVaccine();
            }

            protected void onActionEnd() {
                player.getComponent(PlayerComponent.class).stop();
            }

        }, KeyCode.K);

    }

    //Physics...requires no gravity effects(this is a top down perspective)
    @Override
    protected void initPhysics() {
        FXGL.getPhysicsWorld().setGravity(0, 0);
//        getPhysicsWorld().addCollisionHandler(new BonusPlayerHandler());

        //Purple enemy bullet handling
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyLeftBulletHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyRightBulletHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyDownBulletHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyUpBulletHandler());

        //Purple enemy arrow handling
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyLeftArrowHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyRightArrowHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyDownArrowHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyUpArrowHandler());

        getPhysicsWorld().addCollisionHandler(new PurpleEnemyLeftVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyRightVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyLeftVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyUpVaccineHandler());

        //Purple enemy fireball handling
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyLeftFireballHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyRightFireballHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyDownFireballHandler());
        getPhysicsWorld().addCollisionHandler(new PurpleEnemyUpFireballHandler());

        //Red enemy bullet handling
        getPhysicsWorld().addCollisionHandler(new RedEnemyLeftBulletHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyRightBulletHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyDownBulletHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyUpBulletHandler());

        //Red enemy arrow handling
        getPhysicsWorld().addCollisionHandler(new RedEnemyLeftArrowHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyRightArrowHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyDownArrowHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyUpArrowHandler());

        //Red enemy Fireball handling
        getPhysicsWorld().addCollisionHandler(new RedEnemyLeftFireballHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyRightFireballHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyDownFireballHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyUpFireballHandler());

        //Red enemy Vaccine handling
        getPhysicsWorld().addCollisionHandler(new RedEnemyLeftVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyRightVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyDownVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new RedEnemyUpVaccineHandler());


        //Dragon bullet handling
        getPhysicsWorld().addCollisionHandler(new DragonLeftBulletHandler());
        getPhysicsWorld().addCollisionHandler(new DragonRightBulletHandler());
        getPhysicsWorld().addCollisionHandler(new DragonDownBulletHandler());
        getPhysicsWorld().addCollisionHandler(new DragonUpBulletHandler());

        //Dragon Vaccine handling
        getPhysicsWorld().addCollisionHandler(new DragonLeftVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new DragonRightVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new DragonDownVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new DragonUpVaccineHandler());

        //Dragon arrow handling
        getPhysicsWorld().addCollisionHandler(new DragonLeftArrowHandler());
        getPhysicsWorld().addCollisionHandler(new DragonRightArrowHandler());
        getPhysicsWorld().addCollisionHandler(new DragonDownArrowHandler());
        getPhysicsWorld().addCollisionHandler(new DragonUpArrowHandler());

        //Red_Purple fireball handling
        getPhysicsWorld().addCollisionHandler(new DragonLeftFireballHandler());
        getPhysicsWorld().addCollisionHandler(new DragonRightFireballHandler());
        getPhysicsWorld().addCollisionHandler(new DragonDownFireballHandler());
        getPhysicsWorld().addCollisionHandler(new DragonUpFireballHandler());

        // Red_Purple Bullet handling
        getPhysicsWorld().addCollisionHandler(new RedPurpleLeftBulletHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleRightBulletHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleDownBulletHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleUpBulletHandler());

        //Red_Purple arrow handling
        getPhysicsWorld().addCollisionHandler(new RedPurpleLeftArrowHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleRightArrowHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleDownArrowHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleUpArrowHandler());

        //Red_Purple Fireball handling
        getPhysicsWorld().addCollisionHandler(new RedPurpleLeftFireballHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleRightFireballHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleDownFireballHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleUpFireballHandler());

        //Red_Purple Vaccine handling
        getPhysicsWorld().addCollisionHandler(new RedPurpleLeftVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleRightVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleDownVaccineHandler());
        getPhysicsWorld().addCollisionHandler(new RedPurpleUpVaccineHandler());

        //Player bullet handling
        getPhysicsWorld().addCollisionHandler(new PlayerLeftBulletHandler());
        getPhysicsWorld().addCollisionHandler(new PlayerRightBulletHandler());
        getPhysicsWorld().addCollisionHandler(new PlayerUpBulletHandler());
        getPhysicsWorld().addCollisionHandler(new PlayerDownBulletHandler());

        //Bullet Wall Handling
        getPhysicsWorld().addCollisionHandler(new BulletLeftWallHandler());
        getPhysicsWorld().addCollisionHandler(new BulletRightWallHandler());
        getPhysicsWorld().addCollisionHandler(new BulletUpWallHandler());
        getPhysicsWorld().addCollisionHandler(new BulletDownWallHandler());

        //Fireball Wall Handling
        getPhysicsWorld().addCollisionHandler(new FireballLeftWallHandler());
        getPhysicsWorld().addCollisionHandler(new FireballRightWallHandler());
        getPhysicsWorld().addCollisionHandler(new FireballUpWallHandler());
        getPhysicsWorld().addCollisionHandler(new FireballDownWallHandler());

        //Arrow Wall Handling
        getPhysicsWorld().addCollisionHandler(new ArrowLeftWallHandler());
        getPhysicsWorld().addCollisionHandler(new ArrowRightWallHandler());
        getPhysicsWorld().addCollisionHandler(new ArrowUpWallHandler());
        getPhysicsWorld().addCollisionHandler(new ArrowDownWallHandler());

        //Bullet Portal Handling
        getPhysicsWorld().addCollisionHandler(new BulletLeftPortalHandler());
        getPhysicsWorld().addCollisionHandler(new BulletRightPortalHandler());
        getPhysicsWorld().addCollisionHandler(new BulletUpPortalHandler());
        getPhysicsWorld().addCollisionHandler(new BulletDownPortalHandler());

        //Fireball Portal Handling
        getPhysicsWorld().addCollisionHandler(new FireballLeftPortalHandler());
        getPhysicsWorld().addCollisionHandler(new FireballRightPortalHandler());
        getPhysicsWorld().addCollisionHandler(new FireballUpPortalHandler());
        getPhysicsWorld().addCollisionHandler(new FireballDownPortalHandler());

        //Arrow Portal Handling
        getPhysicsWorld().addCollisionHandler(new ArrowLeftPortalHandler());
        getPhysicsWorld().addCollisionHandler(new ArrowRightPortalHandler());
        getPhysicsWorld().addCollisionHandler(new ArrowUpPortalHandler());
        getPhysicsWorld().addCollisionHandler(new ArrowDownPortalHandler());

        onCollisionOneTimeOnly(PLAYER, PORTAL, (player, portal) -> {
            getGameScene().getViewport().fade(() -> {
                nextLevel();
            });
        });
        onCollisionOneTimeOnly(PLAYER, EXIT, (player, portal) -> {
//            Duration userTime = Duration.seconds(getd("levelTime"));
//
//            showMessage("Congratulations! You finished the game. Your time is \n" +
//                    df2.format(userTime.toSeconds()) + ". You killed ");
            Duration userTime = Duration.seconds(getd("levelTime"));
            //showMessage("Congratulations!" + userTime + "You finished the game.");
            int enemiesKilled = geti("enemiesKilled");
            String numKilled = Integer.toString(enemiesKilled);
            //String boxMessage = ("");
            getDialogService().showConfirmationBox("Congratulations!! You have 2 lives remaining.\n" +
                    "You killed " + numKilled + " enemies. \n" +
                    "Your time is " + df2.format(userTime.toSeconds()) + " seconds. \nPlay Again?", yes -> {
                if (yes) {
                    getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
                    getGameController().startNewGame();
                } else {
                    //int score = geti("score");
                    getGameController().exit();
                }
            });
        });
//        onCollision(PLAYER, WALL, (player, wall) -> {
//            play("bump.mp3");
//        });
    }

    public boolean initialPhysics() {
        try {
            initPhysics();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed");
        }
    }

    @Override
    protected void initGameVars(Map<String, Object> vars) {
        vars.put("level", STARTING_LEVEL);
        vars.put("lives", START_LIVES);
        vars.put("levelTime", 0.0);
        vars.put("enemiesKilled", 0);
    }

    //BackgroundMusic
    @Override
    protected void onPreInit() {
        getSettings().setGlobalMusicVolume(0.1);
        loopBGM("kujakuNujabesBGM.mp3");

        onEvent(GameEvent.ENEMY_KILLED, this::onEnemyKilled);
        //onEvent(GameEvent.ENEMY_REACHED_END, this::onEnemyReachedEnd);
        onEvent(GameEvent.PLAYER_GOT_HIT, this::onPlayerGotHit);
    }

    public void onPlayerGotHit(GameEvent event) {
//        getGameScene().getViewport().shakeTranslational(9.5);

        inc("lives", -1);
        uiController.loseLife();

        if (geti("lives") == 0)
            showGameOver();
    }

    private void showGameOver() {
        Duration userTime = Duration.seconds(getd("levelTime"));
        //showMessage("Congratulations!" + userTime + "You finished the game.");
        int enemiesKilled = geti("enemiesKilled");
        String numKilled = Integer.toString(enemiesKilled);
        //String boxMessage = ("");
        getDialogService().showConfirmationBox("You killed " + numKilled + " enemies.\n" +
                "Your time is " + df2.format(userTime.toSeconds()) + "seconds.\n You have 0 lives left." +
                "\nPlay Again?", yes -> {
            if (yes) {
                getGameWorld().getEntitiesCopy().forEach(Entity::removeFromWorld);
                getGameController().startNewGame();
            } else {
                //int score = geti("score");
                getGameController().exit();
            }
        });
    }


    public void onEnemyKilled(GameEvent event) {
        inc("enemiesKilled", +1);
    }

    @Override
    protected void onUpdate(double tpf) {
        cameraAnimation.onUpdate(tpf);
        inc("levelTime", tpf);
    }

    public boolean initUpdate() {
        try {
            initPhysics();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed");
        }
    }

    //Helper method
    private void nextLevel() {
        if (geti("level") == MAX_LEVEL) {
            showMessage("Congratulations! You've finished the game");
            return;
        }
        inc("level", +1);
        currentLevelNumber++;
        setLevel(geti("level"));
    }

    public boolean initNextLevel() {
        try {
            initPhysics();
            return true;
        } catch (Exception e) {
            throw new RuntimeException("Failed");
        }
    }

    private Animation<Double> cameraAnimation;

    private void animateCamera(Runnable onAnimationFinished) {
        AnimatedValue<Double> value = new AnimatedValue<>(getAppHeight() * 1.0, 0.0);
        cameraAnimation = animationBuilder()
                .duration(Duration.seconds(0.5))
                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                .onFinished(onAnimationFinished::run)
                .animate(value)
                .onProgress(y -> getGameScene().getViewport().setY(y))
                .build();

        cameraAnimation.start();
    }

    public static void main(String[] args) {
        if (args.equals(new String[]{"Testing", "Works"})) {
            System.out.println("Sorry, the game could not be loaded");
        }

        launch(args);
    }


    public class MySceneFactory extends SceneFactory {
        @Override
        public FXGLMenu newMainMenu() {
            return new MyMenu(MenuType.MAIN_MENU);
        }
    }
}
