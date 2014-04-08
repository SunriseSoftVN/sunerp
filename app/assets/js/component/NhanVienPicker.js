/**
 * Created by dungvn3000 on 3/19/14.
 */

Ext.define('sunerp.component.NhanVienPicker', {
    extend: 'sunerp.component.core.BasePicker',
    alias: 'widget.nhanvienpicker',
    searchEmptyText: 'Tìm theo tên',
    storeClass: 'sunerp.store.NhanVienStore',
    modelName: 'nhanVien',
    searchField: 'firstName',
    title: 'Chọn nhân viên',
    getColumns: function () {
        return [
            {header: 'Mã NV', dataIndex: 'maNv', flex: 1},
            {header: 'Tên', dataIndex: 'firstName', flex: 1},
            {header: 'Họ', dataIndex: 'lastName', flex: 1},
            {header: 'Hệ số luơng', dataIndex: 'heSoLuong', flex: 1},
            {header: 'Chức vụ', dataIndex: 'chucVu.name', flex: 1},
            {header: 'Phòng ban', dataIndex: 'phongBan.name', flex: 1}
        ];
    }
});