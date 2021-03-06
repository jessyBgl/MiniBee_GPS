package com.minibee.gps.minibee_gps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Classe Boussole
 */
public class MyCompassView extends View {

    public float getDirection() {
        return direction;
    }

    private float direction = 0;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private boolean firstDraw;

    public MyCompassView(Context context) {
        super(context);
        init();
    }

    public MyCompassView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyCompassView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    /**
     * Initialisation des parametres de la zone de la boussole
     */
    private void init(){

        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLACK);
        paint.setTextSize(25);
        firstDraw = true;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(MeasureSpec.getSize(widthMeasureSpec), MeasureSpec.getSize(heightMeasureSpec));
    }

    /**
     * Affichage de la boussole
     * @param canvas
     */
    @Override
    protected void onDraw(Canvas canvas) {

        int cxCompass = getMeasuredWidth()/2;
        int cyCompass = getMeasuredHeight()/2;
        float radiusCompass;

        if(cxCompass > cyCompass){
            radiusCompass = (float) (cyCompass * 0.9);
        }
        else{
            radiusCompass = (float) (cxCompass * 0.9);
        }
        //canvas.drawCircle(cxCompass, cyCompass, radiusCompass, paint);
        //canvas.drawRect(0, 0, getMeasuredWidth(), getMeasuredHeight(), paint);

        if(!firstDraw){
            Bitmap icon = BitmapFactory.decodeResource(getContext().getResources(), R.drawable.compass);


            float scaleWidth = (float) this.getWidth() / icon.getWidth();
            float scaleHeight = (float) this.getHeight() / icon.getHeight();


            Matrix matrix = new Matrix();
            matrix.setRotate(direction,icon.getWidth()/2,icon.getHeight()/2);
            icon = Bitmap.createScaledBitmap(icon, this.getWidth(), this.getHeight(), true);

            canvas.drawBitmap(icon, matrix, paint);
            /*canvas.drawLine(cxCompass, cyCompass,
                    (float)(cxCompass + radiusCompass * Math.sin((double)(-direction) * 3.14/180)),
                    (float)(cyCompass - radiusCompass * Math.cos((double)(-direction) * 3.14/180)),
                    paint);*/

            //canvas.drawText(String.valueOf(direction), cxCompass, cyCompass, paint);
        }

    }

    /**
     * Mise a jour de la direction de la boussole
     * @param dir
     */
    public void updateDirection(float dir)
    {
        firstDraw = false;
        direction = dir;
        invalidate();
    }

}