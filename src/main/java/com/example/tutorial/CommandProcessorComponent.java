package com.example.tutorial;

import com.example.tutorial.command.BaseCommandsModule;
import com.example.tutorial.command.CommandProcessor;
import com.example.tutorial.command.user.UserCommandsComponent;
import com.example.tutorial.shared.SystemOutModule;
import dagger.Component;

import javax.inject.Singleton;

// injectables available in this component are available in subcomponents
// injectables available in subcomponents are not available in this parent component

@Singleton
@Component(
        modules = {
                BaseCommandsModule.class,
                UserCommandsComponent.InstallationModule.class, // CommandProcessorComponent @Component is parent of UserCommandsComponent @Subcomponent
                SystemOutModule.class,
        })
public interface CommandProcessorComponent {
    CommandProcessor commandProcessor();

    static CommandProcessorComponent create() {
        return DaggerCommandProcessorComponent.create();
    }
}
