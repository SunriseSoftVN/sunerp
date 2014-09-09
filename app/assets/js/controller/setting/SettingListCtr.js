/**
 * Created by dungvn3000 on 4/13/14.
 */

Ext.define('sunerp.controller.setting.SettingListCtr', {
    extend: 'sunerp.controller.core.BaseListController',
    inject: ['companySettingStore'],
    config: {
        companySettingStore: null
    },
    editView: 'sunerp.view.setting.SettingEdit',
    init: function () {
        this.getAddBtn().setDisabled(true);
        this.mainStore = this.getCompanySettingStore();
        this.callParent(arguments);
    }
});