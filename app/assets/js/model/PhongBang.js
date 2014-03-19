/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.model.PhongBang', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'donViId',
        'donVi',
        {name: 'donVi.name', mapping: 'donVi.name'},
        'name'
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.DonVi', name: 'donVi'}
    ]
});
