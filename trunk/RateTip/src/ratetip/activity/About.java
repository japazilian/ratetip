package ratetip.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class About extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.aboutlayout);
		TextView text = (TextView)findViewById(R.id.txt_about);
		text.setText("RateTip is a rating-based tip calculator." +
"Pick a star rating, and it will use your specified tip percentage and phrase."+
"\n\nCheck the preferences section to customize your tip calculator and see all other options available."+

"\n\nTo input a specific tip amount, tap on the text \"Tip: $\"."+

"\n\nRounding is also an option:"+
"\nnone - no rounding"+
"\nup - $4.03 becomes $5.00"+
"\ndown - $18.93 becomes $18.00"+
"\nnormal - $7.45 becomes $7.00 and $7.51 becomes $8.00"+

"\n\nVersion 1.6.1 - small bug fix"+

"\n\nCreated by: Rodrigo Haragutchi"+

"\n\nContact me at rharagutchi@gmail.com if you find any errors or would like any new functionalities." +
"\n\nThank you to David P. and David V. for their help :)");
	}
}