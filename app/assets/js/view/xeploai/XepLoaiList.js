/**
 * Created by dungvn3000 on 5/9/14.
 */
Ext.define('sunerp.view.xeploai.XepLoaiList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.xepLoaiList',
    requires: [
        'sunerp.component.MonthCb',
        'sunerp.component.YearCb'
    ],
    controller: 'sunerp.controller.xeploai.XepLoaiListCtr',
    inject: ['xepLoaiStore', 'userService'],
    config: {
        xepLoaiStore: null,
        userService: null
    },
    searchField: 'nhanVien.firstName',
    initComponent: function () {
        var me = this;
        me.store = this.getXepLoaiStore();
        me.columns = [
            {xtype: 'rownumberer', width: 30},
            {
                header: 'Họ tên',
                dataIndex: 'nhanVien.fullName',
                flex: 1
            },
            {header: 'Xếp loại', dataIndex: 'xepLoai', flex: 1},
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
        var momthCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.MonthCb', {
                name: 'Tháng',
                width: 100
            }),
            fieldName: 'month',
            store: me.store
        });

        var yearCbFilter = Ext.create('sunerp.component.filter.ComboboxFilter', {
            comp: Ext.create('sunerp.component.YearCb', {
                name: 'Năm',
                width: 70
            }),
            fieldName: 'year',
            store: me.store
        });

        var gioiHan = me.getUserService().checkGioiHan('xeploai');
        var phongBanId = me.getUserService().getCurrentUser().phongBanId;
        var donViId = me.getUserService().getCurrentUser().donViId;
        if (gioiHan == "phongban") {
            var phongBangFilter = new Ext.util.Filter({
                property: 'phongBanId',
                value: sunerp.Utils.toString(phongBanId)
            });
            me.store.addFilter(phongBangFilter, false);
        }

        me.tbar.insert(1, [momthCbFilter, yearCbFilter])
    }
});