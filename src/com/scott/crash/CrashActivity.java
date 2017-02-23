package com.scott.crash;

import com.miwan.google.zxing.activity.ShowScanResultDialog;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

/**
 * Android 中处理崩溃异常
 * 
 * @author xqls
 *
 */
public class CrashActivity extends Activity {
	private String s;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		System.out.println(s.equals("any String"));
		new ShowScanResultDialog(this, new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
			}
		}).showDialog("确定", "撒旦法撒旦发射点发射得分撒");
	}
}
