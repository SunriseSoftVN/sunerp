/**
 * Created by dungvn3000 on 3/11/14.
 */

Ext.define('sunerp.model.SoPhanCong', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'nhanVienId',
        'nhanVien',
        'taskId',
        'taskName',
        'task',
        'phongBanId',
        'phongBan',
        'khoiLuong',
        'gio',
        'ghiChu',
        'soPhanCongExtId',
        'soPhanCongExt',
        {name: 'nhanVien.maNv', mapping: 'nhanVien.maNv'},
        {name: 'nhanVien.firstName', mapping: 'nhanVien.firstName'},
        {name: 'soPhanCongExt.lamDem', mapping: 'soPhanCongExt.lamDem', type: 'boolean'},
        {name: 'soPhanCongExt.baoHoLaoDong', mapping: 'soPhanCongExt.baoHoLaoDong', type: 'boolean'},
        {name: 'soPhanCongExt.docHai', mapping: 'soPhanCongExt.docHai', type: 'boolean'},
        {name: 'soPhanCongExt.le', mapping: 'soPhanCongExt.le', type: 'boolean'},
        {name: 'soPhanCongExt.tet', mapping: 'soPhanCongExt.tet', type: 'boolean'},
        {name: 'soPhanCongExt.thaiSan', mapping: 'soPhanCongExt.thaiSan', type: 'boolean'},
        {name: 'soPhanCongExt.dauOm', mapping: 'soPhanCongExt.dauOm', type: 'boolean'},
        {name: 'soPhanCongExt.conOm', mapping: 'soPhanCongExt.conOm', type: 'boolean'},
        {name: 'soPhanCongExt.taiNanLd', mapping: 'soPhanCongExt.taiNanLd', type: 'boolean'},
        {name: 'soPhanCongExt.hop', mapping: 'soPhanCongExt.hop', type: 'boolean'},
        {name: 'soPhanCongExt.hocDaiHan', mapping: 'soPhanCongExt.hocDaiHan', type: 'boolean'},
        {name: 'soPhanCongExt.hocDotXuat', mapping: 'soPhanCongExt.hocDotXuat', type: 'boolean'},
        {name: 'soPhanCongExt.viecRieng', mapping: 'soPhanCongExt.viecRieng', type: 'boolean'},
        {name: 'soPhanCongExt.chuNhat', mapping: 'soPhanCongExt.chuNhat', type: 'boolean'},
        {name: 'ngayPhanCong', type: 'date'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.SoPhanCongExt', name: 'soPhanCongExt'},
        {type: 'belongsTo', model: 'sunerp.model.PhongBan', name: 'phongBan'},
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'},
        {type: 'belongsTo', model: 'sunerp.model.Task', name: 'task'}
    ]
});