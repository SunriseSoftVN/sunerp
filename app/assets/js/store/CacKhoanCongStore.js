/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.CacKhoanCongStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.CacKhoanCong',
    proxy: {
        type: 'restx',
        url: '/cackhoancong'
    }
});