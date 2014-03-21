/**
 * Created by dungvn3000 on 3/21/14.
 */
Ext.define('sunerp.store.QuyenHanhStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.QuyenHanh',
    proxy: {
        type: 'restx',
        url: '/quyenhanh'
    }
});