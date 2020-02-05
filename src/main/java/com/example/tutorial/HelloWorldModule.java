package com.example.tutorial;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module // collection of binding methods
abstract class HelloWorldModule {

    @Binds // when something depends on Command, Dagger will provide HelloWorldCommand
    @IntoMap
    @StringKey("hello")
    abstract Command helloWorldCommand(HelloWorldCommand command);

}
