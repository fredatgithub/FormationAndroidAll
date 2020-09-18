package fr.orsys.gestionclient;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;


public class AddClientActivity extends ActionBarActivity {

    private static final String TAG = AddClientActivity.class.getSimpleName();
    public static final int AGE_MIN = 18;
    public static final int AGE_MAX = 60;

    private Button addButton;
    private EditText nameEditText;
    private EditText firstnameEditText;
    private EditText emailEditText;
    private TextView ageTextView;
    private SeekBar ageSeekBar;
    private RadioGroup sexeRadioGroup;
    private Switch activeSwitch;
    private Spinner levelSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_client);
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        firstnameEditText = (EditText) findViewById(R.id.firstnameEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        ageTextView = (TextView) findViewById(R.id.ageTextView);
        ageSeekBar = (SeekBar) findViewById(R.id.ageSeekBar);

        ageSeekBar.setMax(AGE_MAX - AGE_MIN);

        ageSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                ageTextView.setText(String.valueOf(progress + AGE_MIN));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        sexeRadioGroup = (RadioGroup)findViewById(R.id.sexeRadioGroup);

        levelSpinner = (Spinner)findViewById(R.id.levelSpinner);
        ArrayAdapter<CharSequence> adapter =
                ArrayAdapter.createFromResource(this,R.array.levels_array ,android.R.layout.simple_spinner_item);
        levelSpinner.setAdapter(adapter);
        activeSwitch = (Switch)findViewById(R.id.activeSwitch);
        addButton = (Button) findViewById(R.id.addButton);
    }


    public void addClient(View view) {
        String nameValue = nameEditText.getText().toString();
        Log.d(TAG, "Nom : " + nameValue);
        String firstnameValue = firstnameEditText.getText().toString();
        Log.d(TAG, "Prénom : " + firstnameValue);
        int checkedRadioButtonId = sexeRadioGroup.getCheckedRadioButtonId();
        String sexe = (checkedRadioButtonId == R.id.manRadioButton) ? "Homme" : "Femme";
        Log.d(TAG, "Sexe : " + sexe);
        String emailValue = emailEditText.getText().toString();
        Log.d(TAG, "Email : " + emailValue);
        int ageValue = ageSeekBar.getProgress() + AGE_MIN;
        Log.d(TAG, "Age : " + ageValue);
        String level = levelSpinner.getSelectedItem().toString();
        Log.d(TAG, "Niveau : " + level);
        String activeValue = activeSwitch.isChecked() ? "Oui" : "Non";
        Log.d(TAG, "Actif : " + activeValue);

        Toast toast = Toast.makeText(this, getString(R.string.client_added, nameValue), Toast.LENGTH_SHORT);
        toast.show();
    }

}
