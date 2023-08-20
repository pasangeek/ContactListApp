package com.example.contactlistapp.di

import android.content.Context
import com.example.contactlistapp.ImplRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactProfileModule {
    @Singleton
    @Provides
    fun provideDataStoreRepository(@ApplicationContext context: Context)= ImplRepository ( context)

}