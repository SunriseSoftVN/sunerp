/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.DonViStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.DonVi',
    proxy: {
        type: 'restx',
        url: '/donvi'
    }
});