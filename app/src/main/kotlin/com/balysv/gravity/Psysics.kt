package com.balysv.gravity

import android.support.v4.graphics.ColorUtils
import java.util.*

data class Entity(val name: String, val mass: Double, val radius: Double, var velocity: Vec2, var coords: Point, val color: Int) {
    val density = mass / ((4 / 3) * Math.PI * Math.pow(radius, 3.0))

    override fun equals(other: Any?): Boolean {
        return other is Entity && other.name == name
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}

class Space(val G: Double) {
    val entities = ArrayList<Entity>()

    /**
     * Proceeds one step in the simulation. This should be called for each frame to be rendered
     */
    fun step() {
        applyForces()
        mergeWithEntities(entities)
    }

    /**
     * Adds an entity to Space!
     */
    fun add(e: Entity) {
        entities.add(e)
    }

    /**
     * Computes and applies gravitation forces between each entity
     */
    private fun applyForces() {
        for (e1 in entities) {
            var force = zeroVector()

            for (e2 in entities) {
                if (e1 == e2) continue

                val gravity = G * (e1.mass * e2.mass) / Math.pow(e1.coords.distanceTo(e2.coords), 2.0)
                val dir = unitVector(e1.coords, e2.coords)

                force += dir * gravity
            }

            val acc = force / e1.mass
            e1.velocity = e1.velocity + acc
            e1.coords = e1.coords + e1.velocity
        }
    }

    /**
     * Merges existing entities with the provided ones by checking if position in space of two
     * entities is nearly the same. Combines their mass, radius, colour and movement vector
     */
    private fun mergeWithEntities(entitiesToCheck: ArrayList<Entity>) {
        val toAdd = ArrayList<Entity>()
        val toRemove = ArrayList<Entity>()

        for (e1 in entitiesToCheck) {
            for (e2 in entities) {
                if (e1 == e2 || toRemove.contains(e2)) continue

                // If distance between to entities is lesser than sum of radius - merge entities
                val d = Math.abs(e1.coords.distanceTo(e2.coords))
                if (d < e1.radius + e2.radius) {

                    val name = "${e1.name} & ${e2.name}"
                    val mass = e1.mass + e2.mass
                    val radius = e1.radius + e2.radius
                    val velocity = e1.velocity * (e1.mass / mass) + e2.velocity * (e2.mass / mass)
                    val coords = e1.coords * (e1.mass / mass) + e2.coords * (e2.mass / mass)

                    val color = ColorUtils.blendARGB(e1.color, e2.color, 0.5f)
                    toAdd.add(Entity(name, mass, radius, velocity, coords, color))

                    toRemove.add(e1)
                    toRemove.add(e2)

                    break
                }
            }
        }

        entities.removeAll(toRemove)
        entities.addAll(toAdd)

        if (toAdd.size > 0) {
            mergeWithEntities(toAdd)
        }
    }
}

