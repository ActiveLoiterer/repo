package vieux.foo.tap_cercle;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class TapActivity extends Activity {
	TextView nbCoups;
	ImageButton imgBouton;
	int nb = 0;
	boolean lecture = false;
	long tempsDebut = 0;
	long tempsNorm = 10;
	long tempsReel = 0;
	long muliplicateur = 60/tempsNorm;
	String utilisation; // à partir de òu accès
	Operations op;
	
	private Handler hand = new Handler();
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tap);
		
		nbCoups = (TextView)findViewById(R.id.textViewNb);
		imgBouton = (ImageButton)findViewById(R.id.imageButCoeur);
		retour = (Button)findViewById(R.id.buttonRetourAT);
		
		op = new Operations(this);
		
		utilisation = getIntent().getStringExtra("source");
		// signer la bouton
		retour.setText(utilisation);
		
		Ecouteur ec = new Ecouteur();
		imgBouton.setOnClickListener(ec);
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
					}
					// retour
					finish();
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
    		}
			
			hand.postDelayed(this, 1000);
		}
		
	};
}
