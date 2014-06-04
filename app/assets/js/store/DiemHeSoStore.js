/**
 * Created by dungvn3000 on 5/9/14.
 */

Ext.define('sunerp.store.DiemHeSoStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.DiemHeSo',
    proxy: {
        type: 'restx',
        url: '/diemheso'
    }
});