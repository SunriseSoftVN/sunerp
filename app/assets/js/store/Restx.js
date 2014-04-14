Ext.define('sunerp.store.Restx', {
    extend: 'Ext.data.proxy.Rest',
    alias: 'proxy.restx',
    reader: {
        type: 'json',
        root: 'data'
    }
});