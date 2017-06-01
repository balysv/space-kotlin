package com.balysv.gravity

import android.graphics.Color

/**
 * Configuration of our space instance
 */
class SpaceConfig {
    val SUN = Entity("Sun", 4000000.0, 200.0, zeroVector(), zeroPoint(), Color.YELLOW)
    val PLANET1 = Entity("Planet1", 50000.0, 100.0, vector(0.0, 15.0), point(5000.0, 0.0), Color.BLUE)
    val PLANET2 = Entity("Planet2", 5000.0, 100.0, vector(15.0, -3.0), point(0.0, 7000.0), Color.GREEN)
    val PLANET3 = Entity("Planet3", 5000.0, 100.0, vector(-2.0, -13.0), point(-7000.0, 0.0), Color.GRAY)
    val PLANET4 = Entity("Planet4", 5000.0, 100.0, vector(-12.0, 2.0), point(1000.0, -6000.0), Color.BLUE)
    val PLANET5 = Entity("Planet5", 5000.0, 100.0, vector(6.0, 1.0), point(-1000.0, 6000.0), Color.GREEN)
    val PLANET6 = Entity("Planet6", 5000.0, 100.0, vector(-5.0, -2.0), point(3000.0, -2000.0), Color.GRAY)
    val PLANET7 = Entity("Planet7", 5000.0, 100.0, vector(12.0, -2.0), point(5000.0, -3000.0), Color.BLUE)
    val PLANET8 = Entity("Planet8", 5000.0, 100.0, vector(-6.0, -1.0), point(-3000.0, 9000.0), Color.GREEN)
    val PLANET9 = Entity("Planet9", 5000.0, 100.0, vector(5.0, 2.0), point(5000.0, -4000.0), Color.GRAY)

    val realG: Double = 6.674 * Math.pow(10.0, -11.0) // m^2 / kg^2
    val G: Double = 0.3

    val space = Space(G)

    init {
        space.add(SUN)
        space.add(PLANET1)
        space.add(PLANET2)
        space.add(PLANET3)
        space.add(PLANET4)
        space.add(PLANET5)
        space.add(PLANET6)
        space.add(PLANET7)
        space.add(PLANET8)
        space.add(PLANET9)
    }
}
