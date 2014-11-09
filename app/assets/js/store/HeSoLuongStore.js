/**
 * Created by dungvn3000 on 4/9/14.
 */

Ext.define('sunerp.store.HeSoLuongStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.HeSoLuong',
    proxy: {
        type: 'restx',
        url: '/hesoluong'
    }
});