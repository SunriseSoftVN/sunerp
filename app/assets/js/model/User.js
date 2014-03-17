Ext.define('sunerp.model.User', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'username',
        'password',
        'roleId',
        'role',
        {name: 'role.name', mapping: 'role.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.Role', name: 'role'}
    ]
});