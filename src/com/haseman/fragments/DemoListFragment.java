package com.haseman.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class DemoListFragment extends ListFragment{
	
	String array[];
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.list_fragment, null);
		String entries[] = getResources().getStringArray(R.array.list_entries);
	
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.list_element, entries);
		setListAdapter(adapter);
		array = getResources().getStringArray(R.array.content_values);
		return v;
	}
	@Override
	public void onListItemClick(ListView list, View view, int position, long id){
		Intent i = new Intent(ExampleActivity.ACTION_DISPLAY_TEXT);
		i.putExtra("text", array[position]);
		getActivity().sendBroadcast(i);
	}
}
