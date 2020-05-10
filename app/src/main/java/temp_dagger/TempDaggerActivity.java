package temp_dagger;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.MyApplication;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmlearning.R;

import javax.inject.Inject;

import temp_dagger.smartphone.Battery;
import temp_dagger.smartphone.SmartPhone;

public class TempDaggerActivity extends AppCompatActivity implements View.OnClickListener {
    @Inject
    SmartPhone smartPhone;

    @Inject
    Battery battery;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp_dagger);
        setTitle("Temp Dagger");

        Button open_activity = findViewById(R.id.open_activity);
        open_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TempDaggerActivity.this, AnotherActivity.class);
                startActivity(intent);
            }
        });

        // If we don't have state in any module we can initialize dagger as below
        //  SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.create();


        // If we have state in any module we can initialize dagger with builder as below and pass the value in module as below
       /* SmartPhoneComponent smartPhoneComponent = DaggerSmartPhoneComponent.builder()
                .batteryModule(new BatteryModule(100))
                .build();*/

        MyApplication.getInstance().getSmartPhoneComponent().inject(this);
        smartPhone.makeCall();
        battery.showBatteryName();

    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    @Override
    public void onClick(View view) {

    }
}