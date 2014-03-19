Ext.define('sunerp.component.CongViecPicker', {
    extend: 'sunerp.component.core.BasePicker',
    alias: 'widget.congviecpicker',
    searchEmptyText: 'Tìm theo tên hoặc mã công việc',
    storeClass: 'sunerp.store.TaskStore',
    modelName: 'task',
    searchField: 'nameOrCode',
    title: 'Chọn công việc',
    getColumns: function () {
        return [
            {header: 'Mã CV', dataIndex: 'code'},
            {header: 'Tên', dataIndex: 'name', flex: 1},
            {header: 'Khối lượng', dataIndex: 'defaultValue', xtype: 'numbercolumn'}
        ];
    }
});