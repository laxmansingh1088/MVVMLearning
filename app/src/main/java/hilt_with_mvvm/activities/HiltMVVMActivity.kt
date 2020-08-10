package hilt_with_mvvm.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mvvmlearning.R

/*
*    What we will use in this Project?
*
*    1)  MVVM Architecture
*
*    2)  Navigation Components
*
*    3)  Room Database with coroutines
*
*    4)  Dependency Injection with Dagger

*    Architecture this app will use:
*
*     ## We will use single Activity Architecture
*
*      Single Activity --   HiltMVVMActivity
*
*   contains 5 Fragments   : 1) SetupFragment
*                            2) RunFragment
*                            3) StatisticsFragment
*                            4) TrackingFragment
*                            5) SettingsFragment

*   contains Service:--   TrackingService
*
*
*   contains ViewModel:--   MainViewModel
*                           StatisticsViewModel
*
*   contains Repository:--   MainRepository
*
*   contains Room Database:--
*
* */

class HiltMVVMActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hilt)
    }
}