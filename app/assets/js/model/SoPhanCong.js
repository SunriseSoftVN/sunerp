/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.model.SoPhanCong', {
    extend: 'Ext.data.Model',
    fields: [
        'id',
        'nhanVienId',
        'taskId',
        'phongBangId',
        'khoiLuong',
        'gio',
        'ghiChu',
        'soPhanCongExtId',
        'ngayPhanCong'
    ]
});