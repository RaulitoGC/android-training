package com.rguzmanc.friends.core

import dagger.MapKey
import javax.inject.Qualifier
import kotlin.reflect.KClass

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ApiEndPoint

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class BuildConfigDebug

@Qualifier
@Retention(value = AnnotationRetention.RUNTIME)
annotation class ApplicationContext