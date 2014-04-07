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
        'task',
        'phongBangId',
        'phongBang',
        'khoiLuong',
        'gio',
        'ghiChu',
        'soPhanCongExtId',
        'soPhanCongExt',
        {name: 'task.name', mapping: 'task.name'},
        {name: 'nhanVien.maNv', mapping: 'nhanVien.maNv'},
        {name: 'nhanVien.firstName', mapping: 'nhanVien.firstName'},
        {name: 'soPhanCongExt.lamDem', mapping: 'soPhanCongExt.lamDem'},
        {name: 'soPhanCongExt.baoHoLaoDong', mapping: 'soPhanCongExt.baoHoLaoDong'},
        {name: 'soPhanCongExt.docHai', mapping: 'soPhanCongExt.docHai'},
        {name: 'soPhanCongExt.le', mapping: 'soPhanCongExt.le'},
        {name: 'soPhanCongExt.tet', mapping: 'soPhanCongExt.tet'},
        {name: 'soPhanCongExt.thaiSan', mapping: 'soPhanCongExt.thaiSan'},
        {name: 'soPhanCongExt.dauOm', mapping: 'soPhanCongExt.dauOm'},
        {name: 'soPhanCongExt.conOm', mapping: 'soPhanCongExt.conOm'},
        {name: 'soPhanCongExt.taiNanLd', mapping: 'soPhanCongExt.taiNanLd'},
        {name: 'soPhanCongExt.hop', mapping: 'soPhanCongExt.hop'},
        {name: 'soPhanCongExt.hocDaiHan', mapping: 'soPhanCongExt.hocDaiHan'},
        {name: 'soPhanCongExt.hocDotXuat', mapping: 'soPhanCongExt.hocDotXuat'},
        {name: 'soPhanCongExt.viecRieng', mapping: 'soPhanCongExt.viecRieng'},
        {name: 'soPhanCongExt.chuNhat', mapping: 'soPhanCongExt.chuNhat'},
        {name: 'ngayPhanCong', type: 'date'}

    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.SoPhanCongExt', name: 'soPhanCongExt'},
        {type: 'belongsTo', model: 'sunerp.model.PhongBang', name: 'phongBang'},
        {type: 'belongsTo', model: 'sunerp.model.NhanVien', name: 'nhanVien'},
        {type: 'belongsTo', model: 'sunerp.model.Task', name: 'task'}
    ]
});