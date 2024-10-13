package com.apptick.currenttime

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity

import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil

import com.apptick.currenttime.data.Status
import com.apptick.currenttime.databinding.ActivityMainBinding
import com.apptick.currenttime.ui.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    lateinit var  binding: ActivityMainBinding
    val vm:MainViewModel by viewModels()

    //getting current time from server
    @SuppressLint("SetTextI18n")
    fun getCurrentTime(){
        //request for fresh data
        vm.getCurrentTime()

        //observe changes
        vm.timeResponse.observe(this){data->
            when(data.status){
                Status.SUCCESS->{
                    binding.tvMessage.text = data.data?.result?.weekname+"\n" +"ساعت:" +data.data?.result?.time +  "\n"+"تاریخ:"+data.data?.result?.date
                    binding.pbLoading.visibility = View.INVISIBLE
                }
                Status.ERROR->{
                    binding.tvMessage.text = "مشکلی به وجود آمده است"
                    binding.pbLoading.visibility = View.INVISIBLE
                }
                Status.LOADING->{
                    binding.pbLoading.visibility = View.VISIBLE
                }

                else -> {

                }
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(
            this, R.layout.activity_main)


        getCurrentTime()
    }
}
