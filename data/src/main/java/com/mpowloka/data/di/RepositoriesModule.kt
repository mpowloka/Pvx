package com.mpowloka.data.di

import com.mpowloka.data.localizations.LocalizationsRepositoryImpl
import com.mpowloka.domain.localizations.LocalizationsRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
abstract class RepositoriesModule {

    @Binds
    @Singleton
    abstract fun localizationsRepository(localizationsRepositoryImpl: LocalizationsRepositoryImpl): LocalizationsRepository

}