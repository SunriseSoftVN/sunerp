Ext.define('sunerp.store.Restx', {
    extend: 'Ext.data.proxy.Rest',
    alias: 'proxy.restx',
    listeners: {
        exception: function (proxy, response) {
            Ext.Msg.alert('Error ' + response.status, response.responseText);
        }
    },
    reader: {
        type: 'json',
        root: 'data'
    }
});