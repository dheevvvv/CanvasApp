package com.example.canvasapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView;
    private Canvas mCanvas;
    private Paint mPaint = new Paint();
    private Paint mPaintText = new Paint(Paint.UNDERLINE_TEXT_FLAG);
    private Bitmap mBItmap;

    private int mColorBg;
    private int mColorRect;

    private final static int OFFSET = 120;
    private int mOffset = OFFSET;

    private final static int MULTIPLIER = 100;
    private Rect mRec;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mImageView = findViewById(R.id.myimageview);

        mColorBg = ResourcesCompat.getColor(getResources(),R.color.colorBackground, null);
        mColorRect = ResourcesCompat.getColor(getResources(),R.color.colorRectangle, null);

        mPaint.setColor(mColorBg);
        mPaintText.setColor(ResourcesCompat.getColor(getResources(),R.color.black,null));
        mPaintText.setTextSize(70);
        mRec = new Rect();

        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int vWidth = view.getWidth();
                int vHeight = view.getHeight();
                int halfWidth = vWidth/2;
                int halfHeight = vHeight/2;

                if (mOffset==OFFSET){
                    mBItmap = Bitmap.createBitmap(vWidth, vHeight, Bitmap.Config.ARGB_8888);
                    mImageView.setImageBitmap(mBItmap);

                    mCanvas = new Canvas(mBItmap);
                    mCanvas.drawColor(mColorBg);
                    mCanvas.drawText("Keep Tapping", 100,100,mPaintText);
                    mOffset += OFFSET;

                }
                else {
                    if (mOffset < halfWidth && mOffset < halfHeight){
                        mPaint.setColor(mColorRect - MULTIPLIER*mOffset);
                        mRec.set(mOffset,mOffset,vWidth - mOffset, vHeight -mOffset);
                        mCanvas.drawRect(mRec,mPaint);
                        mOffset += OFFSET;

                    }
                    else {
                        int mColorAccent = ResourcesCompat.getColor(getResources(),R.color.teal_200,null);
                        mPaint.setColor(mColorAccent);
                        mCanvas.drawCircle(halfWidth, halfHeight, halfWidth/3,mPaint);

                        String text = "Done!";
                        Rect mBounds = new Rect();
                        mPaintText.getTextBounds(text,0,text.length(), mBounds);
                        int x = halfWidth - mBounds.centerX();
                        int y = halfHeight -mBounds.centerY();

                        mCanvas.drawText(text, x, y, mPaintText);

                    }

                }

                view.invalidate();


            }
        });
    }
}