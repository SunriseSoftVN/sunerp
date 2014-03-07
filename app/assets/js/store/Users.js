Ext.define('sunerp.store.Users', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.User',
    proxy: {
        type: 'restx',
        url: '/user'
    }
});