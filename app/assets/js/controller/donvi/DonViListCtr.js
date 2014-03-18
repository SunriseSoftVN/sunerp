/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.DonViListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['donViStore'],
    config: {
        donViStore: null
    },
    editView: 'sunerp.view.authority.DonViEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getDonViStore();
        this.callParent(arguments);
    }
});