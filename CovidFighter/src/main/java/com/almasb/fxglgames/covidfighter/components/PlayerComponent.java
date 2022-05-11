package com.almasb.fxglgames.covidfighter.components;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import com.almasb.fxglgames.covidfighter.Config;
import com.almasb.fxglgames.covidfighter.ui.NewLevelSubScene;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;

//@Required(InvincibleComponent.class)
public class PlayerComponent extends Component {

    private PhysicsComponent physics;

    private InvincibleComponent invincibility;

    private AnimatedTexture texture;

    private AnimationChannel animIdle;

    private AnimationChannel animWalk;

    private static String weapon = "";

    private boolean canShoot = true;

    private double lastTimeShot = 0;

    private double dx = 0;

    private double attackSpeed = 3.0;

    private boolean laserBeamActive = false;

    public PlayerComponent() {

        NewLevelSubScene sub = new NewLevelSubScene();
        weapon = sub.getWeapon();

        Image xAxisSpriteSheet;

        if (weapon.equals("VaccineBow")) {
            xAxisSpriteSheet =
                    image("playerSpriteSheets/rightProfilePlayerSpriteSheet - crosssBow.png");
        } else if (weapon.equals("Sanitizer")) {
            xAxisSpriteSheet =
                    image("playerSpriteSheets/rightProfilePlayerSpriteSheet - handSanitizer.png");
        } else { //if (weapon.equals("Disinfectant Spray")) {
            xAxisSpriteSheet = image("playerSpriteSheets/rightProfilePlayerSpriteSheet - spray (1).png");
                    //image("playerSpriteSheets/rightProfilePlayerSpriteSheet.png");
        }

        animIdle = new AnimationChannel(xAxisSpriteSheet, 3, 32, 32, Duration.seconds(.5), 1, 1);
        animWalk = new AnimationChannel(xAxisSpriteSheet, 3, 32, 32, Duration.seconds(0.5), 0, 2);

        texture = new AnimatedTexture(animWalk);
        texture.loop();
    }

    public void rightShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("RightArrow", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void leftShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("LeftArrow", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void upShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("UpArrow", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void downShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("DownArrow", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void rightFireShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("RightFireBall", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void leftFireShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("LeftFireBall", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void upFireShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("UpFireBall", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void downFireShoot() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("DownFireBall", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }




    public void rightVaccine() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("RightVaccine", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void leftVaccine() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("LeftVaccine", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void upVaccine() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("UpVaccine", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }

    public void downVaccine() {
        if (!canShoot)
            return;

        canShoot = false;
        lastTimeShot = getGameTimer().getNow();

        spawn("DownVaccine", new SpawnData(0, 0).put("owner", getEntity()));
        play("shoot3.wav");
    }



    public void shootRedLaser() {
        spawn("RedLaser", entity.getCenter());
    }

    public void shootLaser() {
        if (getd("laserMeter") == Config.LASER_METER_MAX) {
            laserBeamActive = true;

            Entity beam = spawn("LaserBeam");
            beam.xProperty().bind(getEntity().xProperty().add(21));
            beam.setY(-10);
            beam.setOnNotActive(() -> laserBeamActive = false);
        }
    }



    public boolean isLaserBeamActive() {
        return laserBeamActive;
    }

    @Override
    public void onAdded() {
        entity.getTransformComponent().setScaleOrigin(new Point2D(16, 21));
        entity.getViewComponent().addChild(texture);
    }

    @Override
    public void onUpdate(double tpf) {

        dx = 300 * tpf;

        if (!canShoot) {
            if ((getGameTimer().getNow() - lastTimeShot) >= 1.0 / attackSpeed) {
                canShoot = true;
            }
        }

        if (physics.isMovingX() || physics.isMovingY()) {
            if (texture.getAnimationChannel() != animWalk) {
                texture.loopAnimationChannel(animWalk);
            }
        } else {
            if (texture.getAnimationChannel() != animIdle) {
                texture.loopAnimationChannel(animIdle);
            }
        }
    }

    public void enableInvincibility() {
        invincibility.setValue(true);
    }

    public void disableInvincibility() {
        invincibility.setValue(false);
    }


    public void left() {
        getEntity().setScaleX(-1);
        physics.setVelocityX(-250);
    }

    public void right() {
        getEntity().setScaleX(1);
        physics.setVelocityX(250);
    }

    public void up() {
        physics.setVelocityY(-250);
    }

    public void down() {
        physics.setVelocityY(250);
    }

    public void stop() {
        physics.setVelocityX(0);
        physics.setVelocityY(0);
    }
}
