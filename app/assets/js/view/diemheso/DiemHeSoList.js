/**
 * Created by dungvn3000 on 5/9/14.
 */
Ext.define('sunerp.view.diemheso.DiemHeSoList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.diemHeSoList',
    requires: [
        'sunerp.component.YearCb'
    ],
    controller: 'sunerp.controller.diemheso.DiemHeSoListCtr',
    inject: ['diemHeSoStore', 'userService'],
    config: {
        diemHeSoStore: null,
        userService: null
    },
    searchField: 'nhanVien.firstName',
    initComponent: function () {
        var me = this;
        me.store = this.getDiemHeSoStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {
                header: 'Tên',
                dataIndex: 'nhanVien.firstName',
                width: 100
            },
            {
                header: 'Họ',
                dataIndex: 'nhanVien.lastName',
                flex: 1
            },
            {header: 'Đơn vị', dataIndex: 'phongBan.name', flex: 1},
            {header: 'Hệ số', dataIndex: 'heSo', flex: 1},
            {
                xtype: 'actioncolumn',
                header: 'Option',
                sortable: false,
                menuDisabled: true,
                items: [this.deleteBtn()]
            }
        ];
        me.callParent(arguments);
    },
    initTBar: function () {
        var me = this;
        me.callParent(arguments);
        var yearCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.YearCb', {
                name: 'Năm',
                width: 100
            }),
            fieldName: 'year',
            store: me.store
        });

        var donViCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.DonViCb', {
                name: 'Xí Nghiệp',
                width: 200
            }),
            fieldName: 'donViId',
            store: me.store
        });

        var phongBanCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.PhongBanCb', {
                name: 'Đơn vị',
                width: 200
            }),
            fieldName: 'phongBanId',
            store: me.store
        });

        me.tbar.insert(1, [donViCbFilter, phongBanCbFilter, yearCbFilter])
    }
});