package com.almasb.fxglgames.covidfighter.collisions.weaponHandler.Arrow;

import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxglgames.covidfighter.EntityType;
import com.almasb.fxglgames.covidfighter.components.OwnerComponent;

public class ArrowDownWallHandler extends CollisionHandler {

    public ArrowDownWallHandler() {
        super(EntityType.DOWN_ARROW, EntityType.WALL);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity wall) {
        Object owner = bullet.getComponent(OwnerComponent.class).getValue();

        if (owner == EntityType.PURPLE_ENEMY) {
            bullet.removeFromWorld();
        } else if (owner == EntityType.RED_ENEMY) {
            bullet.removeFromWorld();
        } else if (owner == EntityType.DRAGON) {
            bullet.removeFromWorld();
        } else if (owner == EntityType.PLAYER) {
            bullet.removeFromWorld();
        }
    }
}