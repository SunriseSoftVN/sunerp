/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.KhoiDonViListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['khoiDonViStore'],
    config: {
        khoiDonViStore: null
    },
    editView: 'sunerp.view.authority.KhoiDonViEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getKhoiDonViStore();
        this.callParent(arguments);
    }
});