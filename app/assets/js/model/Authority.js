Ext.define('sunerp.model.Authority', {
    extend: 'Ext.data.Model',
    fields: [
        'id',
        'domain',
        'roleId',
        'role',
        {name: 'role.name', mapping: 'role.name'}
    ]
});