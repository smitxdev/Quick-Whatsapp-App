package com.appydesk.quicksend;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
 ImageView btn;
EditText editText;
EditText code;
EditText mssg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        code = findViewById(R.id.code);
        editText= findViewById(R.id.phone);
        btn = findViewById(R.id.btn);
mssg = findViewById(R.id.mssg);

//final String x = editText.getText().toString();
//String num = editText.setText("heloo");
//final String text = mssg.getText().toString();



        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean installed = isAppInstalled("com.whatsapp");

                if (installed)
                {
                    Toast.makeText(MainActivity.this, "Whatsapp is not installed!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://api.whatsapp.com/send?phone="+code.getText().toString()+editText.getText().toString()+"&text="+mssg.getText().toString()));//+ text));
                    startActivity(intent);

                }
            }
        });

    }

    private boolean isAppInstalled(String s) {
        PackageManager packageManager = getPackageManager();
        boolean is_installed;

        try {
            packageManager.getPackageInfo(s, PackageManager.GET_ACTIVITIES);
            is_installed = true;
        } catch (PackageManager.NameNotFoundException e) {
            is_installed = false;
            e.printStackTrace();
        }
        return is_installed;
    }

}
