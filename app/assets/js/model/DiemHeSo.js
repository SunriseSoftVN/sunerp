Ext.define('sunerp.model.DiemHeSo', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'nhanVien',
        'year',
        'heSo',
        {name: 'nhanVien.fullName', mapping: 'nhanVien.fullName'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'}
    ]
});