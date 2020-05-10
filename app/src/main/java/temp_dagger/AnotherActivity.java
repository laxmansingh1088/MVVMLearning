package temp_dagger;

import android.os.Bundle;
import android.view.View;

import androidx.MyApplication;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mvvmlearning.R;

import javax.inject.Inject;

import temp_dagger.smartphone.Battery;
import temp_dagger.smartphone.SmartPhone;

public class AnotherActivity extends AppCompatActivity implements View.OnClickListener {
    @Inject
    SmartPhone smartPhone;

    @Inject
    Battery battery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.another_activity);
        setTitle("Temp Dagger1");
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