Ext.define('sunerp.model.User', {
    extend: 'Ext.data.Model',
    fields: [
        'username',
        'password',
        'roleId',
        'role',
        {name: 'role.name', mapping: 'role.name'}
    ],
    belongsTo: 'Role'
});