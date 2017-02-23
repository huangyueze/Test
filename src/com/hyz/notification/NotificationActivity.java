package com.hyz.notification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.RemoteViews;

public class NotificationActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		Button btn = (Button) findViewById(R.id.btn1);
		btn.setText("正常的notification");
		btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				normalNotify();
			}
		});

		Button btn1 = (Button) findViewById(R.id.btn2);
		btn1.setText("自定义的notification");
		btn1.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				customNotify();
			}
		});
	}

	private static final int ONGOING_ID = 1;
	private static final int CUSTOM_NOTIFY_ID = 2;
	private NotificationManager mNotificationManager;

	private void normalNotify() {
		Context context = this;
		mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

		int icon = android.R.drawable.stat_notify_voicemail;
		CharSequence tickerText = "普通通知";
		long when = System.currentTimeMillis();

		// 新建一个Notification实例
		Notification notification = new Notification(icon, tickerText, when);
		// Notification notification = new NotificationCompat.Builder(this)
		// .setSmallIcon(icon).setContentTitle("程序已启动")
		// .setContentText("通知").build();

		// 设定声音
		notification.defaults |= Notification.DEFAULT_SOUND;

		// 自定义声音
		// notification.sound =
		// Uri.parse("file:///sdcard/notification/ringer.mp3");

		// 设定震动(需加VIBRATE权限)
		notification.defaults |= Notification.DEFAULT_VIBRATE;

		// 自定义震动方式
		// long[] vibrate = {0, 100, 200, 300};
		// notification.vibrate = vibrate;

		// 设定LED灯提醒
		notification.defaults |= Notification.DEFAULT_LIGHTS;

		// 设置点击此通知后自动清除
		notification.flags |= Notification.FLAG_INSISTENT;

		// 把通知放置在"正在运行栏目中"
		// notification.flags |= Notification.FLAG_ONGOING_EVENT;

		CharSequence contentTitle = "普通通知的标题";
		CharSequence contentText = "通知的内容部分,一段长长的文字...";

		Intent intent = new Intent(context, MainActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		notification.setLatestEventInfo(context, contentTitle, contentText,
				contentIntent);
		mNotificationManager.notify(ONGOING_ID, notification);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();

		finish();
		// 取消一个通知
		mNotificationManager.cancel(ONGOING_ID);
		// 结束进程
		android.os.Process.killProcess(android.os.Process.myPid());
		System.exit(0);
	}

	// 个性化通知点击事件
	private void customNotify() {
		Context context = this;

		NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		int icon = R.drawable.ic_launcher;
		CharSequence tickerText = "自定义通知";
		long when = System.currentTimeMillis();

		Notification notificaiton = new Notification(icon, tickerText, when);
		notificaiton.flags |= Notification.FLAG_AUTO_CANCEL;

		RemoteViews contentView = new RemoteViews(context.getPackageName(),
				R.layout.custom_notification_layout);
		contentView.setImageViewResource(R.id.imageView, R.drawable.logo);
		contentView.setTextViewText(R.id.textView, "这是一个个性化的通知视图，代替了系统默认的通知视图");
		// 指定个性化视图
		notificaiton.contentView = contentView;

		Intent intent = new Intent(context, NotificationActivity.class);
		PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
				intent, 0);
		// 指定内容意图
		notificaiton.contentIntent = contentIntent;
		mNotificationManager.notify(CUSTOM_NOTIFY_ID, notificaiton);
	}
}
