package discord.bot.gq.database;

import discord.bot.gq.lib.Helper;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.events.user.update.UserUpdateOnlineStatusEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import javax.annotation.Nonnull;

public class ActiveUsers extends ListenerAdapter {

    public void onUserUpdateOnlineStatus(@Nonnull UserUpdateOnlineStatusEvent event) {

        OnlineStatus newStatus = event.getNewOnlineStatus();
        String userTag = event.getMember().getUser().getAsTag();
        long userId = event.getUser().getIdLong();

        Helper.insertStatus(userId, userTag, newStatus);

        int approximatePresentMember = event.getGuild().retrieveMetaData().complete().getApproximatePresences();

        Helper.insertNumberOnlineMember(approximatePresentMember);


    }
}
