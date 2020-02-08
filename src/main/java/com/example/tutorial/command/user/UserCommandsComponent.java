package com.example.tutorial.command.user;

import com.example.tutorial.command.CommandRouter;
import com.example.tutorial.shared.Database.Account;
import dagger.BindsInstance;
import dagger.Module;
import dagger.Subcomponent;

@PerSession
@Subcomponent(modules = {
        UserCommandsModule.class,
        AmountsModule.class,
})
public interface UserCommandsComponent {
    CommandRouter router();

    @Subcomponent.Factory
    interface Factory {
        UserCommandsComponent create(@BindsInstance Account account);
    }

    @Module(subcomponents = UserCommandsComponent.class)
    interface InstallationModule {
    }

}
