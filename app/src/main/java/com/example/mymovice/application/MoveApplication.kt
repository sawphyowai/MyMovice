package com.example.mymovice.application

import android.app.Application
import android.os.Build
import android.util.Log
import androidx.work.*
import com.example.mymovice.work.RefreshWork
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.util.concurrent.TimeUnit

class MoveApplication: Application() {
    val applicationScope= CoroutineScope(Dispatchers.Default)
    private fun delayInit(){
        applicationScope.launch {
            setupRecurringWork()
            Log.d("WorkManager",setupRecurringWork().toString())
        }
    }

    private fun setupRecurringWork() {
        var constrain= Constraints.Builder()
            .setRequiresCharging(true)
            .setRequiresBatteryNotLow(true)
            .setRequiredNetworkType(NetworkType.UNMETERED)
            .apply {
                if(Build.VERSION.SDK_INT >=Build.VERSION_CODES.M){
                    setRequiresDeviceIdle(true)
                }
            }.build()

        var workRequest:WorkRequest= PeriodicWorkRequestBuilder<RefreshWork>(1,TimeUnit.DAYS)
            .setConstraints(constrain)
            .build()

        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            RefreshWork.WORK_NAME,
            ExistingPeriodicWorkPolicy.KEEP,
            workRequest as PeriodicWorkRequest)
    }

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
        delayInit()
    }
}
