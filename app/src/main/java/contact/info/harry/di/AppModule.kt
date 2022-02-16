package contact.info.harry.di

import android.app.Application
import androidx.room.Room
import contact.info.harry.data.datasource.StudentDatabase
import contact.info.harry.data.repository.StudentRepositoryImpl
import contact.info.harry.domain.repository.StudentRepository
import contact.info.harry.domain.use_case.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(app: Application): StudentDatabase {
        return Room.databaseBuilder(
            app,
            StudentDatabase::class.java,
            StudentDatabase.DATABASE_NAME
        ).build()
    }

    @Provides
    @Singleton
    fun provideRepository(db: StudentDatabase): StudentRepository {
        return StudentRepositoryImpl(db.dao)
    }

    @Provides
    @Singleton
    fun provideUseCases(repository: StudentRepository): UseCases {
        return UseCases(
            createUser = CreateUser(repository),
            displayInfo = DisplayInfo(repository),
            getClassmates = GetClassmates(repository),
            logIn = LogIn(repository)
        )
    }
}