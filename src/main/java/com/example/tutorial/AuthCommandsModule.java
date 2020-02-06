package com.example.tutorial;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
abstract class AuthCommandsModule {

    @Binds
    @IntoMap
    @StringKey("login")
    abstract Command loginCommand(LoginCommand command);

    @Binds
    @IntoMap
    @StringKey("logout")
    abstract Command logoutCommand(LogoutCommand command);

}
