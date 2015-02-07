package vieux.foo.tap_cercle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TapActivity extends Activity {
	TextView nbCoups,textMessage;
	ImageButton imgBouton;
	Button retour;
	int nb = 0;
	boolean lecture = false;
	long tempsDebut = 0;
	int tempsNorm = 10;
	long tempsReel = 0;
	int muliplicateur = 60/tempsNorm;
	String utilisation; // à partir de òu accès
	Operations op;
	
	
	private Handler hand = new Handler();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tap);
		
		nbCoups = (TextView)findViewById(R.id.textViewNb);
		textMessage = (TextView)findViewById(R.id.textViewMoy);
		imgBouton = (ImageButton)findViewById(R.id.imageButCoeur);
		retour = (Button)findViewById(R.id.buttonRetourAT);
		
		op = new Operations(this);
		
		utilisation = getIntent().getStringExtra("source");
		// signer la bouton
		retour.setText(utilisation);
		if(utilisation.equals("Terminer"))
			retour.setEnabled(false);
		
		Ecouteur ec = new Ecouteur();
		imgBouton.setOnClickListener(ec);
		retour.setOnClickListener(ec);
	}
	private class Ecouteur implements OnClickListener
	{

		@Override
		public void onClick(View v) {
			if(v.getId() == R.id.imageButCoeur)
			{
				nb++;
				nbCoups.setText(Integer.toString(nb));
				
				if(!lecture)
				{
					tempsDebut = SystemClock.elapsedRealtime();
					lecture = true;
				}
				hand.postDelayed(runnable, 1000);
			}
			else // bouton retour
			{
				if(utilisation.equals("Menu principal"))
					ecrireFr1();
				else if(utilisation.equals("Suivant"))
				{
					ecrireFr1();
					if((!lecture) && nb != 0 )
					{
						Intent i = new Intent(TapActivity.this, TimerActivity.class);
						startActivity(i);
					}
				}
				else if(utilisation.equals("Terminer"))
				{
					// ajouter données dans la BD, "colonne mesure 2"
					if((!lecture) && nb != 0 )
					{
						op.ouvrirBD();
						op.saveFr2(nb * muliplicateur);
						op.fermerBD();
					}
				}
			}
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
				lecture = false;
				nbCoups.setText((nb * muliplicateur) + " coups par minute");
				Log.i("test","lol11");
				if(utilisation.equals("Terminer"))
				{
					long varTemps = SystemClock.elapsedRealtime();
					long tempsaRest = 60000;
					Log.i("test","lol21");
					
					while(60000000 - (SystemClock.elapsedRealtime() - varTemps)> 0)
					{
						textMessage.setText("Attendez svp ");
					}
					Intent i = new Intent(TapActivity.this, ResultActivity.class);
					startActivity(i);
					finish();
					utilisation = "";
					//while(tempsaRest - (SystemClock.elapsedRealtime() - varTemps) > 0)
					/*while(true)
					{
						textMessage.setText("Attendez svp " + (tempsaRest - (SystemClock.elapsedRealtime() - varTemps)));
						
					}*/
					
					// save fr2
					
					// timer -1min
					
					// passer a tap :) oui encore
					
					// 2-me execution
					
					// save fr3
					
					// passer au resume
					
				}
    		}
			
			hand.postDelayed(this, 1000);
		}		
	};
	public void ecrireFr1()
	{
		// ajouter données dans la BD, "colonne mesure 1"
		if((!lecture) && nb != 0 )
		{
			op.ouvrirBD();
			op.saveFr1(nb * muliplicateur);
			op.fermerBD();
		}
		else // message d'erreur
		{
			Context context = getApplicationContext();
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, "Mesure n'est pas effectuée", duration);
			toast.show();
			finish();
		}
	}
}
