Ext.define('sunerp.store.RoleStore', {
    extend: 'sunerp.store.BaseStore',
    model: 'sunerp.model.Role',
    proxy: {
        type: 'restx',
        url: '/role'
    }
});