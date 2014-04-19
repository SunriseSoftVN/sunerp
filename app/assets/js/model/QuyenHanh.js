/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.model.QuyenHanh', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'domain',
        'read',
        'write',
        'chucVuId',
        'phongBanId',
        'gioiHan',
        'chucVu',
        {name: 'chucVu.name', mapping: 'chucVu.name'},
        {name: 'phongBan.name', mapping: 'phongBan.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.ChucVu', name: 'chucVu'},
        {type: 'belongsTo', model: 'sunerp.model.PhongBan', name: 'phongBan'}
    ]
});