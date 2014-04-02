/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.model.SoPhanCong', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'nhanVien',
        {name: 'nhanVien.firstName', mapping: 'nhanVien.firstName'},
        'taskId',
        'task',
        {name: 'task.name', mapping: 'task.name'},
        'phongBangId',
        'phongBang',
        'khoiLuong',
        'gio',
        'ghiChu',
        'soPhanCongExtId',
        'soPhanCongExt',
        {name: 'ngayPhanCong', type: 'date'}

    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.SoPhanCongExt', name: 'soPhanCongExt'},
        {type: 'belongsTo', model: 'sunerp.model.PhongBang', name: 'phongBang'},
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'},
        {type: 'belongsTo', model: 'sunerp.model.Task', name: 'task'}
    ]
});