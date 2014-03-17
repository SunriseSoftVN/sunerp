/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.PhongBangStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.PhongBang',
    proxy: {
        type: 'restx',
        url: '/phongbang'
    }
});