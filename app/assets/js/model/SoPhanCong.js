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
    ],
    set: function (fieldName, newValue) {
        if (fieldName == "task") {
            this.set('task.name', newValue.get('name'));
            this.set('taskId', newValue.get('id'));
        }
        this.callParent(arguments);
    }
});