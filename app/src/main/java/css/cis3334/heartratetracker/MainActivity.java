package css.cis3334.heartratetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;
/**
 * Created by Tom Gibbons in Feb 2017.
 * For the CIS 3334 class at St. Scholastica
 */

public class MainActivity extends AppCompatActivity {

    ListView lvHeartRates;
    TextView tvSelect;
    HeartRateList heartRateList;
    ArrayAdapter<HeartRate> hrAdapter;
    private static final int CIS3334_REQUEST_CODE = 1001;

    //ArrayList<HeartRate> basicheartRateList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSelect = (TextView) findViewById(R.id.textViewSelect);
        lvHeartRates = (ListView) findViewById(R.id.listViewHeartRates);

        heartRateList = new HeartRateList();
        heartRateList.InitRandomElderly();

        //ArrayAdapter<HeartRate> planetAdapter = new ArrayAdapter<HeartRate>(this, android.R.layout.simple_list_item_1, basicheartRateList);
        hrAdapter = new HeartRateAdapter(this, R.layout.heart_rate_row, R.id.textViewPulse, heartRateList);
        hrAdapter.setDropDownViewResource(R.layout.heart_rate_row);
        lvHeartRates.setAdapter(hrAdapter);

        lvHeartRates.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                HeartRate hr = (HeartRate) parent.getItemAtPosition(position);
                tvSelect.setText("You selected: " + hr.toString());

                //String name = lvHeartRates.getSelectedItem().toString();
                Intent secActIntent = new Intent(MainActivity.this, MainActivity2.class);
                secActIntent.putExtra("HeartRate", hr);
                //startActivity(secActIntent)     // if no result is returned
                startActivityForResult(secActIntent, CIS3334_REQUEST_CODE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK && requestCode == CIS3334_REQUEST_CODE) {
            if (data.hasExtra("NewName")) {
                String result = data.getExtras().getString("NewName");
                if (result != null && result.length() > 0) {
                    tvSelect.setText("New name : " + result);
                    Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
                }
            }
        }

    }
}