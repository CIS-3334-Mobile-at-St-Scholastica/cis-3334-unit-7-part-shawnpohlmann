package css.cis3334.heartratetracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView textViewData;
    String name;
    Double num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        textViewData = (TextView) findViewById(R.id.textViewData);

        Bundle extras = getIntent().getExtras();
        HeartRate hr = (HeartRate) extras.getSerializable("HeartRate");
        textViewData.setText(hr.toString());

    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("HeartRate is:", name);
        setResult(RESULT_OK, intent);
        super.finish();
    }
}