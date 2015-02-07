package vieux.foo.tap_cercle;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;


public class MainActivity extends Activity {
	
	Button buttonEntrainement,buttonTap,buttonStats,buttonOptions ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      
        buttonEntrainement = (Button)findViewById(R.id.buttonStartAM);
        buttonTap = (Button)findViewById(R.id.buttonTapAM);
        buttonStats = (Button)findViewById(R.id.buttonStatsAM);
        
        Operations op = new Operations(this);
        op.ouvrirBD();
        Log.i("test", String.valueOf(op.aUser()));
        if(!op.aUser())
        {
			//Dialog pour la fin de l'entrainement
			final DebutAppDialog dialog = new DebutAppDialog(MainActivity.this);
			dialog.setContentView(R.layout.activity_debut_app_dialog);
			dialog.setTitle("Informations...");

			dialog.show();
        }
        	
        op.fermerBD();	
        
        //Ecouteur
        Ecouteur ec = new Ecouteur();
        buttonEntrainement.setOnClickListener(ec);
        buttonTap.setOnClickListener(ec);
        buttonStats.setOnClickListener(ec);
    }
    
    private class Ecouteur implements OnClickListener
    {

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			Intent i = null;
			if(v.getId() == R.id.buttonStartAM)
			{
				//buttonEntrainement
				i = new Intent(MainActivity.this, TapActivity.class);
                i.putExtra("source", "Suivant"); 
				//i.putExtra("type", 1);
				
			}else if(v.getId() == R.id.buttonTapAM)
			{
				//buttonTap
				i = new Intent(MainActivity.this, TapActivity.class);
				i.putExtra("source", "Menu principal");	
				//i.putExtra("type", 0);
				
			}else if(v.getId() == R.id.buttonStatsAM)
			{
				//buttonStats
				i = new Intent(MainActivity.this, StatsActivity.class);	
			}
			startActivity(i);
    	}
    	
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
}
