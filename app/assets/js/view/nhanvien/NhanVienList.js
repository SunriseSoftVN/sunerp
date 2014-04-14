/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.nhanvien.NhanVienList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.nhanVienList',
    requires: [
        'sunerp.component.PhongBanCb'
    ],
    controller: 'sunerp.controller.nhanvien.NhanVienListCtr',
    inject: ['nhanVienStore'],
    config: {
        nhanVienStore: null
    },
    searchField: 'nameOrMaNv',
    initComponent: function () {
        var me = this;
        me.store = this.getNhanVienStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {header: 'Mã NV', dataIndex: 'maNv', flex: 1},
            {header: 'Tên', dataIndex: 'firstName', flex: 1},
            {header: 'Họ', dataIndex: 'lastName', flex: 1},
            {
                header: 'Chức vụ',
                dataIndex: 'chucVu.name',
                flex: 1
            },
            {
                header: 'Đơn vị',
                dataIndex: 'phongBan.name',
                flex: 1
            },
            {header: 'Hệ số lương', dataIndex: 'heSoLuong', flex: 1},
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
        var chuVuCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.ChucVuCb', {
                name: 'Chức vụ',
                width: 140
            }),
            fieldName: 'chucVuId',
            store: me.store
        });
        me.tbar.insert(1, [phongBanCbFilter, chuVuCbFilter]);
    }
});