package com.example.identity;

import java.util.ArrayList;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class CloudContactsActivity extends ListActivity {
	String name = "";
	String number = "";
	String total = "", cont;
	ArrayList<String> al = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		 if (android.os.Build.VERSION.SDK_INT > 9) {
			    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
			}
		// setContentView(R.layout.activity_main);
		Bundle b = getIntent().getExtras();
		cont = b.getString("cont");

		RegisterOthers ro = new RegisterOthers();
		List<Others> list = ro.getAllValues(cont);
		int len = list.size();
		System.out.println("size @@@@@@@@@@@@@" + len);
		for (int i = 0; i < len; i++) {

			Others oo = list.get(i);
			name = oo.getName();
			number = oo.getNumber();
			total = name + "@" + number;
			al.add(total);

			System.out.println(oo.getName() + "===========" + oo.getNumber());
		}
		ListView lv = getListView();
		lv.setAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, al));
		lv.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				String ite = ((TextView) view).getText().toString();
				Toast.makeText(getApplicationContext(), ite, 30).show();
				checkList(((TextView) view).getText().toString());
			}

			private void checkList(String contact) {
				// TODO Auto-generated method stub
				RegisterOthers rlist = new RegisterOthers();
				// rlist.createDomain();
				String to = contact;
				String[] names = to.split("@");
				String name = names[0];
				String number = names[1];

				Intent in1 = new Intent(CloudContactsActivity.this,
						OptionSActivity.class);
				in1.putExtra("name", name);
				in1.putExtra("number", number);
				startActivity(in1);

			}
		});
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				CloudContactsActivity.this);
		alertDialog.setTitle("Leave application?");
		alertDialog
				.setMessage("Are you sure you want to leave the application?");
		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						CloudContactsActivity.super.onBackPressed();
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
