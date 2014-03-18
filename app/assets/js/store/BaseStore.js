/**
 * Created by dungvn3000 on 2/11/14.
 */

Ext.define('sunerp.store.BaseStore', {
    extend: 'Ext.data.Store',
    requires: [
        'sunerp.store.Restx'
    ],
    autoLoad: true,
    remoteSort: true,
    remoteFilter: true,
    pageSize: 50,
    listeners: {
        metachange: function (store, meta) {
            store.last().set('id', meta.id);
            store.last().setDirty(false);
        }
    }
});