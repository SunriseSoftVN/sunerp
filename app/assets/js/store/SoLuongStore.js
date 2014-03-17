/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.SoLuongStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.SoLuong',
    proxy: {
        type: 'restx',
        url: '/soluong'
    }
});