package com.okry.amt.ui;

import android.content.Context;
import android.graphics.*;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import com.okry.amt.R;
import com.okry.amt.allbase.BaseActivity;

/**
 * Created by MR on 2014/4/14.
 */
public class MagnifyingGlassViewActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(new MagnifyingGlassView(this));
        setContentView(new ShaderView(this));
    }

    /**
     * 放大镜实现方式1
     * @author chroya
     *
     */
    public class ShaderView extends View{
        private Bitmap bitmap;
        private ShapeDrawable drawable;
        //放大镜的半径
        private static final int RADIUS = 80;
        //放大倍数
        private static final int FACTOR = 3;
        private Matrix matrix = new Matrix();

        public ShaderView(Context context) {
            super(context);
            Bitmap bmp = BitmapFactory.decodeResource(getResources(), R.drawable.image_magnifying);
            bitmap = bmp;
            BitmapShader shader = new BitmapShader(
                    Bitmap.createScaledBitmap(bmp, bmp.getWidth()*FACTOR,
                            bmp.getHeight()*FACTOR, true), Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
            //圆形的drawable
            drawable = new ShapeDrawable(new OvalShape());
            drawable.getPaint().setShader(shader);
            drawable.setBounds(0, 0, RADIUS*2, RADIUS*2);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            final int x = (int) event.getX();
            final int y = (int) event.getY();
            //这个位置表示的是，画shader的起始位置
            matrix.setTranslate(RADIUS-x*FACTOR, RADIUS-y*FACTOR);
            drawable.getPaint().getShader().setLocalMatrix(matrix);
            //bounds，就是那个圆的外切矩形
            drawable.setBounds(x-RADIUS, y-RADIUS, x+RADIUS, y+RADIUS);
            invalidate();
            return true;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            canvas.drawBitmap(bitmap, 0, 0, null);
            drawable.draw(canvas);
        }
    }


    public class MagnifyingGlassView extends View {
        private Path mPath = new Path();
        private Matrix matrix = new Matrix();
        private Bitmap bitmap;
        //放大镜的半径
        private static final int RADIUS = 80;
        //放大倍数
        private static final int FACTOR = 2;
        private int mCurrentX, mCurrentY;

        public MagnifyingGlassView(Context context) {
            super(context);
//            mPath.addCircle(0, 0, RADIUS, Path.Direction.CW);
            mPath.addOval(new RectF(0,0,RADIUS,RADIUS + 20), Path.Direction.CW);
            matrix.setScale(FACTOR, FACTOR);

            bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.image_magnifying);
        }

        @Override
        public boolean onTouchEvent(MotionEvent event) {
            mCurrentX = (int) event.getX();
            mCurrentY = (int) event.getY();

            invalidate();
            return true;
        }

        @Override
        public void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            //底图
            canvas.drawBitmap(bitmap, 0, 0, null);
            //剪切
            canvas.translate(mCurrentX - RADIUS, mCurrentY - RADIUS);
            canvas.clipPath(mPath, Region.Op.DIFFERENCE);
            //画放大后的图
            canvas.translate(RADIUS-mCurrentX*FACTOR, RADIUS-mCurrentY*FACTOR);
            canvas.drawBitmap(bitmap, matrix, null);
        }
    }

}
