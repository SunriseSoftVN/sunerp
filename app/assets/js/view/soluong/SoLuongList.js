/**
 * Created by dungvn3000 on 3/18/14.
 */
Ext.define('sunerp.view.soluong.SoLuongList', {
    extend: 'sunerp.view.core.BaseListView',
    alias: 'widget.soLuongList',
    requires: ['sunerp.controller.soluong.SoLuongListCtr'],
    controller: 'sunerp.controller.soluong.SoLuongListCtr',
    inject: ['soLuongStore'],
    config: {
        soLuongStore: null
    },
    initComponent: function () {
        var me = this;
        me.store = this.getSoLuongStore();
        me.columns = [
            {xtype: 'rownumberer'},
            {header: 'Chức vụ', dataIndex: 'chucVu', flex: 1},
            {header: 'Hệ số lương', dataIndex: 'heSoLuong', flex: 1},
            {header: 'Luong nghị định', dataIndex: 'luongNd', flex: 1},
            {header: 'K2', dataIndex: 'k2', flex: 1},
            {header: 'Lương sản phẩm', dataIndex: 'luongSP', flex: 1},
            {
                text: 'Lương thời gian',
                columns: [{
                    text     : 'Cộng',
                    dataIndex: 'luongTgCong'
                }, {
                    text     : 'Tiền',
                    dataIndex: 'luongTgTien'
                }]
            },
            {header: 'Ngày tạo', dataIndex: 'createdDate', flex: 1},
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