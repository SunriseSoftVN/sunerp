/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.soluong.SoLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['soLuongStore'],
    config: {
        soLuongStore: null
    },
    editView: 'sunerp.view.soluong.SoLuongEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getSoLuongStore();
        this.callParent(arguments);
    }
});