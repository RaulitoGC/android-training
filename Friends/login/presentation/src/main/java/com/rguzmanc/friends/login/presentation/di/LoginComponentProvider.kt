package com.rguzmanc.friends.login.presentation.di

interface LoginComponentProvider {
    fun getLoginComponentBuilder(): LoginComponent.Builder
}