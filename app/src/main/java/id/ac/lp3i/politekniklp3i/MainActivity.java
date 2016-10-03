package id.ac.lp3i.politekniklp3i;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.view.ContextMenu;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Toolbar _toolbar;
    private Menu mainmenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        _toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(_toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setIcon(R.drawable.ic_logo_lpei);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflaterMenu = getMenuInflater();
        inflaterMenu.inflate(R.menu.main_menu, menu);
        mainmenu = menu;
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.act_close:
                finish();
                return true;
            case R.id.act_mhs:
                menuSmartMahasiswa(null);
                return true;
            case R.id.act_dosen:
                menuSmartLecturer(null);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void menuSmartMahasiswa(View view){
        //Toast.makeText(getApplicationContext(), "Smart Student", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), WebBrowser.class);
        intent.putExtra("url", "https://mhs.politekniklp3i-jkt.ac.id");
        intent.putExtra("title", "Smart Student");
//        intent.putExtra("url", "file:///android_asset/error_page.html");
        startActivity(intent);
    }

    public void menuSmartLecturer(View view){
        //Toast.makeText(getApplicationContext(), "Smart Lecturer", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(), WebBrowser.class);
        intent.putExtra("url", "http://dosen.politekniklp3i-jkt.ac.id");
        intent.putExtra("title", "Smart Lecturer");
        startActivity(intent);
    }
}
