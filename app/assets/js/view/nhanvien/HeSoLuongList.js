/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.nhanvien.HeSoLuongList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.nhanVienList',
    requires: [
        'sunerp.component.PhongBanCb'
    ],
    controller: 'sunerp.controller.nhanvien.HeSoLuongListCtr',
    inject: ['heSoLuongStore', 'userService'],
    config: {
        heSoLuongStore: null,
        userService: null
    },
    searchField: 'nameOrMaNv',
    initComponent: function () {
        var me = this;
        me.store = this.getHeSoLuongStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Mã NV', dataIndex: 'nhanVien.maNv', flex: 1},
            {header: 'Tên', dataIndex: 'nhanVien.fullName', flex: 1},
            {
                header: 'Đơn vị',
                dataIndex: 'phongBan.name',
                flex: 1
            },
            {header: 'Hệ số lương', dataIndex: 'value', flex: 1},
            {header: 'Tháng', dataIndex: 'month', flex: 1},
            {header: 'Năm', dataIndex: 'year', flex: 1},
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
        var phongBanCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.PhongBanCb', {
                name: 'Đơn vị',
                width: 200
            }),
            fieldName: 'phongBanId',
            store: me.store
        });
        var gioHan = me.getUserService().checkGioiHan("nhanvien");
        var donViId = me.getUserService().getCurrentUser().donViId;
        if (gioHan == "donvi") {
            me.store.addFilter({
                property: 'donViId',
                value: sunerp.Utils.toString(donViId)
            }, false);
        }

        var yearCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.YearCb', {
                name: 'Năm',
                width: 100
            }),
            fieldName: 'year',
            store: me.store
        });

        me.tbar.insert(1, [phongBanCbFilter, yearCbFilter]);
    }
});