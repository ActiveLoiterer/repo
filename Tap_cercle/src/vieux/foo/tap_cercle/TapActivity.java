package vieux.foo.tap_cercle;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

public class TapActivity extends Activity {
	TextView nbCoups;
	ImageButton imgBouton;
	int nb = 0;
	boolean lecture = false;
	long tempsDebut = 0;
	long tempsNorm = 10;
	long tempsReel = 0;
	long muliplicateur = 60/tempsNorm;
	
	private Handler hand = new Handler();
	

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
			
			if(!lecture)
			{
				tempsDebut = SystemClock.elapsedRealtime();
				lecture = true;
			}
			hand.postDelayed(runnable, 1000);
			
		}
		
	}
	private Runnable runnable = new Runnable(){

		@Override
		public void run() {
			// TODO Auto-generated method stub
			tempsReel = SystemClock.elapsedRealtime();
			if(tempsNorm < (tempsReel - tempsDebut)/1000)
    		{
				imgBouton.setEnabled(false);
				nbCoups.setText((nb * muliplicateur) + " coups par minute");
    		}
			
			hand.postDelayed(this, 1000);
		}
		
	};
}
