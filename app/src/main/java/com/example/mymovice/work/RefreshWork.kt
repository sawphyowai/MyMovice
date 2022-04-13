package com.example.mymovice.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.mymovice.database.getInstance
import com.example.mymovice.moveReposity.MoveReporsity
import retrofit2.HttpException

class RefreshWork(var appContext: Context,param:WorkerParameters):CoroutineWorker(appContext,param) {

    companion object{
        const val WORK_NAME="RefreshWorker"
    }
    override suspend fun doWork(): Result {
        var database= getInstance(appContext)
        var repost=MoveReporsity(database)
        return try{
            repost.refreshMove()
            Result.success()
        }catch (e:HttpException){
            Result.failure()
        }
    }
}