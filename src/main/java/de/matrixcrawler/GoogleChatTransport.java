package de.matrixcrawler;

import com.atlassian.bamboo.notification.Notification;
import com.atlassian.bamboo.notification.NotificationTransport;
import com.atlassian.bamboo.resultsummary.ResultsSummary;
import org.jetbrains.annotations.NotNull;

/**
 * Always code as if the guy who ends up maintaining your code will be a violent psychopath that
 * knows where you live. <p> - John Woods <p> Created on 12.03.2018.
 *
 * @author brunswicker
 */
public class GoogleChatTransport implements NotificationTransport {

    private final ResultsSummary resultsSummary;

    public GoogleChatTransport(ResultsSummary resultsSummary) {
        this.resultsSummary = resultsSummary;
    }

    @Override
    public void sendNotification(@NotNull Notification notification) {
        String imContent = notification.getIMContent();
    }
}
