package com.almasb.fxglgames.covidfighter.collisions.enemyHandler.purpleEnemy.Vaccine;

import com.almasb.fxgl.dsl.components.Effect;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.components.TimeComponent;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxglgames.covidfighter.EntityType;
import com.almasb.fxglgames.covidfighter.components.HealthComponent;
import com.almasb.fxglgames.covidfighter.components.OwnerComponent;
import com.almasb.fxglgames.covidfighter.components.PurpleEnemyComponent;
import javafx.util.Duration;

/**
 * @author Almas Baimagambetov (AlmasB) (almaslvl@gmail.com)
 */
public class PurpleEnemyRightVaccineHandler extends CollisionHandler {

    public PurpleEnemyRightVaccineHandler() {
        super(EntityType.RIGHT_VACCINE, EntityType.PURPLE_ENEMY);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
        Object owner = bullet.getComponent(OwnerComponent.class).getValue();

        // some enemy shot the bullet, skip collision handling
        if (owner == EntityType.PURPLE_ENEMY) {
            return;
        } else if (owner == EntityType.RED_PURPLE) {
            return;
        }

        bullet.removeFromWorld();

        HealthComponent hp = enemy.getComponent(HealthComponent.class);
        hp.setValue(hp.getValue() - 1);

        if (hp.getValue() <= 0) {

            if (enemy.hasComponent(PurpleEnemyComponent.class)) {
                enemy.getComponent(PurpleEnemyComponent.class).die();
            }
        } else {
            enemy.getComponentOptional(EffectComponent.class).ifPresent(e -> e.startEffect(new Effect(Duration.seconds(1)) {
                @Override
                public void onStart(Entity entity) {
                    entity.getComponent(TimeComponent.class).setValue(0.15);
                }

                @Override
                public void onEnd(Entity entity) {
                    entity.getComponent(TimeComponent.class).setValue(1);
                }
            }));
        }
    }
}