package com.haseman.fragments;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class ContentFragment extends Fragment{

	String displayString = null;
	
	public ContentFragment(String displayString){
		super();
		this.displayString = displayString;
	}
	public ContentFragment(){
		super();
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.content_layout, null);
		
		if(getActivity().getIntent() != null
				&& getActivity().getIntent().getStringExtra("text") != null){
			setContetText(v, getActivity().getIntent().getStringExtra("text"));
		}
		if(displayString != null){
			setContetText(v, displayString);
		}
		return v;
	}
	
	@Override
	public void onAttach(Activity activity){
		//If our starting activity had the text, use it
		super.onAttach(activity);
	}
	
	private void setContetText(View view, String text){
		TextView tv = (TextView)view.findViewById(R.id.content_text_view);
		tv.setText(text);
	}
	
	@Override
	public void onResume(){
		super.onResume();
		IntentFilter displayIntentFilter = new IntentFilter();
		displayIntentFilter.addAction(ExampleActivity.ACTION_DISPLAY_TEXT);
		getActivity().registerReceiver(fragmentReceiver, displayIntentFilter);
		
	}
	public void onPause(){
		super.onPause();
		getActivity().unregisterReceiver(fragmentReceiver);
	}
	BroadcastReceiver fragmentReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			setContetText(getView(), intent.getStringExtra("text"));
			
		}
	};
}
