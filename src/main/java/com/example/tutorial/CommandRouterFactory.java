package com.example.tutorial;

import dagger.Component;

// tells Dagger to generate implementation
@Component
        // tells Dagger where to look for binding methods
        (modules = { HelloWorldModule.class, SystemOutModule.class, LoginCommandModule.class })
interface CommandRouterFactory {

    CommandRouter router();

}
