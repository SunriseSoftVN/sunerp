/**
 * Created by Hiep on 14/03/14.
 */

Ext.define('sunerp.store.ChucVuStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.ChucVu',
    proxy: {
        type: 'restx',
        url: '/chucvu'
    }
});