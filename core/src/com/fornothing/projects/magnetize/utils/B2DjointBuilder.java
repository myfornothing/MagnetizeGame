package com.fornothing.projects.magnetize.utils;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Joint;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.joints.DistanceJointDef;
import com.badlogic.gdx.physics.box2d.joints.FrictionJointDef;
import com.badlogic.gdx.physics.box2d.joints.PrismaticJointDef;
import com.badlogic.gdx.physics.box2d.joints.RevoluteJointDef;
import com.badlogic.gdx.physics.box2d.joints.WeldJointDef;


public final class B2DjointBuilder {

    private B2DjointBuilder(){  }

    public static Joint createPrismaticJoint(World world, Body bodyA, Body bodyB,
                                             float upperLimit, float lowerLimit,
                                             Vector2 anchorA, Vector2 anchorB){
        PrismaticJointDef pDef = new PrismaticJointDef();
        pDef.bodyA = bodyA;
        pDef.bodyB = bodyB;
        pDef.collideConnected = false;

        pDef.enableLimit = true;
        pDef.localAnchorA.set(anchorA);
        pDef.localAnchorB.set(anchorB);
        pDef.localAxisA.set(0, 1);
        pDef.upperTranslation = upperLimit;
        pDef.lowerTranslation = lowerLimit;
        return world.createJoint(pDef);
    }

    public static Joint createDistanceJoint(World world, Body bodyA, Body bodyB,
                                            Vector2 anchorA, Vector2 anchorB,
                                            float length){
        DistanceJointDef disDef = new DistanceJointDef();
        disDef.bodyA = bodyA;
        disDef.bodyB = bodyB;
        disDef.localAnchorA.set(anchorA);
        disDef.localAnchorB.set(anchorB);
        disDef.length = length;
        return world.createJoint(disDef);
    }

    public static Joint createRevoluteJoint(World world, Body bodyA, Body bodyB,
                                            Vector2 anchorA, Vector2 anchorB,
                                            float lowerAngle, float upperAngle,
                                            float maxTtorque, float maxSpeed){
        RevoluteJointDef revDef = new RevoluteJointDef();
        revDef.bodyA = bodyA;
        revDef.bodyB = bodyB;
        revDef.localAnchorA.set(anchorA);
        revDef.localAnchorB.set(anchorB);
        revDef.lowerAngle = lowerAngle;
        revDef.upperAngle = upperAngle;
        revDef.maxMotorTorque = maxTtorque;
        revDef.motorSpeed = maxSpeed;
        revDef.enableLimit = false;
        revDef.enableMotor = true;
        return world.createJoint(revDef);
    }

    public static Joint createFrictionJoint(World world, Body bodyA, Body bodyB,
                                            Vector2 anchorA, Vector2 anchorB,
                                            float maxForce, float maxTorque){
        FrictionJointDef frxDef = new FrictionJointDef();
        frxDef.bodyA = bodyA;
        frxDef.bodyB = bodyB;
        frxDef.localAnchorA.set(anchorA);
        frxDef.localAnchorB.set(anchorB);
        frxDef.maxForce = maxForce;
        frxDef.maxTorque = maxTorque;
        return world.createJoint(frxDef);
    }

    public static Joint createWeldJoint(World world,Body bodyA, Body bodyB,
                                        Vector2 anchorA, Vector2 anchorB){
        WeldJointDef weldDef = new WeldJointDef();
        weldDef.bodyA = bodyA;
        weldDef.bodyB = bodyB;
        weldDef.localAnchorA.set(anchorA);
        weldDef.localAnchorB.set(anchorB);
        return world.createJoint(weldDef);
    }

}
