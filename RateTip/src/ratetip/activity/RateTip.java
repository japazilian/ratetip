package ratetip.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Vibrator;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.GestureDetector;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.GestureDetector.OnGestureListener;
import android.view.View.OnClickListener;
import android.view.View.OnKeyListener;
import android.view.View.OnTouchListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.RatingBar.OnRatingBarChangeListener;

public class RateTip extends Activity implements OnRatingBarChangeListener, OnTouchListener, OnClickListener, OnKeyListener, OnGestureListener{
	/** Called when the activity is first created. */
	private String question, one, two, three, four;
	private float onev, twov, threev, fourv, taxv;
	private EditText edit_bill, edit_tip, edit_total;
	private RatingBar rate_stars;
	private LinearLayout ll_background;
	private TextView txt_rate, txt_tip, txt_total, txt_bill;
	private SharedPreferences settings;
	private SharedPreferences prefs;
	private SharedPreferences.Editor editor;
	private GestureDetector gestureScanner;
	private Button btn_add, btn_sub;
	private int split_bill;
	private String curr;
	
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        initControls();
        setCurrency();
        edit_bill.requestFocus();
    }
    public void initControls() {
    	 rate_stars = (RatingBar)findViewById(R.id.RatingBar01);
         rate_stars.setOnRatingBarChangeListener(this); 
         rate_stars.setOnTouchListener(this);
         
         edit_bill = (EditText)findViewById(R.id.EditText01);
         //edit_bill.setOnKeyListener(this);
         
         edit_bill.addTextChangedListener(
        	new TextWatcher() {

        	 public void afterTextChanged (Editable s){
        	 calculate();
        	 }
        	 public void beforeTextChanged (CharSequence s, int 
        	 start, int
        	 count, int after){
        	 }
        	 public void onTextChanged (CharSequence s, int start, 
        	 int before,
        	 int count) {
        	 }
        	});
         
         edit_tip = (EditText)findViewById(R.id.EditText02);
         edit_total = (EditText)findViewById(R.id.EditText03);
         
         ll_background = (LinearLayout)findViewById(R.id.LinearLayout01);
         ll_background.setOnTouchListener(this);
         
         txt_total = (TextView)findViewById(R.id.TextView03);
         txt_rate = (TextView)findViewById(R.id.TextView01);
         txt_tip = (TextView)findViewById(R.id.TextView02);
         txt_bill = (TextView)findViewById(R.id.Total_Label);
         txt_tip.setOnTouchListener(this);
         settings = getSharedPreferences("MyPrefsFile", 0);
         editor = settings.edit();
         this.getSavedData();
         gestureScanner = new GestureDetector(this);
         prefs = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
         
         btn_add = (Button)findViewById(R.id.Button02);
         btn_sub = (Button)findViewById(R.id.Button01);
         btn_add.setOnClickListener(this);
         btn_sub.setOnClickListener(this);
         
         split_bill = 1;
         
    }
    public void getSavedData() {
    	 question = settings.getString("txtquestion", "\"How was your meal?\"");
         one=settings.getString("txtonestar", "\"Bad...\"");
 	     two=settings.getString("txttwostar", "\"Alright\"");
 		 three=settings.getString("txtthreestar", "\"Pretty Good\"");
 		 four=settings.getString("txtfourstar", "\"Awesome!\"");
 		
 		 onev = settings.getFloat("tiponestar", 10.0f);
 		 twov = settings.getFloat("tiptwostar", 13.0f);
 		 threev = settings.getFloat("tipthreestar", 15.0f);
 		 fourv = settings.getFloat("tipfourstar", 17.0f);
 		 taxv = settings.getFloat("tax", 0.0f);
 		 
    }
    
    public void onResume() {
    	super.onResume();
    	this.getSavedData();
    	this.setCurrency();
		this.calculate();
        edit_bill.requestFocus();
    }
    
    public void onRestart() {
    	super.onRestart();
    	editor.putBoolean("tip_specific_bool", false);
    	editor.putBoolean("split_bool", false);
    	editor.putInt("split", 1);
     	editor.commit();
     	this.setCurrency();
    	rate_stars.setRating(0.0f);
        edit_bill.requestFocus();
    }
    public void setCurrency() {
    	curr = prefs.getString("pref_currency", "$");
    	txt_bill.setText("Bill: " + curr);
    	txt_tip.setText("Tip: " + curr);
		txt_total.setText("Total: " + curr);
		
		boolean bubble = prefs.getBoolean("pref_bubble", false);
		LinearLayout ll = (LinearLayout)findViewById(R.id.LinearLayout02);
		if(bubble) {
			txt_rate.setBackgroundResource(R.drawable.balloon);
			txt_rate.setTextColor(Color.BLACK);
			//rate_stars.setPadding(0, 0, 0, 0);
			//txt_rate.setPadding(0, 20, 0, 0);
		}
		else {
			//txt_rate.setBackgroundResource(0);
			txt_rate.setBackgroundDrawable(null);
			txt_rate.setTextColor(Color.WHITE);
			//ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, 150));
			//rate_stars.setPadding(0, 20, 0, 0);
			//rate_stars.setPadding(0, 20, 0, 0);
			//txt_rate.setPadding(0, 20, 0, 0);
		}
    }
	public void onRatingChanged(RatingBar r, float n, boolean u) {
    	editor.putBoolean("tip_specific_bool", false);
    	editor.putBoolean("split_bool", false);
     	editor.commit();
     	this.calculate();
    }
	public boolean onTouch(View v, MotionEvent event) {
		switch(v.getId()) {
		case R.id.TextView02:
			startActivity(new Intent(this, AboutTip.class));
			break;
		case R.id.RatingBar01:
		case R.id.LinearLayout01:
			InputMethodManager in = (InputMethodManager)getSystemService
			(Context.INPUT_METHOD_SERVICE);
			in.hideSoftInputFromWindow(findViewById
			(R.id.EditText01).getApplicationWindowToken(),
			InputMethodManager.HIDE_NOT_ALWAYS); 
			ll_background.requestFocus();
			break;
		}
		return false;
	}
	public boolean onKey(View v, int keyCode, KeyEvent event) {
			Log.d("xxx", "here");
			this.calculate();
			return false;
	}
	public void calculate() {
		//initialize all the variables
		double bill_value, tip_value = 0, total_value = 0;
		String billString = edit_bill.getText().toString();
    	if(billString.contentEquals("")) {
    		bill_value=0;
    	}
    	else {
    		try {
    			bill_value = Float.parseFloat(billString);
    		}
    		catch(Exception e) {
    			edit_bill.setText("");
    			bill_value=0;
    		}
    	}
    	//specific tip set
    	if(settings.getBoolean("tip_specific_bool", false)) {
    		float tip_specific = settings.getFloat("tip_specific", 0.0f);
	    	rate_stars.setRating(0);//calls onRatingChanged so we need to change it back
	    	editor.putBoolean("tip_specific_bool", true);
	    	editor.commit();    	
    		if ((tip_specific*10)%10==0)
    			txt_tip.setText("("+(int)tip_specific+"%) Tip: " + curr);
    		else
    			txt_tip.setText("("+tip_specific+"%) Tip: " + curr);
    		tip_value = ((bill_value/(1.0+(taxv/100))) * (tip_specific/100));
    		total_value = (bill_value + tip_value);
    		txt_rate.setText("");
    	}
    	else {
    		int rating_value = (int)rate_stars.getRating();
	    	//normal tip and total calculate
	    	switch(rating_value) {
	    	case 0:
	    		txt_rate.setText(question);
	    		txt_tip.setText("Tip: " + curr);
	    		txt_total.setText("Total: " + curr);
	    		edit_tip.setText("0.00");
	        	edit_total.setText("0.00");
	        	split_bill = 1;
	    		return;
	    	case 1:
	    		txt_rate.setText(one);
	    		if ((onev*10)%10==0)
	    			txt_tip.setText("("+(int)onev+"%) Tip: " + curr);
	    		else
	    			txt_tip.setText("("+onev+"%) Tip: " + curr);
	    		tip_value = ((bill_value/(1.0+(taxv/100))) * (onev/100));
	    		total_value = (bill_value + tip_value);
	    		break;
	    	case 2:
	    		txt_rate.setText(two);
	    		if ((twov*10)%10==0)
	    			txt_tip.setText("("+(int)twov+"%) Tip: " + curr);
	    		else
	    			txt_tip.setText("("+twov+"%) Tip: " + curr);
	    		tip_value = ((bill_value/(1.0+(taxv/100))) * (twov/100));
	    		total_value = (bill_value + tip_value);
	    		break;
	    	case 3:
	    		txt_rate.setText(three);
	    		if ((threev*10)%10==0)
	    			txt_tip.setText("("+(int)threev+"%) Tip: " + curr);
	    		else
	    			txt_tip.setText("("+threev+"%) Tip: " + curr);
	    		tip_value = ((bill_value/(1.0+(taxv/100))) * (threev/100));
	    		total_value = (bill_value + tip_value);
	    		break;
	    	case 4:
	    		txt_rate.setText(four);
	    		if ((fourv*10)%10==0)
	    			txt_tip.setText("("+(int)fourv+"%) Tip: " + curr);
	    		else
	    			txt_tip.setText("("+fourv+"%) Tip: " + curr);
	    		tip_value = ((bill_value/(1.0+(taxv/100))) * (fourv/100));
	    		total_value = (bill_value + tip_value);
	    		break;
	    	}
    	}
    	//split
		if(split_bill > 1) {
    		if(prefs.getBoolean("pref_tip_split", false))
    			tip_value = tip_value/split_bill;
			total_value = total_value/split_bill;
			txt_total.setText("("+split_bill+") Total: " + curr);
			//special rounding for split bill if they want it

    		if(prefs.getBoolean("pref_round_split", false)) {
				switch(settings.getInt("round_type", 1)) {
			    	case 1:
			    		break;
			    	case 2:
			    		tip_value = tip_value + (Math.ceil(total_value) - total_value);
			    		total_value = Math.ceil(total_value);
			    		break;
			    	case 3:
			    		tip_value = tip_value + (Math.floor(total_value) - total_value);
			    		total_value = Math.floor(total_value);
			    		break;
			    	case 4:
			    		tip_value = tip_value + (Math.round(total_value) - total_value);
			    		total_value = Math.round(total_value);
			    		break;    		
		    	}
    		}
    	}
    	else {
    		//take care of rounding
    		txt_total.setText("Total: " + curr);
        	switch(settings.getInt("round_type", 1)) {
        	case 1:
        		break;
        	case 2:
        		total_value = Math.ceil(total_value);
        		tip_value = total_value-bill_value;
        		break;
        	case 3:
        		total_value = Math.floor(total_value);
        		tip_value = total_value-bill_value;
        		break;
        	case 4:
        		total_value = Math.round(total_value);
        		tip_value = total_value-bill_value;
        		break;    		
        	}
    		//if(prefs.getBoolean("pref_tip_split", false))
    		//	tip_value = total_value-bill_value;
    	}
    	edit_tip.setText(String.format("%.2f", tip_value));
    	edit_total.setText(String.format("%.2f", total_value));
	}
	
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
		case R.id.preferences:
			startActivity(new Intent(this, Preferences.class));
			return true;
		case R.id.about:
			startActivity(new Intent(this, About.class));
			return true;
		}
		return false;
	}
	
    public boolean onTouchEvent(MotionEvent me) 
    { 
		return gestureScanner.onTouchEvent(me); 
    } 
	public boolean onDown(MotionEvent e) {
		return false;
	}
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX,
			float velocityY) {
		if(prefs.getBoolean("pref_swipe", false)) {
			 int dx = (int) (e2.getX() - e1.getX());
			 int dy = (int) (e2.getY() - e1.getY());
	         // don't accept the fling if it's too short
	         // as it may conflict with a button push
	         if (Math.abs(dx) > 190 && Math.abs(dy) < 80) {
				edit_bill.setText("");
				rate_stars.setRating(0.0f);
				this.onRatingChanged(rate_stars, 0.0f, true);
				split_bill = 1;
	         }
		}
		return false;
	}
	public void onLongPress(MotionEvent e) {
		
	}
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX,
			float distanceY) {
		return false;
	}
	public void onShowPress(MotionEvent e) {
		
	}
	public boolean onSingleTapUp(MotionEvent e) {
		return false;
	}
	
	public void onClick(View v) {
		switch(v.getId()) {
			case (R.id.Button01):
				if(split_bill > 1)
					split_bill--;
				break;
			case (R.id.Button02):
				split_bill++;
				break;
		}
		this.calculate();
	}
}