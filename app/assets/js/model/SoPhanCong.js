/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.model.SoPhanCong', {
    extend: 'Ext.data.Model',
    fields: [
        'id',
        'nhanVienId',
        {name: 'nhanVien.firstName', mapping: 'nhanVien.firstName'},
        'taskId',
        {name: 'task.name', mapping: 'task.name'},
        'phongBangId',
        'khoiLuong',
        'gio',
        'ghiChu',
        'soPhanCongExtId',
        'ngayPhanCong',
        'task'
    ]
});