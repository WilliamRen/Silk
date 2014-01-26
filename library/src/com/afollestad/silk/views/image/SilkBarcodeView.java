package com.afollestad.silk.views.image;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;
import com.onbarcode.barcode.android.AndroidColor;
import com.onbarcode.barcode.android.Code128;
import com.onbarcode.barcode.android.IBarcode;

public class SilkBarcodeView extends ImageView {

    public SilkBarcodeView(Context context) {
        super(context);
    }

    public SilkBarcodeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SilkBarcodeView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    private String mData;

    @Override
    protected final void onSizeChanged(int w, int h, int oldw, int oldh) {
        /**
         * This method allows the view to wait until it has been measured (a view won't be measured until
         * right before it becomes visible, which is usually after your code first starts executing. This
         * insures that correct dimensions will be used for the image loading size to optimize memory.
         */
        super.onSizeChanged(w, h, oldw, oldh);
        this.invalidate();
    }

    public void setData(String data) {
        mData = data;
        this.invalidate();
    }

    private Code128 mCode;
    private RectF mRect;

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mData != null && (getMeasuredWidth() > 0 && getMeasuredHeight() > 0)) {
            if(mCode == null)
                mCode = new Code128();
            mCode.setData(mData);
            mCode.setUom(IBarcode.UOM_PIXEL);
            mCode.setX(getMeasuredWidth());
            mCode.setY(getMeasuredHeight());
            // barcode image margins
            mCode.setLeftMargin(10f);
            mCode.setRightMargin(10f);
            mCode.setTopMargin(10f);
            mCode.setBottomMargin(10f);
            // barcode image resolution in dpi
            mCode.setResolution(72);
            // space between barcode and barcode encoding data
            mCode.setTextMargin(6);
            mCode.setTextColor(AndroidColor.black);
            // barcode bar color and background color in Android device
            mCode.setForeColor(AndroidColor.black);
            mCode.setBackColor(AndroidColor.white);
            /**
             ** specify your barcode drawing area
	         **/
            if(mRect == null)
                mRect = new RectF(0, 0, 0, 0);
            try {
                mCode.drawBarcode(canvas, mRect);
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}