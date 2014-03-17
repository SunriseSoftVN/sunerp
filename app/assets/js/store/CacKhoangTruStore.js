/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.CacKhoangTruStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.CacKhoangTru',
    proxy: {
        type: 'restx',
        url: '/cackhoangtru'
    }
});