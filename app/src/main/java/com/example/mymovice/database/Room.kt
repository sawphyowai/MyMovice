package com.example.mymovice.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface MoveDao{
    @Query("SELECT * FROM Move")
    fun getAll(): LiveData<List<Move>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    //fun insertAll(vararg move: Move)
    fun insertAll(move:List<Move>)
}

@Database(entities = [ Move::class],version = 3,exportSchema = false)
abstract class MoveDatabase:RoomDatabase() {
    abstract fun moveDao(): MoveDao
}
private lateinit var INSTANCE: MoveDatabase
    fun getInstance(context: Context): MoveDatabase {
        synchronized(MoveDatabase::class.java) {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context.applicationContext,
                    MoveDatabase::class.java,
                    "Videos"
                ).fallbackToDestructiveMigration()
                    .build()
            }
        }
        return INSTANCE
    }
