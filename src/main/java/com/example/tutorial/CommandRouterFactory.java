package com.example.tutorial;

import dagger.Component;

import javax.inject.Singleton;

// instances of classes annotated with @Singleton should be shared among other objects that depend on them in this component.
// e.g. Database is annotated with Singleton, so we'll have only one instance of Database per instance of CommandRouterFactory
@Singleton

// tells Dagger to generate implementation
@Component
        // tells Dagger where to look for binding methods
        (modules = { HelloWorldModule.class, SystemOutModule.class, LoginCommandModule.class, UserCommandsModule.class })
interface CommandRouterFactory {

    CommandRouter router();

}
