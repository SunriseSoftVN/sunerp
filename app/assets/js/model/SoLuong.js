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
        'createdDate',

        {name: 'cacKhoangCong.phuCapTn', mapping: 'cacKhoangCong.phuCapTn'},
        {name: 'cacKhoangCong.phuCapLd', mapping: 'cacKhoangCong.phuCapLd'},
        {name: 'cacKhoangCong.trucBHLD', mapping: 'cacKhoangCong.trucBHLD'},
        {name: 'cacKhoangCong.phuCapKV', mapping: 'cacKhoangCong.phuCapKV'},
        {name: 'cacKhoangCong.congPhanLuong', mapping: 'cacKhoangCong.congPhanLuong'},
        {name: 'cacKhoangCong.chiKhac', mapping: 'cacKhoangCong.chiKhac'},
        {name: 'cacKhoangCong.luongDocHai', mapping: 'cacKhoangCong.luongDocHai'},
        {name: 'cacKhoangCong.nuocUong', mapping: 'cacKhoangCong.nuocUong'},
        {name: 'cacKhoangCong.anGiuaCa', mapping: 'cacKhoangCong.anGiuaCa'},
        {name: 'cacKhoangCong.omDauSinhDe', mapping: 'cacKhoangCong.omDauSinhDe'},
        {name: 'cacKhoangTru.doanPhi', mapping: 'cacKhoangTru.doanPhi'},
        {name: 'cacKhoangTru.ungKy1', mapping: 'cacKhoangTru.ungKy1'},
        {name: 'cacKhoangTru.bhyt', mapping: 'cacKhoangTru.bhyt'},
        {name: 'cacKhoangTru.bhxh', mapping: 'cacKhoangTru.bhxh'},
        {name: 'cacKhoangTru.thuNo', mapping: 'cacKhoangTru.thuNo'},
        {name: 'nhanVien.firstName', mapping: 'nhanVien.firstName'},
        {name: 'nhanVien.lastName', mapping: 'nhanVien.lastName'}

    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.CacKhoanCong', name: 'cacKhoangCong'},
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'},
        {type: 'belongsTo', model: 'sunerp.model.CacKhoanTru', name: 'cacKhoangTru'}
    ]
});