package net.ralphpina.familycentral.family_view.di

import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector
import net.ralphpina.familycentral.family.FamilyRepository
import net.ralphpina.familycentral.family.di.FamilyModule
import net.ralphpina.familycentral.family_view.FamilyFragment
import net.ralphpina.familycentral.family_view.FamilyViewModel
import javax.inject.Scope

@Scope
@kotlin.annotation.Retention(AnnotationRetention.RUNTIME)
annotation class FragmentScope

@Module(
    includes = [
        FamilyModule::class
    ]
)
abstract class FamilyViewModule {
    @FragmentScope
    @ContributesAndroidInjector(
        modules = [
            ProvidesDeps::class
        ]
    )
    internal abstract fun contributeFamilyFragmentInjector(): FamilyFragment

    @Module
    class ProvidesDeps {
        @Provides
        fun provideFamilyViewModelFactory(familyRepository: FamilyRepository): FamilyViewModel.Factory =
            FamilyViewModel.Factory(familyRepository)
    }
}
