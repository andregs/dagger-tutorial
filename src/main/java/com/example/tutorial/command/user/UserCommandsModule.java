package com.example.tutorial.command.user;

import com.example.tutorial.command.Command;
import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;
import dagger.multibindings.StringKey;

@Module
interface UserCommandsModule {

    @Binds
    @IntoMap
    @StringKey("deposit")
    Command depositCommand(DepositCommand depositCommand);

    @Binds
    @IntoMap
    @StringKey("withdraw")
    Command withdrawCommand(WithdrawCommand withdrawCommand);

    @Binds
    @IntoMap
    @StringKey("logout")
    Command logoutCommand(LogoutCommand command);

}
