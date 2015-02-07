package vieux.foo.tap_cercle;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TapActivity extends Activity {
	TextView nbCoups;
	ImageButton imgBouton;
	int nb = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tap);
		
		nbCoups = (TextView)findViewById(R.id.textViewNb);
		imgBouton = (ImageButton)findViewById(R.id.imageButCoeur);
		
		Ecouteur ec = new Ecouteur();
		imgBouton.setOnClickListener(ec);
	}
	private class Ecouteur implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			nb++;
			nbCoups.setText(Integer.toString(nb));
			
		}
		
	}
}
