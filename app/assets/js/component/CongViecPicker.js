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
            {header: 'Đơn vị', dataIndex: 'unit'},
            {header: 'Khối lượng', dataIndex: 'defaultValue', xtype: 'numbercolumn'},
            {header: 'Định mức', dataIndex: 'quota', xtype: 'numbercolumn'}
        ];
    },
    onOk: function () {
        this.getModel().set(this.modelName + "Id", this.getSelect().id);
        this.getModel().set(this.modelName + "Name" , this.getSelect()[this.displayField]);
        this.getWindow().close();
    }
});