package device.s.docreader.office.fc.hssf.usermodel;

import device.s.docreader.office.fc.ss.usermodel.DataFormatter;

import java.text.DecimalFormat;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Locale;


/**
 * HSSFDataFormatter contains methods for formatting the value stored in an
 * HSSFCell. This can be useful for reports and GUI presentations when you
 * need to display data exactly as it appears in Excel. Supported formats
 * include currency, SSN, percentages, decimals, dates, phone numbers, zip
 * codes, etc.
 * <p>
 * Internally, formats will be implemented using subclasses of {@link Format}
 * such as {@link DecimalFormat} and {@link SimpleDateFormat}. Therefore the
 * formats used by this class must obey the same pattern rules as these Format
 * subclasses. This means that only legal number pattern characters ("0", "#",
 * ".", "," etc.) may appear in number formats. Other characters can be
 * inserted <em>before</em> or <em> after</em> the number pattern to form a
 * prefix or suffix.
 * </p>
 * <p>
 * For example the Excel pattern <code>"$#,##0.00 "USD"_);($#,##0.00 "USD")"
 * </code> will be correctly formatted as "$1,000.00 USD" or "($1,000.00 USD)".
 * However the pattern <code>"00-00-00"</code> is incorrectly formatted by
 * DecimalFormat as "000000--". For Excel formats that are not compatible with
 * DecimalFormat, you can provide your own custom {@link Format} implementation
 * via <code>HSSFDataFormatter.addFormat(String,Format)</code>. The following
 * custom formats are already provided by this class:
 * </p>
 * <pre>
 * <ul><li>SSN "000-00-0000"</li>
 *     <li>Phone Number "(###) ###-####"</li>
 *     <li>Zip plus 4 "00000-0000"</li>
 * </ul>
 * </pre>
 * <p>
 * If the Excel format pattern cannot be parsed successfully, then a default
 * format will be used. The default number format will mimic the Excel General
 * format: "#" for whole numbers and "#.##########" for decimal numbers. You
 * can override the default format pattern with <code>
 * HSSFDataFormatter.setDefaultNumberFormat(Format)</code>. <b>Note:</b> the
 * default format will only be used when a Format cannot be created from the
 * cell's data format string.
 *
 * @author James May (james dot may at fmr dot com)
 */
public final class HSSFDataFormatter extends DataFormatter {

    /**
     * Creates a formatter using the given locale.
     */
    public HSSFDataFormatter(Locale locale) {
        super(locale);
    }

    /**
     * Creates a formatter using the {@link Locale#getDefault() default locale}.
     */
    public HSSFDataFormatter() {
        this(Locale.getDefault());
    }

}
