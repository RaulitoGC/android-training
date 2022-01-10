package com.rguzmanc.friends.di

import com.rguzmanc.friends.FriendsApplication
import dagger.Component

@ApplicationScope
@Component(modules = [(ApplicationModule::class)])
interface ApplicationComponent {

}