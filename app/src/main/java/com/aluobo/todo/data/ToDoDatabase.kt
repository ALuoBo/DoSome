package com.aluobo.todo.data

import android.content.Context
import androidx.room.*
import com.aluobo.todo.data.models.ToDoData

@Database(entities = [ToDoData::class], version = 1, exportSchema = false)
@TypeConverters(Converter::class)
abstract class ToDoDatabase : RoomDatabase() {

    abstract fun toDoDao(): ToDoDao

    companion object {
        @Volatile
        private var INSTANCE: ToDoDatabase? = null

        fun getInstance(context: Context): ToDoDatabase {
            // 使用 val 确保此时的 instance 为不可变的
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }

            // 使用同步代码块保证只有一个实例
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ToDoDatabase::class.java,
                    "todo_db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }

    }

}