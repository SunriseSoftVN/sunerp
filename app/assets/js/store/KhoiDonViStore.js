/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.KhoiDonViStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.KhoiDonVi',
    proxy: {
        type: 'restx',
        url: '/khoidonvi'
    }
});