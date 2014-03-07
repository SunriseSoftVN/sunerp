Ext.define('sunerp.model.User', {
    extend: 'Ext.data.Model',
    fields: [
        'username',
        'password',
        'fullname',
        'roleId',
        'role',
        {name: 'role.name', mapping: 'role.name'}
    ],
    belongsTo: 'Role'
});