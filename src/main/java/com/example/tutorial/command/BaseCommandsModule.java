package com.example.tutorial.command;

import com.example.tutorial.shared.Database.Account;
import dagger.Binds;
import dagger.BindsOptionalOf;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
public interface BaseCommandsModule {

    @Binds // when something depends on Command, Dagger will provide HelloWorldCommand
    @IntoMap
    @StringKey("hello")
    Command helloWorldCommand(HelloWorldCommand command);

    @Binds
    @IntoMap
    @StringKey("login")
    Command loginCommand(LoginCommand command);

    @BindsOptionalOf
    Account optionalAccount();

}
