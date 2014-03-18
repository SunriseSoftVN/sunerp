/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.CompanySettingListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['companySettingStore'],
    config: {
        companySettingStore: null
    },
    editView: 'sunerp.view.authority.CompanySettingEdit',
    searchField: 'name',
    init: function () {
        this.mainStore = this.getCompanySettingStore();
        this.callParent(arguments);
    }
});