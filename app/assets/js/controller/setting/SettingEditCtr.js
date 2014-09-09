/**
 * Created by dungvn3000 on 3/18/14.
 */

Ext.define('sunerp.controller.setting.SettingEditCtr', {
    extend: 'sunerp.controller.core.BaseEditController',
    inject: ['companySettingStore'],
    config: {
        companySettingStore: null
    },
    init: function() {
        this.mainStore = this.getCompanySettingStore();
        this.callParent(arguments);
    }
});