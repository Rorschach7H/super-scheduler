package net.roxia.scheduler.common.cron.descriptor.builder;

import net.roxia.scheduler.common.cron.descriptor.I18nMessages;
import net.roxia.scheduler.common.cron.descriptor.Options;

/**
 * @author grhodes
 * @since 10 Dec 2012 14:24:08
 */
public class DayOfMonthDescriptionBuilder extends AbstractDescriptionBuilder {

    private final Options options;

    public DayOfMonthDescriptionBuilder(Options options) {
        this.options = options;
    }

    @Override
    protected String getSingleItemDescription(String expression) {
        return expression;
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {

        return ", "+ I18nMessages.get("every_x")+ getSpace(options) + plural(expression, I18nMessages.get("day"), I18nMessages.get("days"));
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression, boolean omitSeparator) {
    	String format = I18nMessages.get("between_days_of_the_month");
        return omitSeparator ? format : ", "+format;
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("on_day_of_the_month");
    }

    @Override
    protected Boolean needSpaceBetweenWords() {
        return options.isNeedSpaceBetweenWords();
    }

}
