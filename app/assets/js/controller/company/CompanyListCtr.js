/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.CompanyListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['companyStore'],
    config: {
        companyStore: null
    },
    editView: 'sunerp.view.authority.CompanyEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getCompanyStore();
        this.callParent(arguments);
    }
});