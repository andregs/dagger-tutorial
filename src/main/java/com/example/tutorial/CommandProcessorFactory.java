package com.example.tutorial;

import dagger.Component;

import javax.inject.Singleton;

// injectables available in this component are available in subcomponents
// injectables available in subcomponents are not available in this parent component

@Singleton
@Component(
        modules = {
                HelloWorldModule.class,
                AuthCommandsModule.class,
                UserCommandsRouter.InstallationModule.class, // CommandProcessorFactory @Component is parent of UserCommandsRouter @Subcomponent
                SystemOutModule.class,
        })
interface CommandProcessorFactory {
    CommandProcessor commandProcessor();

    static CommandProcessorFactory create() {
        return DaggerCommandProcessorFactory.create();
    }
}
