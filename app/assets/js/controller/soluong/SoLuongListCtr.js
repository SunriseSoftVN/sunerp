/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.soluong.SoLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['soLuongStore'],
    config: {
        soLuongStore: null
    },
    control: {
        addBtn: {
            selector: 'button[action=addNew]',
            listeners: {
                click: 'addNewRow'
            }
        }
    },
    init: function () {
        this.callParent(arguments);
    },
    addNewRow: function () {
        var rec = new sunerp.model.SoLuong();
        this.getSoLuongStore().insert(0, rec);
    }
});