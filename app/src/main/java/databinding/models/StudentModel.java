package databinding.models;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.mvvmlearning.BR;

public class StudentModel extends BaseObservable {

    public String studentName;
    public String studentEmail;

    public StudentModel(String studentName, String studentEmail) {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
    }


    @Bindable
    public String getStudentName() {
        return studentName;
    }


    public void setStudentName(String studentName) {
        this.studentName = studentName;
        notifyPropertyChanged(BR.studentName);
    }

    @Bindable
    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
        notifyPropertyChanged(BR.studentEmail);
    }
}
