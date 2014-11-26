Ext.define('sunerp.model.HeSoLuong', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'value',
        'month',
        'year',
        'phongBan',
        'nhanVien',
        {name: 'nhanVien.maNv', mapping: 'nhanVien.maNv'},
        {name: 'nhanVien.fullName', mapping: 'nhanVien.fullName'},
        {name: 'phongBan.name', mapping: 'phongBan.name'}
    ]
});