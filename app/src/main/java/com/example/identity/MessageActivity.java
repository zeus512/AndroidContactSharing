package com.example.identity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.gsm.SmsManager;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MessageActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_message);
		Bundle b = getIntent().getExtras();
		String number = b.getString("number");
		final EditText et = (EditText) findViewById(R.id.editText1);
		et.setText(number);
		final EditText et2 = (EditText) findViewById(R.id.editText2);

		Button Message = (Button) findViewById(R.id.button1);
		Message.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String number = et.getText().toString();
				String mess = et2.getText().toString();
				SmsManager sms = SmsManager.getDefault();
				sms.sendTextMessage(number, null, mess, null, null);
				Toast.makeText(getApplicationContext(), "Message Sent", 90)
						.show();
				Intent it = new Intent(MessageActivity.this,
						AndroidTabLayoutActivity.class);
				startActivity(it);
				finish();

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_message, menu);
		return true;
	}

}
