package com.example.identity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class OptionSActivity extends Activity {
	
 String number,name;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_option_s);
		Bundle b = getIntent().getExtras();
		 name = b.getString("name");
	  number = b.getString("number");
		TextView tv = (TextView) findViewById(R.id.textView3);
		TextView tv2 = (TextView) findViewById(R.id.textView4);
		tv.setText(name);
		tv2.setText(number);
		Button call = (Button) findViewById(R.id.button1);
		Button save = (Button) findViewById(R.id.button4);
		Button Message = (Button) findViewById(R.id.button2);
		Button shre = (Button) findViewById(R.id.button5);
		call.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Intent.ACTION_CALL);
				it.setData(Uri.parse("tel:" + number));
				startActivity(it);
				finish();
			}
		});
		Message.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(OptionSActivity.this,
						MessageActivity.class);
				it.putExtra("number", number);
				startActivity(it);
			}
		});

		save.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(OptionSActivity.this,
						SaveContactsActivity.class);
				it.putExtra("number", number);
				it.putExtra("name", name);
				startActivity(it);
			}
		});
		shre.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				
				Intent it = new Intent(OptionSActivity.this,
						ShareActivity.class);
				it.putExtra("number", number);
				it.putExtra("name", name);
				startActivity(it);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_option_s, menu);
		return true;
	}
	public void whatsapp(View v)
	{
		openWhatsappContact(number);
	}
	
	void openWhatsappContact(String number) {
	    Uri uri = Uri.parse("smsto:" + number);
	    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
	    i.setPackage("com.whatsapp");  
	    startActivity(Intent.createChooser(i, ""));
	}


}
