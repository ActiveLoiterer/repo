package vieux.foo.tap_cercle;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ResultActivity extends Activity {

	TextView pourcenText, resultatText;
	Button boutonBack;
	
	int red = 0, blue = 0, green = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		pourcenText = (TextView)findViewById(R.id.pourcentText);
		resultatText = (TextView)findViewById(R.id.resultText);
		boutonBack = (Button)findViewById(R.id.boutonBack);
		
		Ecouteur ec = new Ecouteur();
		
		boutonBack.setOnClickListener(ec);
		//get user time from DB, not intent. Call methods from operations...
		//getUserPulse(), getUserAge()
		
		int pulse = 9, age = 20;
		char sexe = 'M';
		
		int pourcent = Stats.pourcentage(pulse, age, sexe);
		
		pourcenText.setText("Intensité de: " + String.valueOf(pourcent) + " %");
		resultatText.setText(checkPourcentage(pourcent));
		
		changeColors(pourcent);
		View view = this.getWindow().getDecorView();
		view.setBackgroundColor(Color.rgb(red, green, blue));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public class Ecouteur implements OnClickListener{

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i;
			i = new Intent(ResultActivity.this, MainActivity.class);
			startActivity(i);			
		}
	
	}
	
	public String checkPourcentage(int pourcentage){
		String s = "";
		
		if(pourcentage >= 95){
			s = "Allez-y moins fort!!";			
		}
		else if(pourcentage >= 85 && pourcentage <= 95){
			s = "Bonne intensité!";
		}
		else if(pourcentage >= 75 && pourcentage <= 85){
			s = "Vous pourriez forcer juste un peu plus.";
		}
		else{
			s = "C'est pas assez!";
		}		
		
		return s;
	}
	
	public void changeColors(int pourcentage){
		if(pourcentage >= 95){
			red = 200;
		}
		else if(pourcentage >= 85 && pourcentage <= 95){
			green = 200;
		}
		else if(pourcentage >= 75 && pourcentage <= 85){
			blue = 100;
			green = 150;
		}
		else{
			green = 75;
			blue = 150;
		}
	}
}
