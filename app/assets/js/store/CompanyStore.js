/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.CompanyStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.Company',
    proxy: {
        type: 'restx',
        url: '/company'
    }
});