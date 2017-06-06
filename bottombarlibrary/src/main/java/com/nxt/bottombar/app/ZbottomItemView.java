package com.nxt.bottombar.app;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * Created by zhangyonglu on 2017/4/18 001817:06
 */

public class ZbottomItemView extends LinearLayout {
    /**
     * 底部图片
     */
    private ImageView itemImgView;
    /**
     * 底部文字
     */
    private TextView itemtextview;
    private Context mcontext;

    private Drawable mdrawable;

    public ZbottomItemView(Context context) {
        super(context);
        this.mcontext=context;
        init();
    }


    public ZbottomItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontext=context;
        init();

    }

    public ZbottomItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontext=context;
        init();

    }

    private void init() {
        setGravity(Gravity.CENTER_HORIZONTAL);
        LayoutInflater.from(mcontext).inflate(R.layout.layout_zbottom_item_view,this);
        itemImgView= (ImageView) findViewById(R.id.img_bottom);
        itemtextview= (TextView) findViewById(R.id.tv_bottom_title);

    }
   public void setItemData(final int position, Drawable drawable, String title){
       itemImgView.setImageDrawable(drawable);
       itemtextview.setText(title);
       itemImgView.setId(position);
       this.mdrawable=drawable;


   }

    /**
     * 设置选中状态
     * @param colorStateList
     * @param isanim
     */
    public void setselectedColor(int colorStateList,boolean isanim){
        itemImgView.setImageDrawable(TintUtil.tintDrawable(mdrawable,colorStateList));
        itemtextview.setTextColor(colorStateList);
        /**
         * 设置动画
         */
        if(isanim) {
            LinearLayout.LayoutParams layoutParams= (LayoutParams) itemImgView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, DensityUtil.dip2px(mcontext,2));
            itemImgView.setLayoutParams(layoutParams);
            itemtextview.setTextSize(13);
        }

    }
    /**
     * 设置默认状态
     * @param colorStateList
     * @param isanim
     */
    public void setnormalColor(int colorStateList,boolean isanim){
        itemImgView.setImageDrawable(TintUtil.tintDrawable(mdrawable,colorStateList));
        itemtextview.setTextColor(colorStateList);
        if(isanim) {
            LinearLayout.LayoutParams layoutParams = (LayoutParams) itemImgView.getLayoutParams();
            layoutParams.setMargins(0, 0, 0, DensityUtil.dip2px(mcontext, 0));
            itemImgView.setLayoutParams(layoutParams);
            itemtextview.setTextSize(12);
        }


    }


}
