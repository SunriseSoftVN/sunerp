/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.QuyLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['quyLuongStore'],
    config: {
        quyLuongStore: null
    },
    editView: 'sunerp.view.authority.QuyLuongEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getQuyLuongStore();
        this.callParent(arguments);
    }
});