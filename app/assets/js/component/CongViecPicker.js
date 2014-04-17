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
            {header: 'Định mức', dataIndex: 'defaultValue', xtype: 'numbercolumn'},
            {header: 'Số lần', dataIndex: 'quota', xtype: 'numbercolumn'}
        ];
    },
    onOk: function () {
        this.getModel().set(this.modelName + "Id", this.getSelect().id);
        this.getModel().set(this.modelName + "Name" , this.getSelect().name);
        this.getModel().set("dinhMuc" , this.getSelect().defaultValue);
        this.getWindow().close();
    }
});