package databinding;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.databinding.DataBindingUtil;

import com.example.mvvmlearning.R;
import com.example.mvvmlearning.databinding.ActivityDataBindingBinding;

import databinding.models.StudentModel;

public class DataBindigActivity extends AppCompatActivity {

    private TextView tv_student_name;
    private TextView tv_student_email;
    private ActivityDataBindingBinding activityDataBindingBinding;
    private ClickHandlers clickHandlers;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_binding);


        activityDataBindingBinding = DataBindingUtil.setContentView(this, R.layout.activity_data_binding);
        activityDataBindingBinding.setStudent(getStudent());

        clickHandlers = new ClickHandlers(this);
        activityDataBindingBinding.setClickHandler(clickHandlers);

     /*   tv_student_name = findViewById(R.id.tv_student_name);
        tv_student_email = findViewById(R.id.tv_student_email);


        tv_student_name.setText(getStudent().getStudentName());
        tv_student_email.setText(getStudent().getStudentEmail());*/
    }


    private StudentModel getStudent() {
        StudentModel studentModel = new StudentModel("Laxman Singh", "lsingh@gmail.com");
        return studentModel;
    }


    public class ClickHandlers {
        Context context;


        ClickHandlers(Context context) {
            this.context = context;
        }


        public void onEnrollButtonClicked(View view) {
            Toast.makeText(context, "Enroll Clicked", Toast.LENGTH_LONG).show();
        }

        public void onCancelButtonClicked(View view) {
            Toast.makeText(context, "Cancel Clicked", Toast.LENGTH_LONG).show();
        }


    }

}
