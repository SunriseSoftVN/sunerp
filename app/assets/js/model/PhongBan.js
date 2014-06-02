/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.model.PhongBan', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'donViId',
        'donVi',
        {name: 'donVi.name', mapping: 'donVi.name'},
        'name',
        'shortName',
        {name: 'showOnReport', type: 'boolean'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.DonVi', name: 'donVi'}
    ]
});
