/**
 * Created by dungvn3000 on 5/9/14.
 */

Ext.define('sunerp.store.XepLoaiStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.XepLoai',
    proxy: {
        type: 'restx',
        url: '/xeploai'
    }
});