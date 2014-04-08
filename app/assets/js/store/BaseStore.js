/**
 * Created by dungvn3000 on 2/11/14.
 */

Ext.define('sunerp.store.BaseStore', {
    extend: 'Ext.data.Store',
    requires: [
        'sunerp.store.Restx'
    ],
    autoLoad: false,
    remoteSort: true,
    remoteFilter: true,
    pageSize: 50,
    constructor: function () {
        this.callParent(arguments);
        this.on('metachange', function (store, meta) {
            var model = store.getNewRecords()[0];
            model.set('id', meta.id);
            model.setDirty(false);
        });
    }
});