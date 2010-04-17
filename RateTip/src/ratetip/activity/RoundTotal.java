package ratetip.activity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class RoundTotal extends Activity implements OnCheckedChangeListener {
	private static final String PREFS_NAME = "MyPrefsFile";
	private RadioGroup rg;
	

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.roundtotallayout);
		rg = (RadioGroup)findViewById(R.id.rg_round);
		rg.setOnCheckedChangeListener(this);
	}


	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		int type=0;
		switch(checkedId) {
		case R.id.round_none:
			type = 1;
			break;
		case R.id.round_up:
			type = 2;
			break;
		case R.id.round_down:
			type = 3;
			break;
		case R.id.round_normal:
			type = 4;
			break;		
		}
		SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
		SharedPreferences.Editor editor = settings.edit();
		editor.putInt("round_type", type);
		editor.commit();
		this.finish();		
	}	
}