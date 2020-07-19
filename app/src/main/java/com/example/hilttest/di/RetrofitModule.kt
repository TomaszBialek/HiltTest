package com.example.hilttest.di

import com.example.hilttest.model.Blog
import com.example.hilttest.retrofit.BlogNetworkEntity
import com.example.hilttest.retrofit.BlogRetrofit
import com.example.hilttest.retrofit.NetworkMapper
import com.example.hilttest.util.EntityMapper
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
object RetrofitModule {

//    @Singleton
//    @Provides
//    fun provideNetworkMapper(): EntityMapper<BlogNetworkEntity, Blog> {
//        return NetworkMapper()
//    }

    @Singleton
    @Provides
    fun provideGsonBuilder(): Gson{
        return GsonBuilder()
            .excludeFieldsWithoutExposeAnnotation()
            .create()
    }

    @Singleton
    @Provides
    fun provideRetrofit(gson: Gson): Retrofit.Builder{
        return Retrofit.Builder()
            .baseUrl("https://open-api.xyz/placeholder/")
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Singleton
    @Provides
    fun provideBlogService(retrofit: Retrofit.Builder): BlogRetrofit {
        return retrofit
            .build()
            .create(BlogRetrofit::class.java)
    }
}