package com.android.accelerometer;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {
    //helps select sensor
    Sensor sensor;
    //Manage sensors
    SensorManager sm;

    TextView displayReading;
    TextView arrayStuff;
    Button stuurButton;
    Button nuButton;

    //variabelen
    float a;
    float b;
    float c;
    float dataKopie[] = new float[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //setting up sensor service
        sm = (SensorManager) getSystemService(SENSOR_SERVICE);
        //select accelerometer
        sensor = sm.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);

        //tekstBox.setText("Begin");

        nuButton = (Button)findViewById(R.id.nuButton);
        arrayStuff = (TextView) findViewById(R.id.arrayStuff);
        nuButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // aan viewtekst
                arrayStuff.setText(String.valueOf(dataKopie[1]));
            }
        });

        stuurButton = (Button)findViewById(R.id.stuurButton);
        stuurButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                // Launching create new product activity
                Intent i = new Intent(getApplicationContext(), Gyroscope.class);
                startActivity(i);
                finish();
            }
        });

        sm.registerListener(this, sensor, SensorManager.SENSOR_DELAY_NORMAL );

        displayReading = (TextView) findViewById(R.id.display_reading);

    }

    public void onAccuracyChanged(Sensor sensor, int accuracy){

    }

  @Override

    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        //variabelen van waardes maken
        a = event.values[0];
        b = event.values[1];
        c = event.values[2];

        //array vullen met waardes
        dataKopie[0] = a;
        dataKopie[1] = b;
        dataKopie[2] = c;

        displayReading.setText("X waarde " + a + "\nY waarde " + b + "\nZ waarde " + c);
        //displayReading.setText("X waarde "+event.values[0]+ "\nY waarde "+event.values[1]+ "\nZ waarde "+event.values[2]);

        // crash
        // tekstBox.setText(dataKopie.toString());
    }

    /**public void onClick(View v)
    {
        stuurButton.setOnClickListener(this);
    }*/



}
