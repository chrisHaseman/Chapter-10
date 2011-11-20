package com.haseman.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Surface;
import android.view.WindowManager;

public class ContentViewingActivity extends FragmentActivity{

	public void onCreate(Bundle data){
		super.onCreate(data);
		setContentView(R.layout.content_activity_layout);
		int rotation = getWindowManager().getDefaultDisplay().getRotation();
		if(rotation == Surface.ROTATION_0 || rotation == Surface.ROTATION_180)
			finish();
	}
}
