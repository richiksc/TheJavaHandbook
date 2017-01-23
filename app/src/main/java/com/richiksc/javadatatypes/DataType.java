package com.richiksc.javadatatypes;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

/**
 * Created by Richik SC on 7/20/2016.
 */
public class DataType {
  private int id;
  private int name;
  private int declaration;
  private Drawable avatar;
  private Drawable squareImage;
  private Context ctx;

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public DataType(int id, @StringRes int name, @StringRes int declaration, @DrawableRes int
      avatar, Context ctx) {

    this.id = id;
    this.name = name;
    this.declaration = declaration;
    int currentApiVersion = Build.VERSION.SDK_INT;
    if (currentApiVersion >= Build.VERSION_CODES.LOLLIPOP) {
      this.avatar = ctx.getResources().getDrawable(avatar, ctx.getTheme());
      this.squareImage = ctx.getResources().getDrawable(avatar, ctx.getTheme());
    } else {
      this.avatar = ctx.getResources().getDrawable(avatar);
      this.squareImage = ctx.getResources().getDrawable(avatar);
    }

    this.ctx = ctx;
    
  }

  @TargetApi(Build.VERSION_CODES.LOLLIPOP)
  public DataType(int id,
                  @StringRes int name,
                  @StringRes int declaration,
                  @DrawableRes int avatar,
                  @DrawableRes int squareImage,
                  Context ctx)
  {

    this.id = id;
    this.name = name;
    this.declaration = declaration;
    int currentApiVersion = Build.VERSION.SDK_INT;
    if (currentApiVersion >= Build.VERSION_CODES.LOLLIPOP) {
      this.avatar = ctx.getResources().getDrawable(avatar, ctx.getTheme());
      this.squareImage = ctx.getResources().getDrawable(squareImage, ctx.getTheme());
    } else {
      this.avatar = ctx.getResources().getDrawable(avatar);
      this.squareImage = ctx.getResources().getDrawable(squareImage);
    }

    this.ctx = ctx;

  }

  public int getId() {
    return id;
  }

  public String getName() {
    return this.ctx.getResources().getString(name);
  }

  public String getDeclaration() {
    return this.ctx.getResources().getString(declaration);
  }

  public Drawable getAvatar() {
    return avatar;
  }

  public Drawable getSquareImage() { return squareImage; }


}
