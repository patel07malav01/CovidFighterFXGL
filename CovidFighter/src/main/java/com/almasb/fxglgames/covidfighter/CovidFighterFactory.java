package com.almasb.fxglgames.covidfighter;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.EffectComponent;
import com.almasb.fxgl.dsl.components.OffscreenCleanComponent;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.dsl.components.RandomMoveComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.CollidableComponent;
import com.almasb.fxgl.entity.components.IrremovableComponent;
import com.almasb.fxgl.particle.ParticleComponent;
import com.almasb.fxgl.particle.ParticleEmitter;
import com.almasb.fxgl.particle.ParticleEmitters;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.physics.PhysicsComponent;
import com.almasb.fxgl.physics.box2d.dynamics.BodyType;
import com.almasb.fxgl.physics.box2d.dynamics.FixtureDef;
import com.almasb.fxgl.texture.Texture;
import com.almasb.fxglgames.covidfighter.components.*;
import com.almasb.fxglgames.covidfighter.components.Arrow.DownArrowComponent;
import com.almasb.fxglgames.covidfighter.components.Arrow.LeftArrowComponent;
import com.almasb.fxglgames.covidfighter.components.Arrow.RightArrowComponent;
import com.almasb.fxglgames.covidfighter.components.Arrow.UpArrowComponent;
import com.almasb.fxglgames.covidfighter.components.Bullet.DownBulletComponent;
import com.almasb.fxglgames.covidfighter.components.Bullet.LeftBulletComponent;
import com.almasb.fxglgames.covidfighter.components.Bullet.RightBulletComponent;
import com.almasb.fxglgames.covidfighter.components.Bullet.UpBulletComponent;
import com.almasb.fxglgames.covidfighter.components.FireBall.DownFireBallComponent;
import com.almasb.fxglgames.covidfighter.components.FireBall.LeftFireBallComponent;
import com.almasb.fxglgames.covidfighter.components.FireBall.RightFireBallComponent;
import com.almasb.fxglgames.covidfighter.components.FireBall.UpFireBallComponent;
import com.almasb.fxglgames.covidfighter.components.Vaccine.DownVaccineComponent;
import com.almasb.fxglgames.covidfighter.components.Vaccine.LeftVaccineComponent;
import com.almasb.fxglgames.covidfighter.components.Vaccine.RightVaccineComponent;
import com.almasb.fxglgames.covidfighter.components.Vaccine.UpVaccineComponent;
import javafx.geometry.Point2D;
import javafx.geometry.Rectangle2D;
import javafx.scene.effect.Bloom;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import static com.almasb.fxgl.dsl.FXGL.*;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppHeight;
import static com.almasb.fxgl.dsl.FXGLForKtKt.getAppWidth;
import static com.almasb.fxglgames.covidfighter.EntityType.*;

public class CovidFighterFactory implements EntityFactory {

    @Spawns("player")
    public Entity newPlayer(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        //Need object to be dynamic so it would be bound by walls.
        physics.setBodyType(BodyType.DYNAMIC);

        //This is supposed to help with wall sticking.
        physics.setFixtureDef(new FixtureDef().friction(0.0f));


        Entity build = entityBuilder(data)
                .type(PLAYER)
                //.from(data)
                .bbox(new HitBox(BoundingShape.box(28, 28)))
                .with(physics)
                .with(new CollidableComponent(true))
                .with(new IrremovableComponent())
                .with(new PlayerComponent())
                .build();

        return build;
    }

    @Spawns("wall")
    public Entity newWall(SpawnData data) {
        return entityBuilder()
                .from(data)
                .type(WALL)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),
                        data.<Integer>get("height"))))
                .with(new PhysicsComponent())
                .build();
    }

    @Spawns("portal")
    public Entity newPortal(SpawnData data) {
        return entityBuilder()
                .type(PORTAL)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),
                        data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("ParticleExplosion")
    public Entity newParticleExplosion(SpawnData data) {
        ParticleEmitter emitter = ParticleEmitters.newExplosionEmitter(FXGL.getAppWidth());
        emitter.setStartColor(Color.web("ffffe0"));
        emitter.setSize(3, 5);
        emitter.setNumParticles(8);

        ParticleComponent particles = new ParticleComponent(emitter);
        particles.setOnFinished(() -> particles.getEntity().removeFromWorld());

        return entityBuilder()
                .from(data)
                .with(particles)
                .build();
    }


    @Spawns("Explosion")
    public Entity newExplosion(SpawnData data) {
        play("explosion.wav");

        var texture = texture("explosion.png", 80 * 16, 80).toAnimatedTexture(16, Duration.seconds(0.5));


        var e = entityBuilder()
                .at(data.getX() - 40, data.getY() - 40)
                // we want a smaller texture, 80x80
                // it has 16 frames, hence 80 * 16
                .view(texture.loop())
                .build();

        texture.setOnCycleFinished(() -> e.removeFromWorld());

        return e;
    }

//    @Spawns("LaserBeam")
//    public Entity newLaserBeam(SpawnData data) {
//        Rectangle view = new Rectangle(10, 800 - 25, Color.color(1.0, 1.0, 1.0, 0.86));
//        view.setArcWidth(15);
//        view.setArcHeight(15);
//        view.setStroke(Color.BLUE);
//        view.setStrokeWidth(1);
//
//        return entityBuilder()
//                .from(data)
//                .type(LASER_BEAM)
//                .viewWithBBox(view)
//                .with(new CollidableComponent(true))
//                .with(new LaserBeamComponent())
//                .build();
//    }

    @Spawns("RightArrow")
    public Entity newArrow(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("arrowSprites/rightArrow.png");
        t.relocate(-2, -20);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(RIGHT_ARROW)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, 25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new RightArrowComponent(500))
                .build();
    }

    @Spawns("LeftArrow")
    public Entity newLeftLaser(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("arrowSprites/leftArrow.png");
        t.relocate(-2, 30);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(LEFT_ARROW)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new LeftArrowComponent(500))
                .build();
    }

    @Spawns("DownArrow")
    public Entity newDownLaser(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("arrowSprites/downArrow.png");
        t.relocate(5.5, 25);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(DOWN_ARROW)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new DownArrowComponent(500))
                .build();
    }

    @Spawns("UpArrow")
    public Entity newUpLaser(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("arrowSprites/upArrow.png");
        t.relocate(5.5, 25);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(UP_ARROW)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new UpArrowComponent(500))
                .build();
    }

    @Spawns("RightFireBall")
    public Entity newRightFireBall(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("redFireballSprites/rightRedFireball.png");
        t.relocate(-2, -35);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(RIGHT_FIREBALL)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, 25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new RightFireBallComponent(500))
                .build();
    }

    @Spawns("LeftFireBall")
    public Entity newLeftFireBall(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("redFireballSprites/leftRedFireball.png");
        t.relocate(-10, 10);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(LEFT_FIREBALL)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new LeftFireBallComponent(500))
                .build();
    }

    @Spawns("DownFireBall")
    public Entity newDownFireBall(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("redFireballSprites/downRedFireball.png");
        t.relocate(-10, 25);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(DOWN_FIREBALL)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new DownFireBallComponent(500))
                .build();
    }

    @Spawns("UpFireBall")
    public Entity newUpFireBall(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("redFireballSprites/upRedFireball.png");
        t.relocate(-10, 0);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(UP_FIREBALL)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new UpFireBallComponent(500))
                .build();
    }

    @Spawns("exit")
    public Entity newExit(SpawnData data) {
        return entityBuilder()
                .type(EXIT)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(data.<Integer>get("width"),
                        data.<Integer>get("height"))))
                .with(new CollidableComponent(true))
                .build();
    }

    @Spawns("LeftVaccine")
    public Entity newLeftSyringe(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("VaccineSprites/syringe_to_left .png");
        t.relocate(-2, 35);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(LEFT_VACCINE)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -40))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new LeftVaccineComponent(500))
                .build();
    }

    @Spawns("DownVaccine")
    public Entity newDownVaccine(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("VaccineSprites/syringe_to_bottom.png");
        t.relocate(5.5, 25);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(DOWN_VACCINE)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-15, 0))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new DownVaccineComponent(500))
                .build();
    }

    @Spawns("RightVaccine")
    public Entity newRightVaccine(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("VaccineSprites/syringe_to_right.png");
        t.relocate(-2, -35);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(RIGHT_VACCINE)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, 25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new RightVaccineComponent(500))
                .build();
    }

    @Spawns("UpVaccine")
    public Entity newUpVaccine(SpawnData data) {
        Entity owner = data.get("owner");

        Texture t = texture("VaccineSprites/syringe_to_top.png");
        t.relocate(-10, 0);
        t.setEffect(new Bloom(0.5));

        return entityBuilder()
                .type(UP_VACCINE)
                //changes arrow coordinate direction
                .at(owner.getCenter().add(-4.5, -25))
                .bbox(new HitBox(BoundingShape.box(9, 20)))
                .view(t)
                .with(new CollidableComponent(true), new OwnerComponent(owner.getType()))
                .with(new OffscreenCleanComponent(), new UpVaccineComponent(500))
                .build();
    }


    @Spawns("LeftBullet")
    public Entity newLeftBullet(SpawnData data) {
        Entity owner = data.get("owner");

        return entityBuilder()
                .type(LEFT_BULLET)
                .at(owner.getCenter().add(-3, 18))
                .viewWithBBox("bulletSprites/leftBullet.png")
                .collidable()
                .with(new OwnerComponent(owner.getType()))
                .with(new ProjectileComponent(new Point2D(0, 1), 600).allowRotation(false))
                .with(new OffscreenCleanComponent(), new LeftBulletComponent(500))
                .with("dead", false)
                .build();
    }

    @Spawns("RightBullet")
    public Entity newRightBullet(SpawnData data) {
        Entity owner = data.get("owner");

        return entityBuilder()
                .type(RIGHT_BULLET)
                .at(owner.getCenter().add(-3, 18))
                .viewWithBBox("bulletSprites/rightBullet.png")
                .collidable()
                .with(new OwnerComponent(owner.getType()))
                .with(new ProjectileComponent(new Point2D(0, 1), 600).allowRotation(false))
                .with(new OffscreenCleanComponent(), new RightBulletComponent(500))
                .with("dead", false)
                .build();
    }

    @Spawns("UpBullet")
    public Entity newUpBullet(SpawnData data) {
        Entity owner = data.get("owner");

        return entityBuilder()
                .type(UP_BULLET)
                .at(owner.getCenter().add(-3, 18))
                .viewWithBBox("bulletSprites/upBullet.png")
                .collidable()
                .with(new OwnerComponent(owner.getType()))
                .with(new ProjectileComponent(new Point2D(0, 1), 600).allowRotation(false))
                .with(new OffscreenCleanComponent(), new UpBulletComponent(500))
                .with("dead", false)
                .build();
    }

    @Spawns("DownBullet")
    public Entity newDownBullet(SpawnData data) {
        Entity owner = data.get("owner");

        return entityBuilder()
                .type(DOWN_BULLET)
                .at(owner.getCenter().add(-3, 18))
                .viewWithBBox("bulletSprites/downBullet.png")
                .collidable()
                .with(new OwnerComponent(owner.getType()))
                .with(new ProjectileComponent(new Point2D(0, 1), 600).allowRotation(false))
                .with(new OffscreenCleanComponent(), new DownBulletComponent(500))
                .with("dead", false)
                .build();
    }

    @Spawns("purple_enemy")
    public Entity newPurpleEnemy(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        //Need object to be dynamic so it would be bound by walls.
        physics.setBodyType(BodyType.DYNAMIC);

        //This is supposed to help with wall sticking.
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        RandomMoveComponent rand =
                new RandomMoveComponent
                        (new Rectangle2D(0,0, getAppWidth(), getAppHeight()), 50);
        
        rand.setMoveSpeed(10);

        return entityBuilder()
                .type(PURPLE_ENEMY)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(28, 28)))
                //.with(physics)
                .with(new CollidableComponent(true), new HealthComponent(2))
                //.with(new IrremovableComponent())
                .with(new PurpleEnemyComponent(), new EffectComponent())
                //.with(new AutoRotationComponent())
                .with(rand)
                .view("purpleEnemySpriteSheet/downPurpleEnemy.png")
                .build();
    }

    @Spawns("red_enemy")
    public Entity newRedEnemy(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        //Need object to be dynamic so it would be bound by walls.
        physics.setBodyType(BodyType.DYNAMIC);

        //This is supposed to help with wall sticking.
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        RandomMoveComponent rand =
                new RandomMoveComponent
                        (new Rectangle2D(0,0, getAppWidth(), getAppHeight()), 50);
        
        rand.setMoveSpeed(10);

        return entityBuilder()
                .type(RED_ENEMY)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(28, 28)))
                //.with(physics)
                .with(new CollidableComponent(true), new HealthComponent(4))
                //.with(new IrremovableComponent())
                .with(new RedEnemyComponent(), new EffectComponent())
                //.with(new AutoRotationComponent())
                .with(rand)
                .view("redEnemySpriteSheet/downRedEnemy.png")
                .build();
    }

    @Spawns("redPurple_enemy")
    public Entity newRedPurpleEnemy(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        //Need object to be dynamic so it would be bound by walls.
        physics.setBodyType(BodyType.DYNAMIC);

        //This is supposed to help with wall sticking.
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        RandomMoveComponent rand =
                new RandomMoveComponent
                        (new Rectangle2D(0,0, getAppWidth(), getAppHeight()), 50);

        rand.setMoveSpeed(10);

        return entityBuilder()
                .type(RED_PURPLE)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(28, 28)))
                //.with(physics)
                .with(new CollidableComponent(true), new HealthComponent(10))
                //.with(new IrremovableComponent())
                .with(new RedPurpleComponent(), new EffectComponent())
                //.with(new AutoRotationComponent())
                .with(rand)
                .view("RedPurple/Red_Purple.png")
                .build();
    }

    @Spawns("dragon")
    public Entity newDragon(SpawnData data) {

        PhysicsComponent physics = new PhysicsComponent();
        //Need object to be dynamic so it would be bound by walls.
        physics.setBodyType(BodyType.DYNAMIC);

        //This is supposed to help with wall sticking.
        physics.setFixtureDef(new FixtureDef().friction(0.0f));

        RandomMoveComponent rand =
                new RandomMoveComponent
                        (new Rectangle2D(0,0, getAppWidth(), getAppHeight()), 50);

        rand.setMoveSpeed(10);

        return entityBuilder()
                .type(DRAGON)
                .from(data)
                .bbox(new HitBox(BoundingShape.box(30, 30)))
                .with(physics)
                .with(new CollidableComponent(true), new HealthComponent(16))
                //.with(new IrremovableComponent())
                .with(new DragonComponent(), new EffectComponent())
                //.with(new AutoRotationComponent())
                .with(rand)
                .view("dragonSpriteSheet/dragon.png")
                .build();
    }

    @Spawns("backgroundImage")
    public Entity newBackground(SpawnData data) {
        return entityBuilder()
                .view(texture("level1.png"))
                .build();
    }
}
