package services;

import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalAlignment;
import net.sf.dynamicreports.report.constant.VerticalAlignment;

import static net.sf.dynamicreports.report.builder.DynamicReports.stl;

/**
 * The Class ReportStyle.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 9:38 AM
 */
public final class ReportStyle {

    public static final StyleBuilder BOLD_STYLE = stl.style().bold();
    public static final StyleBuilder ALIGN_LEFT_STYLE = stl.style().setHorizontalAlignment(HorizontalAlignment.LEFT);
    public static final StyleBuilder ALIGN_CENTER_STYLE = stl.style().setHorizontalAlignment(HorizontalAlignment.CENTER);
    public static final StyleBuilder BOLD_CENTERED_STYLE = stl.style(BOLD_STYLE).setHorizontalAlignment(HorizontalAlignment.CENTER);
    public static final StyleBuilder TITLE_STYLE = stl.style(BOLD_CENTERED_STYLE).setTopPadding(10);
    public static final StyleBuilder LEFT_TITLE_STYLE = stl.style(TITLE_STYLE).setHorizontalAlignment(HorizontalAlignment.LEFT);
    public static final StyleBuilder SUB_TITLE_STYLE = stl.style(BOLD_CENTERED_STYLE).setPadding(5).setBottomPadding(10);
    public static final StyleBuilder LEFT_SUB_TITLE_STYLE = stl.style(SUB_TITLE_STYLE).setHorizontalAlignment(HorizontalAlignment.LEFT);
    public static final StyleBuilder RIGHT_SUB_TITLE_STYLE = stl.style(SUB_TITLE_STYLE).setHorizontalAlignment(HorizontalAlignment.RIGHT);
    public static final StyleBuilder COLUMN_TITLE_STYLE = stl.style(BOLD_CENTERED_STYLE).setBorder(stl.penThin())
            .setVerticalAlignment(VerticalAlignment.MIDDLE);
    public static final StyleBuilder COLUMN_STYLE = stl.style().setBorder(stl.penThin())
            .setHorizontalAlignment(HorizontalAlignment.LEFT)
            .setVerticalAlignment(VerticalAlignment.MIDDLE)
            .setPadding(3);

    public static final StyleBuilder COLUMN_CENTER_STYLE = stl.style(COLUMN_STYLE)
            .setHorizontalAlignment(HorizontalAlignment.CENTER);

    public static final StyleBuilder COLUMN_NUMBER_STYLE = stl.style(COLUMN_STYLE)
            .setHorizontalAlignment(HorizontalAlignment.RIGHT).setRightPadding(3);

}
