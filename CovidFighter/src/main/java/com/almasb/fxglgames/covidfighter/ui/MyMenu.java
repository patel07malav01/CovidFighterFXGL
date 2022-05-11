package com.almasb.fxglgames.covidfighter.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import javafx.beans.binding.Bindings;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.getUIFactoryService;

public class MyMenu extends FXGLMenu {

    public MyMenu(MenuType mainMenu) {
        super(MenuType.MAIN_MENU);
        CovidFighterButton btnEasy = new CovidFighterButton("Play Game", () -> fireNewGame());
        CovidFighterButton btnQuitGame = new CovidFighterButton("Quit Game", () -> fireExit());

        Node bg = createBackground(getAppWidth(), getAppHeight());

        Text titleName = new Text("Covid Fighter");
        titleName.setFont(Font.font("impact", FontPosture.REGULAR, 50));
        titleName.setFill(Color.YELLOW);
        titleName.setStroke(Color.BLACK);
        titleName.setStrokeWidth(4);


        var titleBox = new VBox(10, titleName);
        titleBox.setAlignment(Pos.TOP_CENTER);
        titleBox.setTranslateX(100);
        titleBox.setTranslateY(120);

        var box = new VBox(10, btnEasy, btnQuitGame, new Separator(Orientation.HORIZONTAL),
                getUIFactoryService().newText("WASD Movement, ENTER to select."));
        box.setAlignment(Pos.BOTTOM_CENTER);
        box.setTranslateX(80);
        box.setTranslateY(240);
        //box.setBackground(new Background(new BackgroundFill(Color.GRAY, null, null)));
        getContentRoot().getChildren().add(bg);
        getContentRoot().getChildren().add(titleBox);
        getContentRoot().getChildren().add(box);
    }

    private final Node createBackground(double width, double height) {
        Node bg = FXGL.texture("background/titleScreen.png");
        return bg;
    }

    private class CovidFighterButton extends StackPane {
        private final Color selectedColor = Color.WHITE;
        private final Color notSelectedColor = Color.GRAY;
        private String name;
        private Runnable action;
        private Text text;
        private Circle selectorOne;
        private Circle selectorTwo;

        public CovidFighterButton(String name, Runnable action) {
            this.name = name;
            this.action = action;

            //Menu Selector
            selectorOne = new Circle(15, Color.WHITE);
            selectorOne.setStroke(Color.BLACK);
            selectorOne.setStrokeWidth(3);
            selectorOne.setTranslateX(-140);
            selectorOne.visibleProperty().bind(focusedProperty());

            selectorTwo = new Circle(15, Color.WHITE);
            selectorTwo.setStroke(Color.BLACK);
            selectorTwo.setStrokeWidth(3);
            selectorTwo.setTranslateX(140);
            selectorTwo.visibleProperty().bind(focusedProperty());

            //Title Screen Text
            text = getUIFactoryService().newText(name, Color.WHITE, 30);
            text.fillProperty().bind(
                    Bindings.when(focusedProperty()).
                            then(selectedColor).otherwise(notSelectedColor)
            );

            setOnKeyPressed(e -> {
                if (e.getCode() == KeyCode.ENTER) {
                    action.run();
                }
            });

            setFocusTraversable(true);
            getChildren().addAll(selectorOne, selectorTwo, text);
        }
    }
}