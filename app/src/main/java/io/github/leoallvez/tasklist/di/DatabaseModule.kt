package io.github.leoallvez.tasklist.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.github.leoallvez.tasklist.db.TaskDao
import io.github.leoallvez.tasklist.db.Database
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Room.databaseBuilder(
            context,
            Database::class.java,
            "task_database"
        ).build()
    }

    @Singleton
    @Provides
    fun provideTaskDao(db: Database): TaskDao {
        return db.taskDao()
    }
}
