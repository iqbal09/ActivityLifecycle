package com.activity.lifecycle;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("Actictivity", " Metode Create Dijalankan ");


        button = (Button)findViewById(R.id.buttonShowCustomDialog);

        // add button listener
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {

//-------------------------------------------------
                // custom dialog
                final Dialog dialog = new Dialog(MainActivity.this);
                //layout untuk dialog
                dialog.setContentView(R.layout.custom);
                dialog.setTitle("Belajar Dialog Android Bekup 2");

                // set the custom dialog components - text, image and button
                TextView text = (TextView) dialog.findViewById(R.id.text);
                text.setText("Android custom dialog example!");
                ImageView image = (ImageView) dialog.findViewById(R.id.image);
                image.setImageResource(R.drawable.googlelogo);

                Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
                // if button is clicked, close the custom dialog
                dialogButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                dialog.show();
//-------------------------------------------------


            }
        });






    }


    public void onStart()
    {
        super.onStart();
        Log.d("Actictivity", " Metode Start Dijalankan ");
    }


    public void onResume()
    {
        super.onResume();
        Log.d("Actictivity", " Metode Resume Dijalankan ");
    }

    public void onPause()
    {
        super.onPause();
        Log.d("Actictivity", " Metode Pause Dijalankan ");
    }

    public void onStop()
    {
        super.onStop();
        Log.d("Actictivity", " Metode Stop Dijalankan ");
    }

    public void onDestroy()
    {
        super.onDestroy();
        Log.d("Actictivity", " Metode Destroy Dijalankan ");
    }

    public void onRestart()
    {
        super.onRestart();
        Log.d("Actictivity", " Metode Restart Dijalankan ");
    }


}
