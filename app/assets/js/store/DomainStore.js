/**
 * Created by dungvn3000 on 4/9/14.
 */

Ext.define('sunerp.store.DomainStore', {
    extend: 'sunerp.store.BaseStore',
    fields: ['value', 'name'],
    proxy: {
        type: 'restx',
        url: '/domains'
    }
});