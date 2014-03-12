Ext.define('sunerp.view.sophancong.SoPhanCongList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.soPhanCongList',
    requires: ['sunerp.controller.sophancong.SoPhanCongListCtr'],
    controller: 'sunerp.controller.sophancong.SoPhanCongListCtr',
    inject: ['soPhanCongStore'],
    config: {
        soPhanCongStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = me.getSoPhanCongStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Họ tên', dataIndex: 'nhanVien.firstName', flex: 1},
            {header: 'Công việc', dataIndex: 'task.name', flex: 1, sortable: false},
            {header: 'Khối lượng', dataIndex: 'khoiLuong', flex: 1},
            {header: 'Giờ', dataIndex: 'gio', flex: 1},
            {header: 'Ghi chú', dataIndex: 'ghiChu', flex: 1},
            {header: 'Ngày phân công', dataIndex: 'ngayPhanCong', flex: 1},
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true,
                items: [this.deleteBtn()]
            }
        ];
        me.callParent(arguments);
    }
});