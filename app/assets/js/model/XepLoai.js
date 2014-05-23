Ext.define('sunerp.model.XepLoai', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'nhanVien',
        'month',
        'year',
        'xepLoai',
        {name: 'nhanVien.fullName', mapping: 'nhanVien.fullName'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'}
    ]
});