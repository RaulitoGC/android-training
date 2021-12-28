package com.rguzmanc.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.View
import androidx.core.content.withStyledAttributes

class CustomLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr){

    companion object{
        const val TAG = "CustomLayout"
    }

    private var radius: Float? = 0f
    private var showLabel: Float = 0f
    private var colorType: Int = 0

    val skyPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.WHITE
        alpha = 20
        strokeWidth = (radius ?: 0f)*2f
        strokeCap = Paint.Cap.ROUND
    }

    init {
        Log.d(TAG, "init()")
        context.withStyledAttributes(attrs, R.styleable.CustomLayout) {
            radius = getDimension(R.styleable.CustomLayout_circleRadius, 0f)
            showLabel= getDimension(R.styleable.CustomLayout_showLabel, 0f)
            colorType= getInteger(R.styleable.CustomLayout_circleColorType, 0)
        }
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        Log.d(TAG, "onFinishInflate()")
    }


    /**
     * Must: Subscribe listener in this method
     */
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        Log.d(TAG, "onAttachedToWindow()")
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.d(TAG, "onMeasure() : widthMeasureSpec = $widthMeasureSpec - heightMeasureSpec = $heightMeasureSpec")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.d(TAG, "onLayout() : changed = $changed - left = $left - top = $top - right = $right - bottom = $bottom")
    }


    /**
     * This method is being called on the Main Thread
     */
    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.d(TAG, "onDraw()")

        canvas?.clipRect(0f, 0f, 40f, height.toFloat())
        canvas?.drawRect(0f,0f,75f,75f, drawPaint!!)
    }

    /**
     * Must: Unsubscribe listener in this method
     */
    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        Log.d(TAG, "onDetachedFromWindow()")
    }
}