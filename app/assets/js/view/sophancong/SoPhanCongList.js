Ext.define('sunerp.view.sophancong.SoPhanCongList', {
    extend: 'Ext.grid.Panel',
    requires: [
        'Ext.grid.plugin.CellEditing',
        'Ext.selection.CellModel',
        'Ext.toolbar.TextItem',
        'Ext.form.field.Checkbox',
        'Ext.form.field.Text',
        'Ext.toolbar.Paging',
        'Ext.form.field.ComboBox',
        'sunerp.controller.sophancong.SoPhanCongListCtr'
    ],
    tbar: [
        {
            xtype: 'textfield',
            name: 'searchField',
            hideLabel: true,
            width: 200
        },
        {
            text: 'Thêm mới',
            tooltip: 'Thêm mới',
            iconCls: 'add',
            action: 'addNew'
        },
        {
            text: 'Cập nhật',
            tooltip: 'Cập nhật',
            iconCls: 'save',
            action: 'save'
        }
    ],
    alias: 'widget.soPhanCongList',
    controller: 'sunerp.controller.sophancong.SoPhanCongListCtr',
    inject: ['soPhanCongStore', 'taskStore'],
    config: {
        taskStore: null,
        soPhanCongStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = me.getSoPhanCongStore();
        me.columns = [
            {
                header: 'Họ tên',
                dataIndex: 'nhanVien.firstName',
                flex: 1,
                editor: new Ext.form.field.ComboBox({
                    typeAhead: true,
                    triggerAction: 'all',
                    store: [
                        ['Shade', 'Shade'],
                        ['Mostly Shady', 'Mostly Shady'],
                        ['Sun or Shade', 'Sun or Shade'],
                        ['Mostly Sunny', 'Mostly Sunny'],
                        ['Sunny', 'Sunny']
                    ]
                })
            },
            {
                header: 'Công việc',
                dataIndex: 'task.name',
                flex: 1,
                sortable: false,
                editor: new Ext.form.field.ComboBox({
                    typeAhead: true,
                    triggerAction: 'all',
                    store: this.getTaskStore(),
                    displayField: 'name',
                    valueField: 'id'
                })
            },
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

        me.bbar = Ext.create('Ext.PagingToolbar', {
            store: me.store,
            displayInfo: true,
            displayMsg: 'Displaying topics {0} - {1} of {2}',
            emptyMsg: "No topics to display"
        });


        me.cellEditing = new Ext.grid.plugin.CellEditing({
            clicksToEdit: 1
        });

        me.plugins = [me.cellEditing];

        me.callParent(arguments);
    },
    deleteBtn: function () {
        return {
            icon: '/assets/img/icons/fam/delete.gif',
            tooltip: 'Delete',
            handler: function (view, rowIndex, colIndex, item, e, record) {
                this.fireEvent('deleteRecord', this, view, rowIndex, colIndex, item, e, record);
            }
        }
    }
});