Ext.define('sunerp.model.MenuItem', {
    extend: 'Ext.data.Model',
    fields: [
        'expanded',
        'children',
        'text',
        'id',
        'controller',
        'view',
        'leaf'
    ]
});