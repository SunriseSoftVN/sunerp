Ext.define('sunerp.store.UserStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.User',
    proxy: {
        type: 'restx',
        url: '/user'
    }
});