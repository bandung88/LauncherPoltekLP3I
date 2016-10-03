package id.ac.lp3i.politekniklp3i;

import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new CountDownTimer(2000,1000){

            @Override
            public void onTick(long millisUntilFinished) {
                Toast.makeText(getApplicationContext(), "Selamat Datang ...", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinish() {
                finish();
                Intent mainMenu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(mainMenu);
            }
        }.start();

    }
}
