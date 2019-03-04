package net.ralphpina.familycentral.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import net.ralphpina.familycentral.FamilyApplication
import net.ralphpina.familycentral.MainActivity
import net.ralphpina.familycentral.family_view.di.FamilyViewModule
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class AppScope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class ActivityScope

@Module
abstract class AppModule {
    @ActivityScope
    @ContributesAndroidInjector
    internal abstract fun contributeMainActivityInjector(): MainActivity
}

@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        FamilyViewModule::class
    ]
)
@AppScope
interface AppComponent {

    fun inject(app: FamilyApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(app: FamilyApplication): Builder

        @BindsInstance
        fun context(context: Context): Builder

        fun build(): AppComponent
    }
}