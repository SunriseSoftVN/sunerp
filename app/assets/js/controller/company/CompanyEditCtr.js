/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.authority.CompanyEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['companyStore'],
    config: {
        companyStore: null
    },
    init: function() {
        this.mainStore = this.getCompanyStore();
        this.callParent(arguments);
    }
});