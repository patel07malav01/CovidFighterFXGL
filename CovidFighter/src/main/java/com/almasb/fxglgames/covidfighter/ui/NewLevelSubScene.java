package com.almasb.fxglgames.covidfighter.ui;

import com.almasb.fxgl.input.UserAction;
import com.almasb.fxgl.scene.SubScene;
import com.almasb.fxgl.ui.FontFactory;
//import com.almasb.fxglgames.covidfighter.components.PlayerComponent;
import com.almasb.fxglgames.covidfighter.components.PlayerComponent;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Consumer;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class NewLevelSubScene extends SubScene {

    private static final int WIDTH = 478;
    private static final int HEIGHT = 478;
    private static String weapon = "";

    private HBox gradeBox = new HBox();
    private FontFactory levelFont = getAssetLoader().loadFont("level_font.ttf");
    private BooleanProperty isAnimationDone = new SimpleBooleanProperty(true);

    public NewLevelSubScene() {

        Map<String, Runnable> dialogs = new LinkedHashMap<>();
        Button btn;


        dialogs.put("Easy Difficulty", () ->
                getDialogService().showMessageBox("For beginners"));

        dialogs.put("Normal Difficulty", () ->
                getDialogService().showMessageBox("As the game is intended"));

        dialogs.put("Hard Difficulty", () ->
                getDialogService().showMessageBox("For risk-takers"));

        ChoiceBox<String> cbDialogsDifficulty = new ChoiceBox<>();
        cbDialogsDifficulty.getItems().addAll("Easy Difficulty", "Normal Difficulty", "Hard Difficulty");

        btn = new Button("Click me to initialize");
        btn.setOnAction(e -> weaponOption(cbDialogsDifficulty));

        cbDialogsDifficulty.getSelectionModel().getSelectedItem();
        Button btnDifficulty = getUIFactoryService().newButton("Description");
        btnDifficulty.setOnAction(e -> {
            String dialogType = cbDialogsDifficulty.getSelectionModel().getSelectedItem();
            if (dialogs.containsKey(dialogType)) {
                dialogs.get(dialogType).run();
            } else {
                System.out.println("Unknown dialog type");
            }
        });

        Map<String, Runnable> dialogsWeapon = new LinkedHashMap<>();

        dialogsWeapon.put("VaccineBow", () ->
                getDialogService().showMessageBox("Shoots Vaccines"));
        dialogsWeapon.put("Sanitizer", () ->
                getDialogService().showMessageBox("Squirts HandSanitizer"));
        dialogsWeapon.put("Disinfectant Spray", () ->
                getDialogService().showMessageBox("Cleanses through spray"));

        ChoiceBox<String> cbDialogsWeapon = new ChoiceBox<>();
        cbDialogsWeapon.getItems().addAll("VaccineBow", "Sanitizer", "Disinfectant Spray");

        btn.setOnAction(e -> weaponOption(cbDialogsWeapon));

        Button btnWeapon = getUIFactoryService().newButton("Description");

        btnWeapon.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String dialogType;
                dialogType = cbDialogsWeapon.getSelectionModel().getSelectedItem();

                if (dialogsWeapon.containsKey(dialogType)) {
                    dialogsWeapon.get(dialogType).run();
                } else {
                    System.out.println("Unknown dialog type");
                }
            }
        });

        Map<String, Runnable> dialogsName = new LinkedHashMap<>();

        dialogsName.put("Name Entry", new Runnable() {
            @Override
            public void run() {
                getDialogService().
                        showInputBox("Enter your name", new Consumer<String>() {
                            @Override
                            public void accept(String answer) {
                                if (answer == null || answer.equals(" ")) {
                                    getDialogService().
                                            showMessageBox("Please enter a valid string!");
                                } else {
                                    getDialogService().showMessageBox("Good luck, " + answer);
                                }
                            }
                        });
                }
        });

        ChoiceBox<String> cbDialogsName =
                getUIFactoryService().newChoiceBox(FXCollections.
                        observableArrayList(dialogsName.keySet()));
        cbDialogsName.getSelectionModel().selectFirst();
        Button btnName = getUIFactoryService().newButton("Description");
        btnName.setOnAction(e -> {
            String dialogType1 = cbDialogsName.getSelectionModel().getSelectedItem();
            if (dialogsName.containsKey(dialogType1)) {
                dialogsName.get(dialogType1).run();
            } else {
                System.out.println("Unknown dialog type");
            }
        });

        var bg = new Rectangle(WIDTH, HEIGHT, Color.color(0, 0, 0, 0.85));
        bg.setStroke(Color.BLUE);
        bg.setStrokeWidth(1.75);
        bg.setEffect(new DropShadow(28, Color.color(0, 0, 0, 0.9)));

        var textContinue = getUIFactoryService().newText("Click Here When Done", Color.WHITE, 11.0);
        textContinue.visibleProperty().bind(isAnimationDone);

        animationBuilder(this)
                .repeatInfinitely()
                .autoReverse(true)
                .scale(textContinue)
                .from(new Point2D(1, 1))
                .to(new Point2D(1.25, 1.25))

                //DO NOT REMOVE.EVEN THOUGH INTELLIJ HIGHLIGHTS THIS RED,
                //IT SOMEHOW STILL MAKES THE ANIMATION PLAY.
                //DO.   NOT.     REMOVE.
                .buildAndPlay();

        var vbox = new VBox(20, cbDialogsDifficulty, btnDifficulty, cbDialogsWeapon, btnWeapon,
                cbDialogsName, btnName, btn, textContinue);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(25));

        var root = new StackPane(
                bg, vbox
        );

        root.setTranslateX(0);
        root.setTranslateY(0);

        var textLevel = new Text();
        textLevel.textProperty().bind(getip("level").asString("Level %d"));
        textLevel.setFont(levelFont.newFont(52));
        textLevel.setRotate(-20);

        textLevel.setFill(Color.ORANGE);
        textLevel.setStroke(Color.BLACK);
        textLevel.setStrokeWidth(3.5);

        textLevel.setTranslateX(root.getTranslateX() - textLevel.getLayoutBounds().getWidth() / 3);
        textLevel.setTranslateY(root.getTranslateY() + 25);

        getContentRoot().getChildren().addAll(root);

        getInput().addAction(new UserAction("Close Level End Screen") {
            @Override
            protected void onActionBegin() {
                if (!isAnimationDone.getValue()) {
                    return;
                }

                getSceneService().popSubScene();
            }
        }, MouseButton.PRIMARY);
    }

    public void weaponOption(ChoiceBox<String> weapon) {
        this.weapon = weapon.getValue();
    }

    public String getWeapon() {
        return weapon;
    }
}
