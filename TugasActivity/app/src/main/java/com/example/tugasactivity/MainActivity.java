package com.example.tugasactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText masukan;
    Button kirim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        masukan = (EditText) findViewById(R.id.masukan);
        kirim = (Button) findViewById(R.id.kirim);

        kirim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (masukan.getText().toString().equals("")){
                    masukan.setError("Masukkan kata atau kalimat");
                }
                else {

                    String input = masukan.getText().toString();

                    Intent intent = new Intent(MainActivity.this, Activity2.class);
                    intent.putExtra("masukan", input);
                    startActivity(intent);
                }
            }
        });
    }
}