package com.example.hilttest

import androidx.test.core.app.launchActivity
import androidx.test.espresso.matcher.ViewMatchers.assertThat
import com.example.hilttest.di.AppModule
import com.example.hilttest.ui.MainActivity
import com.example.hilttest.ui.MainFragment
import com.example.hilttest.ui.MainFragmentFactory
import com.example.hilttest.util.launchFragmentInHiltContainer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.containsString
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject
import javax.inject.Singleton


@ExperimentalCoroutinesApi
@UninstallModules(AppModule::class)
@HiltAndroidTest
class MainTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someString: String

    @Inject
    lateinit var fragmentFactory: MainFragmentFactory

    @Before
    fun init() {
        hiltRule.inject()
    }

    @Test
    fun hiltTest() {
        assertThat(someString, containsString("TEST string"))
    }

    @Test
    fun mainFragmentTest() {
        val scenario = launchFragmentInHiltContainer<MainFragment>(
            factory = fragmentFactory
        )
    }

    @Test
    fun mainActivityTest() {
        val scenario = launchActivity<MainActivity>()
    }

    @Module
    @InstallIn(ApplicationComponent::class)
    object TestAppModule {


        @Singleton
        @Provides
        fun provideString(): String {
            return "This is a TEST string I'm providing for injection"
        }
    }
}





