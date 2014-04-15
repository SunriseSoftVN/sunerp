package services;


import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static services.ReportStyle.*;

/**
 * The Class KhoiLuongReportColumnBuilder.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 9:32 AM
 */

public final class KhoiLuongReportColumnBuilder {

    public static JasperReportBuilder buildLayout() {
        TextColumnBuilder<String> maCv = col.column("Mã Cv", "taskCode", type.stringType()).setStyle(COLUMN_CENTER_STYLE);
        TextColumnBuilder<String> taskName = col.column("Nội dung Cv", "taskName", type.stringType()).setFixedWidth(200);
        TextColumnBuilder<String> taskUnit = col.column("Đơn vị", "taskUnit", type.stringType()).setStyle(COLUMN_CENTER_STYLE);
        TextColumnBuilder<Double> total = col.column("Tổng cộng", "total", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);

        JasperReportBuilder builder = report()
                .title(
                        cmp.horizontalList().add(
                                cmp.verticalList().add(
                                        cmp.text("Xí nghiệp ……..").setStyle(stl.style(LEFT_TITLE_STYLE).setLeftPadding(5)),
                                        cmp.text("Cung (Trạm)…………").setStyle(LEFT_SUB_TITLE_STYLE)
                                ).setFixedWidth(150),
                                cmp.verticalList().add(
                                        cmp.text("BIỂU TỔNG HỢP CÔNG VIỆC HÀNG NGÀY").setStyle(TITLE_STYLE),
                                        cmp.text("Tháng ….... Quý....... Năm 20.....").setStyle(SUB_TITLE_STYLE)
                                )
                        )
                )
                .setColumnTitleStyle(COLUMN_TITLE_STYLE)
                .setColumnStyle(COLUMN_STYLE)
                .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
                .setPageFormat(PageType.A3, PageOrientation.LANDSCAPE);

        builder.columns(maCv, taskName, taskUnit, total);
        ColumnTitleGroupBuilder tg = grid.titleGroup();
        tg.setTitle("Ngày thực hiện");
        for (Integer i = 1; i <= 31; i++) {
            TextColumnBuilder<Double> _col = col
                    .column(i.toString(), "khoiLuongCongViec." + i, type.doubleType())
                    .setStyle(COLUMN_NUMBER_STYLE)
                    .setWidth(50);
            builder.addColumn(_col);
            tg.add(_col);
        }
        builder.columnGrid(maCv, taskName, taskUnit, total, tg);
        return builder;
    }

}
