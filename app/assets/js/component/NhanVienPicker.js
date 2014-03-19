/**
 * Created by dungvn3000 on 3/19/14.
 */

Ext.define('sunerp.component.NhanVienPicker', {
    extend: 'sunerp.component.core.BasePicker',
    alias: 'widget.nhanvienpicker',
    searchEmptyText: 'Tìm theo tên',
    storeClass: 'sunerp.store.NhanVienStore',
    modelName: 'nhanVien',
    searchField: 'name',
    title: 'Chọn nhân viên',
    getColumns: function () {
        return [
            {header: 'Tên', dataIndex: 'name', flex: 1}
        ];
    }
});