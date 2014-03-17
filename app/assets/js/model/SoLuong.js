/**
 * Created by dungvn3000 on 3/17/14.
 */

Ext.define('sunerp.model.SoLuong', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'nhanVien',
        'chucVu',
        'heSoLuong',
        'luongNd',
        'k2',
        'luongSP',
        'luongTgCong',
        'luongTgTien',
        'cacKhoangCongId',
        'cacKhoangCong',
        'cacKhoangTruId',
        'cacKhoangTru',
        'createdDate'

    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.CacKhoanCong', name: 'cacKhoangCong'},
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'},
        {type: 'belongsTo', model: 'sunerp.model.CacKhoanTru', name: 'cacKhoangTru'}
    ]
});