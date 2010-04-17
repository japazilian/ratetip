package ratetip.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class AboutTip extends Activity implements OnClickListener {
	private static final String PREFS_NAME = "MyPrefsFile";
	private EditText edit_tip;
	private Button saveButton;
	
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.settiplayout);
		saveButton = (Button)this.findViewById(R.id.button_tip);
		saveButton.setOnClickListener(this);
		edit_tip = (EditText)findViewById(R.id.edit_tip);
	}

	public void onClick(View v) {
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			if ((edit_tip.getText().toString()).contentEquals("")) {
				editor.putBoolean("tip_specific_bool", false);
				editor.commit();
				this.finish();
			}
			else {
				try {
					editor.putFloat("tip_specific", Float.parseFloat(edit_tip.getText().toString()));
					editor.putBoolean("tip_specific_bool", true);
					editor.commit();
					this.finish();
				}
				catch(Exception e) {
					edit_tip.setText("");
				}
			}
	}
	
}