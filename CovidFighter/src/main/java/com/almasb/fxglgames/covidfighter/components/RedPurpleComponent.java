package com.almasb.fxglgames.covidfighter.components;

import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.time.LocalTimer;
import com.almasb.fxglgames.covidfighter.event.GameEvent;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;


public class RedPurpleComponent extends Component {

    private PhysicsComponent physics;

    protected LocalTimer attackTimer;
    protected Duration nextAttack = Duration.seconds(2);

    @Override
    public void onAdded() {
        attackTimer = FXGL.newLocalTimer();
        attackTimer.capture();
//        if (physics.getVelocityX() == 0) {
//            getEntity().setScaleX(-1);
//            physics.setVelocityX(-120);
//        }
    }

    @Override
    public void onUpdate(double tpf) {
        if (attackTimer.elapsed(nextAttack)) {
            if (FXGLMath.randomBoolean(0.3f)) {
                shoot();
            }
            nextAttack = Duration.seconds(5 * Math.random());
            attackTimer.capture();
        }
    }

    protected void shoot() {
        spawn("UpBullet", new SpawnData(0, 0).put("owner", getEntity()));

        play("shoot3.wav");
    }

    public void die() {
        spawn("Explosion", entity.getCenter());
        spawn("ParticleExplosion", entity.getCenter());
        entity.removeFromWorld();
        fire(new GameEvent(GameEvent.ENEMY_KILLED));
    }
}
