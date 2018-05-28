package com.example.identity;


import java.util.ArrayList;

import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.provider.ContactsContract.CommonDataKinds;
import android.provider.ContactsContract.RawContacts;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.provider.ContactsContract.CommonDataKinds.StructuredName;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

@SuppressWarnings("unused")
public class SaveContactsActivity extends Activity {
String name,number;
    @Override
    public void onCreate(Bundle b) {
    	
        super.onCreate(b);
        setContentView(R.layout.activity_save_contacts);
        b=getIntent().getExtras();
         number=b.getString("number");
        name=b.getString("name");
        final EditText etName = (EditText) findViewById(R.id.et_name);
		
		// Getting reference to Mobile EditText 
		final EditText etMobile = (EditText) findViewById(R.id.et_mobile_phone);
		etName.setText(name);
		etMobile.setText(number);
		
        Toast.makeText(getApplicationContext(), name+number,90).show();
        
        // Creating a button click listener for the "Add Contact" button
        OnClickListener addClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Getting reference to Name EditText 
				
				ArrayList<ContentProviderOperation> ops =
				          new ArrayList<ContentProviderOperation>();
				
				int rawContactID = ops.size();
				
				// Adding insert operation to operations list 
				// to insert a new raw contact in the table ContactsContract.RawContacts
				ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
						.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
						.withValue(RawContacts.ACCOUNT_NAME, null)
						.build());

				// Adding insert operation to operations list
				// to insert display name in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
						.withValue(StructuredName.DISPLAY_NAME, etName.getText().toString())
						.build());
				
				// Adding insert operation to operations list
				// to insert Mobile Number in the table ContactsContract.Data
				ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
						.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
						.withValue(ContactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
						.withValue(Phone.NUMBER, etMobile.getText().toString())
						.withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
						.build());
				
							

				try{
					// Executing all the insert operations as a single database transaction
					getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
					Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
				}catch (RemoteException e) {					
					e.printStackTrace();
				}catch (OperationApplicationException e) {
					e.printStackTrace();
				}
			}
		};
		
		
		// Creating a button click listener for the "Add Contact" button
		OnClickListener contactsClickListener = new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// Creating an intent to open Android's Contacts List
				Intent contacts = new Intent(Intent.ACTION_VIEW,ContactsContract.Contacts.CONTENT_URI);
				
				// Starting the activity
				startActivity(contacts);				
			}
		};
		
        
        // Getting reference to "Add Contact" button
        Button btnAdd = (Button) findViewById(R.id.btn_add);
        
        // Getting reference to "Contacts List" button
        Button btnContacts = (Button) findViewById(R.id.btn_contacts);
        
        // Setting click listener for the "Add Contact" button
        btnAdd.setOnClickListener(addClickListener);
        
        // Setting click listener for the "List Contacts" button
        btnContacts.setOnClickListener(contactsClickListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}