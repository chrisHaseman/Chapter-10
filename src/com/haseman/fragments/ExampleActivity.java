package com.haseman.fragments;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.MenuItem;

public class ExampleActivity extends FragmentActivity {
    
	FragmentManager manager;
	
	public static final String ACTION_DISPLAY_TEXT = "com.haseman.DISPLAY_TEXT";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        manager = getSupportFragmentManager();
        setTitle("Fragment Example");
    }
    @Override
    public void onResume(){
    	super.onResume();
    	//If there isn't a 
    	if(findViewById(R.id.content_fragment) == null){
    		registerReceiver(true);
    		FragmentTransaction ft = manager.beginTransaction();
			ft.add(R.id.list_fragment, new DemoListFragment());
			ft.commit();
    	}
    }
    
    private void registerReceiver(boolean regsiter){
    	if(regsiter){
    		IntentFilter displayIntentFilter = new IntentFilter();
    		displayIntentFilter.addAction(ACTION_DISPLAY_TEXT);
    		registerReceiver(intentReceiver, displayIntentFilter);
    	}
    	else{
    		unregisterReceiver(intentReceiver);
    	}
    }
    @Override
    public void onPause(){
    	super.onPause();
    	if(findViewById(R.id.content_fragment) == null){
    		unregisterReceiver(intentReceiver);
    	}
    }
    @Override
    public void onBackPressed(){
    	if(manager.getBackStackEntryCount() > 0){
    		manager.popBackStackImmediate();
    		registerReceiver(true);
    	}
    	else
    		super.onBackPressed();
    }
    
    public boolean onContextItemSelected(MenuItem item){
    	int id = item.getItemId();
    	
    	if(id == android.R.id.home){
    		//We're already home, so don't do anything here
    		return true;
    	}
    	else{
    		//Perform the delete action here
    		return true;
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
    	MenuItem item = menu.add("delete");
    	item.setIcon(android.R.drawable.ic_delete);
//    	if(Build.VERSION.SDK_INT >= 11){
//    		item.setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM);
//    	}
    	return true;
    }
//    private void buildActionBar(){
//    	ActionBar bar = getActionBar();
//    	ActionBar.Tab tab1 = bar.newTab();
//    	tab1.setTabListener(new ExampleTabListener());
//    	bar.addTab(tab1);
//    }
    
    BroadcastReceiver intentReceiver = new BroadcastReceiver() {
		
		@Override
		public void onReceive(Context context, Intent intent) {
			//The fragment isn't on this screen, so start a new activity with the text
			
			FragmentTransaction ft = manager.beginTransaction();
			//ft.remove(manager.findFragmentByTag("list fragment"));
			ft.replace(R.id.list_fragment, new ContentFragment(intent.getStringExtra("text")));
			ft.addToBackStack("contentFragment");
			ft.commit();
			registerReceiver(false);
			sendBroadcast(intent);
//			Intent startCotentActivity = new Intent(getApplicationContext(), ContentViewingActivity.class);
//			startCotentActivity.putExtra("text", intent.getStringExtra("text"));
//			startActivity(startCotentActivity);
		}
	};
}