Ext.define('sunerp.store.Roles', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.Role',
    proxy: {
        type: 'restx',
        url: '/role'
    }
});