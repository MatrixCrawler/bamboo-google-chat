package de.matrixcrawler;

import com.atlassian.bamboo.notification.NotificationRecipient;
import com.atlassian.bamboo.notification.NotificationTransport;
import com.atlassian.bamboo.notification.recipients.AbstractNotificationRecipient;
import com.atlassian.bamboo.plan.cache.ImmutablePlan;
import com.atlassian.bamboo.plugin.descriptor.NotificationRecipientModuleDescriptor;
import com.atlassian.bamboo.resultsummary.ResultsSummary;
import com.atlassian.bamboo.template.TemplateRenderer;
import com.atlassian.plugin.spring.scanner.annotation.component.Scanned;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import com.google.common.collect.Maps;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Always code as if the guy who ends up maintaining your code will be a violent psychopath that
 * knows where you live. <p> - John Woods <p> Created on 12.03.2018.
 *
 * @author brunswicker
 */
@Scanned
public class GoogleChatRecipient extends AbstractNotificationRecipient implements NotificationRecipient.RequiresPlan, NotificationRecipient.RequiresResultSummary {

    private static String WEBHOOK = "webhook";
    private String webhook = "";

    @ComponentImport
    TemplateRenderer templateRenderer;

    private ResultsSummary resultsSummary;
    private ImmutablePlan immutablePlan;

    @NotNull
    @Override
    public List<NotificationTransport> getTransports() {
        ArrayList<NotificationTransport> notificationTransports = new ArrayList<>();
        notificationTransports.add(new GoogleChatTransport(resultsSummary));
        return notificationTransports;
    }

    @Override
    public void init(@Nullable String configurationData) {

        super.init(configurationData);
    }

    @Override
    public void populate(@NotNull Map<String, String[]> params) {
        if (params.containsKey(WEBHOOK)) {
            webhook = params.get(WEBHOOK)[0];
        }
    }

    @Override
    public void setPlan(@Nullable ImmutablePlan immutablePlan) {
        this.immutablePlan = immutablePlan;
    }

    @Override
    public void setResultsSummary(@Nullable ResultsSummary resultsSummary) {
        this.resultsSummary = resultsSummary;
    }

    @NotNull
    @Override
    public String getEditHtml() {
        String editTemplate = ((NotificationRecipientModuleDescriptor) Objects.requireNonNull(getModuleDescriptor())).getEditTemplate();
        return templateRenderer.render(editTemplate, populateContext());
    }

    private Map<String, Object> populateContext() {
        Map<String, Object> context = Maps.newHashMap();
        if (webhook != null) {
            context.put(WEBHOOK, webhook);
        }
        return context;
    }
}
