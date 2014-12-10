/**
 * Created by dungvn3000 on 3/17/14.
 */

function fullName(v, record) {
    return record.data.lastName + " " + record.data.firstName;
}

Ext.define('sunerp.model.NhanVien', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'maNv',
        'password',
        'firstName',
        'lastName',
        {name: 'fullName', convert: fullName},
        'chucVuId',
        'chucVu',
        'nhanVien',
        {name: 'chucVu.name', mapping: 'chucVu.name'},
        'phongBanId',
        'phongBan',
        {name: 'phongBan.name', mapping: 'phongBan.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.ChucVu', name: 'chucVu'},
        {type: 'belongsTo', model: 'sunerp.model.PhongBan', name: 'phongBan'}
    ]
});