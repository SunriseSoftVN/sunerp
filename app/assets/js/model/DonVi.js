/**
 * Created by dungvn3000 on 3/17/14.
 */
Ext.define('sunerp.model.DonVi', {
    extend: 'sunerp.model.BaseModel',
    fields: [
        'id',
        'name',
        'khoiDonViId',
        'khoiDonVi',
        {name: 'khoiDonVi.name', mapping: 'khoiDonVi.name'}
    ],
    associations: [
        {type: 'belongsTo', model: 'sunerp.model.KhoiDonVi', name: 'khoiDonVi'}
    ]
});