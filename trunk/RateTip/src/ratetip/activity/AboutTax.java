package ratetip.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AboutTax extends Activity implements OnClickListener {
	private static final String PREFS_NAME = "MyPrefsFile";
	private EditText edit;
	private Button saveButton;
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.addtaxlayout);
		edit = (EditText)findViewById(R.id.edit_tax);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		edit.setText(""+settings.getFloat("tax", 0.0f));
		saveButton = (Button)this.findViewById(R.id.button_tax);
		saveButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		edit = (EditText)findViewById(R.id.edit_tax);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		if ((edit.getText().toString()).contentEquals("")) {
			this.finish();
		}
		else {
			float tax = 0.0f;
			try {
				tax = Float.parseFloat(edit.getText().toString());
				editor.putFloat("tax", tax);
				editor.commit();
				this.finish();
			}
			catch(Exception e) {
				edit.setText("");
			}
		}
	}
	
}