package services;


import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.grid.HorizontalColumnGridListBuilder;
import net.sf.dynamicreports.report.builder.grid.VerticalColumnGridListBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static services.ReportStyle.COLUMN_TITLE_STYLE;
import static services.ReportStyle.TITLE_STYLE;

/**
 * The Class KhoiLuongReportColumnBuilder.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 9:32 AM
 */

public final class KhoiLuongReportColumnBuilder {

    public static JasperReportBuilder buildLayout() {
        TextColumnBuilder<String> maCv = col.column("Mã Cv", "taskCode", type.stringType());
        TextColumnBuilder<String> taskName = col.column("Nội dung Cv", "taskName", type.stringType()).setFixedWidth(200);
        TextColumnBuilder<String> taskUnit = col.column("Đơn vị", "taskUnit", type.stringType());
        TextColumnBuilder<Double> total = col.column("Tổng cộng", "total", type.doubleType());
        TextColumnBuilder<String> date = col.column("Ngày thực hiện", "data", type.stringType());


        JasperReportBuilder builder = report()
                .title(cmp.text("BIỂU TỔNG HỢP CÔNG VIỆC HÀNG NGÀY").setStyle(TITLE_STYLE))
                .setColumnTitleStyle(COLUMN_TITLE_STYLE)
                .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
                .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE);

        builder.columns(maCv, taskName, taskUnit, total, date);
        HorizontalColumnGridListBuilder hb = grid.horizontalColumnGridList();
        VerticalColumnGridListBuilder vb = grid.verticalColumnGridList();
        for (Integer i = 1; i <= 31; i++) {
            TextColumnBuilder<Double> _col = col.column(i.toString(), i.toString(), type.doubleType()).setWidth(50);
            builder.addColumn(_col);
            hb.add(_col);
        }
        vb.add(date, hb);

        builder.columnGrid(maCv, taskName, taskUnit, total, vb);

        return builder;
    }

}
