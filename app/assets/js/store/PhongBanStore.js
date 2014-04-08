/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.PhongBanStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.PhongBan',
    proxy: {
        type: 'restx',
        url: '/phongban'
    }
});