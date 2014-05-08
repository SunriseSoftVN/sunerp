Ext.define('sunerp.model.XepLoai', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'month',
        'year',
        'xepLoai',
        {name: 'nhanVien.firstName', mapping: 'nhanVien.firstName'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'}
    ]
});