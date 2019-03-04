package net.ralphpina.familycentral.family.di

import dagger.Binds
import dagger.Module
import dagger.Reusable
import net.ralphpina.familycentral.family.FamilyRepository
import net.ralphpina.familycentral.family.FamilyRepositoryImpl

@Module
abstract class FamilyModule {
    @Reusable
    @Binds
    abstract fun provideFamilyRepository(impl: FamilyRepositoryImpl): FamilyRepository
}
