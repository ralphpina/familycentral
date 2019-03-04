package net.ralphpina.familycentral

import android.app.Activity
import android.app.Application
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import net.ralphpina.familycentral.di.DaggerAppComponent
import javax.inject.Inject

class FamilyApplication : Application(), HasActivityInjector {
    @Inject lateinit var dispatchingActivityInjector: DispatchingAndroidInjector<Activity>

    override fun onCreate() {
        super.onCreate()
        val appComponent = DaggerAppComponent.builder()
            .application(this)
            .context(this)
            .build()
        appComponent.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = dispatchingActivityInjector
}
