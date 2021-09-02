package net.roxia.scheduler.common.cron.descriptor.builder;

import net.roxia.scheduler.common.cron.descriptor.I18nMessages;
import net.roxia.scheduler.common.cron.descriptor.Options;
import org.joda.time.DateTime;

import java.text.MessageFormat;

/**
 * @author grhodes
 * @since 15 Sep 2014
 */
public class YearDescriptionBuilder extends AbstractDescriptionBuilder {
    private final Options options;

    public YearDescriptionBuilder(Options options) {
        this.options = options;
    }

    @Override
    protected String getSingleItemDescription(String expression) {
        return new DateTime().withYear(Integer.parseInt(expression)).toString("yyyy", I18nMessages.getCurrentLocale());
    }

    @Override
    protected String getIntervalDescriptionFormat(String expression) {
        return MessageFormat.format(", " + I18nMessages.get("every_x")+ getSpace(options) +
          plural(expression, I18nMessages.get("year"), I18nMessages.get("years")), expression);
    }

    @Override
    protected String getBetweenDescriptionFormat(String expression, boolean omitSeparator) {
    	String format = I18nMessages.get("between_description_format");
    	return omitSeparator ? format : ", "+format;
    }

    @Override
    protected String getDescriptionFormat(String expression) {
        return ", "+I18nMessages.get("only_in_year");
    }

    @Override
    protected Boolean needSpaceBetweenWords() {
        return options.isNeedSpaceBetweenWords();
    }
}
