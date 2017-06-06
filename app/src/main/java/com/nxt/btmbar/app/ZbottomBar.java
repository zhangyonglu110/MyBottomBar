package com.nxt.btmbar.app;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * Created by zhangyonglu on 2017/4/18 001815:58
 */

public class ZbottomBar extends LinearLayout {
    private Context mcontext;
    /**
     * 当前选中位置
     */
    private int currentselectedIndex=0;
    /**
     * 子监听
     */
    private OnItemSelectedListener onItemSelectedListener;
    /**
     * 选中颜色  正常颜色
     */
    private int selectedcolor,normalcolor;
    /**
     * 是否开启动画
     */
    private boolean isanim=false;
    /**
     * 背景图片
     */
    private Drawable bgdrawable;
    /**
     * 背景颜色
     */
    private int bgcolor;
    public ZbottomBar(Context context) {
        super(context);
        this.mcontext=context;
        init(null);
    }
    public ZbottomBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mcontext=context;

        init(attrs);
    }

    public ZbottomBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mcontext=context;

        init(attrs);
    }
    private void init(AttributeSet attrs) {
        setOrientation(HORIZONTAL);
        if(attrs!=null){
            TypedArray ta=getContext().obtainStyledAttributes(attrs,R.styleable.ZBottomBar);
            selectedcolor=ta.getColor(R.styleable.ZBottomBar_selectedcolor,mcontext.getResources().getColor(android.R.color.holo_blue_light));
            normalcolor=ta.getColor(R.styleable.ZBottomBar_normalcolor,mcontext.getResources().getColor(android.R.color.darker_gray));
            isanim=ta.getBoolean(R.styleable.ZBottomBar_isanim,false);
            bgdrawable=ta.getDrawable(R.styleable.ZBottomBar_bgdrawable);
            bgcolor=ta.getColor(R.styleable.ZBottomBar_bgcolor,getResources().getColor(android.R.color.white));
        }else{
            selectedcolor=mcontext.getResources().getColor(android.R.color.holo_blue_light);
            normalcolor=mcontext.getResources().getColor(android.R.color.darker_gray);

        }
     if(bgdrawable!=null){
         setBackgroundDrawable(bgdrawable);
     }else{
         setBackgroundColor(bgcolor);
     }
    }
    public void addItem(final int position,Drawable drawable, String bottomtitle){
        LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT,1f);
        final ZbottomItemView zbottomItemView=new ZbottomItemView(mcontext);
        zbottomItemView.setItemData(position,drawable,bottomtitle);
        zbottomItemView.setLayoutParams(layoutParams);
        zbottomItemView.setId(position);
        addView(zbottomItemView);
        if(position==0){
          zbottomItemView.setselectedColor(selectedcolor,isanim);

        }else{
            zbottomItemView.setnormalColor(normalcolor,isanim);

        }
        zbottomItemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                for(int i=0;i<getChildCount();i++){
                    if(view.getId()==i){
                        if(currentselectedIndex!=view.getId()){
                            ((ZbottomItemView)getChildAt(view.getId())).setselectedColor(selectedcolor,isanim);
                        }else {
                        }
                    }else{
                        ((ZbottomItemView)getChildAt(i)).setnormalColor(normalcolor,isanim);

                    }
                }
                if(onItemSelectedListener!=null)onItemSelectedListener.onitemselected(view,view.getId());
                currentselectedIndex=view.getId();
            }


        });
    }
    interface OnItemSelectedListener{
        void onitemselected(View view,int position);
    }
    public void setOnitemSelectedListener(OnItemSelectedListener onitemselectedlisterner){
        this.onItemSelectedListener=onitemselectedlisterner;

    }
}
