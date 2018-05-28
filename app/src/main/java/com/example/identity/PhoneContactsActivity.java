package com.example.identity;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class PhoneContactsActivity extends Activity {
	ArrayList<String> list1 = new ArrayList<String>();
	String name = "";
	String number = "", cont;
	ArrayAdapter<String> adapter;
	private static final Uri URI = ContactsContract.Contacts.CONTENT_URI;
	private static final String ID = ContactsContract.Contacts._ID;
	private static final Uri PURI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
	private static final String CID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
	private static final String DNAME = ContactsContract.Contacts.DISPLAY_NAME;
	private static final String HPN = ContactsContract.Contacts.HAS_PHONE_NUMBER;
	private static final String PNUM = ContactsContract.CommonDataKinds.Phone.NUMBER;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listv);
		 if (android.os.Build.VERSION.SDK_INT > 9) {
			    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
			    StrictMode.setThreadPolicy(policy);
			}
		Bundle b = getIntent().getExtras();
		cont = b.getString("cont");

		GetData();

		ListView lv = (ListView)findViewById(R.id.listView1);
		  adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_multiple_choice, list1);
	        lv.setAdapter(adapter);
	
		lv.setTextFilterEnabled(true);
		lv.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
		EditText	inputSearch = (EditText) findViewById(R.id.editText1);
		 inputSearch.addTextChangedListener(new TextWatcher() {
             
	            @Override
	            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
	                // When user changed the Text
	                PhoneContactsActivity.this.adapter.getFilter().filter(cs);  
	            }
	             
	            @Override
	            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
	                    int arg3) {
	                // TODO Auto-generated method stub
	                 
	            }
	             
	            @Override
	            public void afterTextChanged(Editable arg0) {
	                // TODO Auto-generated method stub                         
	            }
	        });
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
				rlist.createDomain();
				String to = contact;
				String[] names = to.split("@");
				String name = names[0];
				String number = names[1];
				Others user = new Others(name, number, cont);
				rlist.AddToOthers(user);

				Toast.makeText(getApplicationContext(), "Uploaded", 30).show();

			}
		});
	}

	public void GetData() {
		Cursor cu = getContentResolver().query(URI, null, null, null,
				ContactsContract.Contacts.DISPLAY_NAME + " ASC ");

		while (cu.moveToNext()) {

			String id = cu.getString(cu.getColumnIndex(ID));

			name = cu.getString(cu.getColumnIndex(DNAME));
			int phcounter = 0;
			if (Integer.parseInt(cu.getString(cu.getColumnIndex(HPN))) > 0) {
				Cursor pCur = getContentResolver().query(PURI, null,
						CID + " = ?", new String[] { id }, null);

				while (pCur.moveToNext()) {
					number = pCur.getString(pCur.getColumnIndex(PNUM));
					String data = name + "@ " + number;
					list1.add(data);

					phcounter++;
				}
				pCur.close();
			}
		}
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alertDialog = new AlertDialog.Builder(
				PhoneContactsActivity.this);
		alertDialog.setTitle("Leave application?");
		alertDialog
				.setMessage("Are you sure you want to leave the application?");
		alertDialog.setPositiveButton("YES",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						PhoneContactsActivity.super.onBackPressed();
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
