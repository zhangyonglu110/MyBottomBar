package com.nxt.bottombar.app;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.EditText;
import android.widget.TextView;

import java.lang.reflect.Field;

/**
 * @author: 范建海
 * @createTime: 2016/10/30 14:10
 * @className:  TintUtil
 * @description: 着色工具类
 * @changed by:
 */
public class TintUtil {
    /**
     * 给Drawable着色
     * @param drawable 待着色的drawable
     * @return 完成着色的 Drawable
     */
    public static Drawable tintDrawable(Drawable drawable, int color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTint(wrappedDrawable,color);
        return wrappedDrawable;
    }
    public static Drawable tintDrawable(Drawable drawable, ColorStateList color) {
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable.mutate());
        DrawableCompat.setTintList(wrappedDrawable,color);
        return wrappedDrawable;
    }
    /**
     *  给EditText光标着色
     * @param editText EditText对象
     * @param color Color,如Color.RED
     */
    public static void tintCursorDrawable(EditText editText, int color) {
        try {
            Field fCursorDrawableRes = TextView.class.getDeclaredField("mCursorDrawableRes");
            fCursorDrawableRes.setAccessible(true);
            int mCursorDrawableRes = fCursorDrawableRes.getInt(editText);
            Field fEditor = TextView.class.getDeclaredField("mEditor");
            fEditor.setAccessible(true);
            Object editor = fEditor.get(editText);
            Class<?> clazz = editor.getClass();
            Field fCursorDrawable = clazz.getDeclaredField("mCursorDrawable");
            fCursorDrawable.setAccessible(true);

            if (mCursorDrawableRes <= 0) {
                return;
            }

            Drawable cursorDrawable = editText.getContext().getResources().getDrawable(mCursorDrawableRes);
            if (cursorDrawable == null) {
                return;
            }

            Drawable tintDrawable  = tintDrawable(cursorDrawable, ColorStateList.valueOf(color));
            Drawable[] drawables = new Drawable[] {tintDrawable, tintDrawable};
            fCursorDrawable.set(editor, drawables);
        } catch (Throwable ignored) {
        }
    }
}
