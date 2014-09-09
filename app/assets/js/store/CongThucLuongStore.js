/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.CongThucLuongStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.CongThucLuong',
    proxy: {
        type: 'restx',
        url: '/congthucluong'
    }
});