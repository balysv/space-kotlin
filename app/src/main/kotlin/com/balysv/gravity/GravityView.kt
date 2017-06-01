package com.balysv.gravity

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class GravityView(ctx: Context, attrs: AttributeSet) : View(ctx, attrs) {
    val spaceConfig = SpaceConfig()

    val gridPaint = Paint()
    val spacePaint = Paint()
    val tileSizeRatio = 20

    var drawGrid = false
    var tileSize = 0
    var maxX = 0
    var maxY = 0

    init {
        setWillNotDraw(false)
        gridPaint.color = Color.WHITE
        gridPaint.strokeWidth = resources.displayMetrics.density * 2
        gridPaint.style = Paint.Style.STROKE
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        tileSize = Math.max(width, height) / tileSizeRatio
        maxX = width / tileSize
        maxY = height / tileSize
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        if (drawGrid) {
            drawGrid(canvas)
        }

        for (e in spaceConfig.space.entities) {
            val x = width / 2 + e.coords.x.toFloat() / 1000 * tileSize
            val y = height / 2 + e.coords.y.toFloat() / 1000 * tileSize
            spacePaint.color = e.color
            canvas.drawCircle(x, y, e.radius.toFloat() / 1000 * tileSize, spacePaint)
        }

        spaceConfig.space.step()
        invalidate()
    }

    private fun drawGrid(canvas: Canvas) {
        for (x in 0..maxX) {
            for (y in 0..maxY) {
                canvas.drawLine(0f, y.toFloat() * tileSize, width.toFloat(), y.toFloat() * tileSize, gridPaint)
            }
            canvas.drawLine(x.toFloat() * tileSize, 0f, x.toFloat() * tileSize, height.toFloat(), gridPaint)
        }
    }
}

