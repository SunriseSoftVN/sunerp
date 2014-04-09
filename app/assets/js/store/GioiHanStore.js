/**
 * Created by dungvn3000 on 4/9/14.
 */

Ext.define('sunerp.store.GioiHanStore', {
    extend: 'sunerp.store.BaseStore',
    fields: ['value', 'name'],
    proxy: {
        type: 'ajax',
        url: '/quyenhanh/getGioiHans'
    }
});