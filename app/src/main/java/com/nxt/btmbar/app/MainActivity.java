package com.nxt.btmbar.app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ZbottomBar.OnItemSelectedListener{
    private ZbottomBar zbottomBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        zbottomBar= (ZbottomBar) findViewById(R.id.zbottom_bar);
        zbottomBar.setOnitemSelectedListener(this);
        zbottomBar.addItem(0,getResources().getDrawable(R.mipmap.icon_bottom_home),"首页");
        zbottomBar.addItem(1,getResources().getDrawable(R.mipmap.icon_bottom_market),"集市");
        zbottomBar.addItem(2,getResources().getDrawable(R.mipmap.icon_bottom_cure),"诊断");
        zbottomBar.addItem(3,getResources().getDrawable(R.mipmap.icon_bottom_mine),"我");
    }

    @Override
    public void onitemselected(View view, int position) {
        Toast.makeText(this,position+"",Toast.LENGTH_SHORT).show();
    }
}
