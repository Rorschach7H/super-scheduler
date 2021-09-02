package net.roxia.scheduler.common.cron.descriptor.builder;

import net.roxia.scheduler.common.cron.descriptor.DateAndTimeUtils;
import net.roxia.scheduler.common.cron.descriptor.I18nMessages;
import net.roxia.scheduler.common.cron.descriptor.Options;

import java.text.MessageFormat;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:18:21
 */
public class HoursDescriptionBuilder extends AbstractDescriptionBuilder {

    private final Options options;

    public HoursDescriptionBuilder(Options options) {
        this.options = options;
    }

    @Override
    protected String getSingleItemDescription(String expression) {
        return DateAndTimeUtils.formatTime(expression, "0", options);
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(I18nMessages.get("every_x")+ getSpace(options) +
                plural(expression, I18nMessages.get("hour"), I18nMessages.get("hours")), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression, boolean omitSeparator) {
        return I18nMessages.get("between_x_and_y");
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return I18nMessages.get("at_x");
    }

    @Override
    protected Boolean needSpaceBetweenWords() {
        return options.isNeedSpaceBetweenWords();
    }

}
