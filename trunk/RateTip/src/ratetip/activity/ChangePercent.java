package ratetip.activity;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

public class ChangePercent extends Activity implements OnClickListener {
	private static final String PREFS_NAME = "MyPrefsFile";
	private EditText one, two, three, four;
	private Button save;
		
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.changepercentlayout);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		save = (Button)findViewById(R.id.button_tipsave);
		one = (EditText)findViewById(R.id.edit_tiponestar);
		two = (EditText)findViewById(R.id.edit_tiptwostar);
		three = (EditText)findViewById(R.id.edit_tipthreestar);
		four = (EditText)findViewById(R.id.edit_tipfourstar);
		
		one.setText(""+settings.getFloat("tiponestar", 10.0f));
		two.setText(""+settings.getFloat("tiptwostar", 13.0f));
		three.setText(""+settings.getFloat("tipthreestar", 15.0f));
		four.setText(""+settings.getFloat("tipfourstar", 17.0f));
		save.setOnClickListener(this);
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		
		boolean success=true;
		try {
			editor.putFloat("tiponestar", Float.parseFloat(one.getText().toString()));
		}
		catch(Exception e) {
			one.setText("");
			success=false;
		}
		try {
			editor.putFloat("tiptwostar", Float.parseFloat(two.getText().toString()));
		}
		catch(Exception e){
			two.setText("");
			success=false;
		}
		try {
			editor.putFloat("tipthreestar", Float.parseFloat(three.getText().toString()));
		}
		catch(Exception e) {
			three.setText("");
			success=false;
		}
		try {
			editor.putFloat("tipfourstar", Float.parseFloat(four.getText().toString()));
		}
		catch(Exception e) {
			four.setText("");
			success=false;
		}
		if(success) {
			editor.commit();
			this.finish();
		}
		else {
			InputMethodManager in = (InputMethodManager)getSystemService
			(Context.INPUT_METHOD_SERVICE);
			in.hideSoftInputFromWindow(findViewById
			(R.id.edit_tiponestar).getApplicationWindowToken(),
			InputMethodManager.HIDE_NOT_ALWAYS); 
		}
	}
}