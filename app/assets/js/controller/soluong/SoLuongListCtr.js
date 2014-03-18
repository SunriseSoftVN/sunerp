/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.SoLuongListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['SoLuongStore'],
    config: {
        SoLuongStore: null
    },
    editView: 'sunerp.view.authority.SoLuongEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getSoLuongStore();
        this.callParent(arguments);
    }
});