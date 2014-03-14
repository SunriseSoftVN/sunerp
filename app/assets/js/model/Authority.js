Ext.define('sunerp.model.Authority', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'domain',
        'roleId',
        'role',
        {name: 'role.name', mapping: 'role.name'}
    ]
});