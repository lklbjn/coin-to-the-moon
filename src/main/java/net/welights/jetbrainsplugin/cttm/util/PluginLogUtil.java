package net.welights.jetbrainsplugin.cttm.util;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import com.intellij.openapi.project.Project;

/**
 * @author lklbjn
 */
public class PluginLogUtil {

    private PluginLogUtil(){
        throw new IllegalStateException("Utility class");
    }

    private static Project project;

    public static void init(Project project) {
        PluginLogUtil.project = project;
    }

    public static void info(String message) {
        Notifications.Bus.notify(new Notification("net.welights.jetbrainsplugin", "coin to the moon", message, NotificationType.INFORMATION), project);
    }
}
