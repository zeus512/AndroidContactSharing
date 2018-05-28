package com.example.identity;

import org.example.customui.CustomUI;

import android.app.AlertDialog;
import android.app.TabActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

public class AndroidTabLayoutActivity extends TabActivity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tab);
		
		Bundle b = getIntent().getExtras();
		String name = b.getString("cont");
		TabHost tabHost = getTabHost();

		TabSpec photospec = tabHost.newTabSpec("");
		photospec.setIndicator("",
				getResources().getDrawable(R.drawable.contact));
		Intent photosIntent = new Intent(this, PhoneContactsActivity.class);
		photosIntent.putExtra("cont", name);
		photospec.setContent(photosIntent);

		TabSpec songspec = tabHost.newTabSpec("");
		songspec.setIndicator("",
				getResources().getDrawable(R.drawable.cloudcontacts));
		Intent songsIntent = new Intent(this, CloudContactsActivity.class);
		songsIntent.putExtra("cont", name);
		songspec.setContent(songsIntent);
		
		TabSpec spec = tabHost.newTabSpec("");
		spec.setIndicator("",
				getResources().getDrawable(R.drawable.ic_launcher));
	
		Intent Intent = new Intent(this, CustomUI.class);
		Intent.putExtra("cont", name);
		spec.setContent(Intent);

		tabHost.addTab(photospec);
		tabHost.addTab(songspec);
		tabHost.addTab(spec);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.activity_tab, menu);
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch (item.getItemId()) {
		case R.id.item1:
			Intent it = new Intent(AndroidTabLayoutActivity.this,
					MainActivity.class);
			startActivity(it);
			finish();
			break;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				AndroidTabLayoutActivity.this);
		alertDialog.setTitle("Leave application?");
		alertDialog
				.setMessage("Are you sure you want to leave the application?");
		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						AndroidTabLayoutActivity.super.onBackPressed();
					}
				});
		alertDialog.setNegativeButton("NO",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						// Write your code here to invoke NO event
						dialog.cancel();
					}
				});
		alertDialog.show();
		super.onBackPressed();
	}

}