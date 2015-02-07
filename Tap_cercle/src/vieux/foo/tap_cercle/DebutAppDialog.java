package vieux.foo.tap_cercle;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View.OnClickListener;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class DebutAppDialog extends Dialog {

	Button buttonOk;
	Operations op;
	EditText age;
	RadioButton homme;
	
	public DebutAppDialog(Context context) {
		super(context);
		op = new Operations(context);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_debut_app_dialog);
		
		buttonOk = (Button)findViewById(R.id.buttonOkDialogADAD);
		age = (EditText)findViewById(R.id.editTextAgeDialog);
		homme = (RadioButton)findViewById(R.id.radioButtonHommeDialog);
		
		
		//Ecouteur
		Ecouteur ec = new Ecouteur();
		buttonOk.setOnClickListener(ec);
	}
	
	private class Ecouteur implements android.view.View.OnClickListener
	{
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			op.ouvrirBD();
			
			if(homme.isChecked())
				op.ajouterUser(Integer.valueOf(age.getText().toString()), 'M');
			else
				op.ajouterUser(Integer.valueOf(age.getText().toString()), 'F');
			
			op.fermerBD();
			//SAVE DANS LA BD;
			dismiss();
		}
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
