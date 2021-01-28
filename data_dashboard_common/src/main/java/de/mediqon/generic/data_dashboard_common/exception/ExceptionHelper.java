package de.mediqon.generic.data_dashboard_common.exception;

public class ExceptionHelper {

    public static String stackListToString(final StackTraceElement[] list) {
        String res = "";
        for (final StackTraceElement el : list) {
            res += el.toString() + "\n";
        }

        return res;
    }
}
