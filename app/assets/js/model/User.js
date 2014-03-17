Ext.define('sunerp.model.User', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'username',
        'password',
        'roleId',
        'nhanVienId',
        'role',
        'nhanVien',
        {name: 'role.name', mapping: 'role.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.Role', name: 'role'},
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'}
    ]
});