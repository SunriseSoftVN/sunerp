/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.CompanySettingStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.CompanySetting',
    proxy: {
        type: 'restx',
        url: '/companysetting'
    }
});