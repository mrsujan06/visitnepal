package com.zero.visitnepal.dagger

import com.zero.visitnepal.ui.city.CityFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(cityFragment: CityFragment)
}