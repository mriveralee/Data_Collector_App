package yunzhil.buttontest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.TelecomManager;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Button btn = findViewById(R.id.defaultBtn);
        TelecomManager manger= (TelecomManager) getSystemService(TELECOM_SERVICE);
        final String name=manger.getDefaultDialerPackage();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkDefaultDialer();
            }
        });
    }

    private void checkDefaultDialer(){
        Intent intent = new Intent(TelecomManager.ACTION_CHANGE_DEFAULT_DIALER);
        intent.putExtra(TelecomManager.EXTRA_CHANGE_DEFAULT_DIALER_PACKAGE_NAME,
                this.getPackageName());
        startActivity(intent);
        Toast.makeText(this,"done",Toast.LENGTH_SHORT).show();

    }
}


