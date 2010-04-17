package ratetip.activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ChangeTextTips extends Activity {
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.xml.changetexttipslayout);
		TextView text = (TextView)findViewById(R.id.txt_txtchangetips);
		text.setText("Have fun with it! It doesn't only have to be about the quality of your food - " +
						"It can be things like \"How hot is the waiter/waitress?\" " +
						"Or maybe \"How fancy is your date tonight?\"");
	}
}