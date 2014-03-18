/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.donvi.DonViListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['donViStore'],
    config: {
        donViStore: null
    },
    editView: 'sunerp.view.donvi.DonViEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getDonViStore();
        this.callParent(arguments);
    }
});