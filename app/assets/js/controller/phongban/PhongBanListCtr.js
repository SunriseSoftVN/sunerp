/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.phongban.PhongBanListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['phongBanStore'],
    config: {
        phongBanStore: null
    },
    editView: 'sunerp.view.phongban.PhongBanEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getPhongBanStore();
        this.callParent(arguments);
    }
});