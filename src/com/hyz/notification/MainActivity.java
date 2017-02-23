package com.hyz.notification;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
	}

	public void tv(View v) {
		Log.e("hyz", "------------------onClick--------------------");
		showDialog();
	}

	public void tb(View v) {
		Log.e("hyz", "------------------onClick--------------------");
		showTipDialog(this);
	}

	Dialog dialogx;

	private void showDialog() {
		dialogx = new Dialog(this);
		View view = initView(this);
		dialogx.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		dialogx.setContentView(view);
		dialogx.show();
		Window dialogWindow = dialogx.getWindow();
		dialogWindow
				.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialogWindow.setGravity(Gravity.CENTER);
		WindowManager m = this.getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); //
		// // 获取对话框当前的参数值
		// p.height = d.getHeight() / 2; // 高度设置
		// p.width = d.getWidth() / 2; // 宽度设置
		//
		// p.gravity = Gravity.CENTER;
		// 获取对话框当前的参数值
		int width = d.getWidth();
		int height = d.getHeight();
		if (width > height) {
			width = width * 2 / 3;
			height = width * 2 / 3;
		} else {
			width = height * 2 / 3;
			height = width * 2 / 3;
		}
		p.height = height; // 高度设置
		p.width = width; // 宽度设置
		Log.e("hyz", "(" + d.getHeight() + "," + d.getWidth() + ")");
		Log.e("hyz", "(" + p.height + "," + p.width + ")");
		dialogWindow.setAttributes(p);

	}

	public static int dip2px(Context ctx, int dpValue) {
		float scale = ctx.getResources().getDisplayMetrics().density;
		return (int) (dpValue * scale + 0.5f);
	}

	AlertDialog dialog = null;

	int size = 0;

	void showTipDialog(Context context) {

		View view = initView(context);
		dialog = new AlertDialog.Builder(context).create();
		dialog.show();

		Window dialogWindow = dialog.getWindow();
		dialogWindow
				.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
		dialogWindow.setGravity(Gravity.CENTER);

		WindowManager m = getWindowManager();
		Display d = m.getDefaultDisplay(); // 获取屏幕宽、高用
		WindowManager.LayoutParams p = dialogWindow.getAttributes(); //
		// 获取对话框当前的参数值
		int width = d.getWidth();
		int height = d.getHeight();
		if (width > height) {
			width = width * 2 / 3;
			height = width * 2 / 3;
		} else {
			width = height * 2 / 3;
			height = width * 2 / 3;
		}
		p.height = height; // 高度设置
		p.width = width; // 宽度设置
		p.gravity = Gravity.CENTER;
		Log.e("hyz", "(" + d.getHeight() + "-----" + d.getWidth() + ")");// 1080---1920
		Log.e("hyz", "(" + p.height + "," + p.width + ")");

		dialogWindow.setAttributes(p);
		dialogWindow.setContentView(view);

	}

	@SuppressLint("NewApi")
	private View initView(Context context) {

		RelativeLayout ll_center;
		// --------------------总体布局---------------------------
		RelativeLayout.LayoutParams llpCenter = new RelativeLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		ll_center = new RelativeLayout(context);
		ll_center.setLayoutParams(llpCenter);

		ll_center.setBackground(getShape(15, new float[] { 50, 50, 50, 50, 50,
				50, 50, 50 }, Color.parseColor("#C4A172"),
				Color.parseColor("#DBCBB5")));

		// --------------------标题---------------------------
		RelativeLayout.LayoutParams lp_tv = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT, dip2px(this, 50));
		lp_tv.addRule(RelativeLayout.ALIGN_PARENT_TOP);
		lp_tv.addRule(RelativeLayout.CENTER_HORIZONTAL);

		TextView tvTitle = new TextView(context);
		tvTitle.setLayoutParams(lp_tv);
		tvTitle.setGravity(Gravity.CENTER);
		tvTitle.setTextColor(Color.parseColor("#DACAAE"));
		tvTitle.setTextSize(16);
		tvTitle.setBackground(getShape(30, new float[] { 50, 50, 50, 50, 0, 0,
				0, 0 }, Color.TRANSPARENT, Color.parseColor("#7B6855")));
		tvTitle.setText("扫 描 结 果");
		tvTitle.setId(1);
		ll_center.addView(tvTitle);

		// ---------------------内容--------------------------
		RelativeLayout.LayoutParams rlpTextViewTip = new RelativeLayout.LayoutParams(
				RelativeLayout.LayoutParams.MATCH_PARENT,
				RelativeLayout.LayoutParams.WRAP_CONTENT);
		Log.e("hyz", "px:" + dip2px(this, 10));
		rlpTextViewTip.setMargins(dip2px(this, 10), dip2px(this, 5),
				dip2px(this, 10), dip2px(this, 10));
		rlpTextViewTip.addRule(RelativeLayout.CENTER_IN_PARENT);
		rlpTextViewTip.addRule(RelativeLayout.ABOVE, 3);
		rlpTextViewTip.addRule(RelativeLayout.BELOW, 1);

		TextView tvTip = new TextView(context);
		tvTip.setLayoutParams(rlpTextViewTip);
		tvTip.setId(2);
		tvTip.setGravity(Gravity.CENTER);
		tvTip.setTextColor(Color.parseColor("#7B6855"));
		tvTip.setText("很 抱 歉，游 戏 暂 时 不 支 持 代 付 !应用调用摄像头权限被禁止，请\n设置应用权限后重新登录扫码！应用调用摄像头权限被禁止，请\n设置应用权限后重新登录扫码！应用调用摄像头权限被禁止，请\n设置应用权限后重新登录扫码！"); // 应用调用摄像头权限被禁止，请\n设置应用权限后重新登录扫码！
		ll_center.addView(tvTip);

		// ------------------按钮-----------------------------
		RelativeLayout.LayoutParams rlpTextViewReScan = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, dip2px(this, 45));
		rlpTextViewReScan.setMargins(0, 0, 0, dip2px(this, 20));
		rlpTextViewReScan.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		rlpTextViewReScan.addRule(RelativeLayout.CENTER_HORIZONTAL);
		Button btnConfirm = new Button(context);
		btnConfirm.setId(3);
		btnConfirm.setLayoutParams(rlpTextViewReScan);
		btnConfirm.setGravity(Gravity.CENTER);
		btnConfirm.setPadding(dip2px(this, 30), 0, dip2px(this, 30), 0);
		btnConfirm.setTextColor(Color.parseColor("#7B6855"));

		btnConfirm.setBackgroundDrawable(setdrawable());

		btnConfirm.setText("确    定");
		btnConfirm.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Toast.makeText(MainActivity.this, "点击了确定", Toast.LENGTH_SHORT)
						.show();
				if (dialogx != null)
					dialogx.dismiss();
				if (dialog != null)
					dialog.dismiss();
			}
		});
		ll_center.addView(btnConfirm);
		return ll_center;
	}

	/*
	 * 
	 * <?xml version="1.0" encoding="utf-8"?> <selector
	 * xmlns:android="http://schemas.android.com/apk/res/android" >
	 * 
	 * <!-- Non focused states --> <item android:state_focused="false"
	 * android:state_selected="false" android:state_pressed="false"
	 * android:drawable="@drawable/contact" />
	 * 
	 * <item android:state_focused="false" android:state_selected="true"
	 * android:state_pressed="false" android:drawable="@drawable/contact_sel" />
	 * <!-- Focused states -->
	 * 
	 * <item android:state_focused="true" android:state_selected="false"
	 * android:state_pressed="false" android:drawable="@drawable/contact_sel" />
	 * <item android:state_focused="true" android:state_selected="true"
	 * android:state_pressed="false" android:drawable="@drawable/contact_sel" />
	 * <!-- Pressed --> <item android:state_selected="true"
	 * android:state_pressed="true" android:drawable="@drawable/contact_sel" />
	 * <item android:state_pressed="true"
	 * android:drawable="@drawable/contact_sel" /> </selector>
	 */

	private Drawable setdrawable() {

		// -----------------------------------------------------
		int strokeWidth = 5; // 3px not dp
		int roundRadius = 15; // 8px not dp
		int strokeColor = Color.parseColor("#2E3135");
		int fillColor = Color.parseColor("#DFDFE0");

		GradientDrawable gd = new GradientDrawable();
		gd.setColor(fillColor);
		gd.setCornerRadius(roundRadius);
		gd.setStroke(strokeWidth, strokeColor);

		// -----------------------------------------------------
		int colors1[] = { 0xffF9EBC3, 0xffFDE98D, 0xffD28F3B };// 分别为开始颜色，中间夜色，结束颜色
		GradientDrawable gd1 = new GradientDrawable(
				GradientDrawable.Orientation.BOTTOM_TOP, colors1);// 从左下到右上
		gd1.setCornerRadius(40);
		gd1.setStroke(5, Color.parseColor("#9C866E"));

		int colors2[] = { 0xffFDE98D, 0xffD28F3B, 0xffF9EBC3 };// 分别为开始颜色，中间夜色，结束颜色
		GradientDrawable gd2 = new GradientDrawable(
				GradientDrawable.Orientation.BOTTOM_TOP, colors2);// 从左下到右上
		gd2.setCornerRadius(40);
		gd2.setStroke(5, Color.parseColor("#9C866E"));

		// -----------------------------------------------------

		StateListDrawable drawable = new StateListDrawable();
		// Non focused states
		drawable.addState(
				new int[] { -android.R.attr.state_focused,
						-android.R.attr.state_selected,
						-android.R.attr.state_pressed }, gd1);

		drawable.addState(new int[] { -android.R.attr.state_focused,
				android.R.attr.state_selected, -android.R.attr.state_pressed },
				new ColorDrawable(Color.parseColor("#FFFF00")));
		// Focused states
		drawable.addState(
				new int[] { android.R.attr.state_focused,
						-android.R.attr.state_selected,
						-android.R.attr.state_pressed }, new ColorDrawable(
						Color.parseColor("#FF00FF")));
		drawable.addState(new int[] { android.R.attr.state_focused,
				android.R.attr.state_selected, -android.R.attr.state_pressed },
				new ColorDrawable(Color.parseColor("#0000FF")));
		// Pressed
		drawable.addState(new int[] { android.R.attr.state_selected,
				android.R.attr.state_pressed },
				new ColorDrawable(Color.parseColor("#FF0000")));

		drawable.addState(new int[] { android.R.attr.state_pressed }, gd2);

		// textView.setCompoundDrawablesWithIntrinsicBounds(null, drawable,
		// null,null);
		return drawable;
	}

	/**
	 * 通过代码来获取到shape
	 * 
	 * @param strokeWidth
	 *            边框的宽度,单位px
	 * @param roundRadius
	 *            背景弧度,单位px
	 * @param strokeColor
	 *            边框的颜色
	 * @param fillColor
	 *            填充颜色
	 */
	private GradientDrawable getShape(int strokeWidth, float[] radius,
			int strokeColor, int fillColor) {

		GradientDrawable gd = new GradientDrawable();
		gd.setColor(fillColor);
		// gd.setCornerRadius(roundRadius);
		// 1、2两个参数表示左上角，3、4表示右上角，5、6表示右下角，7、8表示左下角
		gd.setCornerRadii(radius);

		gd.setStroke(strokeWidth, strokeColor);
		return gd;
	}
}
