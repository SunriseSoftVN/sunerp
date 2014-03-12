/**
 * Created by dungvn3000 on 3/12/14.
 */

Ext.define('sunerp.store.SoPhanCongStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.SoPhanCong',
    proxy: {
        type: 'restx',
        url: '/sophancong'
    }
});