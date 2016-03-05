package com.dyl.chatbot.anim;

import com.dyl.chatbot.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Bitmap.Config;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;

public class ChangeColorIconWithText extends View {

    private final int DEFAULT_COLOR = 0xff45c01a;
    private final int DEFAULT_TEXT_SIZE = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 12,
            getResources().getDisplayMetrics());
    private int mColor = DEFAULT_COLOR;
    private Bitmap mIconBitmap;
    private String mText;
    private int mTextSize = DEFAULT_TEXT_SIZE;
    private Canvas mCanvas;
    private Bitmap mBitmap;
    private Paint mPaint;
    private float mAlpha = 1.0f;
    private Rect mIconRect;
    private Rect mTextBounds;
    private Paint mTextPaint;

    public ChangeColorIconWithText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    

    public ChangeColorIconWithText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
     // TODO Auto-generated constructor stub
    }

    public ChangeColorIconWithText(Context context) {
        this(context, null);
        // TODO Auto-generated constructor stub
    }
    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ChangeColorIconWithText);
        BitmapDrawable drawable = (BitmapDrawable) ta.getDrawable(R.styleable.ChangeColorIconWithText_icon);
        mIconBitmap = drawable.getBitmap();
        mColor = ta.getColor(R.styleable.ChangeColorIconWithText_color, 0xff45c01a);
        mText = (String) ta.getText(R.styleable.ChangeColorIconWithText_text);
        mTextSize = ta.getDimensionPixelSize(R.styleable.ChangeColorIconWithText_text_size, DEFAULT_TEXT_SIZE);
        ta.recycle();

        mTextBounds = new Rect();
        mTextPaint = new Paint();
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(0xff555555);
        mTextPaint.getTextBounds(mText, 0, mText.length(), mTextBounds);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int iconWidth = Math.min(getMeasuredWidth() - getPaddingLeft() - getPaddingRight(),
                getMeasuredHeight() - getPaddingBottom() - getPaddingTop() - mTextBounds.height());

        int left = getMeasuredWidth()/2 - iconWidth /2;
        int top = (getMeasuredHeight() - mTextBounds.height() - iconWidth)/2;
        mIconRect = new Rect(left, top, left + iconWidth, top + iconWidth);
    }
    
    @Override
    protected void onDraw(Canvas canvas) {
        Log.d("dyl", "onDraw..mIconBitmap=="+mIconBitmap+",mIconRect=="+mIconRect);
        canvas.drawBitmap(mIconBitmap, null, mIconRect,null);
        int alpha = (int) Math.ceil(255*mAlpha);
        
        setupTargetBitmap(alpha);
        canvas.drawBitmap(mBitmap, 0, 0,null);
    }



    private void setupTargetBitmap(int alpha) {

        mBitmap = Bitmap.createBitmap(getMeasuredWidth(), getMeasuredHeight(), Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mColor);
        mPaint.setDither(true);
        mPaint.setAlpha(alpha);
        mCanvas.drawRect(mIconRect, mPaint);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        mPaint.setAlpha(255);
        mCanvas.drawBitmap(mBitmap, null, mIconRect,mPaint);
    }
}
