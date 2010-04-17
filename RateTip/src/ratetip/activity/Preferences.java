package ratetip.activity;

import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.Preference.OnPreferenceClickListener;

public class Preferences extends PreferenceActivity implements OnPreferenceClickListener{

	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.preferenceslayout);
		
		Preference text = (Preference)findPreference("pref_text");
		text.setOnPreferenceClickListener(this);
		Preference percent = (Preference)findPreference("pref_percent");
		percent.setOnPreferenceClickListener(this);
		Preference tax = (Preference)findPreference("pref_tax");
		tax.setOnPreferenceClickListener(this);
		Preference round = (Preference)findPreference("pref_round");
		round.setOnPreferenceClickListener(this);
	}

	
	public boolean onPreferenceClick(Preference preference) {
		// TODO Auto-generated method stub
		if(preference.getKey().equals("pref_text")) 
			startActivity(new Intent(this, ChangeText.class));
		else if(preference.getKey().equals("pref_percent")) 
			startActivity(new Intent(this, ChangePercent.class));
		else if(preference.getKey().equals("pref_tax")) 
			startActivity(new Intent(this, AboutTax.class));
		else if(preference.getKey().equals("pref_round")) 
			startActivity(new Intent(this, RoundTotal.class));
		return false;
	}	
}