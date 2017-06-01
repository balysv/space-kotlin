package com.balysv.gravity

fun point(x: Double, y: Double): Point {
    return Point(x, y)
}

fun zeroPoint(): Point {
    return Point(0.0, 0.0)
}

fun vector(x: Double, y: Double): Vec2 {
    return vector(point(x, y))
}

fun vector(p: Point): Vec2 {
    return Vec2(p)
}

fun zeroVector(): Vec2 {
    return Vec2(zeroPoint())
}

fun unitVector(p1: Point, p2: Point): Vec2 {
    return Vec2(Point(p2.x - p1.x, p2.y - p1.y)).unit()
}

data class Point(val x: Double, val y: Double) {

    fun distanceTo(p: Point): Double {
        return Math.sqrt(Math.pow((x - p.x), 2.0) + Math.pow((y - p.y), 2.0))
    }

    operator fun times(a: Double): Point {
        return Point(x * a, y * a)
    }

    operator fun plus(p: Point): Point {
        return Point(x + p.x, y + p.y)
    }

    operator fun plus(vec: Vec2): Point {
        return Point(x + vec.x, y + vec.y)
    }

    operator fun minus(p: Point): Point {
        return Point(x - p.x, y - p.y)
    }

    operator fun div(a: Double): Point {
        return Point(x / a, y / a)
    }

    operator fun unaryMinus(): Point {
        return Point(-x, -y)
    }
}

data class Vec2(val p: Point) {

    val x = p.x
    val y = p.y

    fun unit(): Vec2 {
        return Vec2(p / magnitude())
    }

    fun magnitude(): Double {
        return Math.sqrt(Math.pow(x, 2.0) + Math.pow(y, 2.0))
    }

    fun angleTo(vec: Vec2): Double {
        return Math.acos(times(vec) / (magnitude() * vec.magnitude()))
    }

    operator fun plus(vec: Vec2): Vec2 {
        return Vec2(p + vec.p)
    }

    operator fun minus(vec: Vec2): Vec2 {
        return Vec2(p - vec.p)
    }

    operator fun times(a: Double): Vec2 {
        return Vec2(p * a)
    }

    operator fun times(vec: Vec2): Double {
        return p.x * vec.p.x + p.y * vec.p.y
    }

    operator fun div(a: Double): Vec2 {
        return Vec2(p / a)
    }

    operator fun unaryMinus(): Vec2 {
        return Vec2(-p)
    }
}
