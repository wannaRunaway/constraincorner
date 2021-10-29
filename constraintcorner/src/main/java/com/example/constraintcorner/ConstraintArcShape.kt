package com.example.constraintcorner

import android.content.Context
import android.content.res.TypedArray
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import androidx.constraintlayout.widget.ConstraintLayout

class ConstraintArcShape : ConstraintLayout {

    private var viewwidth: Float = 0f
    private var viewheight: Float = 0f
    private var paint: Paint = Paint()
    private var path: Path = Path()
    private var left_top_radius: Int = 0
    private var left_bottom_radius: Int = 0
    private var right_top_radius: Int = 0
    private var right_bottom_radius: Int = 0
    private var radius: Int = 0
    private var constraintColor:Int = 0

    constructor(context: Context) : super(context) {
        init(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init(context, attrs)
    }

    constructor(
        context: Context,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context, attrs)
    }

    fun init(context: Context, attrs: AttributeSet?) {
        //let with run为了计算目的
        //apply also 得到对象
//        paint?.run {
//            color = Color.WHITE
//            strokeWidth = 50f
//        }
        paint?.apply {
            color = Color.BLUE
            strokeWidth = 50f
        }
        var typedArray: TypedArray =
            context.obtainStyledAttributes(attrs, R.styleable.Round_ConstraintLayout)
        typedArray?.run {
            radius = getDimensionPixelOffset(R.styleable.Round_ConstraintLayout_radius, 0)
            left_top_radius =
                getDimensionPixelOffset(R.styleable.Round_ConstraintLayout_left_top_radius, 0)
            left_bottom_radius =
                getDimensionPixelOffset(R.styleable.Round_ConstraintLayout_left_bottom_radius, 0)
            right_top_radius =
                getDimensionPixelOffset(R.styleable.Round_ConstraintLayout_right_top_radius, 0)
            right_bottom_radius =
                getDimensionPixelOffset(R.styleable.Round_ConstraintLayout_right_bottom_radius, 0)
            constraintColor = getColor(R.styleable.Round_ConstraintLayout_constraint_color, Color.GRAY)
        }
        if (radius != 0) {
            left_top_radius = radius
            left_bottom_radius = radius
            right_top_radius = radius
            right_bottom_radius = radius
        }
        typedArray.recycle()
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        viewwidth = width.toFloat()
        viewheight = height.toFloat()
    }

    override fun dispatchDraw(canvas: Canvas?) {
        super.dispatchDraw(canvas)
        //计算viewgroup宽高是大于radius
        var maxWidth: Int =
            left_top_radius.coerceAtLeast(left_bottom_radius) + right_top_radius.coerceAtLeast(
                right_bottom_radius
            )
        var maxHeight: Int =
            left_top_radius.coerceAtLeast(right_top_radius) + left_bottom_radius.coerceAtLeast(
                right_bottom_radius
            )
        if (maxWidth < viewwidth && maxHeight < viewheight) {
            canvas?.apply {
                clipPath(path?.apply {
                    moveTo(left_top_radius.toFloat(), 0f)
                    lineTo(viewwidth - right_top_radius.toFloat(), 0f)
                    quadTo(viewwidth, 0f, viewwidth, right_top_radius.toFloat())
                    lineTo(viewwidth, viewheight - right_bottom_radius)
                    quadTo(viewwidth, viewheight, viewwidth - right_bottom_radius, viewheight)
                    lineTo(left_bottom_radius.toFloat(), viewheight)
                    quadTo(0f, viewheight, 0f, viewheight - left_bottom_radius)
                    lineTo(0f, left_top_radius.toFloat())
                    quadTo(0f, 0f, left_top_radius.toFloat(), 0f)
                })
                if (constraintColor!=Color.GRAY) {
                    drawColor(constraintColor)
                }
            }
        }
    }
}