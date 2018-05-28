package com.example.identity;

import android.app.Activity;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ShareActivity extends Activity {
String number,name;
	@Override
	protected void onCreate(Bundle b) {
		super.onCreate(b);
		setContentView(R.layout.activity_share);
		  b=getIntent().getExtras();
	         number=b.getString("number");
	        name=b.getString("name");
	        final EditText etName = (EditText) findViewById(R.id.editText1);
			
			// Getting reference to Mobile EditText 
			final EditText etMobile = (EditText) findViewById(R.id.editText2);
			etMobile.setText(name+"@"+number);
			Button bt=(Button)findViewById(R.id.button1);
			bt.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String aa=etName.getText().toString();
					String bb=etMobile.getText().toString();
					SmsManager sms=SmsManager.getDefault();
					sms.sendTextMessage(aa, null, bb, null, null);
					Toast.makeText(getApplicationContext(), "Message Sent",90).show();
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_share, menu);
		return true;
	}

}
