package ratetip.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class ChangeText extends Activity implements OnClickListener {
	private static final String PREFS_NAME = "MyPrefsFile";
	private EditText question, one, two, three, four;
	private Button save, tips;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.changetextlayout);
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		question = (EditText)findViewById(R.id.edit_txtquestion);
		one = (EditText)findViewById(R.id.edit_txtonestar);
		two = (EditText)findViewById(R.id.edit_txttwostar);
		three = (EditText)findViewById(R.id.edit_txtthreestar);
		four = (EditText)findViewById(R.id.edit_txtfourstar);
		save = (Button)findViewById(R.id.button_txtsave);
		tips = (Button)findViewById(R.id.button_txttips);
		save.setOnClickListener(this);
		tips.setOnClickListener(this);
		
		question.setText(settings.getString("txtquestion", "\"How was your meal?\""));
		one.setText(settings.getString("txtonestar", "\"Bad...\""));
		two.setText(settings.getString("txttwostar", "\"Alright\""));
		three.setText(settings.getString("txtthreestar", "\"Pretty Good\""));
		four.setText(settings.getString("txtfourstar", "\"Awesome!\""));
	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()) {
		case (R.id.button_txtsave):
			SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
			SharedPreferences.Editor editor = settings.edit();
			
			editor.putString("txtquestion", question.getText().toString());
			editor.putString("txtonestar", one.getText().toString());
			editor.putString("txttwostar", two.getText().toString());
			editor.putString("txtthreestar", three.getText().toString());
			editor.putString("txtfourstar", four.getText().toString());
			
			editor.commit();
			this.finish();
			break;
		case (R.id.button_txttips):
			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("About")
				   .setMessage("Have fun with it! It doesn't only have to be about the quality of your food - " +
						"It can be things like \"How hot is the waiter/waitress?\" " +
						"Or maybe \"How fancy is your date tonight?\"")
				   .setIcon(android.R.drawable.ic_dialog_info)
			       .setCancelable(false)
			       .setNegativeButton("Close", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			                dialog.cancel();
			           }
			       });
			AlertDialog alert = builder.create();
			alert.show();
			break;		
		}
	}	
}