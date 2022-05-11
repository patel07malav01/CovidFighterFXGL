package com.almasb.fxglgames.covidfighter.collisions.enemyHandler.redPurpleEnemy.Vaccine;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxglgames.covidfighter.EntityType;
import com.almasb.fxglgames.covidfighter.components.DragonComponent;
import com.almasb.fxglgames.covidfighter.components.HealthComponent;
import com.almasb.fxglgames.covidfighter.components.OwnerComponent;
import com.almasb.fxglgames.covidfighter.components.RedPurpleComponent;

public class RedPurpleDownVaccineHandler extends CollisionHandler {

    public RedPurpleDownVaccineHandler() {
        super(EntityType.DOWN_VACCINE, EntityType.RED_PURPLE);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity enemy) {
        Object owner = bullet.getComponent(OwnerComponent.class).getValue();

        // some enemy shot the bullet, skip collision handling
        if (owner == EntityType.RED_ENEMY) {
            return;
        } else if (owner == EntityType.DRAGON) {
            return;
        } else if (owner == EntityType.PURPLE_ENEMY) {
            return;
        } else if (owner == EntityType.RED_ENEMY) {
            return;
        } else if (owner == EntityType.RED_PURPLE) {
            return;
        }

        bullet.removeFromWorld();

        HealthComponent hp = enemy.getComponent(HealthComponent.class);
        hp.setValue(hp.getValue() - 1);

        if (hp.getValue() <= 0) {

            if (enemy.hasComponent(RedPurpleComponent.class)) {
                enemy.getComponent(RedPurpleComponent.class).die();
            }
        }
//        } else {
//            enemy.getComponentOptional(EffectComponent.class).ifPresent(e -> e.startEffect(new Effect(Duration.seconds(1)) {
//                @Override
//                public void onStart(Entity entity) {
//                    entity.getComponent(TimeComponent.class).setValue(0.15);
//                }
//
//                @Override
//                public void onEnd(Entity entity) {
//                    entity.getComponent(TimeComponent.class).setValue(1);
//                }
//            }));
//        }
    }
}