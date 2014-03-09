Ext.define('sunerp.store.AuthorityStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.Authority',
    proxy: {
        type: 'restx',
        url: '/authority'
    }
});