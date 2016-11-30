package com.fornothing.projects.magnetize.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

import static com.badlogic.gdx.physics.box2d.BodyDef.BodyType.StaticBody;

public final class B2DBodyBuilder {

    public B2DBodyBuilder() {}

    public static Body createBox(World world, float x, float y,
                                 float width, float height, float friction,  boolean isStatic){
        Body body;
        BodyDef bDef = new BodyDef();
        bDef.type = isStatic? StaticBody : BodyDef.BodyType.DynamicBody;
//        bDef.type = StaticBody;
        bDef.fixedRotation = true;
        bDef.position.set(x, y);
        body = world.createBody(bDef);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(width /2, height /2);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = 1.0f;
        fDef.restitution = 0.1f;
        fDef.friction = friction;
        fDef.isSensor = false;
        body.createFixture(fDef);
        world.createBody(bDef).createFixture(fDef);
        shape.dispose();
        return body;
    }

    public static Body createBall(World world, float x, float y, float radius,
                                  float density, float restitution,
                                  boolean isStatic, boolean isSensor){
        Body body;
        BodyDef bDef = new BodyDef();
        bDef.type = isStatic? StaticBody : BodyDef.BodyType.DynamicBody;
        //bDef.type = BodyDef.BodyType.DynamicBody;
        bDef.fixedRotation = true;
        bDef.position.set(x, y);
        body = world.createBody(bDef);

        CircleShape shape = new CircleShape();
        shape.setRadius(radius);

        FixtureDef fDef = new FixtureDef();
        fDef.shape = shape;
        fDef.density = density;
        fDef.restitution = restitution;
        fDef.isSensor = isSensor;
        body.createFixture(fDef);

        shape.dispose();
        return body;
    }

    public static Body createChainShape(World world, Vector2[] verts){
        Body body;
        BodyDef bDef = new BodyDef();
        bDef.type = StaticBody;
        body = world.createBody(bDef);

        ChainShape shape = new ChainShape();
        shape.createChain(verts);

        body.createFixture(shape, 1.0f);

        shape.dispose();
        return body;
    }

//    public static Body createBox(World world2, float x, float y, float width, float height,
//                                 float density, float restitution, boolean isStatic, boolean isSensor){
//        Body body;
//        BodyDef bDef = new BodyDef();
//        bDef.type = isStatic? BodyDef.BodyType.StaticBody : BodyDef.BodyType.DynamicBody;
//        bDef.fixedRotation = true;
//        bDef.position.set(x / PPM, y / PPM);
//        body = world2.createBody(bDef);
//
//        PolygonShape shape = new PolygonShape();
//        shape.setAsBox(width /2 /PPM, height /2 /PPM);
//
//        FixtureDef fDef = new FixtureDef();
//        fDef.shape = shape;
//        fDef.density = density;
//        fDef.restitution = restitution;
//        fDef.isSensor = isSensor;
//        body.createFixture(fDef);
//
//        world2.createBody(bDef).createFixture(fDef);
//
//        shape.dispose();
//        return body;
//    }


}
