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
        'gioiHan',
        'chucVu',
        {name: 'chucVu.name', mapping: 'chucVu.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.ChucVu', name: 'chucVu'}
    ]
});