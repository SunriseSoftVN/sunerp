Ext.define('sunerp.store.Authorities', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.Authority',
    proxy: {
        type: 'restx',
        url: '/authority'
    }
});