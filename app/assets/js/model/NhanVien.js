/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.model.NhanVien', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'maNv',
        'firstName',
        'lastName',
        'heSoLuong',
        'chucVuId',
        'chucVu',
        'nhanVien',
        {name: 'chucVu.name', mapping: 'chucVu.name'},
        'phongBangId',
        'phongBang',
        {name: 'phongBang.name', mapping: 'phongBang.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.ChucVu', name: 'chucVu'},
        {type: 'belongsTo', model: 'sunerp.model.PhongBang', name: 'phongBang'}
    ]
});