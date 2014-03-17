/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.QuyLuongStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.QuyLuong',
    proxy: {
        type: 'restx',
        url: '/quyluong'
    }
});