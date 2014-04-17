package services;


import com.google.common.base.Strings;
import dtos.report.KhoiLuongReportRequest;
import models.sunerp.PhongBan;
import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.grid.ColumnTitleGroupBuilder;
import net.sf.dynamicreports.report.constant.ComponentPositionType;
import net.sf.dynamicreports.report.constant.PageOrientation;
import net.sf.dynamicreports.report.constant.PageType;
import net.sf.dynamicreports.report.constant.WhenNoDataType;
import org.apache.commons.lang3.StringUtils;
import utils.DateTimeUtils;

import static net.sf.dynamicreports.report.builder.DynamicReports.*;
import static services.ReportStyle.*;

/**
 * The Class KhoiLuongReportColumnBuilder.
 *
 * @author Nguyen Duc Dung
 * @since 4/15/14 9:32 AM
 */

public final class KhoiLuongReportColumnBuilder {

    public static final TextColumnBuilder<String> maCv = col.column("Mã Cv", "taskCode", type.stringType()).setStyle(COLUMN_CENTER_STYLE);
    public static final TextColumnBuilder<String> taskName = col.column("Nội dung Cv", "taskName", type.stringType()).setFixedWidth(200);
    public static final TextColumnBuilder<String> taskUnit = col.column("Đơn vị", "taskUnit", type.stringType()).setStyle(COLUMN_CENTER_STYLE);
    public static final TextColumnBuilder<Double> total = col.column("Tổng cộng", "totalKhoiLuong", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> dinhMuc = col.column("ĐM", "dinhMuc", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> soLan = col.column(" Số lần", "soLan", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> quyKL = col.column(" KL", "quyKl", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> quyGio = col.column(" Giờ", "quyGio", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> conLaiKL = col.column(" KL", "conLaiKL", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> conLaiGio = col.column(" Giờ", "conLaiGio", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> xnKL = col.column(" KL", "xnKL", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);
    public static final TextColumnBuilder<Double> xnGio = col.column(" Giờ", "xnGio", type.doubleType()).setStyle(COLUMN_NUMBER_STYLE);

    public static JasperReportBuilder buildDonViLayout(KhoiLuongReportRequest request) {
        int quarter = DateTimeUtils.getQuarter(request.month());
        JasperReportBuilder builder = report()
                .title(
                        cmp.verticalList(
                                cmp.centerHorizontal(
                                        cmp.horizontalList(
                                                cmp.verticalList(
                                                        cmp.text("CÔNG TY THÔNG TIN TÍN HIỆU ĐƯỜNG SẮT VINH").setStyle(stl.style(SUB_TITLE_STYLE).setBottomPadding(0)),
                                                        cmp.text("XN: " + request.donViName()).setStyle(SUB_TITLE_STYLE)
                                                ).setFixedWidth(400),
                                                cmp.verticalList(
                                                        cmp.text("CỘNG HOÀ XÃ HỘI CHỦ NGHĨA VIỆT NAM").setStyle(stl.style(SUB_TITLE_STYLE).setBottomPadding(0)),
                                                        cmp.text("Độc lập - Tự do - Hạnh phúc").setStyle(stl.style(SUB_TITLE_STYLE).setUnderline(true))
                                                ).setFixedWidth(400)
                                        )
                                ),
                                cmp.verticalList(
                                        cmp.text("TỔNG HỢP THỰC HIỆN KHỐI LƯỢNG SCTX KCHT TTTH ĐS THÁNG " + request.month() + " QUÝ " + quarter).setStyle(stl.style(TITLE_STYLE).setBottomPadding(10))
                                )
                        )
                )
                .setColumnTitleStyle(COLUMN_TITLE_STYLE)
                .setColumnStyle(COLUMN_STYLE)
                .setWhenNoDataType(WhenNoDataType.ALL_SECTIONS_NO_DETAIL)
                .setPageFormat(PageType.A4, PageOrientation.LANDSCAPE);

        builder.columns(maCv, taskName, taskUnit, dinhMuc, soLan, quyKL, quyGio, conLaiKL, conLaiGio, xnKL, xnGio);

        ColumnTitleGroupBuilder khQuy = grid.titleGroup();
        khQuy.setTitle("KH quý");
        khQuy.add(quyKL);
        khQuy.add(quyGio);

        ColumnTitleGroupBuilder conLai = grid.titleGroup();
        conLai.setTitle("Còn lại");
        conLai.add(conLaiKL);
        conLai.add(conLaiGio);

        ColumnTitleGroupBuilder xn = grid.titleGroup();
        xn.setTitle("Toàn XN");
        xn.add(xnKL);
        xn.add(xnGio);

        ColumnTitleGroupBuilder tb = grid.titleGroup();
        tb.setTitle("THỰC HIỆN KẾ HOẠCH THÁNG, QUÝ");
        tb.add(xn);

        for (PhongBan phongBan : request.getPhongBans()) {
            ColumnTitleGroupBuilder phongBanGroup = grid.titleGroup();
            phongBanGroup.setTitle(phongBan.name());

            TextColumnBuilder<Double> klCol = col
                    .column("KL", "khoiLuongCongViec." + phongBan.donViId(), type.doubleType())
                    .setStyle(COLUMN_NUMBER_STYLE)
                    .setWidth(50);

            TextColumnBuilder<Double> gioCol = col
                    .column("Giờ", "gioCongViec." + phongBan.donViId(), type.doubleType())
                    .setStyle(COLUMN_NUMBER_STYLE)
                    .setWidth(50);

            phongBanGroup.add(klCol);
            phongBanGroup.add(gioCol);

            builder.addColumn(klCol);
            builder.addColumn(gioCol);

            tb.add(phongBanGroup);
        }

        builder.columnGrid(maCv, taskName, taskUnit, dinhMuc, soLan, khQuy, conLai, tb);

        return builder;
    }

    public static JasperReportBuilder buildPhongBanLayout(KhoiLuongReportRequest request) {
        int quarter = DateTimeUtils.getQuarter(request.month());
        JasperReportBuilder builder = report()
                .title(
                        cmp.horizontalList().add(
                                cmp.verticalList().add(
                                        cmp.text("Xí nghiệp: " + request.donViName()).setStyle(stl.style(LEFT_SUB_TITLE_STYLE).setBottomPadding(0)),
                                        cmp.text("Cung (Trạm): " + request.phongBanName()).setStyle(LEFT_SUB_TITLE_STYLE)
                                ).setFixedWidth(250),
                                cmp.verticalList().add(
                                        cmp.text("BIỂU TỔNG HỢP CÔNG VIỆC HÀNG NGÀY").setStyle(TITLE_STYLE),
                                        cmp.text("Tháng: " + request.month() + "  Quý: " + quarter + "  Năm: " + request.year()).setStyle(SUB_TITLE_STYLE)
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
