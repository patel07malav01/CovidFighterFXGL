package com.almasb.fxglgames.covidfighter.collisions.enemyHandler;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.physics.CollisionHandler;
import com.almasb.fxglgames.covidfighter.EntityType;
import com.almasb.fxglgames.covidfighter.components.OwnerComponent;
import com.almasb.fxglgames.covidfighter.event.GameEvent;

public class PlayerUpBulletHandler extends CollisionHandler {

    public PlayerUpBulletHandler() {
        super(EntityType.UP_BULLET, EntityType.PLAYER);
    }

    @Override
    protected void onCollisionBegin(Entity bullet, Entity player) {
        Object owner = bullet.getComponent(OwnerComponent.class).getValue();

//        // player shot that bullet so no need to handle collision
//        if (owner == SpaceInvadersType.PLAYER
//                || player.getComponent(InvincibleComponent.class).getValue()) {
//            return;
//        }

        bullet.removeFromWorld();

        FXGL.getEventBus().fireEvent(new GameEvent(GameEvent.PLAYER_GOT_HIT));
    }
}