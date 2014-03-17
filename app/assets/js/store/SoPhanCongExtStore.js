/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.store.SoPhanCongExtStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.SoPhanCongExt',
    proxy: {
        type: 'restx',
        url: '/sophancongext'
    }
});